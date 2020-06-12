package bean;

import entities.DevolucionesEntradas;
import bean.util.JsfUtil;
import bean.util.JsfUtil.PersistAction;
import clasesAuxiliares.reporteDevolucionEntrada;
import entities.Entradas;
import entities.Productos;
import entities.Proveedor;
import sesion.DevolucionesEntradasFacade;

import java.io.Serializable;
import java.math.BigDecimal;
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

@Named("devolucionesEntradasController")
@SessionScoped
public class DevolucionesEntradasController implements Serializable {

    @EJB
    private sesion.DevolucionesEntradasFacade ejbFacade;
    private List<DevolucionesEntradas> items = null;
    private DevolucionesEntradas selected;
    private List<DevolucionesEntradas> items2;
    private DevolucionesEntradas deVentrada;
    private String FechaSistema;
    private String totalV;
    private String numeroGuia;

    @EJB
    private sesion.ProveedorFacade ejbFacadeP;
    private Proveedor selectedP;
    private String Ruc;
    private String Transportista;
    private String LugarLlagada;

    @EJB
    private sesion.ProductosFacade ejbFacadeR;
    private Productos selectedR;
    private Productos productos;
    private String Serial;
    private String cantidadProducto;
    private String productoSeleccionado;
    private int cantidadSeleccionada;

    @EJB
    private sesion.EntradasFacade ejbFacadeA;
    private Entradas selectD;
    private String observaciones;
    private List<Entradas> Lista;

    public int getCantidadSeleccionada() {
        return cantidadSeleccionada;
    }

    public void setCantidadSeleccionada(int cantidadSeleccionada) {
        this.cantidadSeleccionada = cantidadSeleccionada;
    }

    public List<Entradas> getLista() {
        return Lista = ejbFacadeA.findAll();
    }

    public void setLista(List<Entradas> Lista) {
        this.Lista = Lista;
    }

    public List<DevolucionesEntradas> getItems2() {
        return items2;
    }

    public void setItems2(List<DevolucionesEntradas> items2) {
        this.items2 = items2;
    }

    public DevolucionesEntradas getDeVentrada() {
        return deVentrada;
    }

    public void setDeVentrada(DevolucionesEntradas deVentrada) {
        this.deVentrada = deVentrada;
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

    public String getNumeroGuia() {
        return numeroGuia;
    }

    public void setNumeroGuia(String numeroGuia) {
        this.numeroGuia = numeroGuia;
    }

    public Proveedor getSelectedP() {
        return selectedP;
    }

    public void setSelectedP(Proveedor selectedP) {
        this.selectedP = selectedP;
    }

    public String getRuc() {
        return Ruc;
    }

    public void setRuc(String Ruc) {
        this.Ruc = Ruc;
    }

    public String getTransportista() {
        return Transportista;
    }

    public void setTransportista(String Transportista) {
        this.Transportista = Transportista;
    }

    public String getLugarLlagada() {
        return LugarLlagada;
    }

    public void setLugarLlagada(String LugarLlagada) {
        this.LugarLlagada = LugarLlagada;
    }

    public Productos getSelectedR() {
        return selectedR;
    }

    public void setSelectedR(Productos selectedR) {
        this.selectedR = selectedR;
    }

    public Productos getProductos() {
        return productos;
    }

    public void setProductos(Productos productos) {
        this.productos = productos;
    }

    public String getSerial() {
        return Serial;
    }

    public void setSerial(String Serial) {
        this.Serial = Serial;
    }

    public String getCantidadProducto() {
        return cantidadProducto;
    }

    public void setCantidadProducto(String cantidadProducto) {
        this.cantidadProducto = cantidadProducto;
    }

    public String getProductoSeleccionado() {
        return productoSeleccionado;
    }

    public void setProductoSeleccionado(String productoSeleccionado) {
        this.productoSeleccionado = productoSeleccionado;
    }

    public Entradas getSelectD() {
        return selectD;
    }

    public void setSelectD(Entradas selectD) {
        this.selectD = selectD;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public DevolucionesEntradasController() {
    }

    public DevolucionesEntradas getSelected() {
        return selected;
    }

    public void setSelected(DevolucionesEntradas selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private DevolucionesEntradasFacade getFacade() {
        return ejbFacade;
    }

    public DevolucionesEntradas prepareCreate() {
        selected = new DevolucionesEntradas();
        initializeEmbeddableKey();
        return selected;
    }

    @PostConstruct
    public void init() {
        selectedP = new Proveedor();
        selectedR = new Productos();
        items2 = new ArrayList<>();
        Lista = new ArrayList<>();
        deVentrada = new DevolucionesEntradas();
    }

    public void pedirCantidadProducto(String codigo, Productos productos, int cantidad) {
        System.out.println(".jgyyhhh " + codigo);
        this.productoSeleccionado = codigo;
        this.productos = productos;
        this.cantidadSeleccionada = cantidad;
    }

    public void agregarDatosProductos() {
        try {
            if (!(this.cantidadProducto.matches("[0-9]*")) || this.cantidadProducto.equals("0") || this.cantidadProducto.equals("")) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR", " Datos Incorrectos "));
                this.cantidadProducto = null;
            } else {

                System.out.println(".probarrr  " + this.productoSeleccionado);
                this.selectD = this.ejbFacadeA.encontarFactura(this.productoSeleccionado, this.productos);
                System.out.println(".Errorr " + this.selectD.getEntNumero() + "   ");
                if (Double.parseDouble(this.cantidadProducto) > this.selectD.getEntCantidad().doubleValue()) {
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "INCORRECTO", " Cantidad Excedida "));
                    this.cantidadProducto = null;
                } else {
                    items2.add(new DevolucionesEntradas(null, new Date(), Integer.parseInt(this.getCantidadProducto()),
                            this.selectD.getEntNumero(), this.selectD.getEntPrecioUni(),
                            BigDecimal.valueOf(Double.parseDouble(this.cantidadProducto) * (this.productos.getProPrecioUni()).doubleValue()),
                            this.selectD));
                    this.total();
                    this.cantidadProducto = null;
                }
            }

        } catch (Exception e) {
        }
    }

