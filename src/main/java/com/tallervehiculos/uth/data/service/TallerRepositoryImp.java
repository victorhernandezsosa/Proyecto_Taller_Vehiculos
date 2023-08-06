package com.tallervehiculos.uth.data.service;

import java.io.IOException;

import com.tallervehiculos.uth.data.entity.ResponseOrden;
import com.tallervehiculos.uth.data.entity.ResponseServicios;
import com.tallervehiculos.uth.data.entity.ResponseServicios;
import com.tallervehiculos.uth.data.entity.ResponseTaller;
import retrofit2.Call;
import retrofit2.Response;

public class TallerRepositoryImp {

	private static TallerRepositoryImp instance;
	private RepositoryTaller taller;
	
	private TallerRepositoryImp(String url, Long timeout) {
		this.taller = new RepositoryTaller(url, timeout);
	}
	
	//IMPLEMENTANDO PATRÓN SINGLETON
	public static TallerRepositoryImp getInstance(String url, Long timeout) {
		if(instance == null) {
			synchronized (TallerRepositoryImp.class) {
				if(instance == null) {
					instance = new TallerRepositoryImp(url, timeout);
				}
			}
		}
		return instance;
	}
	
	public ResponseTaller getvehiculo() throws IOException {
		Call<ResponseTaller> call = taller.getDatabaseService().obtenerVehiculo();
		Response<ResponseTaller> response = call.execute(); //AQUI ES DONDE SE CONSULTA A LA URL DE LA BASE DE DATOS
		if(response.isSuccessful()){
			return response.body();
		}else {
			return null;
		}
	}
	
	public ResponseOrden getOrden() throws IOException {
		Call<ResponseOrden> call = taller.getDatabaseService().obtenerOrden();
		Response<ResponseOrden> response = call.execute(); //AQUI ES DONDE SE CONSULTA A LA URL DE LA BASE DE DATOS
		if(response.isSuccessful()){
			return response.body();
		}else {
			return null;
		}
	}
	
	public ResponseServicios getServicios() throws IOException {
		Call<ResponseServicios> call = taller.getDatabaseService().obtenerServicios();
		Response<ResponseServicios> response = call.execute(); //AQUI ES DONDE SE CONSULTA A LA URL DE LA BASE DE DATOS
		if(response.isSuccessful()){
			return response.body();
		}else {
			return null;
		}
	}


}
