package com.tallervehiculos.uth.data.entity;

import java.util.List;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;

public class RepuestosReport implements JRDataSource{
	
	private List<repuestos> repuesto;
	private int counter = -1;
	private int maxCounter = 0;
	
	public void setData(List<repuestos> repuesto) {
		this.repuesto = repuesto;
		this.maxCounter = this.repuesto.size()-1;
	}

	

	public List<repuestos> getRepuesto() {
		return repuesto;
	}



	public void setRepuesto(List<repuestos> repuesto) {
		this.repuesto = repuesto;
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
			return repuesto.get(counter).getId_repuesto().toString();
		}else if("REPUESTO".equals(jrField.getName())) {
			return repuesto.get(counter).getNombre_repuesto();
		}else if("PRECIO".equals(jrField.getName())) {
			return repuesto.get(counter).getPrecio();
		}

		return "";

	}

}
