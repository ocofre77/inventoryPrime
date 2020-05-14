package bean;

import entities.AsignarProyecto;
import bean.util.JsfUtil;
import bean.util.JsfUtil.PersistAction;
import clasesAuxiliares.reporteAsignacion;
import entities.Productos;
import entities.Proyecto;
import entities.Responsable;
import sesion.AsignarProyectoFacade;

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

@Named("asignarProyectoController")
@SessionScoped
public class AsignarProyectoController implements Serializable {

    @EJB
    private sesion.AsignarProyectoFacade ejbFacade;
    private List<AsignarProyecto> items = null;
    private AsignarProyecto selected;
    private List<AsignarProyecto> items2;
    private AsignarProyecto asignarproyecto;
    private String FechaSistema;
    private String totalV;
    private String numeroGuia;
    private String numeroGuiaResp;
    private BigDecimal totalProyecto;
    private String numAsigancion;
    
    @EJB
    private sesion.ProyectoFacade ejbFacadeP;
    private Proyecto selectedP;
    private String Transportista;
    private String Observaciones;
    private String gastosAdicionales;
    
    @EJB
    private sesion.ResponsableFacade ejbFacadeRes;
    private Responsable selectedRes;
    
    @EJB
    private sesion.ProductosFacade ejbFacadeR;
    private Productos selectedR;
    private String cantidadProducto;
    private String precioUnitario;
    private int productoSeleccionado;
    private BigDecimal cantidadSeleccionada;
    private List<Productos> Lista;

    public BigDecimal getCantidadSeleccionada() {
        return cantidadSeleccionada;
    }

    public void setCantidadSeleccionada(BigDecimal cantidadSeleccionada) {
        this.cantidadSeleccionada = cantidadSeleccionada;
    }
    
    public String getNumAsigancion() {
        return numAsigancion;
    }

    public void setNumAsigancion(String numAsigancion) {
        this.numAsigancion = numAsigancion;
    }

    public void setItems(List<AsignarProyecto> items) {
        this.items = items;
    }

    public void setItems2(List<AsignarProyecto> items2) {
        this.items2 = items2;
    }

    public void setLista(List<Productos> Lista) {
        this.Lista = Lista;
    }

    public List<Productos> getLista() {
        List<Productos>  lst = new ArrayList<>();
       
          Lista =ejbFacadeR.findAll();
          Iterator<Productos> it= Lista.iterator();
            while (it.hasNext()) {
                Productos producto = it.next();
                if (!producto.getProEstadoEliminar().equals("activo")) {
                    it.remove();
                }
            }
        return Lista;

    }
    
    

    public String getNumeroGuiaResp() {
        return numeroGuiaResp;
    }

    public void setNumeroGuiaResp(String numeroGuiaResp) {
        this.numeroGuiaResp = numeroGuiaResp;
    }
    
    public String getGastosAdicionales() {
        return gastosAdicionales;
    }

    public void setGastosAdicionales(String gastosAdicionales) {
        this.gastosAdicionales = gastosAdicionales;
    }
 
    public String getNumeroGuia() {
        return numeroGuia;
    }

    public void setNumeroGuia(String numeroGuia) {
        this.numeroGuia = numeroGuia;
    }

    public BigDecimal getTotalProyecto() {
        return totalProyecto;
    }

    public void setTotalProyecto(BigDecimal totalProyecto) {
        this.totalProyecto = totalProyecto;
    }

    public AsignarProyecto getAsignarproyecto() {
        return asignarproyecto;
    }

