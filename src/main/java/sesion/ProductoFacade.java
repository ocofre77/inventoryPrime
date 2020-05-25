/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sesion;

import entities.Producto;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author Vanessa
 */
@Stateless
public class ProductoFacade extends AbstractFacade<Producto> {

    @PersistenceContext(unitName = "inventarioF9PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ProductoFacade() {
        super(Producto.class);
    }

    public Producto encontrarProductoEspecifico(final int selected) {
        Producto productos = null;
        String consulta;
        try {
            System.out.println("CÓDIGO: " + selected);
            consulta = "SELECT p FROM Producto p WHERE p.prodId = ?1";
            final TypedQuery<Producto> query = em.createQuery(consulta, Producto.class);
            query.setParameter(1, selected);

            final List<Producto> lstProductos = query.getResultList();
            if (!lstProductos.isEmpty()) {
                productos = lstProductos.get(0);
            }
        } catch (final Exception e) {
            throw e;
        }
        return productos;
    }

}
