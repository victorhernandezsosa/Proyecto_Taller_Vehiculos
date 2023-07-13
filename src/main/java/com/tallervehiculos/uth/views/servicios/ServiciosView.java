package com.tallervehiculos.uth.views.servicios;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.tallervehiculos.uth.data.controller.Servicios_interactor;
import com.tallervehiculos.uth.data.controller.Servicios_interactorImp;
import com.tallervehiculos.uth.data.entity.Servicios;
import com.tallervehiculos.uth.data.entity.Vehiculo;
import com.tallervehiculos.uth.data.entity.repuestos;
import com.tallervehiculos.uth.views.MainLayout;
import com.vaadin.flow.component.HasComponents;
import com.vaadin.flow.component.HasStyle;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.GridVariant;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Main;
import com.vaadin.flow.component.html.OrderedList;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.select.Select;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.theme.lumo.LumoUtility.AlignItems;
import com.vaadin.flow.theme.lumo.LumoUtility.Display;
import com.vaadin.flow.theme.lumo.LumoUtility.FontSize;
import com.vaadin.flow.theme.lumo.LumoUtility.Gap;
import com.vaadin.flow.theme.lumo.LumoUtility.JustifyContent;
import com.vaadin.flow.theme.lumo.LumoUtility.ListStyleType;
import com.vaadin.flow.theme.lumo.LumoUtility.Margin;
import com.vaadin.flow.theme.lumo.LumoUtility.MaxWidth;
import com.vaadin.flow.theme.lumo.LumoUtility.Padding;
import com.vaadin.flow.theme.lumo.LumoUtility.TextColor;

@PageTitle("Servicios")
@Route(value = "servicios", layout = MainLayout.class)
public class ServiciosView extends Main implements HasComponents, HasStyle, ServiciosViewModel {

	private final Grid<Servicios> grid = new Grid<>(Servicios.class, false);
	
	private List<Servicios> servicio;
	private Servicios_interactor controlador;
	//private Servicios servicios;
	
    public ServiciosView() {
    	servicio = new ArrayList<>();
    	this.controlador = new Servicios_interactorImp(this);
        constructUI();
        constructUI2();
        this.controlador.consultarServicios();
    }

    private void constructUI() {
        addClassNames("servicios-view");
        addClassNames(MaxWidth.SCREEN_LARGE, Margin.Horizontal.AUTO, Padding.Bottom.LARGE, Padding.Horizontal.LARGE);

        HorizontalLayout container = new HorizontalLayout();
        container.addClassNames(AlignItems.CENTER, JustifyContent.BETWEEN);

        VerticalLayout headerContainer = new VerticalLayout();
        H2 header = new H2("Servicios Disponibles");
        header.addClassNames(Margin.Bottom.NONE, Margin.Top.XLARGE, FontSize.XXXLARGE);
        headerContainer.add(header);

        container.add(headerContainer);
        add(container);

    }
    
    private void constructUI2() {
    	
    	HorizontalLayout container = new HorizontalLayout();
        container.addClassNames(AlignItems.CENTER, JustifyContent.BETWEEN);
        
    	grid.addColumn(Servicios::getId_servicio).setHeader("ID");
    	grid.addColumn(Servicios::getNombre_servicio).setHeader("Servicio");
    	grid.addColumn(Servicios::getSubservicio).setHeader("Subservicio");
    	grid.addColumn(Servicios::getCosto).setHeader("Costo");
    	grid.addThemeVariants(GridVariant.LUMO_NO_BORDER);
    	grid.addClassName(Margin.Top.XLARGE);
    	container.add(grid);
        add(container);

    }
    
    @Override
	public void refrescarGridServicios(List<Servicios> servicios) {
		Collection<Servicios> items = servicios;
		grid.setItems(items);
		this.servicio = servicios;
    
	}
    
    public Grid<Servicios> getGrid(){
    	return grid;
    }
    
}
