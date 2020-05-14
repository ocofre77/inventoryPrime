/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clasesAuxiliares;
import java.io.*;
import java.sql.*;
import java.util.*;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.util.JRLoader;

public class reporteProductos {
    
        //public void getReporte(String ruta, Integer codC, Integer codV, Integer codF) throws ClassNotFoundException, ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
    public void getReporte(String ruta, String CodigoProducto) throws ClassNotFoundException, ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
        Connection conexion;
        Class.forName("com.mysql.jdbc.Driver").newInstance();
        conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/inventario7", "root", "");

        //Se definen los parametros que el reporte necesita
        Map parameter = new HashMap();
        parameter.put("CodigoProducto", CodigoProducto);


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

    public void getCodeBarByProductPdf(String ruta, List<entities.Productos> productos)throws ClassNotFoundException, ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException, JRException, IOException{

        File file = new File(ruta);
        String nombreArchivo = "attachment; filename=CodigoBarras".concat("-").concat("producto").concat(".pdf");

        HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        response.setContentType("application/pdf");
        response.addHeader("Content-disposition", nombreArchivo);

        JasperReport jasperReport = (JasperReport) JRLoader.loadObjectFromFile(file.getPath());
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport,null, new JRBeanCollectionDataSource(productos));

        JRPdfExporter jrExporter = new JRPdfExporter();
        jrExporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
        jrExporter.setParameter(JRExporterParameter.OUTPUT_STREAM, response.getOutputStream());
        jrExporter.exportReport();
        
        FacesContext.getCurrentInstance().responseComplete();    
    
    }
    
    
    public void getCodeBarPdf(String ruta,  String  proCategoria)throws ClassNotFoundException, ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException, JRException, IOException{

        Connection conexion;
        Class.forName("com.mysql.jdbc.Driver").newInstance();
        conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/inventario7", "root", "");

        Map parameter = new HashMap();
        parameter.put("CATEGORIA", proCategoria);

        File file = new File(ruta);
        String nombreArchivo = "attachment; filename=CodigoBarras".concat("-").concat(proCategoria).concat(".pdf");

        HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        response.setContentType("application/pdf");
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
    
