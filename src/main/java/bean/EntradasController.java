package bean;

import entities.Entradas;
import bean.util.JsfUtil;
import bean.util.JsfUtil.PersistAction;
import clasesAuxiliares.reporteEntrada;
import entities.Bodega;
import entities.EquipoProteccion;
import entities.Herramienta;
import entities.Maquinaria;
import entities.MaterialAcabado;
import entities.Producto;
import entities.Productos;
import entities.Proveedor;
import entities.Vehiculos;
//import entities.ProductoProveedor;
import java.io.IOException;
import sesion.EntradasFacade;



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
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.w3c.dom.*;


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
import javax.faces.validator.ValidatorException;
import javax.servlet.ServletContext;
import net.sf.jasperreports.engine.JRException;

import org.primefaces.event.RowEditEvent;
import org.primefaces.model.UploadedFile;


@Named("entradasController")
@SessionScoped
public class EntradasController implements Serializable {


    private UploadedFile file;
    private String textXml; 


@EJB
    private sesion.EntradasFacade ejbFacade;
    private List<Entradas> items = null;
    private List<Entradas> items2;
    private Entradas selected;
    private Entradas entrada;
    private String FechaSistema;
    private String totalV;    
    private String numeroGuia;
    private String numeroGuiaResp;
    private int codigo=0;
    @EJB
    private sesion.ProveedorFacade ejbFacadeP;
    private Proveedor selectedP;
    private String Ruc;
    private String Transportista;
    private String LugarLlegada;
    
    @EJB
    private sesion.ProductoProveedorFacade ejbFacadeProductoProveedor;
    
    @EJB
    private sesion.ProductosFacade ejbFacadeR;
    private Productos selectedR;
    private String Serial;
    private String cantidadProducto;
    private String precioUnitario;
    private int productoSeleccionado;
    private String productoNombre;
    private List<Productos> Lista;
    private List<Productos> listaProductos;
    private Productos productosSeleccionado;
    private List<ProductosXML> listaProductosXML;
    private entities.Productos selectedProductComplete;
    private ProductosXML selectedProductoXML;

 
    @EJB 
    private sesion.ProductoFacade ejbFacadePr;
    
    @EJB
    private sesion.HerramientaFacade ejbFacade2;
   
    
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
    private sesion.BodegaFacade ejbFacadeB;
    private List<Bodega> lstBodega;
    private Bodega bodega;
     
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

    public String getProductoNombre() {
        return productoNombre;
    }

    public void setProductoNombre(String productoNombre) {
        this.productoNombre = productoNombre;
    }
    
    public List<Productos> getListaProductos() {
        return listaProductos;
    }

    public void setListaProductos(List<Productos> listaProductos) {
        this.listaProductos = listaProductos;
    }

