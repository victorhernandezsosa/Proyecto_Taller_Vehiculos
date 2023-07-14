package com.tallervehiculos.uth.data.controller;

import java.io.IOException;

import com.tallervehiculos.uth.data.entity.ResponseDetalles;
import com.tallervehiculos.uth.data.service.TallerRepositoryImp;
import com.tallervehiculos.uth.views.detallesdeorden.DetallesdeOrdenViewModel;
import com.tallervehiculos.uth.views.servicios.ServiciosViewModel;

public class Detalles_interactorImp implements Detalles_interactor {
	
	private TallerRepositoryImp modelo;
	private DetallesdeOrdenViewModel vista;
	
	public Detalles_interactorImp(DetallesdeOrdenViewModel vista) {
		super();
		this.modelo = TallerRepositoryImp.getInstance("https://apex.oracle.com/", 600000L);
		this.vista = vista;
	}

	
	@Override
	public void consultarDetalles() {
		try {
			ResponseDetalles respuesta = this.modelo.getDetalles();
			this.vista.refrescarGridDetalles(respuesta.getItems());
		}catch(IOException e) {
			e.printStackTrace();
		}
		
	}
}
