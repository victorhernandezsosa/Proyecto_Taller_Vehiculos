package com.tallervehiculos.uth.data.controller;

import java.io.IOException;

import com.tallervehiculos.uth.data.entity.ResponseRepuestos;
import com.tallervehiculos.uth.data.service.RepuestosRepositoryImp;
import com.tallervehiculos.uth.views.repuestos.RepuestosViewModel;

public class Repuesto_InteractorImp implements Repuesto_Interactor {


	private RepuestosRepositoryImp modelo;
	private RepuestosViewModel vista;

	public Repuesto_InteractorImp(RepuestosViewModel vista) {
		super();
		this.modelo = RepuestosRepositoryImp.getInstance("https://apex.oracle.com/", 600000L);
		this.vista = vista;
	}


	@Override
	public void consultarRepuesto() {
		try {
			ResponseRepuestos respuesta = this.modelo.getRepuesto();
			this.vista.refrescarGridRepuestos(respuesta.getItems());
		}catch(IOException e) {
			e.printStackTrace();
		}

	}

}
