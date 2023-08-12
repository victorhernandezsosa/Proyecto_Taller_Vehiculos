package com.tallervehiculos.uth.views.ordendereparación;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.orm.ObjectOptimisticLockingFailureException;

import com.tallervehiculos.uth.data.controller.OrdenReparacion_Interactor;
import com.tallervehiculos.uth.data.controller.OrdenReparacion_InteractorImp;
import com.tallervehiculos.uth.data.entity.OrdenReparacionReport;
import com.tallervehiculos.uth.data.entity.Orden_reparacion;
import com.tallervehiculos.uth.data.entity.Vehiculo;
import com.tallervehiculos.uth.data.service.ReportGenerator;
import com.tallervehiculos.uth.views.MainLayout;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.GridVariant;
import com.vaadin.flow.component.grid.contextmenu.GridContextMenu;
import com.vaadin.flow.component.html.Anchor;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.Notification.Position;
import com.vaadin.flow.component.notification.NotificationVariant;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.splitlayout.SplitLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.StreamResource;

@PageTitle("Orden de Reparación")
@Route(value = "orden-reparacion/:orden_reparacionID?/:action?(edit)", layout = MainLayout.class)
public class OrdendeReparaciónView extends Div implements BeforeEnterObserver, OrdendeReparacionViewModel {

    private final String ORDEN_REPARACION_ID = "orden_reparacionID";
    private final String ORDEN_REPARACION_EDIT_ROUTE_TEMPLATE = "orden-reparacion/%s/edit";
    private final Grid<Orden_reparacion> grid = new Grid<>(Orden_reparacion.class, false);

    private TextField id_orden;
    private ComboBox<Vehiculo> vehiculo_id;
    private TextField descripcion_problema;
    private TextField estado_reparacion;
    private List<Orden_reparacion> orden;
    private List<Vehiculo> vehiculo;

    private final Button cancel = new Button("Cancelar");
    private final Button save = new Button("Guardar");

    private Orden_reparacion ordenes;
    private OrdenReparacion_Interactor controlador;

    public OrdendeReparaciónView() {

        addClassNames("ordende-reparación-view");
        orden = new ArrayList<>();
        this.controlador = new OrdenReparacion_InteractorImp(this);

        // Create UI
        SplitLayout splitLayout = new SplitLayout();

        createGridLayout(splitLayout);
        createEditorLayout(splitLayout);

        add(splitLayout);

        // Configure Grid
        grid.addColumn(Orden_reparacion::getId_orden).setHeader("ID de Orden").setAutoWidth(true);
        grid.addColumn(Orden_reparacion::getVehiculo_id).setHeader("ID de Vehículo").setAutoWidth(true);
        grid.addColumn(Orden_reparacion::getDescripcion_problema).setHeader("Descripción de Problema").setAutoWidth(true);
        grid.addColumn(Orden_reparacion::getEstado_reparacion).setHeader("Estado Actual de Atención").setAutoWidth(true);
       
        grid.addThemeVariants(GridVariant.LUMO_NO_BORDER);
        
        GridContextMenu<Orden_reparacion> menu = grid.addContextMenu();
    	menu.addItem("Generar Reporte", event -> {
    		if(this.orden.isEmpty()) {
        		Notification.show("No hay datos para generar el reporte");
        	}else {
        		Notification.show("Generando reporte PDF...");
            	generarReporteReparacion();
        	}
    	});

        // when a row is selected or deselected, populate form
        grid.asSingleSelect().addValueChangeListener(event -> {
            if (event.getValue() != null) {
                UI.getCurrent().navigate(String.format(ORDEN_REPARACION_EDIT_ROUTE_TEMPLATE, event.getValue().getId_orden()));
            } else {
                clearForm();
                UI.getCurrent().navigate(OrdendeReparaciónView.class);
            }
        });

      //Mndo a traer las ordenes del repositorio
        this.controlador.consultarOrden();
        this.controlador.consultarVehiculo();


        // Configure Form
        cancel.addClickListener(e -> {
            clearForm();
            refreshGrid();
        });

        save.addClickListener(e -> {
            try {
                if (this.ordenes == null) {
                    this.ordenes = new Orden_reparacion();
                }
                //.update(this.orden_reparacion);
                clearForm();
                refreshGrid();
                Notification.show("Data updated");
                UI.getCurrent().navigate(OrdendeReparaciónView.class);
            } catch (ObjectOptimisticLockingFailureException exception) {
                Notification n = Notification.show(
                        "Error updating the data. Somebody else has updated the record while you were making changes.");
                n.setPosition(Position.MIDDLE);
                n.addThemeVariants(NotificationVariant.LUMO_ERROR);
            }
        });
    }

