package com.tallervehiculos.uth.data.controller;

import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.tallervehiculos.uth.data.entity.Detalles;
import com.tallervehiculos.uth.data.entity.ResponseDetalles;
import com.tallervehiculos.uth.data.entity.ResponseServicios;
import com.tallervehiculos.uth.data.service.DetallesRepositoryIMP;
import com.tallervehiculos.uth.data.service.TallerRepositoryImp;
import com.tallervehiculos.uth.views.detallesdeorden.DetallesdeOrdenViewModel;
import com.tallervehiculos.uth.views.servicios.ServiciosViewModel;

public class Detalles_interactorImp implements Detalles_interactor {

    private DetallesRepositoryIMP modelo;
    private DetallesdeOrdenViewModel vista;


    public Detalles_interactorImp(DetallesdeOrdenViewModel vista) {
		super();
        this.modelo = DetallesRepositoryIMP.getInstance("https://apex.oracle.com", 600000L);
        this.vista = vista;
    }

    public void consultarDetalles() {
        try {
            ResponseDetalles respuesta = this.modelo.getDetalles();
            List<Detalles> detalles = respuesta.getItems();
            System.out.println("Detalles_interactorImp - Detalles obtenidos: " + detalles);
            
            if (detalles != null) {
                this.vista.refrescarDetalles(detalles);
            } else {
                System.out.println("Detalles_interactorImp - La lista de detalles es nula.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

