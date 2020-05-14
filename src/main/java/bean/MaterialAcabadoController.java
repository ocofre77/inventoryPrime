package bean;

import entities.MaterialAcabado;
import bean.util.JsfUtil;
import bean.util.JsfUtil.PersistAction;
import sesion.MaterialAcabadoFacade;

import java.io.Serializable;
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

@Named("materialAcabadoController")
@SessionScoped
public class MaterialAcabadoController implements Serializable {

    @EJB
    private sesion.MaterialAcabadoFacade ejbFacade;
    private List<MaterialAcabado> items = null;
    private MaterialAcabado selected;

    public MaterialAcabadoController() {
    }

    public MaterialAcabado getSelected() {
        return selected;
    }

    public void setSelected(MaterialAcabado selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private MaterialAcabadoFacade getFacade() {
        return ejbFacade;
    }

    public MaterialAcabado prepareCreate() {
        selected = new MaterialAcabado();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("resource/Bundle").getString("MaterialAcabadoCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void actualizar() {
        items = null;
    }
    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("resource/Bundle").getString("MaterialAcabadoUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("resource/Bundle").getString("MaterialAcabadoDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<MaterialAcabado> getItems() {
        if (items == null) {
            items = getFacade().findAll();
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

    public MaterialAcabado getMaterialAcabado(java.lang.Integer id) {
        return getFacade().find(id);
    }

    public List<MaterialAcabado> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<MaterialAcabado> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = MaterialAcabado.class)
    public static class MaterialAcabadoControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            MaterialAcabadoController controller = (MaterialAcabadoController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "materialAcabadoController");
            return controller.getMaterialAcabado(getKey(value));
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
            if (object instanceof MaterialAcabado) {
                MaterialAcabado o = (MaterialAcabado) object;
                return getStringKey(o.getProId3());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), MaterialAcabado.class.getName()});
                return null;
            }
        }

    }

}
