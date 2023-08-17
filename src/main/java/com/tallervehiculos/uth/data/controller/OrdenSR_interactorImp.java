package com.tallervehiculos.uth.data.controller;

import java.io.IOException;

import com.tallervehiculos.uth.data.entity.OrdenSR;
import com.tallervehiculos.uth.data.entity.ResponseOrden;
import com.tallervehiculos.uth.data.entity.ResponseOrdenSR;
import com.tallervehiculos.uth.data.entity.ResponseRepuestos;
import com.tallervehiculos.uth.data.entity.ResponseServicios;
import com.tallervehiculos.uth.data.service.TallerRepositoryImp;
import com.tallervehiculos.uth.views.ordensr.OrdenSRViewModel;

public class OrdenSR_interactorImp implements OrdenSR_interactor {

	private TallerRepositoryImp modelo;
	private OrdenSRViewModel vista;

	public OrdenSR_interactorImp(OrdenSRViewModel vista) {
		super();
		this.modelo = TallerRepositoryImp.getInstance("https://apex.oracle.com/", 600000L);
		this.vista = vista;
	}

	/*
	 * @Override public void consultarOrdenSR() { try { ResponseTaller respuesta =
	 * this.modelo.getvehiculo();
	 * this.vista.createRepuestoIdSelect(respuesta.getItems()); }catch(IOException
	 * e) { e.printStackTrace(); } }
	 */

	
	@Override
	public void consultarOrdenSR() {
		try {
			ResponseOrdenSR respuesta = this.modelo.getOrdenSR();
			this.vista.refrescarGridOrdenSR(respuesta.getItems());
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	
	@Override
	public void crearOrdenSR(OrdenSR nuevo) {
		try {
			boolean respuesta = this.modelo.createOrdenSR(nuevo);
			this.vista.mostrarMensajeCreacion(respuesta);
		}catch(IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void eliminarOrdenSR(Integer ID_ORDENSR) {
		try {
			boolean respuesta = this.modelo.deleteOrdenSR(ID_ORDENSR);
			this.vista.mostrarMensajeEliminacion(respuesta);
		}catch(IOException e) {
			e.printStackTrace();
		}
	}





	
	@Override
	public void consultarOrden() {
		try {
			ResponseOrden respuesta = this.modelo.getOrden();
			this.vista.refrescarComboBoxOrden(respuesta.getItems());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	
	@Override
	public void consultarServicios() {
		try {
			ResponseServicios respuesta = this.modelo.getServicios();
			this.vista.refrescarComboBoxServicios(respuesta.getItems_servicios());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	
	@Override
	public void consultarRepuesto() {
		try {
			ResponseRepuestos respuesta = this.modelo.getRepuesto();
			this.vista.refrescarComboBoxRepuestos(respuesta.getItems());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
