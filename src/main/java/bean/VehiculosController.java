package bean;

import entities.Vehiculos;
import bean.util.JsfUtil;
import bean.util.JsfUtil.PersistAction;
import entities.Producto;
import sesion.VehiculosFacade;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@Named("vehiculosController")
@SessionScoped
public class VehiculosController implements Serializable {

    @EJB
    private sesion.VehiculosFacade ejbFacade;
    private List<Vehiculos> items = null;
    private Vehiculos selected;

    public VehiculosController() {
    }

    public Vehiculos getSelected() {
        return selected;
    }

    public void setSelected(Vehiculos selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private VehiculosFacade getFacade() {
        return ejbFacade;
    }

    public Vehiculos prepareCreate() {
        selected = new Vehiculos();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("resource/Bundle").getString("VehiculosCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("resource/Bundle").getString("VehiculosUpdated"));
    }

    public void actualizar(){
        items = null;
    }
    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("resource/Bundle").getString("VehiculosDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<Vehiculos> getItems() {
        List<Vehiculos> lst = new ArrayList<>();
        if (items == null) {
            items = getFacade().findAll();
            Iterator<Vehiculos> it = items.iterator();
            while (it.hasNext()) {
                Vehiculos producto = it.next();
                if (!producto.getProId4().getProEstadoEliminar().equals("activo")) {
                    it.remove();
                }
            }

        }

        return items;
    }

    private void persist(PersistAction persistAction, String successMessage) {
        if (selected != null) {
            setEmbeddableKeys();
            try {
                if (persistAction != PersistAction.DELETE) {
                    getFacade().edit(selected);
                } else {
                    getFacade().remove(selected);
                }
                JsfUtil.addSuccessMessage(successMessage);
            } catch (EJBException ex) {
                String msg = "";
                Throwable cause = ex.getCause();
                if (cause != null) {
                    msg = cause.getLocalizedMessage();
                }
                if (msg.length() > 0) {
                    JsfUtil.addErrorMessage(msg);
                } else {
                    JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("resource/Bundle").getString("PersistenceErrorOccured"));
                }
            } catch (Exception ex) {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
                JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("resource/Bundle").getString("PersistenceErrorOccured"));
            }
        }
    }

    public Vehiculos getVehiculos(java.lang.Integer id) {
        return getFacade().find(id);
    }

    public List<Vehiculos> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<Vehiculos> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = Vehiculos.class)
    public static class VehiculosControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            VehiculosController controller = (VehiculosController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "vehiculosController");
            return controller.getVehiculos(getKey(value));
        }

        java.lang.Integer getKey(String value) {
            java.lang.Integer key;
            key = Integer.valueOf(value);
            return key;
        }

        String getStringKey(java.lang.Integer value) {
            StringBuilder sb = new StringBuilder();
            sb.append(value);
            return sb.toString();
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof Vehiculos) {
                Vehiculos o = (Vehiculos) object;
                return getStringKey(o.getVehId());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Vehiculos.class.getName()});
                return null;
            }
        }

    }

}
