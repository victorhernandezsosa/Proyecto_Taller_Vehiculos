package com.tallervehiculos.uth.views.ordensr;

import java.util.ArrayList;
import java.util.List;

import com.tallervehiculos.uth.data.controller.OrdenSR_interactor;
import com.tallervehiculos.uth.data.controller.OrdenSR_interactorImp;
import com.tallervehiculos.uth.data.entity.Vehiculo;
import com.tallervehiculos.uth.views.MainLayout;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.html.*;
import com.vaadin.flow.component.select.Select;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
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
public class OrdenSRView extends Div implements OrdenSRViewModel{

	Select<String> orden_id;
	private Vehiculo veh;
	//private RegistrodeVehículoView vehiculo;
	private List<Vehiculo> vehiculo;
	private OrdenSR_interactor controlador;


    public OrdenSRView() {
    	vehiculo = new ArrayList<>();
    	this.controlador = new OrdenSR_interactorImp(this);
   //inicio_combobox(veh);
        addClassNames("orden-sr-view");
        addClassNames(Display.FLEX, FlexDirection.COLUMN, Height.FULL);

        Main content = new Main();
        content.addClassNames(Gap.XLARGE, AlignItems.START, JustifyContent.CENTER, MaxWidth.SCREEN_MEDIUM,
                Margin.Horizontal.AUTO, Padding.Bottom.LARGE, Padding.Horizontal.LARGE);



        content.add(createCheckoutForm());
        add(content);
    }

    private Component createCheckoutForm() {
        Section checkoutForm = new Section();
        checkoutForm.addClassNames(Display.FLEX, FlexDirection.COLUMN, Flex.GROW);

        H2 header = new H2("Registro de Orden");
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

        TextField id_ordenSR = new TextField("ID");
        id_ordenSR.setRequiredIndicatorVisible(true);
        id_ordenSR.setPattern("[\\p{L} \\-]+");
        id_ordenSR.addClassNames(Margin.Bottom.MEDIUM);

        orden_id = new Select<>();
        //orden_id = new Select<>();
        orden_id.setLabel("Orden ID");
        orden_id.setRequiredIndicatorVisible(true);
        //orden_id.setItems(veh.getId_vehiculo());
        this.controlador.consultarOrdenSR();

        for(Vehiculo vehiculo : vehiculo) {
			this.orden_id.setItems(String.valueOf(vehiculo.getId_vehiculo()));
		}

        Div subSectionTwo = new Div();
        subSectionTwo.addClassNames(Display.FLEX, FlexWrap.WRAP, Gap.MEDIUM);

        Select<String> servicio_id = new Select<>();
        servicio_id.setLabel("Servicio ID");
        servicio_id.setRequiredIndicatorVisible(true);
        servicio_id.setItems("01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12");

        /*Select<String> repuesto_id = new Select<>();
        repuesto_id.setLabel("Repuesto ID");
        repuesto_id.setRequiredIndicatorVisible(true);
        repuesto_id.setItems("01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12");
        */

        createRepuestoIdSelect(vehiculo);

        subSectionTwo.add(servicio_id);

        paymentInfo.add(id_ordenSR ,orden_id , subSectionTwo);
        return paymentInfo;
    }

    private Footer createFooter() {
        Footer footer = new Footer();
        footer.addClassNames(Display.FLEX, AlignItems.CENTER, JustifyContent.BETWEEN, Margin.Vertical.MEDIUM);

        Button cancel = new Button("Cancelar Orden");
        cancel.addThemeVariants(ButtonVariant.LUMO_TERTIARY);

        Button pay = new Button("Registrar Orden");
        pay.addThemeVariants(ButtonVariant.LUMO_PRIMARY, ButtonVariant.LUMO_SUCCESS);

        footer.add(cancel, pay);
        return footer;
    }



    public Select<String> createRepuestoIdSelect(List<Vehiculo> vehiculos) {
        List<String> nombresClientes = new ArrayList<>();

        for (Vehiculo vehiculo : vehiculos) {
            nombresClientes.add(vehiculo.getNombre_cliente());
        }

        Select<String> repuesto_id = new Select<>();
        repuesto_id.setLabel("Repuesto ID");
        repuesto_id.setRequiredIndicatorVisible(true);
        repuesto_id.setItems(nombresClientes);

        return repuesto_id;
    }


    /*@Override
	public void refrescarSelectRepuesto(List<Vehiculo> vehiculos) {
		for(Vehiculo vehiculo : vehiculos) {
			this.orden_id.setItems(String.valueOf(vehiculos));
		}
	}*/

    /*public void inicio_combobox(List<Vehiculo> prueba) {
    	for(Vehiculo vehiculo : prueba) {
    		orden_id.setItems(vehiculo.getId_vehiculo());
		}
    }*/

    /*public void refrescarComboBoxOrden(List<Vehiculo> items_orden) {
        if (items_orden != null && !items_orden.isEmpty()) {
            ComboBox<Vehiculo> comboBoxModel = new ComboBox<>();
            for (Vehiculo orden : items_orden) {
            	comboBoxModel.addElement(orden);
            }
            comboBox.setModel(comboBoxModel);
            this.orden = new ArrayList<>(items_orden);
        }
    }*/

}
