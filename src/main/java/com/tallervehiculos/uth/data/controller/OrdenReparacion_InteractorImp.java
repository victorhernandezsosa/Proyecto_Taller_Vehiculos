package com.tallervehiculos.uth.data.controller;

import java.io.IOException;

import com.tallervehiculos.uth.data.entity.ResponseOrden;
import com.tallervehiculos.uth.data.entity.ResponseTaller;
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
	public void consultarVehiculo() {
		try {
			ResponseTaller respuesta = this.modelo.getvehiculo();
			this.vista.refrescarComboBoxVehiculos(respuesta.getItems());
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
}
