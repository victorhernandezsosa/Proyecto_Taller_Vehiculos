package com.tallervehiculos.uth.data.entity;

import java.util.List;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;

public class ServiciosReport implements JRDataSource{
	
	private List<Servicios> servicio;
	private int counter = -1;
	private int maxCounter = 0;
	
	public void setData(List<Servicios> servicio) {
		this.servicio = servicio;
		this.maxCounter = this.servicio.size() -1;
	}
	
	
	

	public List<Servicios> getServicio() {
		return servicio;
	}


	public void setServicio(List<Servicios> servicio) {
		this.servicio = servicio;
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
			return servicio.get(counter).getId_servicio().toString();
		}else if("SERVICIO".equals(jrField.getName())) {
			return servicio.get(counter).getNombre_servicio();
		}else if("SUBSERVICIO".equals(jrField.getName())) {
			return servicio.get(counter).getSubservicio();
		}else if("COSTO".equals(jrField.getName())) {
			return servicio.get(counter).getCosto();
		}
		return "";
	
	}

}
