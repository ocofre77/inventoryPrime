/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import entities.Producto;
import entities.Productos;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
 
@WebServlet("/DownloadImage")  
public class DownloadImage extends HttpServlet {
    private static final long serialVersionUID = 4593558495041379082L;
    
    @EJB
    private sesion.ProductosFacade ejbFacade;
    
       
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
            InputStream sImage;
            try {
                String id = request.getParameter("Image_id");
                System.out.println("inside servlet–>" + id);
                int numero = Integer.parseInt(id);
                Productos product = ejbFacade.BuscarEspecifico(numero);
              
                if (product != null) {
                    byte[] bytearray = new byte[1024];
                    int size = 0;
                    //Transformamos los byte en tipo inputstream
                    sImage = new ByteArrayInputStream(product.getProImagen());
                    response.reset();
                    response.setContentType("image/jpeg");
                    while ((size = sImage.read(bytearray)) != -1) {
                        response.getOutputStream().write(bytearray, 0, size);
                    }
                }
            } catch (IOException e) {
            } catch (Exception ex) {
            Logger.getLogger(DownloadImage.class.getName()).log(Level.SEVERE, null, ex);
        }
        }
}