/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sesion;

import entities.Cotizacion;
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
public class CotizacionFacade extends AbstractFacade<Cotizacion> {

    @PersistenceContext(unitName = "inventarioF9PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CotizacionFacade() {
        super(Cotizacion.class);
    }
      public Cotizacion encontarUltimaGuia() {
        Cotizacion cotizacion = null;
        String consulta;
        try {
            System.out.println("Encontrar ultima Cotizacion " );
            consulta = "SELECT a FROM Cotizacion a";        
            Query query = em.createQuery(consulta);
            List<Cotizacion> lsCot = query.getResultList();
            if (!lsCot.isEmpty()) {
                cotizacion = lsCot.get(lsCot.size()-1);
            }
        } catch (Exception e) {
            throw e;
        }
        return cotizacion;
    }
    
}
