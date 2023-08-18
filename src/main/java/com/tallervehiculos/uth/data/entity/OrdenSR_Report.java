package com.tallervehiculos.uth.data.entity;

import java.util.List;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;

public class OrdenSR_Report implements JRDataSource {

    private List<OrdenSR> itemsSR;
    private List<Orden_reparacion> orden;
    private List<Servicios> servicio;
    private List<repuestos> repuesto;

    private int counter = -1;

    public void setData(List<OrdenSR> itemsSR, List<Orden_reparacion> orden, List<Servicios> servicio, List<repuestos> repuesto) {
        this.itemsSR = itemsSR;
        this.orden = orden;
        this.servicio = servicio;
        this.repuesto = repuesto;
        this.counter = -1;
    }

    @Override
    public boolean next() throws JRException {
        if (counter < Math.max(Math.max(orden.size(), servicio.size()), repuesto.size()) - 1) {
            counter++;
            return true;
        }
        return false;
    }

    @Override
    public Object getFieldValue(JRField jrField) throws JRException {
        if (counter >= 0 && counter < itemsSR.size()) {
            if ("ID".equals(jrField.getName())) {
                return itemsSR.get(counter).getId_ordensr().toString();
            } else if ("CLIENTE".equals(jrField.getName())) {
                return itemsSR.get(counter).getNombre_cliente();
            } else if ("NOMBRE_VEHICULO".equals(jrField.getName())) {
                if (counter < orden.size()) {
                    return orden.get(counter).getDescripcion_problema();
                }
            } else if ("REPUESTO".equals(jrField.getName())) {
                if (counter < repuesto.size()) {
                    return repuesto.get(counter).getNombre_repuesto();
                }
            } else if ("SERVICIO".equals(jrField.getName())) {
                if (counter < servicio.size()) {
                    return servicio.get(counter).getNombre_servicio();
                }
            } else if ("TOTAL".equals(jrField.getName())) {
                return itemsSR.get(counter).getTotal_costo();
            }
        }
        return "";
    }
}
