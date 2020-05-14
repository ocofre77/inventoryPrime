/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sesion;

import entities.EquipoProteccion;
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
public class EquipoProteccionFacade extends AbstractFacade<EquipoProteccion> {

    @PersistenceContext(unitName = "inventarioF9PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public EquipoProteccionFacade() {
        super(EquipoProteccion.class);
    }
     public EquipoProteccion encontrarEquipoProteccionEspecifica(int selected) {
        EquipoProteccion equipo = null;
        String consulta;
        try {
            System.out.println("CÓDIGO: " + selected);
            consulta = "SELECT p FROM EquipoProteccion p WHERE p.proId2 = ?1";
            Query query = em.createQuery(consulta);
            query.setParameter(1, selected);

            List<EquipoProteccion> lst = query.getResultList();
            if (!lst.isEmpty()) {
                equipo = lst.get(0);
            }
        } catch (Exception e) {
            throw e;
        }
        return equipo;
    }
    
}
