package bean;

import entities.Productos;
import bean.util.JsfUtil;
import bean.util.JsfUtil.PersistAction;
import clasesAuxiliares.reporteProductos;
import clasesAuxiliares.reporteTotalProductos;
import entities.DarBaja;
import entities.EquipoProteccion;
import entities.Herramienta;
import entities.Maquinaria;
import entities.MaterialAcabado;
import entities.Producto;
import entities.Vehiculos;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import sesion.ProductosFacade;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.SQLException;
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
import net.sf.jasperreports.engine.JRException;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.UploadedFile;

@Named("productosController")
@SessionScoped
public class ProductosController implements Serializable {

    private int cantidad;
    private entities.Productos selectedProductComplete;
    private List<Productos> listaProductos;
    
    @EJB
    private sesion.ProductosFacade ejbFacade;
    private List<Productos> items = null;
    private Productos selected;
    private Productos pro1;
    private String repProductosNom;
    private Productos selectedNew;
    private String nombrePro;
    @EJB
    private sesion.DarBajaFacade ejbFacadeEl;
    private DarBaja elselected;
    
    private UploadedFile file;
    private StreamedContent download;
    private String razones="";
    private String proCategoria="";
    
       //HERRAMIENTA
    @EJB
    private sesion.HerramientaFacade ejbFacade2;
    private Herramienta hselected;
    private String estado;
    private Date fec_mantenimiento;
    private String  raz_mantenimiento;
    private String periodo_mantenimiento;
    private String proAdicionales;
    
    //PRODUCTO
    @EJB
    private sesion.ProductoFacade ejbFacade3;
    private Producto pselected;
    private String ubicacion;
    private String caducidad;
    private Date fecha_ingreso;
    
    //Equipos de Protección
    @EJB
    private sesion.EquipoProteccionFacade ejbFacade4;
    private EquipoProteccion  eselected;
    private String e_nombre;
    private String material;
    private String duracion;
    
    //MATERIAL ACABADO
    @EJB
    private sesion.MaterialAcabadoFacade ejbFacade5;
    private MaterialAcabado mselected;
    private String nombre;
    private String color;
    private String tipo_acabado;
    
    //VEHICULO
    @EJB
    private sesion.VehiculosFacade ejbFacade6;
    private Vehiculos vselected;
    private String placa;
    private String anio;
    private String color_ve;
    
    //MAQUINARIA
    @EJB
    private sesion.MaquinariaFacade ejbFacade7;
    private  Maquinaria maselected; 

    public String getNombrePro() {
        return nombrePro;
    }

    public void setNombrePro(String nombrePro) {
        this.nombrePro = nombrePro;
    }

    public Productos getPro1() {
        return pro1;
    }

    public void setPro1(Productos pro1) {
        this.pro1 = pro1;
    }

    
    
    public DarBaja getElselected() {
        return elselected;
    }

    public void setElselected(DarBaja elselected) {
        this.elselected = elselected;
    }

    public Productos getSelectedNew() {
        return selectedNew;
    }

    public void setSelectedNew(Productos selectedNew) {
        this.selectedNew = selectedNew;
    }

    public String getRazones() {
        return razones;
    }

    public void setRazones(String razones) {
        this.razones = razones;
    }
    
    public Maquinaria getMaselected() {
        return maselected;
    }

    public void setMaselected(Maquinaria maselected) {
        this.maselected = maselected;
    }
    

    public String getRepProductosNom() {
        return repProductosNom;
    }

    public void setRepProductosNom(String repProductosNom) {
        this.repProductosNom = repProductosNom;
    }

    public UploadedFile getFile() {
        return file;
    }

    public void setFile(UploadedFile file) {
        this.file = file;
    }

    public StreamedContent getDownload() {
        return download;
    }

    public void setDownload(StreamedContent download) {
        this.download = download;
    }

    public Herramienta getHselected() {
        return hselected;
    }

