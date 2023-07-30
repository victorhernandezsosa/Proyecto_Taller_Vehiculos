package com.tallervehiculos.uth.views.detallesdeorden;


import com.tallervehiculos.uth.data.controller.Detalles_interactor;
import com.tallervehiculos.uth.data.controller.Detalles_interactorImp;
import com.tallervehiculos.uth.data.entity.Detalles;
import com.tallervehiculos.uth.data.entity.Orden_reparacion;
import com.tallervehiculos.uth.data.entity.Servicios;
import com.tallervehiculos.uth.data.entity.repuestos;
import com.tallervehiculos.uth.views.MainLayout;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.HasComponents;
import com.vaadin.flow.component.HasStyle;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.checkbox.CheckboxGroup;
import com.vaadin.flow.component.combobox.MultiSelectComboBox;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.dependency.Uses;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.GridVariant;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.FlexLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.spring.data.VaadinSpringDataHelpers;
import com.vaadin.flow.theme.lumo.LumoUtility;
import com.vaadin.flow.theme.lumo.LumoUtility.AlignItems;
import com.vaadin.flow.theme.lumo.LumoUtility.FontSize;
import com.vaadin.flow.theme.lumo.LumoUtility.JustifyContent;
import com.vaadin.flow.theme.lumo.LumoUtility.Margin;
import com.vaadin.flow.theme.lumo.LumoUtility.MaxWidth;
import com.vaadin.flow.theme.lumo.LumoUtility.Padding;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Expression;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import com.vaadin.flow.component.treegrid.TreeGrid;

@PageTitle("Detalles de Orden")
@Route(value = "detalles-orden", layout = MainLayout.class)
@Uses(Icon.class)
public class DetallesdeOrdenView extends Div implements DetallesdeOrdenViewModel,HasComponents, HasStyle {

    private final Grid<Detalles> grid = new Grid<>(Detalles.class, false);
    private List<Detalles> items;
    private Detalles_interactor controlador;

    public DetallesdeOrdenView() {
    	items = new ArrayList<>();
        controlador = new Detalles_interactorImp(this);
        
        grid.addColumn(Detalles::getId_detalle).setHeader("ID");
        grid.addColumn(Detalles::getNombre_cliente).setHeader("CLIENTE");
        grid.addColumn(Detalles::getPlaca).setHeader("PLACA");
        grid.addColumn(Detalles::getNombre_servicio).setHeader("SERVICIO");
        grid.addColumn(Detalles::getNombre_repuesto).setHeader("REPUESTO");
        grid.addColumn(Detalles::getTotal).setHeader("TOTAL");
        grid.addThemeVariants(GridVariant.LUMO_NO_BORDER);
        grid.addClassNames(LumoUtility.Border.TOP, LumoUtility.BorderColor.CONTRAST_10);
        add(grid);
        controlador.consultarDetalles();

    }
    

    public Grid<Detalles> getObtenerGrid() {
        return grid;
    }
    
    @Override
    public void refrescarDetalles(List<Detalles> del) {
        if (del != null) {
            Collection<Detalles> items = del;
            grid.setItems(items);
            this.items = del;
        } else {
            System.out.println("DetallesdeOrdenView : " + del);

        }
    }


}
