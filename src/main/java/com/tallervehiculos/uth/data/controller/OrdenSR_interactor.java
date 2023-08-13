package com.tallervehiculos.uth.data.controller;

import com.tallervehiculos.uth.data.entity.OrdenSR;

public interface OrdenSR_interactor {
	//void consultarOrdenSR();
	void consultarOrden();
	void consultarServicios();
	void consultarRepuesto();
	void consultarOrdenSR();
	void crearOrdenSR(OrdenSR nuevo);
	void eliminarOrdenSR(Integer ID_ORDENSR);
}
