package com.tallervehiculos.uth.data.controller;

import com.tallervehiculos.uth.data.entity.Vehiculo;

public interface OrdenVehiculos_Interactor {
	void consultarVehiculo();
	void crearNuevoRegistro_Vehiculo(Vehiculo nuevo);
}
