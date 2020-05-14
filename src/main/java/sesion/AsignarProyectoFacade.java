/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sesion;

import entities.AsignarProyecto;
import entities.Productos;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Vanessa
 */
@Stateless
public class AsignarProyectoFacade extends AbstractFacade<AsignarProyecto> {

    @PersistenceContext(unitName = "inventarioF9PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public AsignarProyectoFacade() {
        super(AsignarProyecto.class);
    }
    
         public AsignarProyecto encontarFactura(String selected) {
        AsignarProyecto asig = null;
        String consulta;
        try {
            System.out.println("PRUEBA: " + selected );
            consulta = "SELECT a FROM AsignarProyecto a WHERE a.salNumero = ?1";        
            Query query = em.createQuery(consulta);
            query.setParameter(1, selected);

            List<AsignarProyecto> lstAsig = query.getResultList();
            if (!lstAsig.isEmpty()) {
                asig = lstAsig.get(0);
            }
        } catch (Exception e) {
            throw e;
        }
        return asig;
    }
         
             
     public List<AsignarProyecto> encontarFactura2(String selected) {
        List<AsignarProyecto> lstAsig = null;
        String consulta;
        try {
            System.out.println("PRUEBA: " + selected);
            consulta = "SELECT a FROM AsignarProyecto a WHERE a.salNumero = ?1";
            Query query = em.createQuery(consulta);
            query.setParameter(1, selected);

            lstAsig = query.getResultList();
        } catch (Exception e) {
            throw e;
        }
        return lstAsig;
    }
 
    public List<AsignarProyecto> encontarProyecto(String selected) {
        List<AsignarProyecto> lstAsig=null ;
        String consulta;
        try {
            System.out.println("PRUEBA: " + selected );
            consulta = "SELECT a FROM AsignarProyecto a WHERE a.proyId.proyNombre = ?1";        
            Query query = em.createQuery(consulta);
            query.setParameter(1, selected);

            lstAsig = query.getResultList();
        } catch (Exception e) {
            throw e;
        }
        return lstAsig;
    }
         
        public AsignarProyecto encontarFactura(String selected, Productos productos) {
        AsignarProyecto asig = null;
        String consulta;
        try {
            System.out.println("Factura: " + selected + " Producto: " + productos.getProNombres());
            consulta = "FROM AsignarProyecto a WHERE a.salNumero = ?1 AND a.proId4 = ?2";   
            Query query = em.createQuery(consulta);
            query.setParameter(1, selected);
            query.setParameter(2, productos);

            List<AsignarProyecto> lstAsig = query.getResultList();
            if (!lstAsig.isEmpty()) {
                asig = lstAsig.get(0);
            }
        } catch (Exception e) {
            throw e;
        }
        return asig;
    }
    
    public AsignarProyecto encontarUltimaGuia() {
        AsignarProyecto asig = null;
        String consulta;
        try {
            System.out.println("Encontrar ultima factura " );
            consulta = "SELECT a FROM AsignarProyecto a";        
            Query query = em.createQuery(consulta);
            List<AsignarProyecto> lstAsig = query.getResultList();
            if (!lstAsig.isEmpty()) {
                asig = lstAsig.get(lstAsig.size()-1);
            }
        } catch (Exception e) {
            throw e;
        }
        return asig;
    }
    
    public AsignarProyecto findLastInProyecto(int codigo) {
        AsignarProyecto asig = null;
        String consulta;
        try {
            System.out.println("Encontrar ultima factura "+ codigo );
            consulta = "SELECT a FROM AsignarProyecto a WHERE a.proyId.proyId = ?1";        
            Query query = em.createQuery(consulta);
            query.setParameter(1, codigo);
            
            List<AsignarProyecto> lstAsig = query.getResultList();
            if (!lstAsig.isEmpty()) {
                asig = lstAsig.get(lstAsig.size()-1);
            }
        } catch (Exception e) {
            throw e;
        }
        return asig;
    }     
    
}
