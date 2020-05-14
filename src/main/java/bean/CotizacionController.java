package bean;

import entities.Cotizacion;
import bean.util.JsfUtil;
import bean.util.JsfUtil.PersistAction;
import clasesAuxiliares.reporteConcilacion;
import entities.Productos;
import entities.Proyecto;
import sesion.CotizacionFacade;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
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
import org.primefaces.event.RowEditEvent;

@Named("cotizacionController")
@SessionScoped
public class CotizacionController implements Serializable {

 @EJB
    private sesion.CotizacionFacade ejbFacade;
    private List<Cotizacion> items = null;
    private Cotizacion selected;
    private List<Cotizacion> items2;
    private Cotizacion selectedC;
    
    private String FechaSistema;
    private String totalV;
    private String numeroGuia;
    private String numeroGuiaResp;
    
    @EJB
    private sesion.ProyectoFacade ejbFacadeP;
    private Proyecto selectedP;
    private String Observaciones;
    
    
    @EJB
    private sesion.ProductosFacade ejbFacadeR;
    private Productos selectedR;
    private String cantidadProducto;
    private String precioUnitario;
    private int productoSeleccionado;
    private List<Productos>Lista;

    public List<Productos> getLista() {
        List<Productos> lst = new ArrayList<>();
        Lista = ejbFacadeR.findAll();
        Iterator<Productos> it = Lista.iterator();
        while (it.hasNext()) {
            Productos producto = it.next();
            if (!producto.getProEstadoEliminar().equals("activo")) {
                it.remove();
            }
        }
        return Lista;
    }

    public void setLista(List<Productos> Lista) {
        this.Lista = Lista;
    }
    
    

    public String getNumeroGuia() {
        return numeroGuia;
    }

    public String getNumeroGuiaResp() {
        return numeroGuiaResp;
    }

    public void setNumeroGuiaResp(String numeroGuiaResp) {
        this.numeroGuiaResp = numeroGuiaResp;
    }
    

    public void setNumeroGuia(String numeroGuia) {
        this.numeroGuia = numeroGuia;
    }


    public List<Cotizacion> getItems2() {
        return items2;
    }

    public void setItems2(List<Cotizacion> items2) {
        this.items2 = items2;
    }

    public Cotizacion getSelectedC() {
        return selectedC;
    }

    public void setSelectedC(Cotizacion selectedC) {
        this.selectedC = selectedC;
    }

    public String getFechaSistema() {
        Date date = new Date();
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        return dateFormat.format(date);
    }

    public void setFechaSistema(String FechaSistema) {
        this.FechaSistema = FechaSistema;
    }

    public String getTotalV() {
        return totalV;
    }

    public void setTotalV(String totalV) {
        this.totalV = totalV;
    }



    public Proyecto getSelectedP() {
        return selectedP;
    }

    public void setSelectedP(Proyecto selectedP) {
        this.selectedP = selectedP;
    }

    public String getObservaciones() {
        return Observaciones;
    }

    public void setObservaciones(String Observaciones) {
        this.Observaciones = Observaciones;
    }

    public Productos getSelectedR() {
        return selectedR;
    }

    public void setSelectedR(Productos selectedR) {
        this.selectedR = selectedR;
    }

    public String getCantidadProducto() {
        return cantidadProducto;
    }

    public void setCantidadProducto(String cantidadProducto) {
        this.cantidadProducto = cantidadProducto;
    }

    public String getPrecioUnitario() {
        return precioUnitario;
    }

    public void setPrecioUnitario(String precioUnitario) {
        this.precioUnitario = precioUnitario;
    }

    public int getProductoSeleccionado() {
        return productoSeleccionado;
    }

    public void setProductoSeleccionado(int productoSeleccionado) {
        this.productoSeleccionado = productoSeleccionado;
    }

    public CotizacionController() {
    }

    public Cotizacion getSelected() {
        return selected;
    }

