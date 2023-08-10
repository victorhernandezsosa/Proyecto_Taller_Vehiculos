package com.tallervehiculos.uth.views.ordensr;

import java.util.List;

import com.tallervehiculos.uth.data.entity.OrdenSR;
import com.tallervehiculos.uth.data.entity.Orden_reparacion;
import com.tallervehiculos.uth.data.entity.Servicios;
import com.tallervehiculos.uth.data.entity.Vehiculo;
import com.tallervehiculos.uth.data.entity.repuestos;
import com.vaadin.flow.component.select.Select;

public interface OrdenSRViewModel {
	//Select<String> createRepuestoIdSelect(List<Vehiculo> vehiculos);

	void refrescarComboBoxOrden(List<Orden_reparacion> orden);
	void refrescarComboBoxServicios(List<Servicios> items_servicios);
	void refrescarComboBoxRepuestos(List<repuestos> items);
	void refrescarGridOrdenSR(List<OrdenSR> items);
}
