package com.tallervehiculos.uth.data.controller;

import com.tallervehiculos.uth.data.entity.Vehiculo;

public interface OrdenVehiculos_Interactor {
	void consultarVehiculo();
	void crearNuevoRegistro_Vehiculo(Vehiculo nuevo);
	void eliminarRegistro_Vehiculo(Integer ID_VEHICULO);
	void actualizarNuevoRegistro_Vehiculo(Vehiculo actualizar);
}