    public void setSelected(Cotizacion selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private CotizacionFacade getFacade() {
        return ejbFacade;
    }

    public Cotizacion prepareCreate() {
        selected = new Cotizacion();
        initializeEmbeddableKey();
        return selected;
    }

     @PostConstruct
    public void init() {
        selectedP = new Proyecto();
        selectedR = new Productos();
        items2 = new ArrayList<>();
        selectedC = new Cotizacion();
        Lista = new ArrayList<>();
    } 
    
    public void agregarDatosProyecto(int codProyecto) {
        try {
            this.selectedP = ejbFacadeP.encontarProyecto(codProyecto);
        } catch (Exception e) {
        }
    }
 
      public void pedirCantidadProducto(int codigo){
        this.productoSeleccionado=codigo;
    }
    
     public void agregarDatosProductos(){
        try{
            if(!(this.cantidadProducto.matches("[0-9]*")) || this.cantidadProducto.equals("0") || this.cantidadProducto.equals("") ){
                 FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"ERROR"," Datos Incorrectos "));
                 this.cantidadProducto = null;
                 this.precioUnitario=null;
            }else{
                this.selectedR = ejbFacadeR.encontarProductos(this.productoSeleccionado);
                items2.add( new Cotizacion(null,new Date(),new BigDecimal(this.precioUnitario),Integer.parseInt(this.getCantidadProducto()),BigDecimal.valueOf(Double.parseDouble(this.cantidadProducto)*(this.selectedR.getProPrecioUni()).doubleValue() ),this.selectedR,this.selectedP )  ); 
                this.total();
                this.cantidadProducto = null;
                this.precioUnitario=null;
            }
            
        }catch(NumberFormatException e){   
             FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR", e.getMessage()));
        }
    }
     public void quitarProductoDetalle(int cod, int fila){
         try{
             int i=0;
             for(Cotizacion en: this.items2){
                 if(en.getProId4().getProId4()==cod && fila==i){
                     this.items2.remove(i); 
                     break;
                 }
                 i++;
             }
             FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "CORRECTO", " Producto Eliminado de la Lista "));
             //ACTUALIZAR EL TOTAL
             this.total();
         }catch(Exception e){
             FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR", e.getMessage()));
         }
     }
    
      public void guardarCotizacion(){
          try {
              if (this.numeroGuia.equals("") || this.numeroGuia == null) {
                  numeroGuiaResp=this.numeroGuia;
                  FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "INCORRECTO", " Debe generar número de guía "));
              } else {
                  System.out.println("INGRESOOOOOOOOOOOOO: ");
                  for (Cotizacion en : items2) {
                      
                      en.setCotNumero(this.numeroGuia);
                      numeroGuiaResp=this.numeroGuia;
                      double aux = (Double.parseDouble(this.totalV) * 25)/28;
                      en.setCotTotal(new BigDecimal(Double.toString(aux)));
                      en.setCotTotalIva(new BigDecimal(this.totalV));
                      this.ejbFacade.create(en);

                  }
                  FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "CORRECTO", " Producto Ingresados "));
                  limpiar();
              }
         } catch (Exception e){
             FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR", e.getMessage()));
         }
      }
     
    public void limpiar() {
        this.selectedP = new Proyecto();
        this.selectedR = new Productos();
        this.items2 = new ArrayList<>();
        this.Observaciones = "";
        this.totalV= "";
        this.numeroGuia = "";
        this.precioUnitario="";
    }
    
     public void total(){
         BigDecimal Total =new BigDecimal("0");
         for(Cotizacion en : items2){
             BigDecimal subtotal= en.getCotPreUni().multiply(new BigDecimal(en.getCotCantidad()));
             en.setCotSubtotal(subtotal);
             Total = Total.add(en.getCotSubtotal());
         }
         double aux = Total.doubleValue()+(Total.doubleValue()*0.12); 
         setTotalV(Double.toString((double)Math.round(aux * 100d) / 100d));
     }
     
    public void generarGuia(){         
         try{
             limpiar();
             this.selected = ejbFacade.encontarUltimaGuia();
             if(getSelected() == null || getSelected().equals(null) ){
                 this.numeroGuia="C000001";
             }else{
                 this.numeroGuia=generarClaves(this.selected.getCotNumero());
             }            
         }catch(Exception e){
              FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR", e.getMessage()));
         }
     } 
     
    public String generarClaves(String cadena) {
        String clave = "";
        String categoria = cadena.substring(1, 7).toUpperCase();
        System.out.println("Numeroo:" + categoria);
        int numeros = Integer.parseInt(categoria) + 1;

        String res = "";
        if (numeros >= 10000) {
            res = "0" + numeros;
        } else if (numeros >= 1000) {
            res = "00" + numeros;
        } else if (numeros >= 100) {
            res = "000" + numeros;
        } else if (numeros >= 10) {
            res = "0000" + numeros;
        } else if (numeros >= 1) {
            res = "00000" + numeros;
        } else {
            res = "NÚMERO EXCEDIDO";
        }

        clave = "C" + res;

        System.out.println("CLAVEE:" + clave);
        System.out.println(numeros);
        return clave;
    }
     
     //metodos pra editar la cantidad del producto en la tabla
     public void onRowEdit(RowEditEvent event) {
         total();
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "INFORMACIÓN", " Se modificaron campos "));
    }
     
    public void onRowCancel(RowEditEvent event) {
         FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "INFORMACIÓN", "  No se hicieron cambios  "));
    }
    
        public void verReporteDos() throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
  try {
       
            if (!selected.getCotNumero().equals("") || !selected.getCotNumero().isEmpty() || selected.getCotNumero() != null) {       
        reporteConcilacion rFactura = new reporteConcilacion();

        FacesContext facesContext = FacesContext.getCurrentInstance();
        ServletContext servletContext = (ServletContext) facesContext.getExternalContext().getContext();
        String ruta = servletContext.getRealPath("/Reportes/CotizacionREporte.jasper");
        rFactura.getReporte(ruta, selected.getCotNumero());
        FacesContext.getCurrentInstance().responseComplete();
         } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Alerta", "Seleccione un producto"));
                
            }
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Seleccione un producto", "Seleccione un producto"));
        }

    }
    
       public void verReporte() throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        guardarCotizacion();   
        //Instancia hacia la clase reporteProductos        
        reporteConcilacion rFactura = new reporteConcilacion();

        FacesContext facesContext = FacesContext.getCurrentInstance();
        ServletContext servletContext = (ServletContext) facesContext.getExternalContext().getContext();
        String ruta = servletContext.getRealPath("/Reportes/CotizacionREporte.jasper");
        rFactura.getReporte(ruta, this.numeroGuiaResp);
        FacesContext.getCurrentInstance().responseComplete();
        this.numeroGuiaResp="";
    }
    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("resource/Bundle").getString("CotizacionCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }
        public void actualizar(){
        items = null;
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("resource/Bundle").getString("CotizacionUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("resource/Bundle").getString("CotizacionDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<Cotizacion> getItems() {
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

    public Cotizacion getCotizacion(java.lang.Integer id) {
        return getFacade().find(id);
    }

    public List<Cotizacion> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<Cotizacion> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = Cotizacion.class)
    public static class CotizacionControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            CotizacionController controller = (CotizacionController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "cotizacionController");
            return controller.getCotizacion(getKey(value));
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
            if (object instanceof Cotizacion) {
                Cotizacion o = (Cotizacion) object;
                return getStringKey(o.getCotId());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Cotizacion.class.getName()});
                return null;
            }
        }

    }

}