    public List<Productos> getLista() {
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

    public void getListaProductosByNombre() {
        String nombreProducto = "%".concat(this.productoNombre).concat("%");
        listaProductos = ejbFacadeR.productosByNombre(nombreProducto);
        Iterator<Productos> it = listaProductos.iterator();
        while (it.hasNext()) {
            Productos producto = it.next();
            if (!producto.getProEstadoEliminar().equals("activo")) {
                it.remove();
            }
        }
        //return listaProductos;
    }

    public void setLista(List<Productos> Lista) {
        this.Lista = Lista;
    }
     
    public String getNumeroGuiaResp() {
        return numeroGuiaResp;
    }

    public void setNumeroGuiaResp(String numeroGuiaResp) {
        this.numeroGuiaResp = numeroGuiaResp;
    }

    public String getNumeroGuia() {
        return numeroGuia;
    }

    public void setNumeroGuia(String numeroGuia) {
        this.numeroGuia = numeroGuia;
    }


    public List<Entradas> getItems2() {
        return items2;
    }

    public void setItems2(List<Entradas> items2) {
        this.items2 = items2;
    }

    public Entradas getEntrada() {
        return entrada;
    }

    public void setEntrada(Entradas entrada) {
        this.entrada = entrada;
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

    public String getLugarLlegada() {
        return LugarLlegada;
    }

    public void setLugarLlegada(String LugarLlegada) {
        this.LugarLlegada = LugarLlegada;
    }

    public Productos getSelectedR() {
        return selectedR;
    }

    public void setSelectedR(Productos selectedR) {
        this.selectedR = selectedR;
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

    public Productos getProductosSeleccionado() {
        return productosSeleccionado;
    }

    public void setProductosSeleccionado(Productos productosSeleccionado) {
        this.productosSeleccionado = productosSeleccionado;
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

    public EntradasController() {
    }

    public Entradas getSelected() {
        return selected;
    }

    public void setSelected(Entradas selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private EntradasFacade getFacade() {
        return ejbFacade;
    }

    public Entradas prepareCreate() {
        selected = new Entradas();
        initializeEmbeddableKey();
        return selected;
    }
   @PostConstruct
    public void init() {
        selectedP = new Proveedor();
        selectedR = new Productos();
        items2 = new ArrayList<>();
        entrada = new Entradas();
        Lista = new ArrayList<>();
        lstBodega = new ArrayList<>();
        bodega=new Bodega();
        LugarLlegada ="";
        Transportista = "";
        totalV ="";
    }
    

     public void agregarDatosProveedor(int codProveedor){
        try{
            this.selectedP = ejbFacadeP.encontarProveedor(codProveedor);   
        }catch(Exception e){        
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"ERROR"," Proveedor no encontrado "));
        }
    }

    public void pedirCantidadProducto(int codigo){
        this.productoSeleccionado=codigo;
    }
    
    public void agregarDatosProductosXML(){
        try{
            //Número de Guia
            //this.generarGuia();
            this.setLugarLlegada(this.bodega.getBdNombre());
            //Cargar items homologados
            for( ProductosXML item: this.listaProductosXML ){
                if( !item.getCodProducto().equals("") ){
                    this.productoSeleccionado = item.getIdProducto() ;
                    this.selectedR = ejbFacadeR.encontarProductos(this.productoSeleccionado);
                    items2.add(new Entradas(null,
                            this.selectedP,
                            this.selectedR,
                            this.selectedR.getProCodigopro(),
                            null,
                            this.Transportista,
                            this.LugarLlegada,
                            new BigDecimal(item.getCantidad()).setScale(2, BigDecimal.ROUND_UP),
                            new BigDecimal(item.getPrecio()).setScale(2, BigDecimal.ROUND_UP), new Date() , 
                            BigDecimal.valueOf(item.getCantidad()* item.getPrecio()) 
                    ));
                    this.total();
                    this.cantidadProducto = "1";
                    this.precioUnitario = null;
                    this.productoNombre = "";
                    this.productosSeleccionado = null;                    
                }
            }
        }
        catch(Exception e){
        }
    }
     
    public void agregarDatosProductos(){
        try
        {
            if(!(this.cantidadProducto.matches("[0-9.0-9]*")) || this.cantidadProducto.equals("0") || this.cantidadProducto.equals("") 
               || !(this.precioUnitario.matches("[0-9.0-9]*")) || this.precioUnitario.equals("0") || this.precioUnitario.equals("") 
                    || this.productosSeleccionado == null ){
                 FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"ERROR"," Datos Incorrectos "));
                 //this.cantidadProducto = null;
                 //this.precioUnitario = null;
                 
            }else{
                this.productoSeleccionado = this.productosSeleccionado.getProId4();
                this.selectedR = ejbFacadeR.encontarProductos(this.productoSeleccionado);
                items2.add(new Entradas(null,
                        this.selectedP,
                        this.selectedR,
                        this.selectedR.getProCodigopro(),
                        null,this.Transportista,
                        this.LugarLlegada,
                        new BigDecimal(this.cantidadProducto),
                        new BigDecimal(this.precioUnitario), new Date() , 
                        BigDecimal.valueOf(Double.parseDouble(this.cantidadProducto)* Double.parseDouble(this.precioUnitario) ) 
                ));
                this.total();
                this.cantidadProducto = null;
                this.precioUnitario = null;
                this.productoNombre = "";
                this.productosSeleccionado = null;
                this.listaProductos = new ArrayList<>();
            }
        }
        catch(Exception e){  
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"ERROR"," Datos Inconrrectos: " + e.getMessage()));
        }
    }
     
     public void quitarProductoDetalle(String cod, int fila){
         try{
             int i=0;
             for(Entradas en: this.items2){
                 if(en.getEntCodigo().equals(cod) && fila==i){
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
     
    public void total(){
         BigDecimal Total =new BigDecimal("0");
         for(Entradas en : items2){
            BigDecimal subtotal= en.getEntPrecioUni().multiply(en.getEntCantidad());
            en.setEntSubtotal(subtotal);
            Total = Total.add(subtotal);
         }
         double aux = (Total.doubleValue()*1.12); 
         setTotalV(Double.toString((double)Math.round(aux * 100d) / 100d));
    }

     
    public void guardarEntrada(){
        try {
            System.out.println("INGRESOOOOOOOOOOOOO: ");             
            if (this.numeroGuia.equals("") || this.numeroGuia == null) {
                numeroGuiaResp=getNumeroGuia();
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "INCORRECTO", " Debe generar número de guía "));
            } 
            else
            {
                for (Entradas en : items2) {
                    en.setEntTransportista(this.Transportista);
                    en.setEntLugarLlegada(this.bodega.getBdNombre());
                    en.setProvId(this.selectedP);
                    numeroGuiaResp=getNumeroGuia();
                    en.setEntNumero(this.numeroGuia);
                    double aux = (Double.parseDouble(this.totalV) * 25)/28;
                    en.setEntTotal(new BigDecimal(Double.toString(aux)));
                    en.setEntTotaliva(new BigDecimal(this.totalV));
                    this.ejbFacade.create(en);

                    Productos pro = this.ejbFacadeR.encontarProductos2(en.getEntCodigo());
                    if (this.bodega.getBdNombre().equals(pro.getBdId().getBdNombre())) {
                        pro.setProCantidad(new BigDecimal(en.getEntCantidad().doubleValue() + pro.getProCantidad().doubleValue()));
                        pro.setProPrecioUni(en.getEntPrecioUni());
                        pro.setProSubPrec(BigDecimal.valueOf(pro.getProCantidad().doubleValue() * pro.getProPrecioUni().doubleValue()));
                        pro.setProTotalIva(BigDecimal.valueOf(pro.getProSubPrec().doubleValue() + pro.getProSubPrec().doubleValue() * 0.12));
                        this.ejbFacadeR.edit(pro);

                        if (pro.getProCategoria().equals("Producto")) {
                            Producto p = this.ejbFacadePr.encontrarProductoEspecifico(pro.getProId4());
                            p.setProdFechaIng(new Date());
                            this.ejbFacadePr.edit(p);
                        }
                    } 
                    else 
                    {      
                        Boolean bandera = true;
                        List<Productos> lstProductos=this.ejbFacadeR.encontrarBodegas(en.getEntCodigo());
                        for(int i=0;i<lstProductos.size();i++)
                        {
                           if(this.bodega.getBdNombre().equals(lstProductos.get(i).getBdId().getBdNombre()))
                           {           
                               Productos pro2 = lstProductos.get(i);
                               bandera =  false;
                               pro2.setProCantidad(new BigDecimal(en.getEntCantidad().doubleValue() + pro2.getProCantidad().doubleValue()));
                               pro2.setProPrecioUni(en.getEntPrecioUni());
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
                        
                        if(bandera==true)
                        {
                            //Crear
                            codigo = pro.getProId4();
                            pro.setProId4(null);
                            pro.setProCantidad(en.getEntCantidad());
                            pro.setProPrecioUni(en.getEntPrecioUni());
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
                        }//fin crear
                    }
                }//fin for
                 
                List<Productos> ls = ejbFacadeR.findAll();
                BigDecimal acumulativo = new BigDecimal("0");
                for (Productos en : ls) {
                    BigDecimal subtotal = en.getProTotalIva();
                    acumulativo = acumulativo.add(subtotal);
                    en.setProTotalPrec(acumulativo);
                    this.ejbFacadeR.edit(en);
                }
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "CORRECTO", " Producto Ingresados "));
                limpiar();
            }
        } catch (Exception e){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR", e.getMessage()));
        }
    }
     
    public void limpiar(){
        this.selectedP=new Proveedor();
        this.selectedR = new Productos();
        this.items2 = new ArrayList<>();
        this.listaProductosXML = new ArrayList<>();
        this.Ruc="";
        this.Serial="";
        this.Transportista="";
        this.LugarLlegada="";
        this.totalV="";
        this.numeroGuia="";
    }
 
    public void generarGuia(){         
        try{
            limpiar();
            this.selected = ejbFacade.encontarUltimaGuia();
            if(getSelected() == null){
                this.numeroGuia="E000001";
            }else{
                this.numeroGuia=generarClaves(this.selected.getEntNumero());
            }            
        }catch(Exception e){
             FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR", e.getMessage()));
        }
    } 
     
    public String generarClaves(String cadena) {
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

        String clave = "E" + res;

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
         if (!selected.getEntNumero().equals("") || !selected.getEntNumero().isEmpty() || selected.getEntNumero()!= null) {
        reporteEntrada rFactura = new reporteEntrada();

        FacesContext facesContext = FacesContext.getCurrentInstance();
        ServletContext servletContext = (ServletContext) facesContext.getExternalContext().getContext();
        String ruta = servletContext.getRealPath("/Reportes/EntradaReporte.jasper");
        rFactura.getReporte(ruta, selected.getEntNumero());
        FacesContext.getCurrentInstance().responseComplete();
          } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Alerta", "Seleccione una entrada"));
                
            }
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Seleccione una entrada", "Seleccione una entrada"));
        }
    }
    
    public void verReporte() throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException, JRException, IOException {

        if( this.selectedP.getProvNombre() == null ){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("CONALGI", "Debe Seleccionar Proveedor"));
        }
        else if( this.bodega == null ){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("CONALGI", "Debe Seleccionar Lugar llegada"));
        }
        else if( this.Transportista.trim().equals("") ){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("CONALGI", "Debe Ingresar Transportista"));
        }
        else if( this.totalV.length() == 0  ){
                FacesContext context = FacesContext.getCurrentInstance();
                context.addMessage(null, new FacesMessage("CONALGI", "Debe Ingresar Productos"));
        }
        else
        {
            this.LugarLlegada = this.bodega.getBdNombre();
            guardarEntrada();
            //Instancia hacia la clase reporteProductos        
            reporteEntrada rFactura = new reporteEntrada();

            FacesContext facesContext = FacesContext.getCurrentInstance();
            ServletContext servletContext = (ServletContext) facesContext.getExternalContext().getContext();
            String ruta = servletContext.getRealPath("/Reportes/EntradaReporte.jasper");
            rFactura.getReportePdf(ruta, this.numeroGuiaResp);
            FacesContext.getCurrentInstance().responseComplete();
            this.numeroGuiaResp="";
        }
    }
    
    
    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("resource/Bundle").getString("EntradasCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

        public void actualizar(){
        items = null;
    }
    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("resource/Bundle").getString("EntradasUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("resource/Bundle").getString("EntradasDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<Entradas> getItems() {
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

    public Entradas getEntradas(java.lang.Integer id) {
        return getFacade().find(id);
    }

    public List<Entradas> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<Entradas> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    public UploadedFile getFile() {
        return file;
    }

    public void setFile(UploadedFile file) {
        this.file = file;
    }

    public String getTextXml() {
        return textXml;
    }

    public void setTextXml(String textXml) {
        this.textXml = textXml;
    }

    public ProductosXML getSelectedProductoXML() {
        return selectedProductoXML;
    }

    public void setSelectedProductoXML(ProductosXML selectedProductoXML) {
        this.selectedProductoXML = selectedProductoXML;
    }

    
    
    @FacesConverter(forClass = Entradas.class)
    public static class EntradasControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            EntradasController controller = (EntradasController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "entradasController");
            return controller.getEntradas(getKey(value));
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
            if (object instanceof Entradas) {
                Entradas o = (Entradas) object;
                return getStringKey(o.getEntId());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Entradas.class.getName()});
                return null;
            }
        }

    }


    public void validator(FacesContext context, UIComponent component, Object value ){
        if(file.getContentType().equals("text/plain") ){
            throw new ValidatorException(new FacesMessage("File is not text file"));
        }
    
    }
    
    public void upload() throws IOException {
        if (file != null) {
            FacesMessage msg = new FacesMessage("Succesful", file.getFileName() + " is uploaded.");
            FacesContext.getCurrentInstance().addMessage(null, msg);
            
            textXml = readFileXml();
            System.out.println(textXml);
        }
    }
    public String readFileXml () {
            String xmlFactura="";
            try {

                //abrimos archivo xml
                //File file = new File("D:\\tempo\\factura.xml");
                //DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
                //Document doc = builder.parse(file);

                Scanner s = new Scanner(file.getInputstream());
                String textFactura = s.useDelimiter("\\A").next();
                String textFacturaAux = new String(textFactura.getBytes("UTF-8"));		
                
                org.w3c.dom.Document doc = loadXMLFrom(textFacturaAux);
                
                
                //leimos el cdata de la factura
                NodeList nodes = doc.getElementsByTagName("comprobante");
                Element element = (Element) nodes.item(0);
                xmlFactura = getCharacterDataFromElement(element);

                String cadenaAux = new String(xmlFactura.getBytes("UTF-8"));		
                org.w3c.dom.Document docXml = loadXMLFrom(cadenaAux);

                //Recorremos Detalles
                //String listaProductos = "<table border=1>";
                List<ProductosXML> lstAux =new ArrayList<>();
                        
                NodeList detalles = docXml.getElementsByTagName("detalle");
                for (int i = 0; i < detalles.getLength(); i++) {
                    //listaProductos = listaProductos + "<tr>";
                    Element detalle = (Element) detalles.item(i);

                    if (detalle.getNodeType() == Node.ELEMENT_NODE) {
                            Element item = (Element) detalle;
//                            listaProductos = listaProductos + "<td>" + item.getElementsByTagName("codigoPrincipal").item(0).getTextContent()  + "</td>";
//                            listaProductos = listaProductos + "<td>" + item.getElementsByTagName("descripcion").item(0).getTextContent()  + "</td>";
//                            listaProductos = listaProductos + "<td>" + item.getElementsByTagName("cantidad").item(0).getTextContent()  + "</td>";
//                            listaProductos = listaProductos + "<td>" + item.getElementsByTagName("precioUnitario").item(0).getTextContent()  + "</td>";
                    
                            ProductosXML itemXml2 = new ProductosXML(
                                item.getElementsByTagName("codigoPrincipal").item(0).getTextContent(), 
                                item.getElementsByTagName("descripcion").item(0).getTextContent(), 
                                Double.parseDouble(item.getElementsByTagName("cantidad").item(0).getTextContent()),
                                Double.parseDouble(item.getElementsByTagName("precioUnitario").item(0).getTextContent()));
                            
                        lstAux.add(itemXml2);
                    }
//                    listaProductos = listaProductos + "</tr>";
                }	

//                listaProductos = listaProductos + "</table>";

                this.listaProductosXML = lstAux;
                
//                xmlFactura = (listaProductos);

                
            } catch (final Exception e) {
                    
            } 
        return xmlFactura;    
    }

    public List<bean.ProductosXML> getListaProductosXML() {
        return listaProductosXML;
    }

    public void setListaProductosXML(List<bean.ProductosXML> listaProductosXML) {
        this.listaProductosXML = listaProductosXML;
    }

    


    public static org.w3c.dom.Document loadXMLFrom(String xml) throws org.xml.sax.SAXException, java.io.IOException {
        return loadXMLFromX(new java.io.ByteArrayInputStream(xml.getBytes()));
    }

    public static org.w3c.dom.Document loadXMLFromX(java.io.InputStream is)
        throws org.xml.sax.SAXException, java.io.IOException {
        final javax.xml.parsers.DocumentBuilderFactory factory = javax.xml.parsers.DocumentBuilderFactory.newInstance();
        factory.setNamespaceAware(true);
        javax.xml.parsers.DocumentBuilder builder = null;
        try {
            builder = factory.newDocumentBuilder();
        } 
        catch (final javax.xml.parsers.ParserConfigurationException ex) {
        }
        final org.w3c.dom.Document doc = builder.parse(is);
        is.close();
        return doc;
    }

    public static String getCharacterDataFromElement(Element e) {
        Node child = e.getFirstChild();
        if (child instanceof CharacterData) {
          CharacterData cd = (CharacterData) child;
          return cd.getData();
        }
        return "";
    }

    
    public void agregarProductoXML(){
        try{
            if(!(this.cantidadProducto.matches("[0-9.0-9]*")) || this.cantidadProducto.equals("0") || this.cantidadProducto.equals("") 
               || !(this.precioUnitario.matches("[0-9.0-9]*")) || this.precioUnitario.equals("0") || this.precioUnitario.equals("") ){
                 FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"ERROR"," Datos Incorrectos "));
                 this.cantidadProducto = null;
                 this.precioUnitario = null;
            }else{
                this.selectedR = ejbFacadeR.encontarProductos(this.productoSeleccionado);
                items2.add(new Entradas(null,
                        this.selectedP, //Proveedor
                        this.selectedR, //Producto
                        this.selectedR.getProCodigopro(),
                        null,this.Transportista,
                        this.LugarLlegada,
                        new BigDecimal(this.cantidadProducto).setScale(2, BigDecimal.ROUND_UP),
                        new BigDecimal(this.precioUnitario).setScale(2, BigDecimal.ROUND_UP), new Date() , 
                        BigDecimal.valueOf(Double.parseDouble(this.cantidadProducto)* Double.parseDouble(this.precioUnitario) ) 
                ));
                this.total();
                this.cantidadProducto = null;
                this.precioUnitario = null;
                this.productoNombre = "";
                this.listaProductos = new ArrayList<>();
            }
        }
        catch(Exception e){        
        }
    }

    public Productos getSelectedProductComplete() {
        return selectedProductComplete;
    }

    public void setSelectedProductComplete(Productos selectedProductComplete) {
        this.selectedProductComplete = selectedProductComplete;
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
    
    /**
     * Obtiene una lista de producto para componete autocomplete
     * @param query, criterio de consulta 
     * @return, Lista de Productos
     */
    public List<entities.Productos> getListaProductosComplete(String query) {
        listaProductos = ejbFacadeR.productosByNombre("%".concat(query).concat("%"));
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
     * Actualiza la lista de productos cardados del archivo XML de la 
     * factura electronica, se asina el código del producto local
     */
    public void updateItemListaProductoXML(){
        int i =0;
        while(i < this.listaProductosXML.size() ){
            ProductosXML item = listaProductosXML.get(i);
            if(item.getCodigo().equals(this.selectedProductoXML.getCodigo())){
                item.setCodProducto( this.selectedProductComplete.getProCodigopro());
                item.setIdProducto(this.selectedProductComplete.getProId4());
                this.selectedProductoXML = null;
                this.selectedProductComplete = null;
                break;
            }
            i++;
        }
    }

    /**
     * Guardar relación del código del Producto del Proveedor
     * con el codigo de producto local
     */
    public void agregarProductoProveedor(){
        int i =0;
        while(i < this.listaProductosXML.size()){
            ProductosXML item = listaProductosXML.get(i);
            if(!item.getCodProducto().equals("")){
                entities.ProductoProveedor productoProveedor;
                productoProveedor = new entities.ProductoProveedor(selectedP.getProvId(),item.getCodProducto(), item.getCodigo() );
                this.ejbFacadeProductoProveedor.create(productoProveedor);
            }
            i++;
        }
    }

    public void getListaProductosProveedor(){
        this.ejbFacadeProductoProveedor.findAll();
    
    }
    
}
