package com.tallervehiculos.uth.data.controller;

import java.io.IOException;

import com.tallervehiculos.uth.data.entity.ResponseServicios;
import com.tallervehiculos.uth.data.service.TallerRepositoryImp;
import com.tallervehiculos.uth.views.servicios.ServiciosViewModel;

public class Servicios_interactorImp implements Servicios_interactor {


	private TallerRepositoryImp modelo;
	private ServiciosViewModel vista;

	public Servicios_interactorImp(ServiciosViewModel vista) {
		super();
		this.modelo = TallerRepositoryImp.getInstance("https://apex.oracle.com/", 600000L);
		this.vista = vista;
	}


	@Override
	public void consultarServicios() {
		try {
			ResponseServicios respuesta = this.modelo.getServicios();
			this.vista.refrescarGridServicios(respuesta.getItems_servicios());
		}catch(IOException e) {
			e.printStackTrace();
		}

	}

}
