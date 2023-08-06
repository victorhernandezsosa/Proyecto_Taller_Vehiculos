package com.tallervehiculos.uth.data.service;

import java.io.IOException;

import com.tallervehiculos.uth.data.entity.ResponseOrden;

import retrofit2.Call;
import retrofit2.Response;

public class OrdenRepositoryImp {

	private static OrdenRepositoryImp instance;
	private RepositoryOrden orden;

	private OrdenRepositoryImp(String url, Long timeout) {
		this.orden = new RepositoryOrden(url, timeout);
	}

	//IMPLEMENTANDO PATRÓN SINGLETON
		public static OrdenRepositoryImp getInstance(String url, Long timeout) {
			if(instance == null) {
				synchronized (OrdenRepositoryImp.class) {
					if(instance == null) {
						instance = new OrdenRepositoryImp(url, timeout);
					}
				}
			}
			return instance;
		}


	public ResponseOrden getOrden() throws IOException {
		Call<ResponseOrden> call = orden.getDatabaseService().obtenerOrden();
		Response<ResponseOrden> response = call.execute(); //AQUI ES DONDE SE CONSULTA A LA URL DE LA BASE DE DATOS
		if(response.isSuccessful()){
			return response.body();
		}else {
			return null;
		}
	}
}
