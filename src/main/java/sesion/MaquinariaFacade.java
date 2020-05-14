/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sesion;

import entities.Maquinaria;
import entities.MaterialAcabado;
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
public class MaquinariaFacade extends AbstractFacade<Maquinaria> {

    @PersistenceContext(unitName = "inventarioF9PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public MaquinariaFacade() {
        super(Maquinaria.class);
    }
    
 public Maquinaria encontrarMaquinariaEspecifica(int selected) {
        Maquinaria maquinaria = null;
        String consulta;
        try {
            System.out.println("CÓDIGO: " + selected);
            consulta = "SELECT p FROM Maquinaria p WHERE p.maqId = ?1";
            Query query = em.createQuery(consulta);
            query.setParameter(1, selected);

            List<Maquinaria> lstMaq = query.getResultList();
            if (!lstMaq.isEmpty()) {
                maquinaria = lstMaq.get(0);
            }
        } catch (Exception e) {
            throw e;
        }
        return maquinaria;
    }
    
}
