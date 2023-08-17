package com.tallervehiculos.uth.data.service;

import java.io.IOException;

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
import retrofit2.Response;

public class TallerRepositoryImp {

	private static TallerRepositoryImp instance;
	private RepositoryTaller taller;

	private TallerRepositoryImp(String url, Long timeout) {
		this.taller = new RepositoryTaller(url, timeout);
	}

	// IMPLEMENTANDO PATRÓN SINGLETON
	public static TallerRepositoryImp getInstance(String url, Long timeout) {
		if (instance == null) {
			synchronized (TallerRepositoryImp.class) {
				if (instance == null) {
					instance = new TallerRepositoryImp(url, timeout);
				}
			}
		}
		return instance;
	}

	// VISTA DE REGISTRO DE VEHICULO
	public ResponseTaller getvehiculo() throws IOException {
		Call<ResponseTaller> call = taller.getDatabaseService().obtenerVehiculo();
		Response<ResponseTaller> response = call.execute(); // AQUI ES DONDE SE CONSULTA A LA URL DE LA BASE DE DATOS
		if (response.isSuccessful()) {
			return response.body();
		} else {
			return null;
		}
	}

	public boolean createRegistro_Vehiculo(Vehiculo nuevo) throws IOException {
		Call<ResponseBody> call = taller.getDatabaseService().crearRegistro_vehiculo(nuevo);
		Response<ResponseBody> response = call.execute(); // AQUI ES DONDE SE CONSULTA A LA URL DE LA BASE DE DATOS
		return response.isSuccessful();
	}

	public boolean updateRegistro_Vehiculo(Vehiculo actualizar) throws IOException {
		Call<ResponseBody> call = taller.getDatabaseService().actualizarRegistro_vehiculo(actualizar);
		Response<ResponseBody> response = call.execute(); // AQUI ES DONDE SE CONSULTA A LA URL DE LA BASE DE DATOS
		return response.isSuccessful();
	}

	public boolean deleteRegistro_Vehiculo(Integer ID_VEHICULO) throws IOException {
		Call<ResponseBody> call = taller.getDatabaseService().eliminarRegistro_Vehiculo(ID_VEHICULO);
		Response<ResponseBody> response = call.execute(); // AQUI ES DONDE SE CONSULTA A LA URL DE LA BASE DE DATOS
		return response.isSuccessful();
	}



	// VISTA DE ORDEN DE REPARACION
	public ResponseOrden getOrden() throws IOException {
		Call<ResponseOrden> call = taller.getDatabaseService().obtenerOrden();
		Response<ResponseOrden> response = call.execute(); // AQUI ES DONDE SE CONSULTA A LA URL DE LA BASE DE DATOS
		if (response.isSuccessful()) {
			return response.body();
		} else {
			return null;
		}
	}

	public boolean createOrden_Reparacion(Orden_reparacion nuevo) throws IOException {
		Call<ResponseBody> call = taller.getDatabaseService().crearOrden_Reparacion(nuevo);
		Response<ResponseBody> response = call.execute(); // AQUI ES DONDE SE CONSULTA A LA URL DE LA BASE DE DATOS
		return response.isSuccessful();
	}

	public boolean updateOrden_Reparacion(Orden_reparacion actualizar) throws IOException {
		Call<ResponseBody> call = taller.getDatabaseService().actualizarOrden_Reparacion(actualizar);
		Response<ResponseBody> response = call.execute(); // AQUI ES DONDE SE CONSULTA A LA URL DE LA BASE DE DATOS
		return response.isSuccessful();
	}

	public boolean deleteOrden_Reparacion(Integer ID_ORDEN) throws IOException {
		Call<ResponseBody> call = taller.getDatabaseService().eliminarOrden_Reparacion(ID_ORDEN);
		Response<ResponseBody> response = call.execute(); // AQUI ES DONDE SE CONSULTA A LA URL DE LA BASE DE DATOS
		return response.isSuccessful();

	}



	// VISTA DE ORDEN DE SERVICIOS
	public ResponseServicios getServicios() throws IOException {
		Call<ResponseServicios> call = taller.getDatabaseService().obtenerServicios();
		Response<ResponseServicios> response = call.execute(); // AQUI ES DONDE SE CONSULTA A LA URL DE LA BASE DE DATOS
		if (response.isSuccessful()) {
			return response.body();
		} else {
			return null;
		}
	}

	public boolean updateServicios(Servicios actualizar) throws IOException {
		Call<ResponseBody> call = taller.getDatabaseService().actualizarServicios(actualizar);
		Response<ResponseBody> response = call.execute(); // AQUI ES DONDE SE CONSULTA A LA URL DE LA BASE DE DATOS
		return response.isSuccessful();
	}


	// VISTA DE ORDEN DE ORDEN DE SERVICIOS Y REPUESTOS (S/R)
	public ResponseOrdenSR getOrdenSR() throws IOException {
		Call<ResponseOrdenSR> call = taller.getDatabaseService().obtenerOrdenSR();
		Response<ResponseOrdenSR> response = call.execute(); // AQUI ES DONDE SE CONSULTA A LA URL DE LA BASE DE DATOS
		if (response.isSuccessful()) {
			return response.body();
		} else {
			return null;
		}
	}

	public boolean createOrdenSR(OrdenSR nuevo) throws IOException {
		Call<ResponseBody> call = taller.getDatabaseService().crearOrdenSR(nuevo);
		Response<ResponseBody> response = call.execute(); // AQUI ES DONDE SE CONSULTA A LA URL DE LA BASE DE DATOS
		return response.isSuccessful();
	}

	public boolean deleteOrdenSR(Integer ID_ORDENSR) throws IOException {
		Call<ResponseBody> call = taller.getDatabaseService().eliminarOrdenSR(ID_ORDENSR);
		Response<ResponseBody> response = call.execute(); // AQUI ES DONDE SE CONSULTA A LA URL DE LA BASE DE DATOS
		return response.isSuccessful();

	}



	// VISTA DE ORDEN DE REPUESTOS
	public ResponseRepuestos getRepuesto() throws IOException {
		Call<ResponseRepuestos> call = taller.getDatabaseService().obtenerRepuesto();
		Response<ResponseRepuestos> response = call.execute(); // AQUI ES DONDE SE CONSULTA A LA URL DE LA BASE DE DATOS
		if (response.isSuccessful()) {
			return response.body();
		} else {
			return null;
		}
	}

	public boolean updateRepuesto(repuestos actualizar) throws IOException {
		Call<ResponseBody> call = taller.getDatabaseService().actualizarRepuesto(actualizar);
		Response<ResponseBody> response = call.execute(); // AQUI ES DONDE SE CONSULTA A LA URL DE LA BASE DE DATOS
		return response.isSuccessful();
	}

}
