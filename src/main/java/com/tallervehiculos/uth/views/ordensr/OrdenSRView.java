package com.tallervehiculos.uth.views.ordensr;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.tallervehiculos.uth.data.controller.OrdenSR_interactor;
import com.tallervehiculos.uth.data.controller.OrdenSR_interactorImp;
import com.tallervehiculos.uth.data.entity.OrdenSR;
import com.tallervehiculos.uth.data.entity.Orden_reparacion;
import com.tallervehiculos.uth.data.entity.Servicios;
import com.tallervehiculos.uth.data.entity.repuestos;
import com.tallervehiculos.uth.views.MainLayout;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.GridVariant;
import com.vaadin.flow.component.html.*;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.theme.lumo.LumoUtility;
import com.vaadin.flow.theme.lumo.LumoUtility.AlignItems;
import com.vaadin.flow.theme.lumo.LumoUtility.Display;
import com.vaadin.flow.theme.lumo.LumoUtility.Flex;
import com.vaadin.flow.theme.lumo.LumoUtility.FlexDirection;
import com.vaadin.flow.theme.lumo.LumoUtility.FlexWrap;
import com.vaadin.flow.theme.lumo.LumoUtility.FontSize;
import com.vaadin.flow.theme.lumo.LumoUtility.Gap;
import com.vaadin.flow.theme.lumo.LumoUtility.Height;
import com.vaadin.flow.theme.lumo.LumoUtility.JustifyContent;
import com.vaadin.flow.theme.lumo.LumoUtility.Margin;
import com.vaadin.flow.theme.lumo.LumoUtility.MaxWidth;
import com.vaadin.flow.theme.lumo.LumoUtility.Padding;
import com.vaadin.flow.theme.lumo.LumoUtility.TextColor;

@PageTitle("Orden S/R")
@Route(value = "orden_SR", layout = MainLayout.class)
public class OrdenSRView extends Div implements OrdenSRViewModel {

	private Grid<OrdenSR> grid;
	private ComboBox<Orden_reparacion> orden_id;
	private ComboBox<Servicios> servicio_id;
	private ComboBox<repuestos> repuesto_id;
	private List<Orden_reparacion> orden_reparacion;
	private List<Servicios> servicios;
	private List<repuestos> repuesto;

	private OrdenSR_interactor controlador;

	public OrdenSRView() {
		this.controlador = new OrdenSR_interactorImp(this);
		// this.controlador.consultarOrdenSR();
		this.controlador.consultarOrden();
		this.controlador.consultarRepuesto();
		this.controlador.consultarServicios();

		addClassNames("orden-sr-view");
		addClassNames(Display.FLEX, FlexDirection.COLUMN, Height.FULL);

		Main content = new Main();
		content.addClassNames(Gap.XLARGE, AlignItems.START, JustifyContent.CENTER, MaxWidth.SCREEN_MEDIUM,
				Margin.Horizontal.AUTO, Padding.Bottom.LARGE, Padding.Horizontal.LARGE);

		content.add(createCheckoutForm());

		VerticalLayout layout = new VerticalLayout(content, createGrid());
		this.controlador.consultarOrdenSR();
		layout.setSizeFull();
		layout.setPadding(false);
		layout.setSpacing(false);

		add(layout);
	}

	private Component createCheckoutForm() {
		Section checkoutForm = new Section();
		checkoutForm.addClassNames(Display.FLEX, FlexDirection.COLUMN, Flex.GROW);

		H2 header = new H2("Registro de Orden");
		header.addClassName("orden-sr-view-h2-1");
		header.addClassNames(Margin.Bottom.NONE, Margin.Top.XLARGE, FontSize.XXXLARGE);
		Paragraph note = new Paragraph("Registre la orden de los servicios y repuestos que se le daran a un vehiculo.");
		note.addClassNames(Margin.Bottom.XLARGE, Margin.Top.NONE, TextColor.SECONDARY);
		checkoutForm.add(header, note);

		checkoutForm.add(createPaymentInformationSection());
		checkoutForm.add(new Hr());
		checkoutForm.add(createFooter());

		return checkoutForm;
	}