    public void quitarProductoDetalle(int cod, int fila) {
        try {
            int i = 0;
            for (DevolucionesEntradas en : this.items2) {
                if (en.getEntId().getProId4().getProId4() == cod && fila == i) {
                    this.items2.remove(i);
                    break;
                }
                i++;
            }
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "CORRECTO", " Producto Eliminado de la Lista "));
            //ACTUALIZAR EL TOTAL
            this.total();
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR", e.getMessage()));
        }
    }

    public void total() {
        BigDecimal Total = new BigDecimal("0");
        for (DevolucionesEntradas en : items2) {
            BigDecimal subtotal = en.getDevePUnitario().multiply(new BigDecimal(en.getDeveCantidad()));
            en.setDeveSubtotal(subtotal);
            Total = Total.add(subtotal);
        }
        double aux = Total.doubleValue() + (Total.doubleValue() * 0.12);
        setTotalV(Double.toString((double) Math.round(aux * 100d) / 100d));
    }

    /**
     * Guardar Devolución de Entrada
     */
    public void guardarEntrada() {
        try {
            System.out.println("INGRESOOOOOOOOOOOOO: ");

            for (DevolucionesEntradas devEntrada : items2) {
                devEntrada.setDeveTotal(new BigDecimal(this.totalV));
                devEntrada.setDeveRazon(this.observaciones);
                this.ejbFacade.create(devEntrada);
                
                Productos pro = this.ejbFacadeR.encontarProductos2(devEntrada.getEntId().getEntCodigo());
                pro.setProCantidad(BigDecimal.valueOf(pro.getProCantidad().doubleValue() - devEntrada.getDeveCantidad().doubleValue()));
                pro.setProPrecioUni(devEntrada.getDevePUnitario());
                pro.setProSubPrec(BigDecimal.valueOf(pro.getProCantidad().doubleValue() * pro.getProPrecioUni().doubleValue()));
                pro.setProTotalIva(BigDecimal.valueOf(pro.getProSubPrec().doubleValue() + pro.getProSubPrec().doubleValue() * 0.12));
                this.ejbFacadeR.edit(pro);
                
                Entradas entrada = devEntrada.getEntId();
                entrada.setEntCantidad(BigDecimal.valueOf(entrada.getEntCantidad().doubleValue() - devEntrada.getDeveCantidad().doubleValue()));
                entrada.setEntSubtotal(BigDecimal.valueOf(entrada.getEntSubtotal().doubleValue() - entrada.getEntCantidad().doubleValue() * entrada.getEntPrecioUni().doubleValue()));
                entrada.setEntTotal(entrada.getEntSubtotal());
                entrada.setEntTotaliva(BigDecimal.valueOf(entrada.getEntSubtotal().doubleValue() + entrada.getEntSubtotal().doubleValue() * 0.12));
                this.ejbFacadeA.edit(entrada);

//                 Entradas entrada2 = en.getEntId();
//                 entrada2.setEntCantidad(entrada2.getEntCantidad()-en.getDeveCantidad());
//                 entrada2.setEntSubtotal(BigDecimal.valueOf(entrada2.getEntCantidad()*entrada2.getProId4().getProPrecioUni().doubleValue()));
//                 System.out.println("Subtotal D antes"+entrada2.getEntSubtotal());
//                 this.ejbFacadeA.edit(entrada2);
            }

            //Actualiza en valor de la entrada modificada
            System.out.println("NUm guia" + productoSeleccionado);
            List<Entradas> entradas = ejbFacadeA.encontarFactura2(productoSeleccionado);

            BigDecimal acumulativo = new BigDecimal("0");
            BigDecimal acumulativo2 = new BigDecimal("0");

            for (Entradas en : entradas) {
                en.setEntTotal(en.getEntSubtotal());
                //System.out.println("Subtotal D" + en.getEntSubtotal());
                BigDecimal subtotal1 = en.getEntTotal();
                acumulativo = acumulativo.add(subtotal1);
                en.setEntTotal(acumulativo);
                //System.out.println("Total D" + en.getEntTotal());
                BigDecimal subtotal2 = BigDecimal.valueOf(en.getEntTotal().doubleValue() + en.getEntTotal().doubleValue() * 0.12);
                acumulativo2 = acumulativo2.add(subtotal2);
                en.setEntTotaliva(acumulativo2);
                //System.out.println("Total con iva D" + en.getEntTotaliva());
                this.ejbFacadeA.edit(en);
            }
            
            //Actualiza el stock de producto
            List<Productos> ls = ejbFacadeR.findAll();
            BigDecimal acumulativo3 = new BigDecimal("0");
            for (Productos producto : ls) {
                BigDecimal subtotal = producto.getProTotalIva();
                acumulativo3 = acumulativo3.add(subtotal);
                producto.setProTotalPrec(acumulativo3);
                this.ejbFacadeR.edit(producto);
            }

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "CORRECTO", " Producto regresados con éxito "));
            
            //this.selected = ejbFacade.findAll()[0];
            this.verReporte(productoSeleccionado);
            limpiar();
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR", e.getMessage()));
        }
    }

    public void limpiar() {
        this.selectedP = new Proveedor();
        this.selectedR = new Productos();
        this.items2 = new ArrayList<>();
        this.Ruc = "";
        this.Serial = "";
        this.Transportista = "";
        this.LugarLlagada = "";
        this.totalV = "";
        this.numeroGuia = "";
        this.observaciones = "";
    }

