package com.tallervehiculos.uth.data.entity;

import java.util.List;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;

public class RegistroVehiculoReport implements JRDataSource{
	
	private List<Vehiculo> vehiculos;
	private int counter = -1;
	private int maxCounter = 0;
	
	public void setData(List<Vehiculo> vehiculos) {
		this.vehiculos = vehiculos;
		this.maxCounter = this.vehiculos.size() -1;
	}
	
	

	public List<Vehiculo> getVehiculos() {
		return vehiculos;
	}



	public void setVehiculos(List<Vehiculo> vehiculos) {
		this.vehiculos = vehiculos;
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
			return vehiculos.get(counter).getId_vehiculo().toString();
		}else if("CLIENTE".equals(jrField.getName())) {
			return vehiculos.get(counter).getNombre_cliente();
		}else if("MARCA".equals(jrField.getName())) {
			return vehiculos.get(counter).getMarca();
		}else if("MODELO".equals(jrField.getName())) {
			return vehiculos.get(counter).getModelo();
		}else if("PLACA".equals(jrField.getName())) {
			return vehiculos.get(counter).getPlaca();
		}
		return "";
	}

}
