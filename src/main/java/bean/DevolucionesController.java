package bean;

import entities.Devoluciones;
import bean.util.JsfUtil;
import bean.util.JsfUtil.PersistAction;
import clasesAuxiliares.reporteDevolucionSalida;
import entities.AsignarProyecto;
import entities.Bodega;
import entities.EquipoProteccion;
import entities.Herramienta;
import entities.Maquinaria;
import entities.MaterialAcabado;
import entities.Producto;
import entities.Productos;
import entities.Proyecto;
import entities.Vehiculos;
import sesion.DevolucionesFacade;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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

@Named("devolucionesController")
@SessionScoped
public class DevolucionesController implements Serializable {

  @EJB
    private sesion.DevolucionesFacade ejbFacade;
    private List<Devoluciones> items = null;
    private Devoluciones selected;
    private List<Devoluciones> items2;
    private Devoluciones selectedD;
    private BigDecimal CantidadSeleccionada;
    private int codigo=0;
    
    private String FechaSistema;
    private String totalV;
    private String fact;
    
    @EJB
    private sesion.ProyectoFacade ejbFacadeP;
    private Proyecto selectedP;
    private String Observaciones;
    
    
    @EJB
    private sesion.ProductosFacade ejbFacadeR;
    private Productos selectedR;
    private Productos productos;
    private String cantidadProducto;
    private String precioUnitario;
    private String productoSeleccionado;
    private List<AsignarProyecto>Lista;
    
    @EJB
    private sesion.AsignarProyectoFacade ejbFacadeA;
    private AsignarProyecto selectD;
    
    @EJB
    private sesion.BodegaFacade ejbFacadeB;
    private List<Bodega> lstBodega;
    private Bodega bodega;
    
     //Equipos de Protección
    @EJB
    private sesion.EquipoProteccionFacade ejbFacade4;
    
    //MATERIAL ACABADO
    @EJB
    private sesion.MaterialAcabadoFacade ejbFacade5;

    
    //VEHICULO
    @EJB
    private sesion.VehiculosFacade ejbFacade6;
  
    
    //MAQUINARIA
    @EJB
    private sesion.MaquinariaFacade ejbFacade7;
    
    @EJB 
    private sesion.ProductoFacade ejbFacadePr;
    
    @EJB
    private sesion.HerramientaFacade ejbFacade2;

    public BigDecimal getCantidadSeleccionada() {
        return CantidadSeleccionada;
    }

