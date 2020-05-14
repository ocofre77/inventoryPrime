/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clasesAuxiliares;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporter;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.jasperreports.engine.util.JRLoader;

/**
 *
 * @author Vanessa
 */
public class reporteEntrada {

    //public void getReporte(String ruta, Integer codC, Integer codV, Integer codF) throws ClassNotFoundException, ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
    public void getReporte(String ruta,  String  Num_entrada) throws ClassNotFoundException, ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
        Connection conexion;
        Class.forName("com.mysql.jdbc.Driver").newInstance();
        conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/inventario7", "root", "");

        //Se definen los parametros que el reporte necesita
        Map parameter = new HashMap();
        parameter.put("Num_entrada", Num_entrada);

        try {
            File file = new File(ruta);

            HttpServletResponse httpServletResponse = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();

            httpServletResponse.setContentType("application/pdf");
            httpServletResponse.addHeader("Content-Type", "application/pdf");
            
            JasperReport jasperReport = (JasperReport) JRLoader.loadObjectFromFile(file.getPath());

            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameter, conexion);
           

            JRExporter jrExporter = null;
            jrExporter = new JRPdfExporter();
            jrExporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
            jrExporter.setParameter(JRExporterParameter.OUTPUT_STREAM, httpServletResponse.getOutputStream());
            
            if (jrExporter != null) {
                try {
                    jrExporter.exportReport();
                } catch (JRException e) {
                    e.printStackTrace();
                }
            }            
            
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (conexion != null) {
                try {
                    conexion.close();                    
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }


    public void getReporteExcel(String ruta,  String  numEntrada)throws ClassNotFoundException, ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException, JRException, IOException{

        Connection conexion;
        Class.forName("com.mysql.jdbc.Driver").newInstance();
        conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/inventario7", "root", "");

        Map parameter = new HashMap();
        parameter.put("Num_entrada", numEntrada);

        File file = new File(ruta);
        String nombreArchivo = "attachment; filename=Entrada".concat("-").concat(numEntrada).concat(".xls");

        HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        //reponse.setContentType("application/xls");
        response.addHeader("Content-disposition", nombreArchivo);

        JasperReport jasperReport = (JasperReport) JRLoader.loadObjectFromFile(file.getPath());
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameter, conexion);

        JRXlsExporter jrExporter = new JRXlsExporter();
        jrExporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
        jrExporter.setParameter(JRExporterParameter.OUTPUT_STREAM, response.getOutputStream());
        jrExporter.exportReport();
        
        FacesContext.getCurrentInstance().responseComplete();
    }

    public void getReportePdf(String ruta,  String  numEntrada)throws ClassNotFoundException, ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException, JRException, IOException{

        Connection conexion;
        Class.forName("com.mysql.jdbc.Driver").newInstance();
        conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/inventario7", "root", "");

        Map parameter = new HashMap();
        parameter.put("Num_entrada", numEntrada);

        File file = new File(ruta);
        String nombreArchivo = "attachment; filename=Entrada".concat("-").concat(numEntrada).concat(".pdf");

        HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        //reponse.setContentType("application/xls");
        response.addHeader("Content-disposition", nombreArchivo);

        JasperReport jasperReport = (JasperReport) JRLoader.loadObjectFromFile(file.getPath());
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameter, conexion);

        JRPdfExporter jrExporter = new JRPdfExporter();
        jrExporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
        jrExporter.setParameter(JRExporterParameter.OUTPUT_STREAM, response.getOutputStream());
        jrExporter.exportReport();
        
        FacesContext.getCurrentInstance().responseComplete();
    }

}
