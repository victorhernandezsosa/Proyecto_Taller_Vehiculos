package com.tallervehiculos.uth.prueba;

import com.tallervehiculos.uth.data.entity.ResponseOrden;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;

public interface OrdenRepository {
	@Headers({
	    "Content-Type: application/json",
	    "Accept-Charset: utf-8",
	    "User-Agent: Retrofit-Sample-App"
	})
	@GET("/pls/apex/allantorres_pa/vehiculos/orden_reparacion/")
	Call<ResponseOrden> obtenerOrden();
}