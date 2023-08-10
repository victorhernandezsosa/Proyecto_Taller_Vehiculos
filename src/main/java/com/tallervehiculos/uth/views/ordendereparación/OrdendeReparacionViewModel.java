package com.tallervehiculos.uth.views.ordendereparación;

import java.util.List;

import com.tallervehiculos.uth.data.entity.Orden_reparacion;
import com.tallervehiculos.uth.data.entity.Vehiculo;

public interface OrdendeReparacionViewModel {
	void refrescarGridOrden(List<Orden_reparacion> orden);
	void refrescarComboBoxVehiculos(List<Vehiculo> items);

	//void refrescarGridOrden(List<Orden_reparacion> items_orden);

}
