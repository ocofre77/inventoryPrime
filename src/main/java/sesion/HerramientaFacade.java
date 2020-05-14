/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sesion;

import entities.Herramienta;
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
public class HerramientaFacade extends AbstractFacade<Herramienta> {

    @PersistenceContext(unitName = "inventarioF9PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public HerramientaFacade() {
        super(Herramienta.class);
    }
    
      public Herramienta encontrarHerramientaEspecifica(int selected) {
        Herramienta herrmienta = null;
        String consulta;
        try {
            System.out.println("CÓDIGO: " + selected);
            consulta = "SELECT p FROM Herramienta p WHERE p.herId = ?1";
            Query query = em.createQuery(consulta);
            query.setParameter(1, selected);

            List<Herramienta> lst = query.getResultList();
            if (!lst.isEmpty()) {
                herrmienta = lst.get(0);
            }
        } catch (Exception e) {
            throw e;
        }
        return herrmienta;
    }
}
