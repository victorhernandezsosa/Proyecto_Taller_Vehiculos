package com.tallervehiculos.uth.data.entity;

import java.util.List;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;

public class OrdenReparacionReport implements JRDataSource{
	
	private List<Orden_reparacion> orden;
	private int counter = -1;
	private int maxCounter = 0;
	
	public void setData(List<Orden_reparacion> orden) {
		this.orden = orden;
		this.maxCounter = this.orden.size() -1;
	}
	
	

	public List<Orden_reparacion> getOrden() {
		return orden;
	}



	public void setOrden(List<Orden_reparacion> orden) {
		this.orden = orden;
	}



	public int getCounter() {
		return counter;
	}



	public void setCounter(int counter) {
		this.counter = counter;
	}



	public int getMaxCounter() {
		return maxCounter;
	}



	public void setMaxCounter(int maxCounter) {
		this.maxCounter = maxCounter;
	}



	@Override
	public boolean next() throws JRException {
		if(counter < maxCounter) {
			counter++;
			return true;
		}
		return false;
	}

	@Override
	public Object getFieldValue(JRField jrField) throws JRException {
		if("ID".equals(jrField.getName())) {
			return orden.get(counter).getId_orden().toString();
		}else if("VEHICULO_ID".equals(jrField.getName())) {
			return orden.get(counter).getVehiculo_id().toString();
		}else if("PROBLEMA".equals(jrField.getName())) {
			return orden.get(counter).getDescripcion_problema();
		}else if("ESTADO".equals(jrField.getName())) {
			return orden.get(counter).getEstado_reparacion();
		}
		return "";
	}

}
