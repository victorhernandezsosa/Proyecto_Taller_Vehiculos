package com.tallervehiculos.uth.data.entity;


import jakarta.persistence.Entity;

@Entity
public class Detalles  extends AbstractEntity {

	
    private String id_detalle;
    private String nombre_cliente;
    private String placa;
	private String nombre_servicio;
    private String nombre_repuesto;
    private String total;
    
	public String getId_detalle() {
		return id_detalle;
	}
	public void setId_detalle(String id_detalle) {
		this.id_detalle = id_detalle;
	}
	public String getNombre_cliente() {
		return nombre_cliente;
	}
	public void setNombre_cliente(String nombre_cliente) {
		this.nombre_cliente = nombre_cliente;
	}
	public String getPlaca() {
		return placa;
	}
	public void setPlaca(String placa) {
		this.placa = placa;
	}
	public String getNombre_servicio() {
		return nombre_servicio;
	}
	public void setNombre_servicio(String nombre_servicio) {
		this.nombre_servicio = nombre_servicio;
	}
	public String getNombre_repuesto() {
		return nombre_repuesto;
	}
	public void setNombre_repuesto(String nombre_repuesto) {
		this.nombre_repuesto = nombre_repuesto;
	}
	public String getTotal() {
		return total;
	}
	public void setTotal(String total) {
		this.total = total;
	}
    
	
    
    

}
