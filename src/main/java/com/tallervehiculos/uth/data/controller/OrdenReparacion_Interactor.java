package com.tallervehiculos.uth.data.controller;

import com.tallervehiculos.uth.data.entity.Orden_reparacion;

public interface OrdenReparacion_Interactor {
	void consultarOrden();
	void consultarVehiculo();
	void crearNuevaOrden_Reparacion(Orden_reparacion nuevo);
	void actualizarNuevaOrden_Reparacion(Orden_reparacion actualizar);
	void eliminarOrden_Reparacion(Integer ID_ORDEN);
}
