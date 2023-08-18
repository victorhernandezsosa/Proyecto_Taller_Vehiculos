package com.tallervehiculos.uth.views.ordensr;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.tallervehiculos.uth.data.controller.OrdenSR_interactor;
import com.tallervehiculos.uth.data.controller.OrdenSR_interactorImp;
import com.tallervehiculos.uth.data.entity.OrdenSR;
import com.tallervehiculos.uth.data.entity.OrdenSR_Report;
import com.tallervehiculos.uth.data.entity.Orden_reparacion;
import com.tallervehiculos.uth.data.entity.Servicios;
import com.tallervehiculos.uth.data.entity.Vehiculo;
import com.tallervehiculos.uth.data.entity.repuestos;
import com.tallervehiculos.uth.data.service.ReportGenerator;
import com.tallervehiculos.uth.views.MainLayout;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.GridVariant;
import com.vaadin.flow.component.grid.contextmenu.GridContextMenu;
import com.vaadin.flow.component.html.*;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.NotificationVariant;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.data.renderer.ComponentRenderer;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.StreamResource;
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
	private List<Orden_reparacion> orden;
	private List<Servicios> servicio;
	private List<repuestos> repuesto;
	private List<OrdenSR> itemsSR;
	private List<Vehiculo> vehiculos;


	private OrdenSR_interactor controlador;
	private OrdenSR ordensr;

	private Button pay;
	private Button cancel;

	public OrdenSRView() {
		itemsSR=new ArrayList<>();
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


		pay.addClickListener(e -> {

            if (this.ordensr == null) {
                this.ordensr = new OrdenSR();

                this.ordensr.setOrden_id(this.orden_id.getValue().getId_orden());
                this.ordensr.setServicio_id(this.servicio_id.getValue().getId_servicio());
                this.ordensr.setRepuesto_id(this.repuesto_id.getValue().getId_repuesto());
                this.controlador.crearOrdenSR(ordensr);
            }
            clearForm();
            refreshGrid();
    });

		cancel.addClickListener(e -> {
            clearForm();
            refreshGrid();
        });
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
		orden_id.setItems(orden);
		orden_id.setItemLabelGenerator(Orden_reparacion::getDescripcion_problema);

		Div subSectionTwo = new Div();
		subSectionTwo.addClassNames(Display.FLEX, FlexWrap.WRAP, Gap.MEDIUM);

		servicio_id = new ComboBox<>("Servicio");
		servicio_id.setRequiredIndicatorVisible(true);
		servicio_id.setItems(servicio);
		servicio_id.setItemLabelGenerator(Servicios::getNombre_servicio);

		repuesto_id = new ComboBox<>("Repuesto");
		repuesto_id.setRequiredIndicatorVisible(true);
		repuesto_id.setItems(repuesto);
		repuesto_id.setItemLabelGenerator(repuestos::getNombre_repuesto);

		orden_id.setPrefixComponent(VaadinIcon.FILE_TEXT_O.create());
		servicio_id.setPrefixComponent(VaadinIcon.COG_O.create());
		repuesto_id.setPrefixComponent(VaadinIcon.PACKAGE.create());

		subSectionTwo.add(servicio_id, repuesto_id);

		paymentInfo.add(orden_id, subSectionTwo);
		return paymentInfo;
	}

	private Component createGrid() {

		H4 header = new H4("Detalles de Orden S/R");
		header.addClassName("orden-sr-view-h4-1");
		header.addClassNames(Margin.Bottom.NONE, Margin.Top.XLARGE, FontSize.XXXLARGE, AlignItems.CENTER);

		grid = new Grid<>(OrdenSR.class, false);
		grid.addColumn(ordensr -> ordensr.getId_ordensr()).setHeader("ID").setAutoWidth(true);
		grid.addColumn(OrdenSR::getNombre_cliente).setHeader("Cliente").setAutoWidth(true);
		grid.addColumn(OrdenSR::getOrden_id).setHeader("Problema Vehiculo").setAutoWidth(true);
		grid.addColumn(OrdenSR::getServicio_id).setHeader("Servicio").setAutoWidth(true);
		grid.addColumn(OrdenSR::getRepuesto_id).setHeader("Repuesto").setAutoWidth(true);
		grid.addColumn(OrdenSR::getTotal_costo).setHeader("Total").setAutoWidth(true);
		grid.addColumn(
                new ComponentRenderer<>(Button::new, (button, ordensr) -> {
                    button.addThemeVariants(ButtonVariant.LUMO_ICON,
                            ButtonVariant.LUMO_ERROR,
                            ButtonVariant.LUMO_TERTIARY);
                    button.addClickListener(e -> {
                    	this.controlador.eliminarOrdenSR(ordensr.getId_ordensr());
                        clearForm();
                        refreshGrid();
                });
                    button.setIcon(new Icon(VaadinIcon.TRASH));
                })).setHeader("Borrar");

		GridContextMenu<OrdenSR> menu = grid.addContextMenu();
    	menu.addItem("Generar Reporte", event -> {
    		if(this.itemsSR.isEmpty()) {
        		Notification.show("No hay datos para generar el reporte");
        	}else {
        		Notification.show("Generando reporte PDF...");
            	generarReporteServicios();
        	}
    	});

		grid.addThemeVariants(GridVariant.LUMO_NO_BORDER);
		grid.addClassNames(LumoUtility.BorderColor.CONTRAST_10, AlignItems.CENTER, JustifyContent.CENTER, Margin.Top.XLARGE);

		VerticalLayout nose = new VerticalLayout(header, grid);
		nose.setAlignItems(FlexComponent.Alignment.STRETCH);

		return nose;
	}

	private void generarReporteServicios() {
		ReportGenerator generador = new ReportGenerator();
        Map<String, Object> parametros = new HashMap<>();
        parametros.put("LOGO_DIR","logo.png");
        parametros.put("LOGO_BAR","barcode.png");
        OrdenSR_Report datasource = new OrdenSR_Report();
        datasource.setData(itemsSR,orden,servicio,repuesto);
       /* datasource.setServi(servicio);
        datasource.setOrdenRepa(orden);
        datasource.setRepuestos(repuesto);
        //datasource.setVehiculo(vehiculos);*/
        String rutaPDF = generador.generarReportePDF("reporte_ordensr", parametros, datasource);

        if (rutaPDF != null) {
            StreamResource resource = new StreamResource("Reporte de OrdenSR.pdf", () -> {
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
	
	private Footer createFooter() {
		Footer footer = new Footer();
		footer.addClassNames(Display.FLEX, AlignItems.CENTER, JustifyContent.BETWEEN, Margin.Vertical.MEDIUM);

		cancel = new Button("Cancelar");
		cancel.addThemeVariants(ButtonVariant.LUMO_TERTIARY);

		pay = new Button("Guardar Orden");
		pay.addThemeVariants(ButtonVariant.LUMO_PRIMARY);

		footer.add(cancel, pay);
		return footer;
	}

    private void refreshGrid() {
    	this.controlador.consultarOrdenSR();
        grid.select(null);
        grid.getDataProvider().refreshAll();
    }

    private void clearForm() {
        this.orden_id.setValue(null);
        this.repuesto_id.setValue(null);
        this.servicio_id.setValue(null);
        this.ordensr = null;
    }

	@Override
	public void refrescarComboBoxOrden(List<Orden_reparacion> orden) {
		this.orden = orden;
	}

	@Override
	public void refrescarComboBoxServicios(List<Servicios> items_servicios) {
		this.servicio = items_servicios;
	}

	@Override
	public void refrescarComboBoxRepuestos(List<repuestos> items) {
		this.repuesto = items;
	}

	@Override
	public void refrescarGridOrdenSR(List<OrdenSR> itemsSR) {
		Collection<OrdenSR> items = itemsSR;
		grid.setItems(items);
		this.itemsSR = itemsSR;

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
	}

}
