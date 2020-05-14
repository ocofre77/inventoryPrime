/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sesion;

import entities.Proveedor;
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
public class ProveedorFacade extends AbstractFacade<Proveedor> {

    @PersistenceContext(unitName = "inventarioF9PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ProveedorFacade() {
        super(Proveedor.class);
    }
    
     public Proveedor encontarProveedor(int selected) {
        Proveedor proveedor = null;
        String consulta;
        try {
            System.out.println("PRUEBA: " + selected );
            consulta = "SELECT p FROM Proveedor p WHERE p.provId = ?1";
            Query query = em.createQuery(consulta);
            query.setParameter(1, selected);

            List<Proveedor> lstProveedor = query.getResultList();
            if (!lstProveedor.isEmpty()) {
                proveedor = lstProveedor.get(0);
            }
        } catch (Exception e) {
            throw e;
        }
        return proveedor;
    }
     
    public Proveedor encontarProveedor1(String selected) {
        Proveedor proveedor = null;
        String consulta;
        try {
            System.out.println("PRUEBA: " + selected );
            consulta = "SELECT p FROM Proveedor p WHERE p.provRuc = ?1";
            Query query = em.createQuery(consulta);
            query.setParameter(1, selected);

            List<Proveedor> lstProveedor = query.getResultList();
            if (!lstProveedor.isEmpty()) {
                proveedor = lstProveedor.get(0);
            }
        } catch (Exception e) {
            throw e;
        }
        return proveedor;
    }
   
    
    
}
