/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sesion;

import entities.Vehiculos;
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
public class VehiculosFacade extends AbstractFacade<Vehiculos> {

    @PersistenceContext(unitName = "inventarioF9PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public VehiculosFacade() {
        super(Vehiculos.class);
    }
    
     public Vehiculos encontrarVehiculosEspecifica(int selected) {
        Vehiculos vehiculo = null;
        String consulta;
        try {
            System.out.println("CÓDIGO: " + selected);
            consulta = "SELECT p FROM Vehiculos p WHERE p.vehId = ?1";
            Query query = em.createQuery(consulta);
            query.setParameter(1, selected);

            List<Vehiculos> lst = query.getResultList();
            if (!lst.isEmpty()) {
                vehiculo = lst.get(0);
            }
        } catch (Exception e) {
            throw e;
        }
        return vehiculo;
    }
    
}
