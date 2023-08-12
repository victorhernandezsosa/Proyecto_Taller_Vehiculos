package com.tallervehiculos.uth.data.service;

import com.tallervehiculos.uth.data.entity.ResponseOrden;
import com.tallervehiculos.uth.data.entity.ResponseOrdenSR;
import com.tallervehiculos.uth.data.entity.ResponseRepuestos;
import com.tallervehiculos.uth.data.entity.ResponseServicios;
import com.tallervehiculos.uth.data.entity.ResponseTaller;
import com.tallervehiculos.uth.data.entity.Vehiculo;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface TallerRepository {

	
	//VISTA DE REGISTRO DE VEHICULO
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
	@POST("/pls/apex/allantorres_pa/vehiculos/registro_vehiculo/")
	Call<ResponseBody> crearRegistro_vehiculo(@Body Vehiculo nuevo);
	
	
	//VISTA DE ORDEN DE REPARACION
	@Headers({
	    "Content-Type: application/json",
	    "Accept-Charset: utf-8",
	    "User-Agent: Retrofit-Sample-App"
	})
	@GET("/pls/apex/allantorres_pa/vehiculos/orden_reparacion")
	Call<ResponseOrden> obtenerOrden();
	
	
	
	//VISTA DE ORDEN DE SERVICIOS
	@Headers({
	    "Content-Type: application/json",
	    "Accept-Charset: utf-8",
	    "User-Agent: Retrofit-Sample-App"
	})
	@GET("/pls/apex/allantorres_pa/vehiculos/servicios/")
	Call<ResponseServicios> obtenerServicios();

	
	//VISTA DE ORDEN DE REPUESTOS
	@Headers({
	    "Content-Type: application/json",
	    "Accept-Charset: utf-8",
	    "User-Agent: Retrofit-Sample-App"
	})
	@GET("/pls/apex/allantorres_pa/vehiculos/respuestos/")
	Call<ResponseRepuestos> obtenerRepuesto();

	
	
	//VISTA DE ORDEN DE ORDEN DE SERVICIOS Y REPUESTOS (S/R)
	@Headers({
	    "Content-Type: application/json",
	    "Accept-Charset: utf-8",
	    "User-Agent: Retrofit-Sample-App"
	})
	@GET("/pls/apex/allantorres_pa/vehiculos/orden_sr/")
	Call<ResponseOrdenSR> obtenerOrdenSR();
}

