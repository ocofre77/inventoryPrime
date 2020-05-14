/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sesion;

import entities.Proyecto;
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
public class ProyectoFacade extends AbstractFacade<Proyecto> {

    @PersistenceContext(unitName = "inventarioF9PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ProyectoFacade() {
        super(Proyecto.class);
    }
    public Proyecto encontarProyecto(int selected) {
        Proyecto proyecto = null;
        String consulta;
        try {
            System.out.println("PROYECTO: " + selected);
            consulta = "SELECT p FROM Proyecto p WHERE p.proyId = ?1";
            Query query = em.createQuery(consulta);
            query.setParameter(1, selected);

            List<Proyecto> lstProyecto = query.getResultList();
            if (!lstProyecto.isEmpty()) {
                proyecto = lstProyecto.get(0);
            }
        } catch (Exception e) {
            throw e;
        }
        return proyecto;
    }
    
}
