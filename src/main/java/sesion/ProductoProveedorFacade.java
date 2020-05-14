/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sesion;
import entities.ProductoProveedor;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Orlando
 */
@Stateless
public class ProductoProveedorFacade extends AbstractFacade<ProductoProveedor>{

    @PersistenceContext(unitName = "inventarioF9PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ProductoProveedorFacade() {
        super(ProductoProveedor.class);
    }

    public List<ProductoProveedor> encontarProductosProveedor(int proveedorId) {
        List<ProductoProveedor> lstProductoProveedor=new ArrayList<>();
        String consulta;
        try {
            System.out.println("ProductoProveedor: " + proveedorId );
            consulta = "SELECT p FROM ProductoProveedor p WHERE p.provId = ?1";
            Query query = em.createQuery(consulta);
            query.setParameter(1, proveedorId);

            lstProductoProveedor = query.getResultList();
            
        } catch (Exception e) {
            throw e;
        }
        return lstProductoProveedor;
    }
    
}
