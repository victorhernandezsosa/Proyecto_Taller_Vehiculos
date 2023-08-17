package com.tallervehiculos.uth.data.entity;

import java.util.List;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;

public class OrdenSR_Report implements JRDataSource{
	
	private List<Orden_reparacion> orden;
	private List<OrdenSR> itemsSR;
	private List<Servicios> servicio;
	private List<repuestos> repuesto;
	//private List<Vehiculo> vehiculos;
	private int counter = -1;
	private int maxCounter = 0;
	
	public void setData(List<OrdenSR> itemsSR) {
		this.itemsSR = itemsSR;
		this.maxCounter = this.itemsSR.size() -1;
	}
	
	public void setOrdenRepa(List<Orden_reparacion> orden) {
		this.orden = orden;
		this.maxCounter = this.orden.size() -1;
	}
	
	public void setServi(List<Servicios> servicio) {
		this.servicio = servicio;
		this.maxCounter = this.servicio.size() -1;
	}
	
	public void setRepuestos(List<repuestos> repuesto) {
		this.repuesto = repuesto;
		this.maxCounter = this.repuesto.size() -1;
	}
	

	public List<repuestos> getRepuesto() {
		return repuesto;
	}



	public void setRepuesto(List<repuestos> repuesto) {
		this.repuesto = repuesto;
	}



	public List<Orden_reparacion> getOrden() {
		return orden;
	}


	public void setOrden(List<Orden_reparacion> orden) {
		this.orden = orden;
	}


	public List<OrdenSR> getItemsSR() {
		return itemsSR;
	}



	public void setItemsSR(List<OrdenSR> itemsSR) {
		this.itemsSR = itemsSR;
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
	    if ("ID".equals(jrField.getName())) {
	        return itemsSR.get(counter).getId_ordensr().toString();
	    } else if("CLIENTE".equals(jrField.getName())){
	    	return itemsSR.get(counter).getNombre_cliente();
	    }else if ("NOMBRE_VEHICULO".equals(jrField.getName())) {
	        return orden.get(counter).getDescripcion_problema();
	    } else if ("REPUESTO".equals(jrField.getName())) {
	        return repuesto.get(counter).getNombre_repuesto();
	    } else if ("SERVICIO".equals(jrField.getName())) {
	        return servicio.get(counter).getNombre_servicio();
	    }else if("TOTAL".equals(jrField.getName())) {
	        return itemsSR.get(counter).getTotal_costo();
	    }
	    return "";
	}
}
