package com.tallervehiculos.uth.data.service;


<<<<<<< HEAD
import com.tallervehiculos.uth.data.entity.ResponseRepuestos;
import com.tallervehiculos.uth.data.entity.ResponseTaller;
=======
>>>>>>> 882a3bfee318580ebee70ed01262c8c4dcae6ae3

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
<<<<<<< HEAD
	@GET("/pls/apex/allantorres_pa/vehiculos/orden_reparación/")
	Call<ResponseTaller> obtenerOrden();
	
	
	@Headers({
	    "Content-Type: application/json",
	    "Accept-Charset: utf-8",
	    "User-Agent: Retrofit-Sample-App"
	})
	@GET("/pls/apex/allantorres_pa/vehiculos/respuestos/")
	
	Call<ResponseRepuestos> obtenerRepuesto();
	
	
	
}
=======
	@GET("/pls/apex/allantorres_pa/vehiculos/orden_reparacion/")
	Call<ResponseOrden> obtenerOrden();
	
	@Headers({
	    "Content-Type: application/json",
	    "Accept-Charset: utf-8",
	    "User-Agent: Retrofit-Sample-App"
	})
	@GET("/pls/apex/allantorres_pa/vehiculos/servicios/")
	Call<ResponseServicios> obtenerServicios();
	
	

}

>>>>>>> 882a3bfee318580ebee70ed01262c8c4dcae6ae3
