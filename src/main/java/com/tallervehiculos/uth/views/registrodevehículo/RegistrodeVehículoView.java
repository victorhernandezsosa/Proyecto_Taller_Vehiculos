package com.tallervehiculos.uth.views.registrodevehículo;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.springframework.orm.ObjectOptimisticLockingFailureException;

import com.nimbusds.jose.util.events.Event;
import com.tallervehiculos.uth.data.controller.OrdenVehiculos_Interactor;
import com.tallervehiculos.uth.data.controller.OrdenVehiculos_InteractorImp;
import com.tallervehiculos.uth.data.entity.RegistroVehiculoReport;
import com.tallervehiculos.uth.data.entity.Vehiculo;
import com.tallervehiculos.uth.data.service.ReportGenerator;
import com.tallervehiculos.uth.views.MainLayout;
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
import com.vaadin.flow.component.splitlayout.SplitLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteAlias;
import com.vaadin.flow.server.StreamResource;

@PageTitle("Registro de Vehículo")
@Route(value = "registro-vehiculo/:vehiculoID?/:action?(edit)", layout = MainLayout.class)
@RouteAlias(value = "", layout = MainLayout.class)
public class RegistrodeVehículoView extends Div implements BeforeEnterObserver, registrodevehiculoViewModel {

    private final String VEHICULO_ID = "vehiculoID";
    private final String VEHICULO_EDIT_ROUTE_TEMPLATE = "registro-vehiculo/%s/edit";

    private final Grid<Vehiculo> grid = new Grid<>(Vehiculo.class, false);

    //private TextField id_vehiculo;
    private TextField nombre_cliente;
    private TextField marca;
    private TextField modelo;
    private TextField placa;
    private List<Vehiculo> vehiculos;
    private Integer control_id;

    private final Button cancel = new Button("Cancelar");
    private final Button save = new Button("Guardar");
    private final Button delete = new Button("Eliminar");

    private Vehiculo vehiculo;
    private OrdenVehiculos_Interactor controlador;

    public RegistrodeVehículoView() {

        addClassNames("registrode-vehículo-view");
        vehiculos = new ArrayList<>();
        this.controlador = new OrdenVehiculos_InteractorImp(this);
        //Mndo a traer los vhiculos del repositorio
        this.controlador.consultarVehiculo();

        // Create UI
        SplitLayout splitLayout = new SplitLayout();
        splitLayout.setSplitterPosition(75);

        createGridLayout(splitLayout);
        createEditorLayout(splitLayout);

        add(splitLayout);

        // Configure Grid
        grid.addColumn(Vehiculo::getId_vehiculo).setHeader("ID de vehículo").setAutoWidth(true);
        grid.addColumn(Vehiculo::getNombre_cliente).setHeader("Nombre del Cliente").setAutoWidth(true);
        grid.addColumn(Vehiculo::getMarca).setHeader("Marca del Vehículo").setAutoWidth(true);
        grid.addColumn(Vehiculo::getModelo).setHeader("Modelo del Vehículo").setAutoWidth(true);
        grid.addColumn(Vehiculo::getPlaca).setHeader("Placa/Matricula").setAutoWidth(true);
        /*grid.setItems(query -> vehiculoService.list(
                PageRequest.of(query.getPage(), query.getPageSize(), VaadinSpringDataHelpers.toSpringDataSort(query)))
                .stream());*/
        grid.addThemeVariants(GridVariant.LUMO_NO_BORDER);
        
        
        
        GridContextMenu<Vehiculo> menu = grid.addContextMenu();
    	menu.addItem("Generar Reporte", event -> {
    		if(this.vehiculos.isEmpty()) {
        		Notification.show("No hay datos para generar el reporte");
        	}else {
        		Notification.show("Generando reporte PDF...");
            	generarReporteRegistro();
        	}
    	});

        // when a row is selected or deselected, populate form
        grid.asSingleSelect().addValueChangeListener(event -> {
            if (event.getValue() != null) {
                UI.getCurrent().navigate(String.format(VEHICULO_EDIT_ROUTE_TEMPLATE, event.getValue().getId_vehiculo()));
                control_id = event.getValue().getId_vehiculo();
            } else {
                clearForm();
                UI.getCurrent().navigate(RegistrodeVehículoView.class);
            }
        });

        // Configure Form
        cancel.addClickListener(e -> {
            clearForm();
            refreshGrid();
        });

        save.addClickListener(e -> {
            try {
                if (this.vehiculo == null) {
                    this.vehiculo = new Vehiculo();
                    //this.vehiculo.setId_vehiculo(Integer.parseInt(this.id_vehiculo.getValue()));
                    //this.vehiculo.setId_vehiculo(id);
                    //System.out.println("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA " + vehiculo.getId_vehiculo());
                    this.vehiculo.setNombre_cliente(this.nombre_cliente.getValue());
                    this.vehiculo.setMarca(this.marca.getValue());
                    this.vehiculo.setModelo(this.modelo.getValue());
                    this.vehiculo.setPlaca(this.placa.getValue());
                    this.controlador.crearNuevoRegistro_Vehiculo(vehiculo);
                } else {
                	this.vehiculo.setNombre_cliente(this.nombre_cliente.getValue());
                    this.vehiculo.setMarca(this.marca.getValue());
                    this.vehiculo.setModelo(this.modelo.getValue());
                    this.vehiculo.setPlaca(this.placa.getValue());
                    this.controlador.actualizarNuevoRegistro_Vehiculo(vehiculo);
                }
                //vehiculoService.update(this.vehiculo);
                clearForm();
                refreshGrid();
                //Notification.show("Data updated");
                UI.getCurrent().navigate(RegistrodeVehículoView.class);
            } catch (ObjectOptimisticLockingFailureException exception) {
                Notification n = Notification.show(
                        "Error updating the data. Somebody else has updated the record while you were making changes.");
                n.setPosition(Position.MIDDLE);
                n.addThemeVariants(NotificationVariant.LUMO_ERROR);
            }
        });
        
        delete.addClickListener(e -> {
        	this.controlador.eliminarRegistro_Vehiculo(control_id);
            clearForm();
            refreshGrid();
        });
        
    }

