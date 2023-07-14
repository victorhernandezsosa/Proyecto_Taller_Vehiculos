package com.tallervehiculos.uth.views.repuestos;

import com.tallervehiculos.uth.views.MainLayout;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.tallervehiculos.uth.data.controller.Repuesto_Interactor;
import com.tallervehiculos.uth.data.controller.Repuesto_InteractorImp;
import com.tallervehiculos.uth.data.entity.Vehiculo;
import com.tallervehiculos.uth.data.entity.repuestos;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.GridVariant;
import com.vaadin.flow.component.HasComponents;
import com.vaadin.flow.component.HasStyle;
import com.vaadin.flow.component.html.H5;
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

@PageTitle("Repuestos")
@Route(value = "repuestos", layout = MainLayout.class)
public class RepuestosView extends Main implements HasComponents, HasStyle,RepuestosViewModel {
	
	private final Grid<repuestos> grid = new Grid<>(repuestos.class, false);
	
	private List<repuestos> repuesto;
	private Repuesto_Interactor controlador;

    public RepuestosView() {
    	 repuesto = new ArrayList<>();
    	this.controlador = new Repuesto_InteractorImp(this);
        constructUI();
        constructUI2();
        this.controlador.consultarRepuesto();
    }

    private void constructUI() {
        addClassNames("repuestos-view");
        addClassNames(MaxWidth.SCREEN_LARGE, Margin.Horizontal.AUTO, Padding.Bottom.LARGE, Padding.Horizontal.LARGE);

        HorizontalLayout container = new HorizontalLayout();
        container.addClassNames(AlignItems.CENTER, JustifyContent.BETWEEN);

        VerticalLayout headerContainer = new VerticalLayout();
        H5 header = new H5("Repuestos Disponibles");
        header.addClassNames(Margin.Bottom.NONE, Margin.Top.XLARGE, FontSize.XXXLARGE);
        headerContainer.add(header);

        container.add(headerContainer);
        add(container);
    }
    
    private void constructUI2() {
    	HorizontalLayout container = new HorizontalLayout();
        container.addClassNames(AlignItems.CENTER, JustifyContent.BETWEEN);
        
    	grid.addColumn(repuestos::getId_repuesto).setHeader("ID");
    	grid.addColumn(repuestos::getNombre_repuesto).setHeader("Repuesto");
    	grid.addColumn(repuestos::getPrecio).setHeader("Precio");
    	grid.addThemeVariants(GridVariant.LUMO_NO_BORDER);
    	grid.addClassName(Margin.Top.XLARGE);
    	container.add(grid);
        add(container);
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
