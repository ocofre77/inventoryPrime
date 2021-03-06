/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sesion;

import entities.DevolucionesEntradas;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Vanessa
 */
@Stateless
public class DevolucionesEntradasFacade extends AbstractFacade<DevolucionesEntradas> {

    @PersistenceContext(unitName = "inventarioF9PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public DevolucionesEntradasFacade() {
        super(DevolucionesEntradas.class);
    }
    
}
