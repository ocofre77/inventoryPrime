/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sesion;

import entities.Usuario;
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
public class UsuarioFacade extends AbstractFacade<Usuario> {

    @PersistenceContext(unitName = "inventarioF9PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UsuarioFacade() {
        super(Usuario.class);
    }
        public Usuario iniciarSesion(Usuario selected) {
        Usuario usuario = null;
        String consulta;
        try {
            System.out.println("PRUEBA: " + selected.getUsuNombre() + " 2 Prueba: " + selected.getUsuPassword());
            consulta = "FROM Usuario u WHERE u.usuNombre = ?1 AND u.usuPassword = ?2";
            Query query = em.createQuery(consulta);
            query.setParameter(1, selected.getUsuNombre());
            query.setParameter(2, selected.getUsuPassword());

            List<Usuario> lstusuarios = query.getResultList();
            if (!lstusuarios.isEmpty()) {
                usuario = lstusuarios.get(0);
            }
        } catch (Exception e) {
            throw e;
        }
        return usuario;

    }
}
