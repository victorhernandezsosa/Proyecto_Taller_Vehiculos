package com.tallervehiculos.uth.data.service;

import java.io.IOException;
import java.util.List;

import com.tallervehiculos.uth.data.entity.Detalles;
import com.tallervehiculos.uth.data.entity.ResponseDetalles;

import retrofit2.Call;
import retrofit2.Response;

public class DetallesRepositoryIMP {
	
	private static DetallesRepositoryIMP instance;
	private RepositoryTaller taller;
	
	private DetallesRepositoryIMP(String url, Long timeout) {
		this.taller = new RepositoryTaller(url, timeout);
	}
	
	public static DetallesRepositoryIMP getInstance(String url, Long timeout) {
		if(instance == null) {
			synchronized (DetallesRepositoryIMP.class) {
				if(instance == null) {
					instance = new DetallesRepositoryIMP(url, timeout);
				}
				
			}
		}
		return instance;
	}
	
	public ResponseDetalles getDetalles() throws IOException {
	    Call<ResponseDetalles> call = taller.getObtenerDataService().obtenerDetalles();
	    Response<ResponseDetalles> response = call.execute();
	    if (response.isSuccessful()) {
	        ResponseDetalles detalles = response.body();
	        List<Detalles> detallesList = detalles.getItems(); // Utilizamos getDel() para obtener la lista de detalles
	        System.out.println("DetallesRepositoryIMP - Detalles obtenidos: " + detallesList);
	        return detalles;
	    } else {
	        System.out.println("DetallesRepositoryIMP - Error al obtener detalles. Código de error: " + response.code());
	        return null;
	    }
	}

	

}
