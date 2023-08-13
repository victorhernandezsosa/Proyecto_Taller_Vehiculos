package com.tallervehiculos.uth.data.controller;

import java.io.IOException;

import com.tallervehiculos.uth.data.entity.Orden_reparacion;
import com.tallervehiculos.uth.data.entity.ResponseOrden;
import com.tallervehiculos.uth.data.entity.ResponseTaller;
import com.tallervehiculos.uth.data.entity.Vehiculo;
import com.tallervehiculos.uth.data.service.TallerRepositoryImp;
import com.tallervehiculos.uth.views.ordendereparación.OrdendeReparacionViewModel;


public class OrdenReparacion_InteractorImp  implements OrdenReparacion_Interactor {

	private TallerRepositoryImp modelo;
	private OrdendeReparacionViewModel vista;

	public OrdenReparacion_InteractorImp(OrdendeReparacionViewModel vista) {
		super();
		this.modelo = TallerRepositoryImp.getInstance("https://apex.oracle.com", 600000L);
		this.vista = vista;
	}

	@Override
	public void consultarOrden() {
		try {
			ResponseOrden respuesta = this.modelo.getOrden();
			this.vista.refrescarGridOrden(respuesta.getItems());
		}catch(IOException e) {
			e.printStackTrace();
		}

	}
	
	@Override
	public void crearNuevaOrden_Reparacion(Orden_reparacion nuevo) {
		try {
			boolean respuesta = this.modelo.createOrden_Reparacion(nuevo);
			this.vista.mostrarMensajeCreacion(respuesta);
		}catch(IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void actualizarNuevaOrden_Reparacion(Orden_reparacion actualizar) {
		try {
			boolean respuesta = this.modelo.updateOrden_Reparacion(actualizar);
			this.vista.mostrarMensajeAtualizacion(respuesta);
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void eliminarOrden_Reparacion(Integer ID_ORDEN) {
		try {
			boolean respuesta = this.modelo.deleteOrden_Reparacion(ID_ORDEN);
			this.vista.mostrarMensajeEliminacion(respuesta);
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	
	
	
	//CONSULTA DE VEHICULOS PARA CARGAR COMBOBOX
	@Override
	public void consultarVehiculo() {
		try {
			ResponseTaller respuesta = this.modelo.getvehiculo();
			this.vista.refrescarComboBoxVehiculos(respuesta.getItems());
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
}
