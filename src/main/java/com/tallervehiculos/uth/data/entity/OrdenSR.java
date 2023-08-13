package com.tallervehiculos.uth.data.entity;

//import jakarta.persistence.Entity;

//@Entity
public class OrdenSR /*extends AbstractEntity */{

    private Integer id_ordensr;
    private String orden_id;
    private String servicio_id;
    private String repuesto_id;
    private String nombre_cliente;
    private String total_costo;
    

	public String getNombre_cliente() {
		return nombre_cliente;
	}
	public void setNombre_cliente(String nombre_cliente) {
		this.nombre_cliente = nombre_cliente;
	}
	public String getTotal_costo() {
		return total_costo;
	}
	public void setTotal_costo(String total_costo) {
		this.total_costo = total_costo;
	}
	public Integer getId_ordensr() {
		return id_ordensr;
	}
	public void setId_ordensr(Integer id_ordensr) {
		this.id_ordensr = id_ordensr;
	}
	public String getOrden_id() {
		return orden_id;
	}
	public void setOrden_id(String orden_id) {
		this.orden_id = orden_id;
	}
	public String getServicio_id() {
		return servicio_id;
	}
	public void setServicio_id(String servicio_id) {
		this.servicio_id = servicio_id;
	}
	public String getRepuesto_id() {
		return repuesto_id;
	}
	public void setRepuesto_id(String repuesto_id) {
		this.repuesto_id = repuesto_id;
	}

}