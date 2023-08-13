package com.tallervehiculos.uth.data.controller;

import java.io.IOException;

import com.tallervehiculos.uth.data.entity.ResponseRepuestos;
import com.tallervehiculos.uth.data.entity.Vehiculo;
import com.tallervehiculos.uth.data.entity.repuestos;
import com.tallervehiculos.uth.data.service.TallerRepositoryImp;
import com.tallervehiculos.uth.views.repuestos.RepuestosViewModel;

public class Repuesto_InteractorImp implements Repuesto_Interactor {


	private TallerRepositoryImp modelo;
	private RepuestosViewModel vista;

	public Repuesto_InteractorImp(RepuestosViewModel vista) {
		super();
		this.modelo = TallerRepositoryImp.getInstance("https://apex.oracle.com/", 600000L);
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

	@Override
	public void actualizarRepuesto(repuestos actualizar) {
		try {
			boolean respuesta = this.modelo.updateRepuesto(actualizar);
			this.vista.mostrarMensajeAtualizacion(respuesta);
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	
}
