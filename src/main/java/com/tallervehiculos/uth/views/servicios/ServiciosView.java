package com.tallervehiculos.uth.views.servicios;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.springframework.orm.ObjectOptimisticLockingFailureException;

import com.tallervehiculos.uth.data.controller.Servicios_interactor;
import com.tallervehiculos.uth.data.controller.Servicios_interactorImp;

import com.tallervehiculos.uth.data.entity.Servicios;

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
import com.vaadin.flow.component.html.Div;

import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.NotificationVariant;
import com.vaadin.flow.component.notification.Notification.Position;
import com.vaadin.flow.component.orderedlayout.FlexComponent.Alignment;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.splitlayout.SplitLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;



@PageTitle("Servicios")
@Route(value = "servicios", layout = MainLayout.class)
public class ServiciosView extends Div implements HasComponents, HasStyle, ServiciosViewModel,BeforeEnterObserver {

	private final Grid<Servicios> grid = new Grid<>(Servicios.class, false);
	private TextField id_servicio;
    private TextField Nombre_servicio;
    private TextField Subservicio;
    private TextField Costo;
    
    private final Button cancel = new Button("Cancelar");
    private final Button save = new Button("Guardar");
    
    private final String SERVICIO_ID = "ServicioID";
    private final String SERVICIO_EDIT_ROUTE_TEMPLATE = "editar-servicio/%s/edit";

	
	private List<Servicios> servicio;
	private Servicios servi;
	private Servicios_interactor controlador;
	//private Servicios servicios;
	
    public ServiciosView() {
    	setSizeFull();
        addClassNames("servicios-view");
    	servicio = new ArrayList<>();
    	this.controlador = new Servicios_interactorImp(this);
       // constructUI();
        //constructUI2();
        
    	SplitLayout splitLayout = new SplitLayout();
        createGridLayout(splitLayout);
        createEditorLayout(splitLayout);
        add(splitLayout);
        
        
        grid.addColumn(Servicios::getId_servicio).setHeader("ID");
    	grid.addColumn(Servicios::getNombre_servicio).setHeader("Servicio");
    	grid.addColumn(Servicios::getSubservicio).setHeader("Subservicio");
    	grid.addColumn(Servicios::getCosto).setHeader("Costo");
    	grid.addThemeVariants(GridVariant.LUMO_NO_BORDER);
    	grid.asSingleSelect().addValueChangeListener(event -> {
            if (event.getValue() != null) {
                populateForm(event.getValue());
            } else {
                clearForm(); 
            }
        });
    	
        this.controlador.consultarServicios();
        
        cancel.addClickListener(e -> {
            clearForm();
            refreshGrid();
        });

        save.addClickListener(e -> {
            try {
                if (this.servi == null) {
                    this.servi = new Servicios();
                }
                //.update(this.orden_reparacion);
                clearForm();
                refreshGrid();
                Notification.show("Data updated");
                UI.getCurrent().navigate(ServiciosView.class);
            } catch (ObjectOptimisticLockingFailureException exception) {
                Notification n = Notification.show(
                        "Error updating the data. Somebody else has updated the record while you were making changes.");
                n.setPosition(Position.MIDDLE);
                n.addThemeVariants(NotificationVariant.LUMO_ERROR);
            }
        });
        
        
    }
    
    @Override
    public void beforeEnter(BeforeEnterEvent event) {
        Optional<String> servicioId = event.getRouteParameters().get(SERVICIO_ID);
        boolean encontrado = false;
        if (servicioId.isPresent()) {
        	for(Servicios e: this.servicio) {
        		if(e.getId_servicio().equals(servicioId.get())) {
        			populateForm(e);
        			encontrado = true;
        			break;
        		}
        	}
        	if(!encontrado) {
        		Notification.show(String.format("El servicio con ID = %s", servicioId.get()+" no fue encontrada"),
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
    
    
    private void populateForm(Servicios value) {
    	this.servi = value;
        if(value == null){
        	this.id_servicio.setValue("");
            this.Nombre_servicio.setValue("");
            this.Subservicio.setValue("");
            this.Costo.setValue("");
        } else {
        	this.id_servicio.setValue(value.getId_servicio());
            this.Nombre_servicio.setValue(value.getNombre_servicio());
            this.Subservicio.setValue(value.getSubservicio());
            this.Costo.setValue(value.getCosto());
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
        id_servicio = new TextField("ID");
        Nombre_servicio = new TextField("Servicio");
        Subservicio = new TextField("Subservicio");
        Costo = new TextField("Costo");
        formLayout.add(id_servicio, Nombre_servicio, Subservicio, Costo);
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
        
        Div spacer = new Div(); // Div para agregar espacio entre el formulario y los botones
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
	public void refrescarGridServicios(List<Servicios> servicios) {
		Collection<Servicios> items = servicios;
		grid.setItems(items);
		this.servicio = servicios;
		System.out.println("DetallesdeOrdenView : " + servicio);
	}
    
    public Grid<Servicios> getGrid(){
    	return grid;
    }
    
}
