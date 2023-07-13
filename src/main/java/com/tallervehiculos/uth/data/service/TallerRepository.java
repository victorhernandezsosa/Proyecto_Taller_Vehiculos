package com.tallervehiculos.uth.data.service;


import com.tallervehiculos.uth.data.entity.ResponseOrden_reparacion;
import com.tallervehiculos.uth.data.entity.ResponseVehiculo;

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
	Call<ResponseVehiculo> obtenerVehiculo();
	
	
	
	@Headers({
	    "Content-Type: application/json",
	    "Accept-Charset: utf-8",
	    "User-Agent: Retrofit-Sample-App"
	})
	@GET("/pls/apex/allantorres_pa/vehiculos/orden_reparacion/")
	Call<ResponseOrden_reparacion> obtenerOrden();
	
}