    public void setCantidadSeleccionada(BigDecimal CantidadSeleccionada) {
        this.CantidadSeleccionada = CantidadSeleccionada;
    }

    
    
    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }
     

    public List<Bodega> getLstBodega() {
         return lstBodega = this.ejbFacadeB.findAll();
    }

    public void setLstBodega(List<Bodega> lstBodega) {
        this.lstBodega = lstBodega;
    }

    public Bodega getBodega() {
        return bodega;
    }

    public void setBodega(Bodega bodega) {
        this.bodega = bodega;
    }

    public List<AsignarProyecto> getLista() {
        return Lista = ejbFacadeA.findAll();
    }

    public void setLista(List<AsignarProyecto> Lista) {
        this.Lista = Lista;
    }
    
    public Productos getProductos() {
        return productos;
    }

    public void setProductos(Productos productos) {
        this.productos = productos;
    }

    
    
    public List<Devoluciones> getItems2() {
        return items2;
    }

    public void setItems2(List<Devoluciones> items2) {
        this.items2 = items2;
    }

    public Devoluciones getSelectedD() {
        return selectedD;
    }

    public void setSelectedD(Devoluciones selectedD) {
        this.selectedD = selectedD;
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

    public String getFact() {
        return fact;
    }

    public void setFact(String fact) {
        this.fact = fact;
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

    public String getProductoSeleccionado() {
        return productoSeleccionado;
    }

    public void setProductoSeleccionado(String productoSeleccionado) {
        this.productoSeleccionado = productoSeleccionado;
    }

 

    public AsignarProyecto getSelectD() {
        return selectD;
    }

    public void setSelectD(AsignarProyecto selectD) {
        this.selectD = selectD;
    }

    public DevolucionesController() {
    }

    public Devoluciones getSelected() {
        return selected;
    }

    public void setSelected(Devoluciones selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private DevolucionesFacade getFacade() {
        return ejbFacade;
    }

    public Devoluciones prepareCreate() {
        selected = new Devoluciones();
        initializeEmbeddableKey();
        return selected;
    }

    @PostConstruct
    public void init() {
        selectedP = new Proyecto();
        selectedR = new Productos();
        items2 = new ArrayList<>();
        selectedD = new Devoluciones();
        Lista = new ArrayList<>();
        lstBodega = new ArrayList<>();
        bodega=new Bodega();
    } 
    
    public void agregarDatosProyecto(int codProyecto) {
        try {
            System.out.println(".jgyyhhh "+ codProyecto);
            this.selectedP = ejbFacadeP.encontarProyecto(codProyecto);
        } catch (Exception e) {
        }
    }
 
    public void pedirCantidadProducto(String codigo, Productos productos,BigDecimal cantidad){
          System.out.println(".jgyyhhh "+ codigo);
        this.productoSeleccionado=codigo;
        this.productos = productos;
        this.CantidadSeleccionada=cantidad;
    }
    
     public void agregarDatosProductos(){
        try{
            if(!(this.cantidadProducto.matches("[0-9.0-9]*")) || this.cantidadProducto.equals("0") || this.cantidadProducto.equals("") ){
                 FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"ERROR"," Datos Incorrectos "));
                 this.cantidadProducto = null;
            }else {

                System.out.println(".probarrr  " + this.productoSeleccionado);
                this.selectD = this.ejbFacadeA.encontarFactura(this.productoSeleccionado, this.productos);
                System.out.println(".Errorr " + this.selectD.getSalNumero() + "   ");
                if ( (new BigDecimal(this.cantidadProducto)).doubleValue() > this.selectD.getSalCantidad().doubleValue()) {
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "INCORRECTO", " Cantidad Excedida "));
                    this.cantidadProducto = null;
                } else {
                    BigDecimal subTotal = new BigDecimal(Double.parseDouble(this.cantidadProducto) * (this.productos.getProPrecioUni()).doubleValue()).setScale(2, BigDecimal.ROUND_HALF_EVEN);
                    //subTotal.setScale(2, BigDecimal.ROUND_HALF_EVEN);
                    items2.add(new Devoluciones(null, new Date(), 
                            BigDecimal.valueOf( Double.parseDouble( this.getCantidadProducto())),
                            subTotal,
                            this.Observaciones, this.selectD.getSalNumero(), this.selectD));                    
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
             for(Devoluciones en: this.items2){
                 if(en.getSalId().getProId4().getProId4()==cod && fila==i){
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
             System.out.println("INGRESOOOOOOOOOOOOO: ");
             for (Devoluciones en : items2) {
                 en.setDevFecha(new Date());
                 en.setDevRazon(this.Observaciones);
                 double aux = (Double.parseDouble(this.totalV) * 25)/28;
                 en.setDevTotal(new BigDecimal(Double.toString(aux)));
                 en.setDevTotalIva(new BigDecimal(this.totalV));
                 en.setDevNumero(this.selectD.getSalNumero());
                 this.ejbFacade.create(en);
                
                 System.out.println("Codigo producto" + en.getSalId().getProId4().getProId4());
                 
                 
                 Productos pro = this.ejbFacadeR.encontarProductos(en.getSalId().getProId4().getProId4());
                 
                 if (this.bodega.getBdNombre().equals(pro.getBdId().getBdNombre())) {
                         pro.setProCantidad(new BigDecimal(pro.getProCantidad().doubleValue() +en.getDevCantidad().doubleValue()));
                         pro.setProSubPrec(BigDecimal.valueOf(pro.getProCantidad().doubleValue() * pro.getProPrecioUni().doubleValue()));
                         pro.setProTotalIva(BigDecimal.valueOf(pro.getProSubPrec().doubleValue() + pro.getProSubPrec().doubleValue() * 0.12));
                         this.ejbFacadeR.edit(pro);
                  } else {      
                         Boolean bandera = true;
                         List<Productos> lstProductos=this.ejbFacadeR.encontrarBodegas(en.getSalId().getProId4().getProCodigopro());
                         for(int i=0;i<lstProductos.size();i++)
                         {
                            if(this.bodega.getBdNombre().equals(lstProductos.get(i).getBdId().getBdNombre()))
                            {           
                                Productos pro2 = lstProductos.get(i);
                                bandera =  false;
                                pro2.setProCantidad(new BigDecimal(en.getDevCantidad().doubleValue() + pro2.getProCantidad().doubleValue()));
                
                                pro2.setProSubPrec(BigDecimal.valueOf(pro2.getProCantidad().doubleValue() * pro2.getProPrecioUni().doubleValue()));
                                pro2.setProTotalIva(BigDecimal.valueOf(pro2.getProSubPrec().doubleValue() + pro2.getProSubPrec().doubleValue() * 0.12));
                                this.ejbFacadeR.edit(pro2);

                                if (pro.getProCategoria().equals("Producto")) {
                                    Producto p = this.ejbFacadePr.encontrarProductoEspecifico(pro2.getProId4());
                                    p.setProdFechaIng(new Date());
                                    this.ejbFacadePr.edit(p);
                                }
                            }
                         }
                         if(bandera==true){
                         
                         //Crear
                                                 codigo = pro.getProId4();
                         pro.setProId4(null);
                         pro.setProCantidad(en.getDevCantidad());
                         pro.setProSubPrec(BigDecimal.valueOf(pro.getProCantidad().doubleValue() * pro.getProPrecioUni().doubleValue()));
                         pro.setProTotalIva(BigDecimal.valueOf(pro.getProSubPrec().doubleValue() + pro.getProSubPrec().doubleValue() * 0.12));
                         pro.setBdId(bodega);
                         this.ejbFacadeR.create(pro);
                         Productos pro1 = this.ejbFacadeR.Obtenerobj();
                         
                          System.out.println("Listo para la creacion de especificos: "+ pro1.getProId4());
                         if (pro1.getProCategoria().equals("Producto")) {
                             System.out.println("Entro productos: "+ codigo);
                             Producto p = this.ejbFacadePr.encontrarProductoEspecifico(codigo);
                             p.setProdId(pro1.getProId4());
                             p.setProId4(pro1);
                             p.setProdFechaIng(new Date());
                             System.out.println("Ver productos: "+ p.getProdPeriodoCadu());
                             this.ejbFacadePr.create(p);
                         }
                         if (pro1.getProCategoria().equals("Herramientas")) {
                             System.out.println("Entro Herramienta "+ codigo);
                             Herramienta h = this.ejbFacade2.encontrarHerramientaEspecifica(codigo);
                             h.setProId4(pro1);
                             h.setHerId(pro1.getProId4());
                             System.out.println("Fecha cadaM Herramienta "+ h.getHerFecCadaMante());
                             this.ejbFacade2.create(h);
                         }

                         if (pro1.getProCategoria().equals("Equipos de Protección")) {
                             System.out.println("Entro proteccion: " + codigo);
                             EquipoProteccion ep = this.ejbFacade4.encontrarEquipoProteccionEspecifica(codigo);
                             ep.setProId4(pro1);
                             ep.setProId2(pro1.getProId4());
                             System.out.println("Equipo Herramienta "+ ep.getEquiMaterial());
                             this.ejbFacade4.create(ep);
                         }
                         if (pro1.getProCategoria().equals("Material de acabados")) {
                             System.out.println("Material de acabado "+ codigo);
                             MaterialAcabado ma = this.ejbFacade5.encontrarMaterialAcabadoEspecifica(codigo);
                             ma.setProId4(pro1);
                             ma.setProId3(pro1.getProId4());
                             System.out.println("Color Material "+ ma.getMatColor());
                             this.ejbFacade5.create(ma);
                         }
                         if (pro1.getProCategoria().equals("Vehículos")) {
                             System.out.println("Entro Vehiculos "+ codigo);
                             Vehiculos ve = this.ejbFacade6.encontrarVehiculosEspecifica(codigo);
                             System.out.println(" Vehiculo "+ ve.getVehPlaca());
                             ve.setProId4(pro1);
                             ve.setVehId(pro1.getProId4());
                             this.ejbFacade6.create(ve);
                         }
                         if (pro1.getProCategoria().equals("Maquinaria")) {
                             System.out.println("Entro maquinaria: "+ codigo);
                             Maquinaria m = this.ejbFacade7.encontrarMaquinariaEspecifica(codigo);
                             m.setProId4(pro1);
                             m.setMaqId(pro1.getProId4());
                             System.out.println("Razón: "+ m.getMaqRazonMante());
                             this.ejbFacade7.create(m);
                         }
                         
                         } 
                  
                  
                  }
                 
                 
                 AsignarProyecto asigpro = en.getSalId();
                 asigpro.setSalCantidad(new BigDecimal(asigpro.getSalCantidad().doubleValue()-en.getDevCantidad().doubleValue()));
                 asigpro.setSalSubtotal(BigDecimal.valueOf(asigpro.getSalCantidad().doubleValue() * asigpro.getProId4().getProPrecioUni().doubleValue()));
                 System.out.println("Subtotal D antes"+asigpro.getSalSubtotal());
                 
                
                    
                 this.ejbFacadeA.edit(asigpro);
             }      
             System.out.println("NUm guia"+productoSeleccionado);
             List<AsignarProyecto> lsA=ejbFacadeA.encontarFactura2(productoSeleccionado);
            
             BigDecimal acumulativo = new BigDecimal("0");
             BigDecimal acumulativo2 = new BigDecimal("0");
                 for (AsignarProyecto en : lsA) {
                     
                     en.setSalTotal(en.getSalSubtotal());
                     System.out.println("Subtotal D"+en.getSalSubtotal());
                     BigDecimal subtotal1 = en.getSalTotal();
                     acumulativo = acumulativo.add(subtotal1);
                     en.setSalTotal(acumulativo);
                     System.out.println("Total D"+en.getSalTotal());
                     BigDecimal subtotal2 = BigDecimal.valueOf(en.getSalTotal().doubleValue() +en.getSalTotal().doubleValue()*0.12 );
                     acumulativo2 = acumulativo2.add(subtotal2);
                     en.setSalTotalIva(acumulativo2);
                     en.setSalTotalproyecto(BigDecimal.valueOf(en.getSalTotal().doubleValue()+en.getSalIngresoadicional().doubleValue() + (en.getSalTotal().doubleValue()+en.getSalIngresoadicional().doubleValue())*0.12 ));
                     System.out.println("Total con iva D"+en.getSalTotalIva());
                     this.ejbFacadeA.edit(en);
                 }
            //Para total proyecto
             List<AsignarProyecto> lsTP=ejbFacadeA.encontarProyecto(lsA.get(0).getProyId().getProyNombre());
             BigDecimal acumulativo1 = new BigDecimal("0");
                 for (AsignarProyecto en : lsTP) {
                     BigDecimal subtotal = en.getSalTotalproyecto();
                     acumulativo1 = acumulativo1.add(subtotal);
                     en.setSalTotalproyecto(acumulativo1);
                     this.ejbFacadeA.edit(en);
                 }
             List<Productos> ls = ejbFacadeR.findAll();
             BigDecimal acumulativo3 = new BigDecimal("0");
             for (Productos en : ls) {
                 BigDecimal subtotal = en.getProTotalIva();
                 acumulativo3 = acumulativo3.add(subtotal);
                 en.setProTotalPrec(acumulativo3);
                 this.ejbFacadeR.edit(en);
             } 
                 
                 
                 
             FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "CORRECTO", " Producto Ingresados "));
             limpiar();
         } catch (Exception e){
             FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR", e.getMessage()));
         }
      }
     
    public void limpiar() {
        this.selectedP = new Proyecto();
        this.selectedR = new Productos();
        this.items2 = new ArrayList<>();
        this.fact = "";
        this.Observaciones = "";
        this.cantidadProducto="";
        this.precioUnitario="";
        this.totalV= "";
       
    }
    
    public void total(){
        BigDecimal Total =new BigDecimal("0");
        for(Devoluciones en : items2){

            BigDecimal subtotal= en.getSalId().getProId4().getProPrecioUni().multiply(new BigDecimal(en.getDevCantidad().doubleValue()));
            en.setDevSubtotal(subtotal);
            Total = Total.add(en.getDevSubtotal());
        }
        double aux = Total.doubleValue()+(Total.doubleValue()*0.12); 
        setTotalV(Double.toString((double)Math.round(aux * 100d) / 100d));
     }
     
    //metodos pra editar la cantidad del producto en la tabla
    public void onRowEdit(RowEditEvent event) {
        this.total();
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "INFORMACIÓN", " Se modificaron campos "));
    }
     
    public void onRowCancel(RowEditEvent event) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "INFORMACIÓN", "  No se hicieron cambios  "));
    }
    
        public void verReporteDos() throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
          try {       
         if (!selected.getDevNumero().equals("") || !selected.getDevNumero().isEmpty() || selected.getDevNumero()!= null) {
    
        //Instancia hacia la clase reporteProductos        
        reporteDevolucionSalida rFactura = new reporteDevolucionSalida();

        FacesContext facesContext = FacesContext.getCurrentInstance();
        ServletContext servletContext = (ServletContext) facesContext.getExternalContext().getContext();
        String ruta = servletContext.getRealPath("/Reportes/DevolucionSalida.jasper");
        rFactura.getReporte(ruta, selected.getDevNumero());
        FacesContext.getCurrentInstance().responseComplete();
        } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Alerta", "Seleccione una salida"));
                
            }
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Seleccione una salida", "Seleccione una salida"));
        }
        
    }
   
    public void verReporte() throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        guardarCotizacion();   
        //Instancia hacia la clase reporteProductos        
        reporteDevolucionSalida rFactura = new reporteDevolucionSalida();

        FacesContext facesContext = FacesContext.getCurrentInstance();
        ServletContext servletContext = (ServletContext) facesContext.getExternalContext().getContext();
        String ruta = servletContext.getRealPath("/Reportes/DevolucionSalida.jasper");
        
        rFactura.getReporte(ruta, selectD.getSalNumero());
        FacesContext.getCurrentInstance().responseComplete();
       
    }
        
    
    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("resource/Bundle").getString("DevolucionesCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }
    
     public void actualizar(){
        items = null;
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("resource/Bundle").getString("DevolucionesUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("resource/Bundle").getString("DevolucionesDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<Devoluciones> getItems() {
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

    public Devoluciones getDevoluciones(java.lang.Integer id) {
        return getFacade().find(id);
    }

    public List<Devoluciones> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<Devoluciones> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = Devoluciones.class)
    public static class DevolucionesControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            DevolucionesController controller = (DevolucionesController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "devolucionesController");
            return controller.getDevoluciones(getKey(value));
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
            if (object instanceof Devoluciones) {
                Devoluciones o = (Devoluciones) object;
                return getStringKey(o.getDevId());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Devoluciones.class.getName()});
                return null;
            }
        }

    }

}