	private Component createPaymentInformationSection() {
		Section paymentInfo = new Section();
		paymentInfo.addClassNames(Display.FLEX, FlexWrap.WRAP, Gap.MEDIUM, Margin.Top.MEDIUM);

		/*
		 * ComboBox id_ordenSR = new ComboBox<>("ID");
		 * id_ordenSR.setRequiredIndicatorVisible(true);
		 * id_ordenSR.setPattern("[\\p{L} \\-]+");
		 * id_ordenSR.addClassNames(Margin.Bottom.MEDIUM);
		 */

		orden_id = new ComboBox<>("Orden");
		orden_id.setRequiredIndicatorVisible(true);
		orden_id.setItems(orden_reparacion);
		orden_id.setItemLabelGenerator(Orden_reparacion::getDescripcion_problema);

		Div subSectionTwo = new Div();
		subSectionTwo.addClassNames(Display.FLEX, FlexWrap.WRAP, Gap.MEDIUM);

		servicio_id = new ComboBox<>("Servicio");
		servicio_id.setRequiredIndicatorVisible(true);
		servicio_id.setItems(servicios);
		servicio_id.setItemLabelGenerator(Servicios::getNombre_servicio);

		repuesto_id = new ComboBox<>("Repuesto");
		repuesto_id.setRequiredIndicatorVisible(true);
		repuesto_id.setItems(repuesto);
		repuesto_id.setItemLabelGenerator(repuestos::getNombre_repuesto);

		subSectionTwo.add(servicio_id, repuesto_id);

		paymentInfo.add(orden_id, subSectionTwo);
		return paymentInfo;
	}

	private Component createGrid() {
		
		H4 header = new H4("Detalles de Orden S/R");
		header.addClassName("orden-sr-view-h4-1");
		header.addClassNames(Margin.Bottom.NONE, Margin.Top.XLARGE, FontSize.XXXLARGE, AlignItems.CENTER);

		grid = new Grid<>(OrdenSR.class, false);
		grid.addColumn(OrdenSR::getId_ordensr).setHeader("ID").setAutoWidth(true);
		grid.addColumn(OrdenSR::getOrden_id).setHeader("Problema Vehiculo").setAutoWidth(true);
		grid.addColumn(OrdenSR::getRepuesto_id).setHeader("Repuesto").setAutoWidth(true);
		grid.addColumn(OrdenSR::getServicio_id).setHeader("Servicio").setAutoWidth(true);
		grid.addThemeVariants(GridVariant.LUMO_NO_BORDER);
		grid.addClassNames(LumoUtility.Border.TOP, LumoUtility.BorderColor.CONTRAST_10, AlignItems.CENTER, JustifyContent.CENTER);
		
		VerticalLayout nose = new VerticalLayout(header, grid);
		nose.setAlignItems(FlexComponent.Alignment.STRETCH);
		
		return nose;
	}

	private Footer createFooter() {
		Footer footer = new Footer();
		footer.addClassNames(Display.FLEX, AlignItems.CENTER, JustifyContent.BETWEEN, Margin.Vertical.MEDIUM);

		Button cancel = new Button("Cancelar Orden");
		cancel.addThemeVariants(ButtonVariant.LUMO_TERTIARY);

		Button pay = new Button("Guardar Orden");
		pay.addThemeVariants(ButtonVariant.LUMO_PRIMARY/* , ButtonVariant.LUMO_SUCCESS */);

		footer.add(cancel, pay);
		return footer;
	}

	@Override
	public void refrescarComboBoxOrden(List<Orden_reparacion> orden) {
		this.orden_reparacion = orden;
	}

	@Override
	public void refrescarComboBoxServicios(List<Servicios> items_servicios) {
		this.servicios = items_servicios;
	}

	@Override
	public void refrescarComboBoxRepuestos(List<repuestos> items) {
		this.repuesto = items;
	}

	@Override
	public void refrescarGridOrdenSR(List<OrdenSR> itemsSR) {
		Collection<OrdenSR> items = itemsSR;
		grid.setItems(items);

	}

}
