package com.tallervehiculos.uth.data.controller;

import java.io.IOException;

import com.tallervehiculos.uth.data.entity.ResponseTaller;
import com.tallervehiculos.uth.data.service.TallerRepositoryImp;
import com.tallervehiculos.uth.views.ordensr.OrdenSRViewModel;

public class OrdenSR_interactorImp implements OrdenSR_interactor{

	private TallerRepositoryImp modelo;
	private OrdenSRViewModel vista;

	public OrdenSR_interactorImp(OrdenSRViewModel vista) {
		super();
		this.modelo = TallerRepositoryImp.getInstance("https://apex.oracle.com/", 600000L);
		this.vista = vista;
	}


	@Override
	public void consultarOrdenSR() {
		try {
			ResponseTaller respuesta = this.modelo.getvehiculo();
			this.vista.createRepuestoIdSelect(respuesta.getItems());
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
}