    public void setHselected(Herramienta hselected) {
        this.hselected = hselected;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getProCategoria() {
        return proCategoria;
    }

    public void setProCategoria(String proCategoria) {
        this.proCategoria = proCategoria;
    }

    public Date getFec_mantenimiento() {
        return fec_mantenimiento;
    }

    public void setFec_mantenimiento(Date fec_mantenimiento) {
        this.fec_mantenimiento = fec_mantenimiento;
    }

    public String getRaz_mantenimiento() {
        return raz_mantenimiento;
    }

    public void setRaz_mantenimiento(String raz_mantenimiento) {
        this.raz_mantenimiento = raz_mantenimiento;
    }

    public String getPeriodo_mantenimiento() {
        return periodo_mantenimiento;
    }

    public void setPeriodo_mantenimiento(String periodo_mantenimiento) {
        this.periodo_mantenimiento = periodo_mantenimiento;
    }

    public String getProAdicionales() {
        return proAdicionales;
    }

    public void setProAdicionales(String proAdicionales) {
        this.proAdicionales = proAdicionales;
    }

    public Producto getPselected() {
        return pselected;
    }

    public void setPselected(Producto pselected) {
        this.pselected = pselected;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public String getCaducidad() {
        return caducidad;
    }

    public void setCaducidad(String caducidad) {
        this.caducidad = caducidad;
    }

    public Date getFecha_ingreso() {
        return fecha_ingreso;
    }

    public void setFecha_ingreso(Date fecha_ingreso) {
        this.fecha_ingreso = fecha_ingreso;
    }

    public EquipoProteccion getEselected() {
        return eselected;
    }

    public void setEselected(EquipoProteccion eselected) {
        this.eselected = eselected;
    }

    public String getE_nombre() {
        return e_nombre;
    }

    public void setE_nombre(String e_nombre) {
        this.e_nombre = e_nombre;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public String getDuracion() {
        return duracion;
    }

    public void setDuracion(String duracion) {
        this.duracion = duracion;
    }

    public MaterialAcabado getMselected() {
        return mselected;
    }

    public void setMselected(MaterialAcabado mselected) {
        this.mselected = mselected;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getTipo_acabado() {
        return tipo_acabado;
    }

    public void setTipo_acabado(String tipo_acabado) {
        this.tipo_acabado = tipo_acabado;
    }

    public Vehiculos getVselected() {
        return vselected;
    }

    public void setVselected(Vehiculos vselected) {
        this.vselected = vselected;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public List<Productos> getListaProductos() {
        return listaProductos;
    }

    public void setListaProductos(List<Productos> listaProductos) {
        this.listaProductos = listaProductos;
    }

    public String getAnio() {
        return anio;
    }

    public void setAnio(String anio) {
        this.anio = anio;
    }

    public String getColor_ve() {
        return color_ve;
    }

    public void setColor_ve(String color_ve) {
        this.color_ve = color_ve;
    }
    
    @PostConstruct
    public void init() {
        hselected = new Herramienta();
        pselected = new Producto();
        eselected = new EquipoProteccion();
        mselected = new MaterialAcabado();
        vselected = new Vehiculos();
        maselected=new Maquinaria();
        selectedNew = new Productos();
        cantidad = 0;
    }

    public Productos getSelectedProductComplete() {
        return selectedProductComplete;
    }

    public void setSelectedProductComplete(Productos selectedProductComplete) {
        this.selectedProductComplete = selectedProductComplete;
    }

    public ProductosController() {
    }

    public Productos getSelected() {
        return selected;
    }

    public void setSelected(Productos selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private ProductosFacade getFacade() {
        return ejbFacade;
    }

    public Productos prepareCreate() {
        selected = new Productos();
        initializeEmbeddableKey();
        return selected;
    }
       public BigDecimal allCantidad() {
        BigDecimal sum = new BigDecimal(0);
        items.forEach((s) -> {
            sum.add(s.getProCantidad());
        });
        return sum;
    }

    public static String generarClaves(String categoria, String letras, int numeros) {
        String clave = null;
        String sSubCadena = letras.substring(0, 2).toUpperCase();
        String categoria1 = categoria.substring(0, 1).toUpperCase();

        String res = "";
        if (numeros >= 100000) {
            res = "0" + numeros;
        }
        if (numeros >= 10000) {
            res = "00" + numeros;
        }
        if (numeros >= 1000) {
            res = "000" + numeros;
        }
        if (numeros >= 100) {
            res = "0000" + numeros;
        }
        if (numeros >= 10) {
            res = "00000" + numeros;
        }
        if (numeros >= 1) {
            res = "000000" + numeros;
        }

        clave = "CL" + categoria1 + sSubCadena + res;

        System.out.println("CLAVEEEEEEEEEEEEEEE   " + clave);
        System.out.println(numeros);
        return clave;

    }

    public void bajarImagen() {
        InputStream sImage;
        try {
            //recuerda el selected con el postconstructor 
            Productos product = ejbFacade.BuscarEspecifico(selected.getProId4());
            if (product != null) {
                byte[] bytearray = new byte[1024];
                int size = 0;
                //Transformamos los byte en tipo inputstream
                sImage = new ByteArrayInputStream(product.getProImagen());
                download = new DefaultStreamedContent(sImage, "image/jpg", "descarga.jpg");
            }
        } catch (Exception e) {

        }
    }


    
    public void guardarImagen() {
        try {
            if (file.getSize() > 0) {
                InputStream input = file.getInputstream();
                ByteArrayOutputStream output = new ByteArrayOutputStream();
                byte[] buffer = new byte[1048576];
                for (int lenght = 0; (lenght = input.read(buffer)) > 0;) {
                    output.write(buffer, 0, lenght);
                }
                // output.flush();
                selected.setProImagen(output.toByteArray());
            }
        } catch (IOException e) {

        }
    }
    
    public void imprimirCodigoBarras(){
        FacesContext facesContext = FacesContext.getCurrentInstance();
        ServletContext servletContext = (ServletContext) facesContext.getExternalContext().getContext();
        String ruta = servletContext.getRealPath("/Reportes/PrimProducto.jasper");
        
        FacesContext.getCurrentInstance().responseComplete();
    
    }

    public void verReporte() throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        try {
            repProductosNom = selected.getProCodigopro();
            if (!repProductosNom.equals("") || !repProductosNom.isEmpty() || repProductosNom != null) {
                reporteProductos rFactura = new reporteProductos();
                FacesContext facesContext = FacesContext.getCurrentInstance();
                ServletContext servletContext = (ServletContext) facesContext.getExternalContext().getContext();
                String ruta = servletContext.getRealPath("/Reportes/PrimProducto.jasper");
                rFactura.getReporte(ruta, repProductosNom);
                FacesContext.getCurrentInstance().responseComplete();
            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Alerta", "Seleccione un producto"));
                
            }
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Seleccione un producto", "Seleccione un producto"));
        }
    }

//    public String getBaseDir() {
//        String baseDir = "/Reportes/";
//        try {
//            return FacesContext.getCurrentInstance()
//                    .getExternalContext()
//                    .getResource(baseDir)
//                    .getPath();
//        } catch (MalformedURLException mue) {
////log.error(mue, mue);
//            return null;
//}
//}
        public void verReporteTodosProdu() throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException { 
        reporteTotalProductos rFactura = new reporteTotalProductos();
        FacesContext facesContext = FacesContext.getCurrentInstance();
        ServletContext servletContext = (ServletContext) facesContext.getExternalContext().getContext();
        String ruta = servletContext.getRealPath("/Reportes/ProducTotales.jasper");
       // String imagen=FacesContext.getCurrentInstance().getExternalContext().getRealPath("/images/logo-reporte.jpg");
         //rFactura.getReporte(ruta,imagen);
         //String imagen = servletContext.getRealPath("/Reportes/logo-reporte1.jpg");
        //rFactura.getReporte(ruta,getClass().getResource("/Reportes/logo-reporte.jpg").openStream());
       rFactura.getReporte(ruta);
        FacesContext.getCurrentInstance().responseComplete();
    }
     
     
    public void darBaja() {
        try {
            DarBaja obj = new DarBaja();      
            obj.setProId4(selected);
            obj.setDbNombrepro(selected.getProNombres());
            
            System.out.println("Nombre: " + selected.getProNombres());
            if (!this.razones.isEmpty()|| !this.razones.equals("") || this.razones!=null) {
                obj.setDbRazones(razones);
                this.ejbFacadeEl.create(obj);
                selected.setProEstadoEliminar("Pasivo");
                this.ejbFacade.edit(selected);
            }else{
                System.out.println("ENTROOO A DAR BAJA");
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Warning", "Es obligatorio ingresar la razón"));
            }
           // selectedNew = new Productos();
           this.razones = "";
           getItems();

        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR", e.getMessage()));
        }
    }
    
     
    public void create() {
        guardarImagen();
        this.pro1 = ejbFacade.Obtenerobj();
        try {
        if (getPro1()== null || getPro1().equals(null)) {
            
            selected.setProTotalPro(1);
            if(selected.getProCantidad()==null || selected.getProCantidad().equals(null)){
                  selected.setProCantidad(new BigDecimal(0));
            }
            
            selected.setProNombres(selected.getProNombres().toUpperCase());
            selected.setProSubPrec(new BigDecimal(selected.getProCantidad().doubleValue()*selected.getProPrecioUni().doubleValue()));
            selected.setProTotalPrec(selected.getProSubPrec());
            selected.setProTotalIva(BigDecimal.valueOf(selected.getProSubPrec().doubleValue()+selected.getProSubPrec().doubleValue()*0.12));
            selected.setProTotalPrec(selected.getProTotalIva());           
            selected.setProEstadoEliminar("activo");
            selected.setProCodigopro(generarClaves(selected.getProCategoria(),selected.getProNombres(),1));
            persist(PersistAction.CREATE, ResourceBundle.getBundle(("Bundle")ring("ProductosCreated"));
            if (!JsfUtil.isValidationFailed()) {
                items = null;    // Invalidate list of items to trigger re-query.
            }
            Productos pro = ejbFacade.Obtenerobj();
            System.out.println("Objetos: " + pro.getProId4());
            if (selected.getProCategoria().equals("Herramientas")) {
                hselected.setProId4(pro);
                hselected.setHerId(pro.getProId4());
                this.ejbFacade2.create(hselected);
                
                
            }
            if (selected.getProCategoria().equals("Producto")) {
                pselected.setProId4(pro);
                pselected.setProdId(pro.getProId4());
                ejbFacade3.create(pselected);
            }
            if (selected.getProCategoria().equals("Equipos de Protección")) {
                eselected.setProId4(pro);
                eselected.setProId2(pro.getProId4());
                ejbFacade4.create(eselected);
            }
            if (selected.getProCategoria().equals("Material de acabados")) {
                mselected.setProId4(pro);
                mselected.setProId3(pro.getProId4());
                ejbFacade5.create(mselected);
            }
            if (selected.getProCategoria().equals("Vehículos")) {
                vselected.setProId4(pro);
                vselected.setVehId(pro.getProId4());
                ejbFacade6.create(vselected);
            }
            if (selected.getProCategoria().equals("Maquinaria")) {
                maselected.setProId4(pro);
                maselected.setMaqId(pro.getProId4());
                ejbFacade7.create(maselected);
            }

        } else {
            selected.setProTotalPro(items.size()+1);
            System.out.println("Total Productos "+items.size());
            if(selected.getProCantidad()==null ){ selected.setProCantidad(new BigDecimal(0));}
            selected.setProSubPrec(new BigDecimal(selected.getProCantidad().doubleValue()*selected.getProPrecioUni().doubleValue()));
            System.out.println("Subtotal "+selected.getProSubPrec());
            selected.setProTotalPrec(selected.getProSubPrec());
            System.out.println("Total sin Iva "+selected.getProTotalPrec());
            selected.setProTotalIva(BigDecimal.valueOf(selected.getProSubPrec().doubleValue()+selected.getProSubPrec().doubleValue()*0.12));
            System.out.println("Total con  Iva "+selected.getProTotalIva());
            System.out.println("TOTAL Anterior "+pro1.getProTotalPrec());
            selected.setProTotalPrec(BigDecimal.valueOf(pro1.getProTotalPrec().doubleValue()+selected.getProTotalIva().doubleValue()));
             System.out.println("TOTAL TOTAL "+selected.getProTotalPrec());
            selected.setProCodigopro(generarClaves(selected.getProCategoria(),selected.getProNombres(),pro1.getProId4()+1));
            selected.setProEstadoEliminar("activo");
            persist(PersistAction.CREATE, ResourceBundle.getBundle(("Bundle")ring("ProductosCreated"));
            if (!JsfUtil.isValidationFailed()) {
                items = null;    // Invalidate list of items to trigger re-query.
            }
            Productos pro = ejbFacade.Obtenerobj();
            System.out.println("Objetos: " + pro.getProId4());
            if (selected.getProCategoria().equals("Herramientas")) {
                hselected.setProId4(pro);
                hselected.setHerId(pro.getProId4());
                this.ejbFacade2.create(hselected);
            }
            if (selected.getProCategoria().equals("Producto")) {
                pselected.setProId4(pro);
                pselected.setProdId(pro.getProId4());
                ejbFacade3.create(pselected);
            }
            if (selected.getProCategoria().equals("Equipos de Protección")) {
                eselected.setProId4(pro);
                eselected.setProId2(pro.getProId4());
                ejbFacade4.create(eselected);
            }
            if (selected.getProCategoria().equals("Material de acabados")) {
                mselected.setProId4(pro);
                mselected.setProId3(pro.getProId4());
                ejbFacade5.create(mselected);
            }
            if (selected.getProCategoria().equals("Vehículos")) {
                vselected.setProId4(pro);
                vselected.setVehId(pro.getProId4());
                ejbFacade6.create(vselected);
            }
            if (selected.getProCategoria().equals("Maquinaria")) {
                maselected.setProId4(pro);
                maselected.setMaqId(pro.getProId4());
                ejbFacade7.create(maselected);
            }
        }    
        } catch (Exception e) {
            
              FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR", e.getMessage()));
        }
        
    }

     public void actualizar(){
        items = null;
    }
    public void update() {
        this.pro1 = ejbFacade.Obtenerobj();
        guardarImagen();
        if(selected.getProCantidad()==null ){ selected.setProCantidad(new BigDecimal(0));}
            selected.setProSubPrec(new BigDecimal(selected.getProCantidad().doubleValue()*selected.getProPrecioUni().doubleValue()));
            System.out.println("Subtotal "+selected.getProSubPrec());
            selected.setProTotalPrec(selected.getProSubPrec());
            System.out.println("Total sin Iva "+selected.getProTotalPrec());
            selected.setProTotalIva(BigDecimal.valueOf(selected.getProSubPrec().doubleValue()+selected.getProSubPrec().doubleValue()*0.12));
            System.out.println("Total con  Iva "+selected.getProTotalIva());            
            selected.setProTotalPrec(BigDecimal.valueOf(pro1.getProTotalPrec().doubleValue()+selected.getProTotalIva().doubleValue()));
        persist(PersistAction.UPDATE, ResourceBundle.getBundle(("Bundle")ring("ProductosUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle(("Bundle")ring("ProductosDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<Productos> getItems() {
        List<Productos>  lst = new ArrayList<>();
        if (items == null) {
            items = getFacade().findAll();
            try {
                Iterator<Productos> it= items.iterator();
            while (it.hasNext()) {
                Productos producto = it.next();
                if (!producto.getProEstadoEliminar().equals("activo")) {
                    it.remove();
                }
            }
            System.out.println("TAMAÑO "+ items.size());
                
            } catch (Exception e) {
                System.out.println("Se produjo un  "+ items.size());
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
                    JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle(("Bundle")).getString("PersistenceErrorOccured"));
                }
            } catch (Exception ex) {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
                JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle(("Bundle")ring("PersistenceErrorOccured"));
            }
        }
    }

    public Productos getProductos(java.lang.Integer id) {
        return getFacade().find(id);
    }

    public List<Productos> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<Productos> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = Productos.class)
    public static class ProductosControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            ProductosController controller = (ProductosController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "productosController");
            return controller.getProductos(getKey(value));
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
            if (object instanceof Productos) {
                Productos o = (Productos) object;
                return getStringKey(o.getProId4());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Productos.class.getName()});
                return null;
            }
        }

    }

    
    public void BarCodes() throws ClassNotFoundException, SQLException, JRException, IOException, InstantiationException, IllegalAccessException{
        if( this.proCategoria.length() != 0 && !this.proCategoria.contains("---")){ 
            reporteProductos reporte = new reporteProductos();
            FacesContext facesContext = FacesContext.getCurrentInstance();
            ServletContext servletContext = (ServletContext) facesContext.getExternalContext().getContext();
            String ruta = servletContext.getRealPath("/Reportes/ProductBarCode.jasper");
            reporte.getCodeBarPdf(ruta, this.proCategoria);
            FacesContext.getCurrentInstance().responseComplete();
            this.proCategoria="";
        }
        else if( this.selectedProductComplete != null && this.cantidad > 0 ){
            reporteProductos reporte = new reporteProductos();
            List<entities.Productos> resultProductos=new ArrayList<>();
            for(int i = 0; i < this.cantidad; i++ ){
                resultProductos.add(selectedProductComplete);
            }
            
            FacesContext facesContext = FacesContext.getCurrentInstance();
            ServletContext servletContext = (ServletContext) facesContext.getExternalContext().getContext();
            String ruta = servletContext.getRealPath("/Reportes/ProductBarCodeCantidad.jasper");
            reporte.getCodeBarByProductPdf(ruta, resultProductos);
            FacesContext.getCurrentInstance().responseComplete();
            this.proCategoria="";        
        }
        else
        {
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage("Second Message", "Debe Seleccionar el tipo de categoria."));
        }
    }


    /**
     * Obtiene una lista de producto para componete autocomplete
     * @param query, criterio de consulta 
     * @return, Lista de Productos
     */
    public List<entities.Productos> getListaProductosComplete(String query) {
        listaProductos = ejbFacade.productosByNombre("%".concat(query).concat("%"));
        List<entities.Productos> resultProductos=new ArrayList<>();
        Iterator<entities.Productos> it = listaProductos.iterator();
        while (it.hasNext()) {
            Productos producto = it.next();
            if( (int)(resultProductos.size()) == 0 ){
                resultProductos.add(producto);
            }
            else{
                if( ! existeProducto(producto.getProCodigopro(),resultProductos )){
                    resultProductos.add(producto);
                }
            }
        }
        return resultProductos;
    }    
    
    /**
     * Verifica la existncia de un codigo de producto en una lista
     * @param codigoProducto, a see buscado
     * @param productos, lista de productos
     * @return verdadero y existe, falso si no existe
     */
    public boolean existeProducto( String codigoProducto, List<entities.Productos> productos) {
        Iterator<Productos> iterator = productos.iterator();
        while (iterator.hasNext()) {
            Productos customer = iterator.next();
            if (customer.getProCodigopro().equals(codigoProducto)) {
                return true;
            }
        }
        return false;
    }
        
}
