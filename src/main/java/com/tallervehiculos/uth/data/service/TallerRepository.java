package com.tallervehiculos.uth.data.service;

import com.tallervehiculos.uth.data.entity.OrdenSR;
import com.tallervehiculos.uth.data.entity.Orden_reparacion;
import com.tallervehiculos.uth.data.entity.ResponseOrden;
import com.tallervehiculos.uth.data.entity.ResponseOrdenSR;
import com.tallervehiculos.uth.data.entity.ResponseRepuestos;
import com.tallervehiculos.uth.data.entity.ResponseServicios;
import com.tallervehiculos.uth.data.entity.ResponseTaller;
import com.tallervehiculos.uth.data.entity.Servicios;
import com.tallervehiculos.uth.data.entity.Vehiculo;
import com.tallervehiculos.uth.data.entity.repuestos;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Query;

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
	
	@Headers({
	    "Content-Type: application/json",
	    "Accept-Charset: utf-8",
	    "User-Agent: Retrofit-Sample-App"
	})
	@PUT("/pls/apex/allantorres_pa/vehiculos/registro_vehiculo/")
	Call<ResponseBody> actualizarRegistro_vehiculo(@Body Vehiculo actualizar);
	
	@Headers({
	    "Accept-Charset: utf-8",
	    "User-Agent: Retrofit-Sample-App"
	})
	@DELETE("/pls/apex/allantorres_pa/vehiculos/registro_vehiculo/")
	Call<ResponseBody> eliminarRegistro_Vehiculo(@Query("id_vehiculo") Integer ID_VEHICULO);
	
	
	
	
	//VISTA DE ORDEN DE REPARACION
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
	@POST("/pls/apex/allantorres_pa/vehiculos/orden_reparacion/")
	Call<ResponseBody> crearOrden_Reparacion(@Body Orden_reparacion nuevo);
	
	@Headers({
	    "Content-Type: application/json",
	    "Accept-Charset: utf-8",
	    "User-Agent: Retrofit-Sample-App"
	})
	@PUT("/pls/apex/allantorres_pa/vehiculos/orden_reparacion/")
	Call<ResponseBody> actualizarOrden_Reparacion(@Body Orden_reparacion actualizar);
	
	@Headers({
	    "Accept-Charset: utf-8",
	    "User-Agent: Retrofit-Sample-App"
	})
	@DELETE("/pls/apex/allantorres_pa/vehiculos/orden_reparacion/")
	Call<ResponseBody> eliminarOrden_Reparacion(@Query("id_orden") Integer ID_ORDEN);
	
	
	
	//VISTA DE ORDEN DE SERVICIOS
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
	@PUT("/pls/apex/allantorres_pa/vehiculos/servicios/")
	Call<ResponseBody> actualizarServicios(@Body Servicios actualizar);
	
	
	
	
	
	//VISTA DE ORDEN DE REPUESTOS
	@Headers({
	    "Content-Type: application/json",
	    "Accept-Charset: utf-8",
	    "User-Agent: Retrofit-Sample-App"
	})
	@GET("/pls/apex/allantorres_pa/vehiculos/respuestos/")
	Call<ResponseRepuestos> obtenerRepuesto();

	@Headers({
	    "Content-Type: application/json",
	    "Accept-Charset: utf-8",
	    "User-Agent: Retrofit-Sample-App"
	})
	@PUT("/pls/apex/allantorres_pa/vehiculos/respuestos/")
	Call<ResponseBody> actualizarRepuesto(@Body repuestos actualizar);
	
	
	
	
	//VISTA DE ORDEN DE ORDEN DE SERVICIOS Y REPUESTOS (S/R)
	@Headers({
	    "Content-Type: application/json",
	    "Accept-Charset: utf-8",
	    "User-Agent: Retrofit-Sample-App"
	})
	@GET("/pls/apex/allantorres_pa/vehiculos/orden_sr/")
	Call<ResponseOrdenSR> obtenerOrdenSR();

	@Headers({
	    "Content-Type: application/json",
	    "Accept-Charset: utf-8",
	    "User-Agent: Retrofit-Sample-App"
	})
	@POST("/pls/apex/allantorres_pa/vehiculos/orden_sr/")
	Call<ResponseBody> crearOrdenSR(@Body OrdenSR nuevo);
	
	@Headers({
	    "Accept-Charset: utf-8",
	    "User-Agent: Retrofit-Sample-App"
	})
	@DELETE("/pls/apex/allantorres_pa/vehiculos/orden_sr/")
	Call<ResponseBody> eliminarOrdenSR(@Query("id_ordensr") Integer ID_ORDENSR);
	
}

