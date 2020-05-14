package bean;

import entities.Maquinaria;
import bean.util.JsfUtil;
import bean.util.JsfUtil.PersistAction;
import clasesAuxiliares.reporteMaquinariaMantenimiento;
import sesion.MaquinariaFacade;

import java.io.Serializable;
import java.sql.SQLException;
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
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.servlet.ServletContext;

@Named("maquinariaController")
@SessionScoped
public class MaquinariaController implements Serializable {

    @EJB
    private sesion.MaquinariaFacade ejbFacade;
    private List<Maquinaria> items = null;
    private Maquinaria selected;

    public MaquinariaController() {
    }

    public Maquinaria getSelected() {
        return selected;
    }

    public void setSelected(Maquinaria selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private MaquinariaFacade getFacade() {
        return ejbFacade;
    }

    public Maquinaria prepareCreate() {
        selected = new Maquinaria();
        initializeEmbeddableKey();
        return selected;
    }
        public void verReporte() throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException { 
            try {
                 reporteMaquinariaMantenimiento rFactura = new reporteMaquinariaMantenimiento();
                FacesContext facesContext = FacesContext.getCurrentInstance();
                ServletContext servletContext = (ServletContext) facesContext.getExternalContext().getContext();
                String ruta = servletContext.getRealPath("/Reportes/ProductosMaquinaria.jasper");
                rFactura.getReporte(ruta);
                FacesContext.getCurrentInstance().responseComplete();
            } catch (ClassNotFoundException | IllegalAccessException | InstantiationException | SQLException e) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Seleccione un producto", "Seleccione un producto"));
            }
     
    }

       public void cambiarFechaMan() {
        Date nuevaFechaMan = new Date();

        if (selected.getMaqEstado().equals("Atendido")) {
            if (selected.getMaqFecCadaMante().equals("Mensual")) {
                Calendar cal = Calendar.getInstance();
                cal.setTime(new Date());
                cal.add(Calendar.MONTH, 1);
                nuevaFechaMan = cal.getTime();
                selected.setMaqFechaMantenimiento(nuevaFechaMan);
            } else if (selected.getMaqFecCadaMante().equals("Bimestral")) {
                Calendar cal = Calendar.getInstance();
                cal.setTime(new Date());
                cal.add(Calendar.MONTH, 2);
                nuevaFechaMan = cal.getTime();
                selected.setMaqFechaMantenimiento(nuevaFechaMan);
            } else if (selected.getMaqFecCadaMante().equals("Trimestral")) {
                Calendar cal = Calendar.getInstance();
                cal.setTime(new Date());
                cal.add(Calendar.MONTH, 3);
                nuevaFechaMan = cal.getTime();
                selected.setMaqFechaMantenimiento(nuevaFechaMan);

            } else if (selected.getMaqFecCadaMante().equals("Semestral")) {
                Calendar cal = Calendar.getInstance();
                cal.setTime(new Date());
                cal.add(Calendar.MONTH, 6);
                nuevaFechaMan = cal.getTime();
                selected.setMaqFechaMantenimiento(nuevaFechaMan);
            } else {
                Calendar cal = Calendar.getInstance();
                cal.setTime(new Date());
                cal.add(Calendar.MONTH, 12);
                nuevaFechaMan = cal.getTime();
                selected.setMaqFechaMantenimiento(nuevaFechaMan);
            }
        }
       
    }
        
    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("resource/Bundle").getString("MaquinariaCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

        public void actualizar(){
        items = null;
    }
    public void update() {
        cambiarFechaMan();
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("resource/Bundle").getString("MaquinariaUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("resource/Bundle").getString("MaquinariaDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<Maquinaria> getItems() {
      List<Maquinaria>  lst = new ArrayList<>();
         if (items == null) {
          items = getFacade().findAll();
          Iterator<Maquinaria> it= items.iterator();
            while (it.hasNext()) {
                Maquinaria producto = it.next();
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

    public Maquinaria getMaquinaria(java.lang.Integer id) {
        return getFacade().find(id);
    }

    public List<Maquinaria> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<Maquinaria> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = Maquinaria.class)
    public static class MaquinariaControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            MaquinariaController controller = (MaquinariaController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "maquinariaController");
            return controller.getMaquinaria(getKey(value));
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
            if (object instanceof Maquinaria) {
                Maquinaria o = (Maquinaria) object;
                return getStringKey(o.getMaqId());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Maquinaria.class.getName()});
                return null;
            }
        }

    }

}