    private void generarReporteReparacion() {
    	ReportGenerator generador = new ReportGenerator();
        Map<String, Object> parametros = new HashMap<>();
        parametros.put("LOGO_DIR","logo.png");
        parametros.put("LOGO_BAR","barcode.png");
        OrdenReparacionReport datasource = new OrdenReparacionReport();
        datasource.setData(orden);
        String rutaPDF = generador.generarReportePDF("reporte_Orden", parametros, datasource);

        if (rutaPDF != null) {
            StreamResource resource = new StreamResource("Reporte de Orden Reparacion.pdf", () -> {
                try {
                    return new FileInputStream(rutaPDF);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                    return null;
                }
            });

            Anchor url = new Anchor(resource, "Abrir reporte PDF");
            url.setTarget("_blank");

            add(url);

            Notification notificacion = new Notification(url);
            notificacion.setDuration(20000);
            notificacion.open();
        } else {
            Notification notificacion = new Notification("Ocurrió un problema al generar el reporte");
            notificacion.addThemeVariants(NotificationVariant.LUMO_ERROR);
            notificacion.setDuration(10000);
            notificacion.open();
        }
		
	}

	@Override
    public void beforeEnter(BeforeEnterEvent event) {
        Optional<String> orden_reparacionId = event.getRouteParameters().get(ORDEN_REPARACION_ID);
        boolean encontrado = false;
        if (orden_reparacionId.isPresent()) {
        	for(Orden_reparacion e: this.orden) {
        		if(e.getId_orden().equals(orden_reparacionId.get())) {
        			populateForm(e);
        			encontrado = true;
        			break;
        		}
        	}
        	if(!encontrado) {
        		Notification.show(String.format("La orden de reparacion con ID = %s", orden_reparacionId.get()+" no fue encontrada"),
                        3000, Notification.Position.BOTTOM_START);
                // when a row is selected but the data is no longer available,
                // refresh grid
                refreshGrid();
                event.forwardTo(OrdendeReparaciónView.class);
        	}
        }
    }

    private void createEditorLayout(SplitLayout splitLayout) {
        Div editorLayoutDiv = new Div();
        editorLayoutDiv.setClassName("editor-layout");

        Div editorDiv = new Div();
        editorDiv.setClassName("editor");
        editorLayoutDiv.add(editorDiv);

        FormLayout formLayout = new FormLayout();
        id_orden = new TextField("ID");
        vehiculo_id = new ComboBox<>("Vehiculo ID");
        descripcion_problema = new TextField("Problema");
        estado_reparacion = new TextField("Estado");
        id_orden = new TextField("ID de Orden");
        descripcion_problema = new TextField("Descripción de Problema");
        estado_reparacion = new TextField("Estado Actual de Atención");
        formLayout.add(id_orden, vehiculo_id, descripcion_problema, estado_reparacion);
       
        id_orden.setPrefixComponent(VaadinIcon.EDIT.create());
        vehiculo_id.setPrefixComponent(VaadinIcon.CAR.create());
        descripcion_problema.setPrefixComponent(VaadinIcon.WARNING.create());
        estado_reparacion.setPrefixComponent(VaadinIcon.TOOLBOX.create());
        
        editorDiv.add(formLayout);
        createButtonLayout(editorLayoutDiv);

        splitLayout.addToSecondary(editorLayoutDiv);
    }

    private void createButtonLayout(Div editorLayoutDiv) {
        HorizontalLayout buttonLayout = new HorizontalLayout();
        buttonLayout.setClassName("button-layout");
        cancel.addThemeVariants(ButtonVariant.LUMO_TERTIARY);
        save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        buttonLayout.add(save, cancel);
        editorLayoutDiv.add(buttonLayout);
    }

    private void createGridLayout(SplitLayout splitLayout) {
        Div wrapper = new Div();
        wrapper.setClassName("grid-wrapper");
        splitLayout.addToPrimary(wrapper);
        wrapper.add(grid);
    }

    private void refreshGrid() {
        grid.select(null);
        grid.getDataProvider().refreshAll();
    }

    private void clearForm() {
        populateForm(null);
    }

    private void populateForm(Orden_reparacion value) {
    	this.ordenes = value;

        if(value == null){
        	this.id_orden.setValue("");
            this.vehiculo_id.setValue(null);
            this.descripcion_problema.setValue("");
            this.estado_reparacion.setValue("");
        } else {
        	this.id_orden.setValue(value.getId_orden());
            this.vehiculo_id.setValue(buscarVehiculoSeleccionado(value.getVehiculo_id()));
            this.descripcion_problema.setValue(value.getDescripcion_problema());
            this.estado_reparacion.setValue(value.getEstado_reparacion());

        }
        
        
    }

    private Vehiculo buscarVehiculoSeleccionado(Integer id_vehiculo) {
    	Vehiculo seleccionado = new Vehiculo();
    	for(Vehiculo v : vehiculo) {
    		if(v.getId_vehiculo() == id_vehiculo) {
    			seleccionado = v;
    			break;
    		}
    	}
    	return seleccionado;
    }
    
    @Override
	public void refrescarGridOrden(List<Orden_reparacion> items_orden) {
    	if(items_orden != null && !items_orden.isEmpty()) {
    		Collection<Orden_reparacion> items = items_orden;
    		grid.setItems(items);
    		this.orden = items_orden;
        	}

	}

    @Override
    public void refrescarComboBoxVehiculos(List<Vehiculo> items) {
    	vehiculo_id.setItems(items);
    	vehiculo_id.setItemLabelGenerator(Vehiculo::getPlaca);
    	this.vehiculo = items;
    }

    public Grid<Orden_reparacion> getGrid(){
    	return grid;
    }

}
