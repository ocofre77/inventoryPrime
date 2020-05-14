/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sesion;

import entities.Productos;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.primefaces.model.UploadedFile;

/**
 *
 * @author Vanessa
 */
@Stateless
public class ProductosFacade extends AbstractFacade<Productos> {

    @PersistenceContext(unitName = "inventarioF9PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ProductosFacade() {
        super(Productos.class);
    }
    
    public Productos Obtenerobj() {
        Productos producto =null;
        String consulta;
        try{
            consulta = "SELECT p FROM Productos p";
            Query query = em.createQuery(consulta);
            List<Productos> lstusuarios = query.getResultList();
            System.out.println("ENTROOOOOOOOOO");
            if (!lstusuarios.isEmpty()) {
                 System.out.println("Tamaño: "+(lstusuarios.size()-1));
                producto = lstusuarios.get(lstusuarios.size()-1);
               
            }
        }catch (Exception e){
            throw e;
        }
        return producto;
    }
         
       public Productos InsertarImagen(Productos pro, UploadedFile file) throws Exception{
        Productos producto =null;
        String consulta;
        try{
            System.out.println("PRUEBA: "+ pro.getProNombres()+ " 2 Prueba: "+ file);
            consulta = "UPDATE Productos p SET p.proImagen=?1 WHERE p.proId4 = ?2";
            Query query = em.createQuery(consulta);
            query.setParameter(1, file.getInputstream());
            query.setParameter(2, pro.getProId4());
            
            List<Productos> lstproducto = query.getResultList();
            if (!lstproducto.isEmpty()) {
                producto = lstproducto.get(0);
            }
        }catch (Exception e){
            throw e;
        }
        return producto;
    
    }
       
       
       
       
     public Productos BuscarEspecifico(int id){
        Productos producto = null;
        String consulta;
        try{
            consulta = "SELECT p FROM Productos p WHERE p.proId4 = ?1";
            Query query = em.createQuery(consulta);
            query.setParameter(1, id);
            
            List<Productos> lstproducto = query.getResultList();
            if (!lstproducto.isEmpty()) {
                producto = lstproducto.get(0);
            }
        }catch (Exception e){
            throw e;
        }
        return producto;
    
    }
     
       public Productos encontarProductos(int selected) {
        Productos productos = null;
        String consulta;
        try {
            System.out.println("PRUEBA: " + selected );
            consulta = "SELECT p FROM Productos p WHERE p.proId4 = ?1";
            Query query = em.createQuery(consulta);
            query.setParameter(1, selected);

            List<Productos> lstProductos = query.getResultList();
            if (!lstProductos.isEmpty()) {
                productos = lstProductos.get(0);
            }
        } catch (Exception e) {
            throw e;
        }
        return productos;
    }

     
     public Productos encontarProductos1(String selected) {
        Productos productos = null;
        String consulta;
        try {
            System.out.println("PRUEBA: " + selected );
            consulta = "SELECT p FROM Productos p WHERE p.proSerial = ?1";
            Query query = em.createQuery(consulta);
            query.setParameter(1, selected);

            List<Productos> lstProductos = query.getResultList();
            if (!lstProductos.isEmpty()) {
                productos = lstProductos.get(0);
            }
        } catch (Exception e) {
            throw e;
        }
        return productos;
    }
    
     public Productos encontarProductos2(String selected) {
        Productos productos = null;
        String consulta;
        try {
            System.out.println("PRUEBA: " + selected );
            consulta = "SELECT p FROM Productos p WHERE p.proCodigopro = ?1";
            Query query = em.createQuery(consulta);
            query.setParameter(1, selected);

            List<Productos> lstProductos = query.getResultList();
            if (!lstProductos.isEmpty()) {
                productos = lstProductos.get(0);
            }
        } catch (Exception e) {
            throw e;
        }
        return productos;
    }
      
      
     public List<Productos> encontrarBodegas(String selected) {
        List<Productos> lstProductos=new ArrayList<>();
        String consulta;
        try {
            System.out.println("PRUEBA: " + selected );
            consulta = "SELECT p FROM Productos p WHERE p.proCodigopro = ?1";
            Query query = em.createQuery(consulta);
            query.setParameter(1, selected);
            lstProductos = query.getResultList();  
        } catch (Exception e) {
            throw e;
        }
        return lstProductos;
    }
     
     
    public List<Productos> productosByNombre(String nombreProducto) {
        List<Productos> lstProductos=new ArrayList<>();
        try {
            if( !nombreProducto.equals("") ){
            System.out.println("PRUEBA OR: " + nombreProducto );
            Query query = em.createQuery("SELECT distinct(p) FROM Productos p WHERE p.proNombres LIKE ?1");
            query.setParameter(1, nombreProducto);
            lstProductos = query.getResultList();
            }
        } catch (Exception e) {
            throw e;
        }
        return lstProductos;
    } 
    
}
