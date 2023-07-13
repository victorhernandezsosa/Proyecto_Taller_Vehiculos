package com.tallervehiculos.uth.data.controller;

import java.io.IOException;
import com.tallervehiculos.uth.data.entity.ResponseOrden;
import com.tallervehiculos.uth.data.service.OrdenRepositoryImp;
import com.tallervehiculos.uth.views.ordendereparación.OrdendeReparacionViewModel;


public class OrdenReparacion_InteractorImp  implements OrdenReparacion_Interactor {

	private OrdenRepositoryImp modelo;
	private OrdendeReparacionViewModel vista;
	
	public OrdenReparacion_InteractorImp(OrdendeReparacionViewModel vista) {
		super();
		this.modelo = OrdenRepositoryImp.getInstance("https://apex.oracle.com/", 600000L);
		this.vista = vista;
	}

	@Override
	public void consultarOrden() {
		try {
			ResponseOrden respuesta = this.modelo.getOrden();
			this.vista.refrescarGridOrden(respuesta.getItems_orden());
		}catch(IOException e) {
			e.printStackTrace();
		}
		
	}
}
