/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sesion;

import entities.Responsable;
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
public class ResponsableFacade extends AbstractFacade<Responsable> {

    @PersistenceContext(unitName = "inventarioF9PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ResponsableFacade() {
        super(Responsable.class);
    }
    
    public Responsable encontarResponsable(int selected) {
        Responsable responsable = null;
        String consulta;
        try {
            System.out.println("RESPONSABLE: " + selected );
            consulta = "SELECT r FROM Responsable r WHERE r.resId = ?1";
            Query query = em.createQuery(consulta);
            query.setParameter(1, selected);

            List<Responsable> lstResponsable = query.getResultList();
            if (!lstResponsable.isEmpty()) {
                responsable = lstResponsable.get(0);
            }
        } catch (Exception e) {
            throw e;
        }
        return responsable;
    }
    
}
