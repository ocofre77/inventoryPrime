/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sesion;

import entities.Entradas;
import entities.Productos;
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
public class EntradasFacade extends AbstractFacade<Entradas> {

    @PersistenceContext(unitName = "inventarioF9PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public EntradasFacade() {
        super(Entradas.class);
    }
    
    public Entradas encontarFactura(String selected, Productos productos) {
        Entradas en = null;
        String consulta;
        try {
            System.out.println("Factura: " + selected + " Producto: " + productos.getProNombres());
            consulta = "FROM Entradas a WHERE a.entNumero = ?1 AND a.proId4 = ?2";
            Query query = em.createQuery(consulta);
            query.setParameter(1, selected);
            query.setParameter(2, productos);

            List<Entradas> lstEn = query.getResultList();
            if (!lstEn.isEmpty()) {
                en = lstEn.get(0);
            }
        } catch (Exception e) {
            throw e;
        }
        return en;
    }

    public Entradas encontarUltimaGuia() {
        Entradas entrada = null;
        String consulta;
        try {
            System.out.println("Encontrar ultima factura ");
            consulta = "SELECT a FROM Entradas a";
            Query query = em.createQuery(consulta);
            List<Entradas> lsEntradas = query.getResultList();
            if (!lsEntradas.isEmpty()) {
                entrada = lsEntradas.get(lsEntradas.size() - 1);
            }
        } catch (Exception e) {
            throw e;
        }
        return entrada;
    }

        public List<Entradas> encontarFactura2(String selected) {
        List<Entradas> lstAsig = null;
        String consulta;
        try {
            System.out.println("PRUEBA: " + selected);
            consulta = "SELECT a FROM Entradas a WHERE a.entNumero = ?1";
            Query query = em.createQuery(consulta);
            query.setParameter(1, selected);

            lstAsig = query.getResultList();
        } catch (Exception e) {
            throw e;
        }
        return lstAsig;
    }
    
}
