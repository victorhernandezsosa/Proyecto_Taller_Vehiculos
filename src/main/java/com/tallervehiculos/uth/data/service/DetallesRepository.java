package com.tallervehiculos.uth.data.service;

import com.tallervehiculos.uth.data.entity.ResponseDetalles;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;

public interface DetallesRepository {
	@Headers({
	    "Content-Type: application/json",
	    "Accept-Charset: utf-8",
	    "User-Agent: Retrofit-Sample-App"
	})
	@GET("/pls/apex/allantorres_pa/vehiculos/detalles_orden/")
	Call<ResponseDetalles> obtenerDetalles();

}
