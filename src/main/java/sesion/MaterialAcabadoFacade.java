/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sesion;

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
public class MaterialAcabadoFacade extends AbstractFacade<MaterialAcabado> {

    @PersistenceContext(unitName = "inventarioF9PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public MaterialAcabadoFacade() {
        super(MaterialAcabado.class);
    }
        public MaterialAcabado encontrarMaterialAcabadoEspecifica(int selected) {
        MaterialAcabado materialaca = null;
        String consulta;
        try {
            System.out.println("CÓDIGO: " + selected);
            consulta = "SELECT p FROM MaterialAcabado p WHERE p.proId3 = ?1";
            Query query = em.createQuery(consulta);
            query.setParameter(1, selected);

            List<MaterialAcabado> lst = query.getResultList();
            if (!lst.isEmpty()) {
                materialaca = lst.get(0);
            }
        } catch (Exception e) {
            throw e;
        }
        return materialaca;
    }
}