    public void setAsignarproyecto(AsignarProyecto asignarproyecto) {
        this.asignarproyecto = asignarproyecto;
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

    public String getTransportista() {
        return Transportista;
    }

    public void setTransportista(String Transportista) {
        this.Transportista = Transportista;
    }

    public String getObservaciones() {
        return Observaciones;
    }

    public void setObservaciones(String Observaciones) {
        this.Observaciones = Observaciones;
    }

    public Responsable getSelectedRes() {
        return selectedRes;
    }

    public void setSelectedRes(Responsable selectedRes) {
        this.selectedRes = selectedRes;
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
    
    public List<AsignarProyecto> getItems2() {
        return items2;
    }

    public AsignarProyectoController() {
    }

    public AsignarProyecto getSelected() {
        return selected;
    }

    public void setSelected(AsignarProyecto selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private AsignarProyectoFacade getFacade() {
        return ejbFacade;
    }

    public AsignarProyecto prepareCreate() {
        selected = new AsignarProyecto();
        initializeEmbeddableKey();
        return selected;
    }

    @PostConstruct
    public void init() {
        selectedP = new Proyecto();
        selectedR = new Productos();
        selectedRes = new Responsable();
        items2 = new ArrayList<>();
        asignarproyecto = new AsignarProyecto();
        Lista = new ArrayList<>();
    }

    public void agregarDatosProyecto(int codProyecto) {
        try {
            this.selectedP = ejbFacadeP.encontarProyecto(codProyecto);
        } catch (Exception e) {
        }
    }

    public void agregarDatosResponsable(int codResponsable) {
        try {
            this.selectedRes = ejbFacadeRes.encontarResponsable(codResponsable);
        } catch (Exception e) {
        }
    }

    public void pedirCantidadProducto(int codigo,BigDecimal cantidad) {
        this.productoSeleccionado = codigo;
        this.cantidadSeleccionada=cantidad;
        //csntidas
    }
    
     public void agregarDatosProductos(){
        try{
            if(!(this.cantidadProducto.matches("[0-9.0-9]*")) || this.cantidadProducto.equals("0") || this.cantidadProducto.equals("") ){
                 FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"ERROR"," Datos Incorrectos "));
                 this.cantidadProducto = null;
            }else{
                this.selectedR = ejbFacadeR.encontarProductos(this.productoSeleccionado);
                if( Double.parseDouble(this.cantidadProducto)>this.selectedR.getProCantidad().doubleValue() ){
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "INCORRECTO", " No existe Stock suficiente "));
                    this.cantidadProducto = null;
                }else{
                    items2.add(new AsignarProyecto(null, new Date(), this.Transportista, 
                        BigDecimal.valueOf(Double.parseDouble(this.getCantidadProducto())), this.Observaciones, BigDecimal.valueOf(Double.parseDouble(this.cantidadProducto)*(this.selectedR.getProPrecioUni()).doubleValue() ),this.selectedP,
                        this.selectedR,this.selectedRes)  );  
                    this.total();
                    this.cantidadProducto = null;
                }
            }
            
        }catch(NumberFormatException e){   
             FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR", e.getMessage()));
        }
    }
     public void quitarProductoDetalle(int cod, int fila){
         try{
             int i=0;
             for(AsignarProyecto en: this.items2){
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
    
      public void guardarEntrada(){
         try {
             System.out.println("INGRESOOOOOOOOOOOOO: ");
             //Calcula el total del proyecto
             totalProyecto();
             total();
             
             String guia=getNumeroGuia();
             numeroGuiaResp=getNumeroGuia();
             if(guia.equals("") || guia==null){
                   FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Incorrecto", " Debe generar número de guía "));            
             }
             else{
                for (AsignarProyecto en : items2) {
                    if(this.Transportista.equals("")){
                       en.setSalTransportista("NA");
                    }else{
                       en.setSalTransportista(this.Transportista);
                    }
                    en.setSalObservaciones(this.Observaciones);
                    en.setSalNumero(this.numeroGuia);
                    if (this.gastosAdicionales == null || this.gastosAdicionales.equals("")) {
                        //|| this.gastosAdicionales.matches("[a-zA-Z]") || this.gastosAdicionales.matches(">=[0-9]") || this.gastosAdicionales.matches(">=[0-9]!\\.>=[0-9]{2}") ){
                        en.setSalIngresoadicional(new BigDecimal("0"));

                    } else if (this.gastosAdicionales.charAt(0) == '-' || this.gastosAdicionales.matches("[a-zA-Z]")) {
                        System.out.println("eentraaaaaaaaaaaaaaaaa3333333333333333333333");
                        en.setSalIngresoadicional(new BigDecimal("0"));
                    } else {
                        en.setSalIngresoadicional(new BigDecimal(this.gastosAdicionales));
                    }
                    
                    double su = Double.parseDouble(this.totalV) + (en.getSalIngresoadicional().doubleValue() + en.getSalIngresoadicional().doubleValue() * .12);
                    double aux = (su * 25)/28;
                    en.setSalTotal(new BigDecimal(Double.toString(aux)));                    
                    en.setSalTotalIva(new BigDecimal(su));
                                 
                    System.out.println("Total a guardar: "+ this.totalProyecto);
                    en.setSalTotalproyecto(this.totalProyecto);
                    this.ejbFacade.create(en); 

                    Productos pro = this.ejbFacadeR.encontarProductos(en.getProId4().getProId4());
                    pro.setProCantidad(BigDecimal.valueOf(pro.getProCantidad().doubleValue() -en.getSalCantidad().doubleValue()));
                    pro.setProSubPrec(BigDecimal.valueOf(pro.getProCantidad().doubleValue()*pro.getProPrecioUni().doubleValue()));
                    pro.setProTotalIva( BigDecimal.valueOf(pro.getProSubPrec().doubleValue()+pro.getProSubPrec().doubleValue()*0.12));
                    
                    this.ejbFacadeR.edit(pro);

                }
                 List<Productos> ls = ejbFacadeR.findAll();
                 BigDecimal acumulativo = new BigDecimal("0");
                 for (Productos en : ls) {
                     BigDecimal subtotal = en.getProTotalIva();
                     acumulativo = acumulativo.add(subtotal);
                     en.setProTotalPrec(acumulativo);
                     this.ejbFacadeR.edit(en);
                 }
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "CORRECTO", " Productos Asignados con éxito "));
                limpiar();
             }
         } catch (Exception e){
             FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR", "Formato Incorrecto"));
         }
      }
     
    public void limpiar() {
        this.selectedP = new Proyecto();
        this.selectedR = new Productos();
        this.selectedRes = new Responsable();
        this.items2 = new ArrayList<>();
        this.Transportista = "";
        this.Observaciones = "";
        this.totalV= "";
        this.numeroGuia="";
        this.gastosAdicionales = "";
        this.totalProyecto = new BigDecimal("0");
    }
    
    public void total(){
         BigDecimal Total =new BigDecimal("0");
         for(AsignarProyecto en : items2){
             BigDecimal subtotal= en.getProId4().getProPrecioUni().multiply(new BigDecimal(en.getSalCantidad().doubleValue()));
             en.setSalSubtotal(subtotal);
             Total = Total.add(en.getSalSubtotal());
         }
         double aux = Total.doubleValue()+(Total.doubleValue()*0.12);          
         setTotalV(Double.toString((double)Math.round(aux * 100d) / 100d));
     }
    

    
    public void totalProyecto(){
        BigDecimal Total = new BigDecimal(this.getTotalV());
        System.out.println("ID Total: " + this.selectedP.getProyId());
        AsignarProyecto p = ejbFacade.findLastInProyecto(this.selectedP.getProyId());
        if (p == null || p.equals(null)) {
            if (this.gastosAdicionales == null || this.gastosAdicionales.equals("")) {
                this.gastosAdicionales = "0";
            }
            setTotalProyecto(BigDecimal.valueOf(Total.doubleValue()+Double.parseDouble(this.gastosAdicionales) +Double.parseDouble(this.gastosAdicionales)*0.12));
            
        } else {
            System.out.println("Total Proyecto: " + p.getSalTotalproyecto());
            if (this.gastosAdicionales == null || this.gastosAdicionales.equals("")) {
                setTotalProyecto(BigDecimal.valueOf(Total.doubleValue() + p.getSalTotalIva().doubleValue()));
                System.out.println("Total Proyecto Nuevo: " + BigDecimal.valueOf(Total.doubleValue() + p.getSalTotalproyecto().doubleValue()));
            } else {
                this.totalProyecto = (BigDecimal.valueOf(Total.doubleValue() + p.getSalTotalproyecto().doubleValue() + (Double.parseDouble(this.gastosAdicionales) +Double.parseDouble(this.gastosAdicionales)*0.12 )));
                
                System.out.println("Total Proyecto Nuevo: " + BigDecimal.valueOf(Total.doubleValue() + p.getSalTotalproyecto().doubleValue() +(Double.parseDouble(this.gastosAdicionales) +Double.parseDouble(this.gastosAdicionales)*0.12)));
            }
        }
    }
     
     //Metodo para obtener la ultima factura
    public void generarGuia(){         
         try{
             limpiar();
             this.selected = ejbFacade.encontarUltimaGuia();
             if(getSelected() == null || getSelected().equals(null) ){
                 this.numeroGuia="A000001";
             }else{
                 this.numeroGuia=generarClaves(this.selected.getSalNumero());
             }            
         }catch(Exception e){
              FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR", e.getMessage()));
         }
     }
        
     public String generarClaves(String cadena) {
        String clave = "";
        String categoria = cadena.substring(1, 7).toUpperCase();
        System.out.println("Numeroo:" + categoria);
        int numeros = Integer.parseInt(categoria)+1;
        
        String res = "";
        if (numeros >= 10000) {
            res = "0" + numeros;
        }else
        if (numeros >= 1000) {
            res = "00" + numeros;
        }else
        if (numeros >= 100) {
            res = "000" + numeros;
        }else
        if (numeros >= 10) {
            res = "0000" + numeros;
        }else
        if (numeros >= 1) {
            res = "00000" + numeros;
        }
        else{
            res= "NÚMERO EXCEDIDO";
        }            

        clave = "A" + res;

        System.out.println("CLAVEE:" + clave);
        System.out.println(numeros);
        return clave;
    }
     
     
     //metodos pra editar la cantidad del producto en la tabla
     public void onRowEdit(RowEditEvent event) {
        
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "INFORMACIÓN", " Se modificaron campos "));
        this.total();
     }
     
    public void onRowCancel(RowEditEvent event) {
         FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "INFORMACIÓN", "  No se hicieron cambios  "));
    }
   
    public void verReporteDos() throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
          try {       
         if (!selected.getSalNumero().equals("") || !selected.getSalNumero().isEmpty() || selected.getSalNumero()!= null) {
        System.out.println("getSalNumero"+selected.getSalNumero());
        //Instancia hacia la clase reporteProductos        
        reporteAsignacion rFactura = new reporteAsignacion();

        FacesContext facesContext = FacesContext.getCurrentInstance();
        ServletContext servletContext = (ServletContext) facesContext.getExternalContext().getContext();
        String ruta = servletContext.getRealPath("/Reportes/SalidaReporte.jasper");
        rFactura.getReporte(ruta, selected.getSalNumero());
        FacesContext.getCurrentInstance().responseComplete();
        } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Alerta", "Seleccione una salida"));
                
            }
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Seleccione una salida", "Seleccione una salida"));
        }
        
    }
   
    
    
    public void verReporte() throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        guardarEntrada();   
        //Instancia hacia la clase reporteProductos        
        reporteAsignacion rFactura = new reporteAsignacion();

        FacesContext facesContext = FacesContext.getCurrentInstance();
        ServletContext servletContext = (ServletContext) facesContext.getExternalContext().getContext();
        String ruta = servletContext.getRealPath("/Reportes/SalidaReporte.jasper");
        
        rFactura.getReporte(ruta, this.numeroGuiaResp);
        FacesContext.getCurrentInstance().responseComplete();
        this.numeroGuiaResp = "";
    }
   
     public List<Productos> productosActivos() {
        List<Productos>  lst = new ArrayList<>();
        if (Lista == null) {
            Lista =ejbFacadeR.findAll();
          Iterator<Productos> it= Lista.iterator();
            while (it.hasNext()) {
                Productos producto = it.next();
                if (!producto.getProEstadoEliminar().equals("activo")) {
                    it.remove();
                }
            }

        }

        return Lista;
    }
    
    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("resource/Bundle").getString("AsignarProyectoCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }
        public void actualizar(){
        items = null;
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("resource/Bundle").getString("AsignarProyectoUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("resource/Bundle").getString("AsignarProyectoDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<AsignarProyecto> getItems() {
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

    public AsignarProyecto getAsignarProyecto(java.lang.Integer id) {
        return getFacade().find(id);
    }

    public List<AsignarProyecto> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<AsignarProyecto> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = AsignarProyecto.class)
    public static class AsignarProyectoControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            AsignarProyectoController controller = (AsignarProyectoController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "asignarProyectoController");
            return controller.getAsignarProyecto(getKey(value));
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
            if (object instanceof AsignarProyecto) {
                AsignarProyecto o = (AsignarProyecto) object;
                return getStringKey(o.getSalId());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), AsignarProyecto.class.getName()});
                return null;
            }
        }

    }

}
