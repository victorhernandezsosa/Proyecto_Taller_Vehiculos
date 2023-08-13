package com.tallervehiculos.uth.views.registrodevehículo;

import java.util.List;

import com.tallervehiculos.uth.data.entity.Vehiculo;


public interface registrodevehiculoViewModel {
	void refrescarGridVehiculos(List<Vehiculo> vehiculos);
	void mostrarMensajeCreacion(boolean respuesta);
	void mostrarMensajeEliminacion(boolean respuesta);
	void mostrarMensajeAtualizacion(boolean respuesta);
}
