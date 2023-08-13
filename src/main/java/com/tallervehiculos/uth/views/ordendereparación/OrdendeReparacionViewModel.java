package com.tallervehiculos.uth.views.ordendereparación;

import java.util.List;

import com.tallervehiculos.uth.data.entity.Orden_reparacion;
import com.tallervehiculos.uth.data.entity.Vehiculo;

public interface OrdendeReparacionViewModel {
	void refrescarGridOrden(List<Orden_reparacion> orden);
	void refrescarComboBoxVehiculos(List<Vehiculo> items);
	void mostrarMensajeCreacion(boolean respuesta);
	void mostrarMensajeAtualizacion(boolean respuesta);
	void mostrarMensajeEliminacion(boolean respuesta);

	//void refrescarGridOrden(List<Orden_reparacion> items_orden);

}