    private void generarReporteRegistro() {
    	ReportGenerator generador = new ReportGenerator();
        Map<String, Object> parametros = new HashMap<>();
        parametros.put("LOGO_DIR","logo.png");
        parametros.put("LOGO_BAR","barcode.png");
        RegistroVehiculoReport datasource = new RegistroVehiculoReport();
        datasource.setData(vehiculos);
        String rutaPDF = generador.generarReportePDF("reporte_registro", parametros, datasource);

        if (rutaPDF != null) {
            StreamResource resource = new StreamResource("Reporte de Registro Vehiculos.pdf", () -> {
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
        Optional<String> vehiculoId = event.getRouteParameters().get(VEHICULO_ID);
        boolean encontrado = false;
        if (vehiculoId.isPresent()) {

        	for(Vehiculo e: this.vehiculos) {
        		if(String.valueOf(e.getId_vehiculo()).equals(vehiculoId.get())) {
        			populateForm(e);
        			encontrado = true;
        			break;
        		}
        	}

        	if(!encontrado) {
        		Notification.show(String.format("El Vehiculo con ID = %s", vehiculoId.get()+" no fue encontrado"),
                        3000, Notification.Position.BOTTOM_START);
                // when a row is selected but the data is no longer available,
                // refresh grid
                refreshGrid();
                event.forwardTo(RegistrodeVehículoView.class);
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
        //id_vehiculo = new TextField("ID de vehículo");
        //id_vehiculo.setReadOnly(true);
        //id_vehiculo.setValue(String.valueOf(id));
        nombre_cliente = new TextField("Cliente");
        marca = new TextField("Marca");
        modelo = new TextField("Modelo");
        placa = new TextField("Placa");
        formLayout.add(nombre_cliente, marca, modelo, placa);
        
        //id_vehiculo.setPrefixComponent(VaadinIcon.EDIT.create());
        nombre_cliente.setPrefixComponent(VaadinIcon.USER.create());
        marca.setPrefixComponent(VaadinIcon.INFO_CIRCLE_O.create());
        modelo.setPrefixComponent(VaadinIcon.INFO_CIRCLE_O.create());
        placa.setPrefixComponent(VaadinIcon.PASSWORD.create());
        editorDiv.add(formLayout);
        
 
        
        createButtonLayout(editorLayoutDiv);

        splitLayout.addToSecondary(editorLayoutDiv);
    }

    private void createButtonLayout(Div editorLayoutDiv) {
        HorizontalLayout buttonLayout = new HorizontalLayout();
        buttonLayout.setClassName("button-layout");
        cancel.addThemeVariants(ButtonVariant.LUMO_TERTIARY);
        save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        delete.addThemeVariants(ButtonVariant.LUMO_PRIMARY,
                ButtonVariant.LUMO_ERROR);
        buttonLayout.add(save,  delete, cancel);
        editorLayoutDiv.add(buttonLayout);
    }

    private void createGridLayout(SplitLayout splitLayout) {
        Div wrapper = new Div();
        wrapper.setClassName("grid-wrapper");
        splitLayout.addToPrimary(wrapper);
        wrapper.add(grid);
    }

    private void refreshGrid() {
    	this.controlador.consultarVehiculo();
        grid.select(null);
        grid.getDataProvider().refreshAll();
    }

    private void clearForm() {
        populateForm(null);
    }

    private void populateForm(Vehiculo value) {
    	this.vehiculo = value;
        if(value == null){
        	//this.id_vehiculo.setValue(String.valueOf(id));
        	//this.id_vehiculo.setValue("");
            this.nombre_cliente.setValue("");
            this.marca.setValue("");
            this.modelo.setValue("");
            this.placa.setValue("");
        } else {
        	//this.id_vehiculo.setValue(String.valueOf(value.getId_vehiculo()));
            this.nombre_cliente.setValue(value.getNombre_cliente());
            this.marca.setValue(value.getMarca());
            this.modelo.setValue(value.getModelo());
            this.placa.setValue(value.getPlaca());
        }
    }

	@Override
	public void refrescarGridVehiculos(List<Vehiculo> vehiculos) {
		Collection<Vehiculo> items = vehiculos;
		grid.setItems(items);
		this.vehiculos = vehiculos;
	}

	public Grid<Vehiculo> getGrid(){

    	return grid;
    }

	@Override
	public void mostrarMensajeCreacion(boolean respuesta) {
		String mensajeMostrar = "Registro Exitoso!";
		if(!respuesta) {
			mensajeMostrar = "Error al Registrar";
		}
		 Notification.show(mensajeMostrar);
	}

	@Override
	public void mostrarMensajeEliminacion(boolean respuesta) {
		String mensajeMostrar = "Registro eliminado exitosamente!";
		if(!respuesta) {
			mensajeMostrar = "Registro no pudo ser eliminado";
		}
		 Notification.show(mensajeMostrar);
		 this.controlador.consultarVehiculo();
	}

	@Override
	public void mostrarMensajeAtualizacion(boolean respuesta) {
		String mensajeMostrar = "Registro Actualizado Exitosamente!";
		if(!respuesta) {
			mensajeMostrar = "Error al Actualizar Registro";
		}
		 Notification.show(mensajeMostrar);
	}
}