//metodos pra editar la cantidad del producto en la tabla
    public void onRowEdit(RowEditEvent event) {
        total();
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "INFORMACIÓN", " Se modificaron campos "));
    }

    public void onRowCancel(RowEditEvent event) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "INFORMACIÓN", "  No se hicieron cambios  "));
    }

    public void verReporte(String numEntrada) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        try {
            if (!numEntrada.equals("") || !numEntrada.isEmpty()) {

                //Instancia hacia la clase reporteProductos        
                reporteDevolucionEntrada rFactura = new reporteDevolucionEntrada();

                FacesContext facesContext = FacesContext.getCurrentInstance();
                ServletContext servletContext = (ServletContext) facesContext.getExternalContext().getContext();
                String ruta = servletContext.getRealPath("/Reportes/DevolucionesEntradas.jasper");
                rFactura.getReporte(ruta, numEntrada);
                FacesContext.getCurrentInstance().responseComplete();
            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Alerta", "Seleccione una salida"));
            }
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Seleccione una salida", "Seleccione una salida"));
        }
    }
    
    
    public void verReporteDos() throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        try {
            if (!selected.getDeveNumero().equals("") || !selected.getDeveNumero().isEmpty() || selected.getDeveNumero() != null) {

                //Instancia hacia la clase reporteProductos        
                reporteDevolucionEntrada rFactura = new reporteDevolucionEntrada();

                FacesContext facesContext = FacesContext.getCurrentInstance();
                ServletContext servletContext = (ServletContext) facesContext.getExternalContext().getContext();
                String ruta = servletContext.getRealPath("/Reportes/DevolucionesEntradas.jasper");
                rFactura.getReporte(ruta, selected.getDeveNumero());
                FacesContext.getCurrentInstance().responseComplete();
            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Alerta", "Seleccione una salida"));
            }
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Seleccione una salida", "Seleccione una salida"));
        }
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("Bundle").getString("DevolucionesEntradasCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void actualizar() {
        items = null;
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("Bundle").getString("DevolucionesEntradasUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("Bundle").getString("DevolucionesEntradasDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<DevolucionesEntradas> getItems() {
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
                    JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("Bundle").getString("PersistenceErrorOccured"));
                }
            } catch (Exception ex) {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
                JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("Bundle").getString("PersistenceErrorOccured"));
            }
        }
    }

    public DevolucionesEntradas getDevolucionesEntradas(java.lang.Integer id) {
        return getFacade().find(id);
    }

    public List<DevolucionesEntradas> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<DevolucionesEntradas> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = DevolucionesEntradas.class)
    public static class DevolucionesEntradasControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            DevolucionesEntradasController controller = (DevolucionesEntradasController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "devolucionesEntradasController");
            return controller.getDevolucionesEntradas(getKey(value));
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
            if (object instanceof DevolucionesEntradas) {
                DevolucionesEntradas o = (DevolucionesEntradas) object;
                return getStringKey(o.getDeveId());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), DevolucionesEntradas.class.getName()});
                return null;
            }
        }

    }

}
