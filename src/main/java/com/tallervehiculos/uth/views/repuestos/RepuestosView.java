package com.tallervehiculos.uth.views.repuestos;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.orm.ObjectOptimisticLockingFailureException;

import com.tallervehiculos.uth.data.controller.Repuesto_Interactor;
import com.tallervehiculos.uth.data.controller.Repuesto_InteractorImp;
import com.tallervehiculos.uth.data.entity.RepuestosReport;
import com.tallervehiculos.uth.data.entity.repuestos;
import com.tallervehiculos.uth.data.service.ReportGenerator;
import com.tallervehiculos.uth.views.MainLayout;
import com.tallervehiculos.uth.views.ordendereparación.OrdendeReparaciónView;
import com.vaadin.flow.component.HasComponents;
import com.vaadin.flow.component.HasStyle;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
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
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.splitlayout.SplitLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.StreamResource;
import com.vaadin.flow.theme.lumo.LumoUtility.Margin;

@PageTitle("Repuestos")
@Route(value = "repuestos", layout = MainLayout.class)
public class RepuestosView extends Div implements HasComponents, HasStyle,RepuestosViewModel,BeforeEnterObserver {

	private final Grid<repuestos> grid = new Grid<>(repuestos.class, false);

	 private final String REPUESTO_ID = "repuestoID";
	 private final String REPUESTO_EDIT_ROUTE_TEMPLATE = "editar-repuesto/%s/edit";

	private TextField idRepuesto;
    private TextField nombreRepuesto;
    private TextField precioRepuesto;

    private final Button cancel = new Button("Cancelar");
    private final Button save = new Button("Guardar");

	private List<repuestos> repuesto;
	private repuestos resp;
	private Repuesto_Interactor controlador;

    public RepuestosView() {
    	setSizeFull();
    	 repuesto = new ArrayList<>();
    	this.controlador = new Repuesto_InteractorImp(this);

    	SplitLayout splitLayout = new SplitLayout();
        createGridLayout(splitLayout);
        createEditorLayout(splitLayout);
        add(splitLayout);

        grid.addColumn(repuestos::getId_repuesto).setHeader("ID de Repuesto");
    	grid.addColumn(repuestos::getNombre_repuesto).setHeader("Repuesto");
    	grid.addColumn(repuestos::getPrecio).setHeader("Precio");
    	grid.addThemeVariants(GridVariant.LUMO_NO_BORDER);
    	grid.addClassName(Margin.Top.XLARGE);
    	
    	GridContextMenu<repuestos> menu = grid.addContextMenu();
    	menu.addItem("Generar Reporte", event -> {
    		if(this.repuesto.isEmpty()) {
        		Notification.show("No hay datos para generar el reporte");
        	}else {
        		Notification.show("Generando reporte PDF...");
            	generarReporteRepuestos();
        	}
    	});

    	grid.asSingleSelect().addValueChangeListener(event -> {
            if (event.getValue() != null) {
                populateForm(event.getValue());
            } else {
                clearForm();
            }
        });

        this.controlador.consultarRepuesto();

        cancel.addClickListener(e -> {
            clearForm();
            refreshGrid();
        });

        save.addClickListener(e -> {
            try {
                if (this.resp == null) {
                    this.resp = new repuestos();
                }
                //.update(this.orden_reparacion);
                clearForm();
                refreshGrid();
                Notification.show("Data updated");
                UI.getCurrent().navigate(RepuestosView.class);
            } catch (ObjectOptimisticLockingFailureException exception) {
                Notification n = Notification.show(
                        "Error updating the data. Somebody else has updated the record while you were making changes.");
                n.setPosition(Position.MIDDLE);
                n.addThemeVariants(NotificationVariant.LUMO_ERROR);
            }
        });


    }

    private void generarReporteRepuestos() {
    	ReportGenerator generador = new ReportGenerator();
        Map<String, Object> parametros = new HashMap<>();
        parametros.put("LOGO_DIR","logo.png");
        parametros.put("LOGO_BAR","barcode.png");
        RepuestosReport datasource = new RepuestosReport();
        datasource.setData(repuesto);
        String rutaPDF = generador.generarReportePDF("reporte_repuestos", parametros, datasource);

        if (rutaPDF != null) {
            StreamResource resource = new StreamResource("Reporte de Repuestos.pdf", () -> {
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
        Optional<String> repuestoID = event.getRouteParameters().get(REPUESTO_ID);
        boolean encontrado = false;
        if (repuestoID.isPresent()) {
        	for(repuestos e: this.repuesto) {
        		if(e.getId_repuesto().equals(repuestoID.get())) {
        			populateForm(e);
        			encontrado = true;
        			break;
        		}
        	}
        	if(!encontrado) {
        		Notification.show(String.format("El repuesto con ID = %s", repuestoID.get()+" no fue encontrada"),
                        3000, Notification.Position.BOTTOM_START);
                // when a row is selected but the data is no longer available,
                // refresh grid
                refreshGrid();
                event.forwardTo(OrdendeReparaciónView.class);
        	}
        }
    }

    private void clearForm() {
        populateForm(null);
    }


    private void populateForm(repuestos value) {
    	this.resp = value;
        if(value == null){
        	this.idRepuesto.setValue("");
            this.nombreRepuesto.setValue("");
            this.precioRepuesto.setValue("");
        } else {
        	this.idRepuesto.setValue(value.getId_repuesto());
            this.nombreRepuesto.setValue(value.getNombre_repuesto());
            this.precioRepuesto.setValue(value.getPrecio());
        }
    }

    private void createEditorLayout(SplitLayout splitLayout) {
        Div editorLayoutDiv = new Div();
        editorLayoutDiv.setClassName("editor-layout");
        editorLayoutDiv.setSizeFull();

        VerticalLayout editorContentLayout = new VerticalLayout();
        editorContentLayout.setSizeFull();
        editorLayoutDiv.add(editorContentLayout);

        FormLayout formLayout = new FormLayout();
        idRepuesto = new TextField("ID de Repuesto");
        nombreRepuesto = new TextField("Repuesto");
        precioRepuesto = new TextField("Precio");
        formLayout.add(idRepuesto, nombreRepuesto, precioRepuesto);
        
        idRepuesto.setPrefixComponent(VaadinIcon.EDIT.create());
        nombreRepuesto.setPrefixComponent(VaadinIcon.STOCK.create());
        precioRepuesto.setPrefixComponent(VaadinIcon.MONEY.create());
  
        
        
        editorContentLayout.add(formLayout);

        createButtonLayout(editorContentLayout);

        splitLayout.addToSecondary(editorLayoutDiv);
        splitLayout.setSplitterPosition(80);

        splitLayout.setSizeFull();

    }

    private void createButtonLayout(VerticalLayout editorContentLayout) {
        HorizontalLayout buttonLayout = new HorizontalLayout();
        buttonLayout.setClassName("button-layout");
        cancel.addThemeVariants(ButtonVariant.LUMO_TERTIARY);
        save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        buttonLayout.add(save, cancel);

        Div spacer = new Div();
        spacer.setSizeFull();
        editorContentLayout.add(spacer);
        editorContentLayout.add(buttonLayout);

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




	@Override
	public void refrescarGridRepuestos(List<repuestos> repuestos) {
		Collection<repuestos> items = repuestos;
   		grid.setItems(items);
   		this.repuesto = repuestos;
	}

    public Grid<repuestos> getGrid(){

    	return grid;
    }



}
