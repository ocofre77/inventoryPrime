package bean;

import entities.Herramienta;
import bean.util.JsfUtil;
import bean.util.JsfUtil.PersistAction;
import clasesAuxiliares.reporteHerramientasMantenimiento;
import sesion.HerramientaFacade;

import java.io.Serializable;
import java.sql.SQLException;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
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
import javax.servlet.ServletContext;

@Named("herramientaController")
@SessionScoped
public class HerramientaController implements Serializable {

    @EJB
    private sesion.HerramientaFacade ejbFacade;
    private List<Herramienta> items = null;
    private Herramienta selected;

    public HerramientaController() {
    }

    public Herramienta getSelected() {
        return selected;
    }

    public void setSelected(Herramienta selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private HerramientaFacade getFacade() {
        return ejbFacade;
    }

    public Herramienta prepareCreate() {
        selected = new Herramienta();
        initializeEmbeddableKey();
        return selected;
    }
     public void verReporte() throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        reporteHerramientasMantenimiento rFactura = new reporteHerramientasMantenimiento();
        FacesContext facesContext = FacesContext.getCurrentInstance();
        ServletContext servletContext = (ServletContext) facesContext.getExternalContext().getContext();
        String ruta = servletContext.getRealPath("/Reportes/ProductosHerramientas.jasper");
        rFactura.getReporte(ruta);
        FacesContext.getCurrentInstance().responseComplete();
    }

         public void cambiarFechaMan() {
        Date nuevaFechaMan = new Date();

        if (selected.getHerEstado().equals("Atendido")) {
            if (selected.getHerFecCadaMante().equals("Mensual")) {
                Calendar cal = Calendar.getInstance();
                cal.setTime(new Date());
                cal.add(Calendar.MONTH, 1);
                nuevaFechaMan = cal.getTime();
                selected.setHerFechaMantenimiento(nuevaFechaMan);
            } else if (selected.getHerFecCadaMante().equals("Bimestral")) {
                Calendar cal = Calendar.getInstance();
                cal.setTime(new Date());
                cal.add(Calendar.MONTH, 2);
                nuevaFechaMan = cal.getTime();
                selected.setHerFechaMantenimiento(nuevaFechaMan);
            } else if (selected.getHerFecCadaMante().equals("Trimestral")) {
                Calendar cal = Calendar.getInstance();
                cal.setTime(new Date());
                cal.add(Calendar.MONTH, 3);
                nuevaFechaMan = cal.getTime();
                selected.setHerFechaMantenimiento(nuevaFechaMan);

            } else if (selected.getHerFecCadaMante().equals("Semestral")) {
                Calendar cal = Calendar.getInstance();
                cal.setTime(new Date());
                cal.add(Calendar.MONTH, 6);
                nuevaFechaMan = cal.getTime();
                selected.setHerFechaMantenimiento(nuevaFechaMan);
            } else {
                Calendar cal = Calendar.getInstance();
                cal.setTime(new Date());
                cal.add(Calendar.MONTH, 12);
                nuevaFechaMan = cal.getTime();
                selected.setHerFechaMantenimiento(nuevaFechaMan);
            }
        }
       
    }
    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("resource/Bundle").getString("HerramientaCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void actualizar(){
        items = null;
    }
    public void update() {
        cambiarFechaMan();
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("resource/Bundle").getString("HerramientaUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("resource/Bundle").getString("HerramientaDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<Herramienta> getItems() {
      List<Herramienta>  lst = new ArrayList<>();
        if (items == null) {
            items = getFacade().findAll();
          Iterator<Herramienta> it= items.iterator();
            while (it.hasNext()) {
                Herramienta producto = it.next();
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

    public Herramienta getHerramienta(java.lang.Integer id) {
        return getFacade().find(id);
    }

    public List<Herramienta> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<Herramienta> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = Herramienta.class)
    public static class HerramientaControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            HerramientaController controller = (HerramientaController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "herramientaController");
            return controller.getHerramienta(getKey(value));
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
            if (object instanceof Herramienta) {
                Herramienta o = (Herramienta) object;
                return getStringKey(o.getHerId());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Herramienta.class.getName()});
                return null;
            }
        }

    }

}
