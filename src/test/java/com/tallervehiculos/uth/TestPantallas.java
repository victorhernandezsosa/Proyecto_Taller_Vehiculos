package com.tallervehiculos.uth;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.tallervehiculos.uth.data.entity.Orden_reparacion;
import com.tallervehiculos.uth.data.entity.Servicios;
import com.tallervehiculos.uth.data.entity.Vehiculo;
import com.tallervehiculos.uth.data.entity.repuestos;
import com.tallervehiculos.uth.views.ordendereparación.OrdendeReparaciónView;
import com.tallervehiculos.uth.views.registrodevehículo.RegistrodeVehículoView;
import com.tallervehiculos.uth.views.repuestos.RepuestosView;
import com.tallervehiculos.uth.views.servicios.ServiciosView;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.data.provider.Query;

class TestPantallas {

	private OrdendeReparaciónView vista;
	private RegistrodeVehículoView carview;
	private RepuestosView repview;
	private ServiciosView serView;
	@BeforeEach
	public void setUp() throws Exception {

		vista = new OrdendeReparaciónView();
		carview = new RegistrodeVehículoView();
		repview = new RepuestosView();
		serView = new ServiciosView();

	}

	@Test
	public void OrdenGrid() {
		System.out.println("Se está ejecutando la prueba de Orden de reparacion");
	    List<Orden_reparacion> items = new ArrayList<>();
	    Orden_reparacion selec1 = new Orden_reparacion();
	    selec1.setId_orden("1");
	    Orden_reparacion selec2 = new Orden_reparacion();
	    selec2.setId_orden("2");
	    Orden_reparacion selec3 = new Orden_reparacion();
	    selec3.setId_orden("3");

	    items.add(selec1);
	    items.add(selec2);
	    items.add(selec3);

	    Grid<Orden_reparacion> grid = vista.getGrid();
	    grid.setItems(items);

	    Assertions.assertEquals(items, vista.getGrid().getDataProvider().fetch(new Query<>()).collect(Collectors.toList()));
	}

	@Test
	public void RegistroVehiculoTest() {
		System.out.println("Se está ejecutando la prueba de Registro de Vehiculos");
		List<Vehiculo> car = new ArrayList<>();
		Vehiculo car1 = new Vehiculo();
		car1.setId_vehiculo(1);
		car.add(car1);

		Grid<Vehiculo> grid = carview.getGrid();
		grid.setItems(car);

		List<Vehiculo> mostrardatos = carview.getGrid().getDataProvider().fetch(new Query<>()).collect(Collectors.toList());
		assertEquals(car.size(), mostrardatos.size());
	    assertTrue(car.contains(car1));

	}

	@Test
	public void RepuestosTest() {
		System.out.println("Se está ejecutando la prueba de Repuestos");
		List<repuestos> listarepuesto = new ArrayList<>();
		repuestos re1 = new repuestos();
		re1.setId_repuesto("2");
		re1.setNombre_repuesto("Pastillas de freno");
		re1.setPrecio("15");
		listarepuesto.add(re1);

		List<repuestos> listaherramientas = listarepuesto.stream()
	            .filter(repuesto -> repuesto.getNombre_repuesto().equals("Pastillas de freno"))
	            .collect(Collectors.toList());

		Grid<repuestos> grid = repview.getGrid();
		grid.setItems(listaherramientas);

		List<repuestos> mostrardatos = repview.getGrid().getDataProvider().fetch(new Query<>()).collect(Collectors.toList());
		assertEquals(listaherramientas.size(), mostrardatos.size());
		assertTrue(mostrardatos.contains(re1));


	}

	@Test
	public void ServicioTest() {
		System.out.println("Se está ejecutando la prueba de Servicio");
		List<Servicios> listaservicio = new ArrayList<>();
		Servicios serv1 = new Servicios();
		serv1.setId_servicio("1");
		serv1.setNombre_servicio("Cambio de aceite");
		serv1.setSubservicio("Aceite sintético");
		serv1.setCosto("50");
		listaservicio.add(serv1);




		Grid<Servicios> grid = serView.getGrid();
		grid.setItems(listaservicio);

		List<Servicios> mostrardatos = serView.getGrid().getDataProvider().fetch(new Query<>()).collect(Collectors.toList());
		assertEquals(listaservicio.size(), mostrardatos.size());
		assertTrue(mostrardatos.contains(serv1));
	}
}
