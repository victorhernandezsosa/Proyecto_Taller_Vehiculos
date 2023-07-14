package com.tallervehiculos.uth.data.service;


import com.tallervehiculos.uth.data.entity.ResponseDetalles;

import com.tallervehiculos.uth.data.entity.ResponseRepuestos;
import com.tallervehiculos.uth.data.entity.ResponseTaller;
import com.tallervehiculos.uth.data.entity.ResponseOrden;
import com.tallervehiculos.uth.data.entity.ResponseServicios;
import com.tallervehiculos.uth.data.entity.ResponseTaller;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;

public interface TallerRepository {

	@Headers({
	    "Content-Type: application/json",
	    "Accept-Charset: utf-8",
	    "User-Agent: Retrofit-Sample-App"
	})
	@GET("/pls/apex/allantorres_pa/vehiculos/registro_vehiculo/")
	Call<ResponseTaller> obtenerVehiculo();
	
	@Headers({
	    "Content-Type: application/json",
	    "Accept-Charset: utf-8",
	    "User-Agent: Retrofit-Sample-App"
	})

	@GET("/pls/apex/allantorres_pa/vehiculos/orden_reparacion/")
	Call<ResponseOrden> obtenerOrden();

	

	
	@Headers({
	    "Content-Type: application/json",
	    "Accept-Charset: utf-8",
	    "User-Agent: Retrofit-Sample-App"
	})

	@GET("/pls/apex/allantorres_pa/vehiculos/servicios/")
	Call<ResponseServicios> obtenerServicios();
	
	@Headers({
	    "Content-Type: application/json",
	    "Accept-Charset: utf-8",
	    "User-Agent: Retrofit-Sample-App"
	})
	@GET("/pls/apex/allantorres_pa/vehiculos/orden_sr/")
	Call<ResponseDetalles> obtenerDetalles();
	
	

}


	
