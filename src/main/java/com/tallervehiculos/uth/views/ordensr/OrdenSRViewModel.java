package com.tallervehiculos.uth.views.ordensr;

import java.util.List;

import com.tallervehiculos.uth.data.entity.Vehiculo;
import com.vaadin.flow.component.select.Select;

public interface OrdenSRViewModel {
	Select<String> createRepuestoIdSelect(List<Vehiculo> vehiculos);
}
