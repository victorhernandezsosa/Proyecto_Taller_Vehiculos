package com.tallervehiculos.uth.prueba;

public class RepuestosRepositoryImp {

//	private static RepuestosRepositoryImp instance;
//	private RepositoryTaller taller;
//
//	private RepuestosRepositoryImp(String url, Long timeout) {
//		this.taller = new RepositoryTaller(url, timeout);
//	}
//
//	//IMPLEMENTANDO PATRÓN SINGLETON
//	public static RepuestosRepositoryImp getInstance(String url, Long timeout) {
//		if(instance == null) {
//			synchronized (RepuestosRepositoryImp.class) {
//				if(instance == null) {
//					instance = new RepuestosRepositoryImp(url, timeout);
//				}
//			}
//		}
//		return instance;
//	}
//
//	public  ResponseRepuestos getRepuesto() throws IOException {
//		Call<ResponseRepuestos> call = taller.getDatabaseService().obtenerRepuesto();
//		Response<ResponseRepuestos> response = call.execute(); //AQUI ES DONDE SE CONSULTA A LA URL DE LA BASE DE DATOS
//		if(response.isSuccessful()){
//			return response.body();
//		}else {
//			return null;
//		}
//	}
//

}
