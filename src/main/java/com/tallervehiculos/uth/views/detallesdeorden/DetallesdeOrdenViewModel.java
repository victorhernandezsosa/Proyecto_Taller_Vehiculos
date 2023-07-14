package com.tallervehiculos.uth.views.detallesdeorden;

import java.util.List;

import com.tallervehiculos.uth.data.entity.Detalles;

public interface DetallesdeOrdenViewModel {
	void refrescarGridDetalles(List<Detalles> orden);
}
