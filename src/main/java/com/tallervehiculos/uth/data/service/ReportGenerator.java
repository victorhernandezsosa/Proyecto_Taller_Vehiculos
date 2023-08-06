package com.tallervehiculos.uth.data.service;

import java.io.File;
import java.util.Map;

import org.springframework.util.ResourceUtils;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;

public class ReportGenerator {

	private String ubicacion;

	public String generarReportePDF(String reportName, Map<String, Object> parameters, JRDataSource datasource) {
	    String rutaPDF = null;

	    try {
	        JasperReport reporte = (JasperReport) JRLoader.loadObjectFromFile(fetchReportPath(reportName + ".jasper"));
	        JasperPrint impresora = JasperFillManager.fillReport(reporte, parameters, datasource);
	        rutaPDF = generateReportSavePath() + reportName + ".pdf";
	        this.ubicacion = rutaPDF;
	        JasperExportManager.exportReportToPdfFile(impresora, rutaPDF);
	    } catch (Exception error) {
	        error.printStackTrace();
	    }
	    return rutaPDF;
	}

	private String generateReportSavePath() {
		String path = null;
		try {
			path = File.createTempFile("temp", ".pdf").getAbsolutePath();
		}catch(Exception error) {
			error.printStackTrace();
		}
		return path;
	}

	private String fetchReportPath(String reportName) {
		String path = null;
		try{
			path = ResourceUtils.getFile("classpath:"+reportName).getAbsolutePath();
		}catch(Exception error) {
			error.printStackTrace();
		}
		return path;
	}

	public String getUbicacion() {
		return ubicacion;
	}


}
