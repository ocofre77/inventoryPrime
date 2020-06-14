/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Vanessa
 */
@Entity
@Table(name = "productos")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Productos.findAll", query = "SELECT p FROM Productos p")
    , @NamedQuery(name = "Productos.findByProId4", query = "SELECT p FROM Productos p WHERE p.proId4 = :proId4")
    , @NamedQuery(name = "Productos.findByProCodigopro", query = "SELECT p FROM Productos p WHERE p.proCodigopro = :proCodigopro")
    , @NamedQuery(name = "Productos.findByProNombres", query = "SELECT p FROM Productos p WHERE p.proNombres = :proNombres")
    , @NamedQuery(name = "Productos.findByProModelo", query = "SELECT p FROM Productos p WHERE p.proModelo = :proModelo")
    , @NamedQuery(name = "Productos.findByProSerial", query = "SELECT p FROM Productos p WHERE p.proSerial = :proSerial")
    , @NamedQuery(name = "Productos.findByProTamanomedida", query = "SELECT p FROM Productos p WHERE p.proTamanomedida = :proTamanomedida")
    , @NamedQuery(name = "Productos.findByProCategoria", query = "SELECT p FROM Productos p WHERE p.proCategoria = :proCategoria")
    , @NamedQuery(name = "Productos.findByProCantidad", query = "SELECT p FROM Productos p WHERE p.proCantidad = :proCantidad")
    , @NamedQuery(name = "Productos.findByProPrecioUni", query = "SELECT p FROM Productos p WHERE p.proPrecioUni = :proPrecioUni")
    , @NamedQuery(name = "Productos.findByProAdiObservacion", query = "SELECT p FROM Productos p WHERE p.proAdiObservacion = :proAdiObservacion")
    , @NamedQuery(name = "Productos.findByProEstadoEliminar", query = "SELECT p FROM Productos p WHERE p.proEstadoEliminar = :proEstadoEliminar")
    , @NamedQuery(name = "Productos.findByProSubtotalPro", query = "SELECT p FROM Productos p WHERE p.proSubtotalPro = :proSubtotalPro")
    , @NamedQuery(name = "Productos.findByProTotalPro", query = "SELECT p FROM Productos p WHERE p.proTotalPro = :proTotalPro")
    , @NamedQuery(name = "Productos.findByProSubPrec", query = "SELECT p FROM Productos p WHERE p.proSubPrec = :proSubPrec")
    , @NamedQuery(name = "Productos.findByProTotalPrec", query = "SELECT p FROM Productos p WHERE p.proTotalPrec = :proTotalPrec")
    , @NamedQuery(name = "Productos.findByProTotalIva", query = "SELECT p FROM Productos p WHERE p.proTotalIva = :proTotalIva")})
public class Productos implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "PRO_ID4")
    private Integer proId4;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "PRO_CODIGOPRO")
    private String proCodigopro;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "PRO_NOMBRES")
    private String proNombres;
    @Size(max = 50)
    @Column(name = "PRO_MODELO")
    private String proModelo;
    @Size(max = 20)
    @Column(name = "PRO_SERIAL")
    private String proSerial;
    @Size(max = 100)
    @Column(name = "PRO_TAMANOMEDIDA")
    private String proTamanomedida;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "PRO_CATEGORIA")
    private String proCategoria;
    @Column(name = "PRO_CANTIDAD")
    private BigDecimal proCantidad;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(name = "PRO_PRECIO_UNI")
    private BigDecimal proPrecioUni;
    @Lob
    @Column(name = "PRO_IMAGEN")
    private byte[] proImagen;
    @Size(max = 1000)
    @Column(name = "PRO_ADI_OBSERVACION")
    private String proAdiObservacion;
    @Size(max = 30)
    @Column(name = "PRO_ESTADO_ELIMINAR")
    private String proEstadoEliminar;
    @Column(name = "PRO_SUBTOTAL_PRO")
    private Integer proSubtotalPro;
    @Column(name = "PRO_TOTAL_PRO")
    private Integer proTotalPro;
    @Column(name = "PRO_SUB_PREC")
    private BigDecimal proSubPrec;
    @Column(name = "PRO_TOTAL_PREC")
    private BigDecimal proTotalPrec;
    @Column(name = "PRO_TOTAL_IVA")
    private BigDecimal proTotalIva;
    @OneToMany(mappedBy = "proId4")
    private List<EquipoProteccion> equipoProteccionList;
    @OneToMany(mappedBy = "proId4")
    private List<DarBaja> darBajaList;
    @OneToMany(mappedBy = "proId4")
    private List<Herramienta> herramientaList;
    @OneToMany(mappedBy = "proId4")
    private List<Producto> productoList;
    @OneToMany(mappedBy = "proId4")
    private List<Vehiculos> vehiculosList;
    @OneToMany(mappedBy = "proId4")
    private List<AsignarProyecto> asignarProyectoList;
    @OneToMany(mappedBy = "proId4")
    private List<Cotizacion> cotizacionList;
    @OneToMany(mappedBy = "proId4")
    private List<Entradas> entradasList;
    @OneToMany(mappedBy = "proId4")
    private List<MaterialAcabado> materialAcabadoList;
    @JoinColumn(name = "BD_ID", referencedColumnName = "BD_ID")
    @ManyToOne(optional = false)
    private Bodega bdId;
    @OneToMany(mappedBy = "proId4")
    private List<Maquinaria> maquinariaList;

    public Productos() {
    }

    public Productos(Integer proId4) {
        this.proId4 = proId4;
    }

    public Productos(Integer proId4, String proCodigopro, String proNombres, String proCategoria, BigDecimal proPrecioUni) {
        this.proId4 = proId4;
        this.proCodigopro = proCodigopro;
        this.proNombres = proNombres;
        this.proCategoria = proCategoria;
        this.proPrecioUni = proPrecioUni;
    }

    public Integer getProId4() {
        return proId4;
    }

    public void setProId4(Integer proId4) {
        this.proId4 = proId4;
    }

    public String getProCodigopro() {
        return proCodigopro;
    }

    public void setProCodigopro(String proCodigopro) {
        this.proCodigopro = proCodigopro;
    }

    public String getProNombres() {
        return proNombres;
    }

    public void setProNombres(String proNombres) {
        this.proNombres = proNombres;
    }

    public String getProModelo() {
        return proModelo;
    }

    public void setProModelo(String proModelo) {
        this.proModelo = proModelo;
    }

    public String getProSerial() {
        return proSerial;
    }

    public void setProSerial(String proSerial) {
        this.proSerial = proSerial;
    }

    public String getProTamanomedida() {
        return proTamanomedida;
    }

    public void setProTamanomedida(String proTamanomedida) {
        this.proTamanomedida = proTamanomedida;
    }

    public String getProCategoria() {
        return proCategoria;
    }

    public void setProCategoria(String proCategoria) {
        this.proCategoria = proCategoria;
    }

    public BigDecimal getProCantidad() {
        return proCantidad;
    }

    public void setProCantidad(BigDecimal proCantidad) {
        this.proCantidad = proCantidad;
    }

    public BigDecimal getProPrecioUni() {
        return proPrecioUni;
    }

    public void setProPrecioUni(BigDecimal proPrecioUni) {
        this.proPrecioUni = proPrecioUni;
    }

    public byte[] getProImagen() {
        return proImagen;
    }

    public void setProImagen(byte[] proImagen) {
        this.proImagen = proImagen;
    }

    public String getProAdiObservacion() {
        return proAdiObservacion;
    }

    public void setProAdiObservacion(String proAdiObservacion) {
        this.proAdiObservacion = proAdiObservacion;
    }

    public String getProEstadoEliminar() {
        return proEstadoEliminar;
    }

    public void setProEstadoEliminar(String proEstadoEliminar) {
        this.proEstadoEliminar = proEstadoEliminar;
    }

    public Integer getProSubtotalPro() {
        return proSubtotalPro;
    }

    public void setProSubtotalPro(Integer proSubtotalPro) {
        this.proSubtotalPro = proSubtotalPro;
    }

    public Integer getProTotalPro() {
        return proTotalPro;
    }

    public void setProTotalPro(Integer proTotalPro) {
        this.proTotalPro = proTotalPro;
    }

    public BigDecimal getProSubPrec() {
        return proSubPrec;
    }

    public void setProSubPrec(BigDecimal proSubPrec) {
        this.proSubPrec = proSubPrec;
    }

    public BigDecimal getProTotalPrec() {
        return proTotalPrec;
    }

    public void setProTotalPrec(BigDecimal proTotalPrec) {
        this.proTotalPrec = proTotalPrec;
    }

    public BigDecimal getProTotalIva() {
        return proTotalIva;
    }

    public void setProTotalIva(BigDecimal proTotalIva) {
        this.proTotalIva = proTotalIva;
    }

    @XmlTransient
    public List<EquipoProteccion> getEquipoProteccionList() {
        return equipoProteccionList;
    }

    public void setEquipoProteccionList(List<EquipoProteccion> equipoProteccionList) {
        this.equipoProteccionList = equipoProteccionList;
    }

    @XmlTransient
    public List<DarBaja> getDarBajaList() {
        return darBajaList;
    }

    public void setDarBajaList(List<DarBaja> darBajaList) {
        this.darBajaList = darBajaList;
    }

    @XmlTransient
    public List<Herramienta> getHerramientaList() {
        return herramientaList;
    }

    public void setHerramientaList(List<Herramienta> herramientaList) {
        this.herramientaList = herramientaList;
    }

    @XmlTransient
    public List<Producto> getProductoList() {
        return productoList;
    }

    public void setProductoList(List<Producto> productoList) {
        this.productoList = productoList;
    }

    @XmlTransient
    public List<Vehiculos> getVehiculosList() {
        return vehiculosList;
    }

    public void setVehiculosList(List<Vehiculos> vehiculosList) {
        this.vehiculosList = vehiculosList;
    }

    @XmlTransient
    public List<AsignarProyecto> getAsignarProyectoList() {
        return asignarProyectoList;
    }

    public void setAsignarProyectoList(List<AsignarProyecto> asignarProyectoList) {
        this.asignarProyectoList = asignarProyectoList;
    }

    @XmlTransient
    public List<Cotizacion> getCotizacionList() {
        return cotizacionList;
    }

    public void setCotizacionList(List<Cotizacion> cotizacionList) {
        this.cotizacionList = cotizacionList;
    }

    @XmlTransient
    public List<Entradas> getEntradasList() {
        return entradasList;
    }

    public void setEntradasList(List<Entradas> entradasList) {
        this.entradasList = entradasList;
    }

    @XmlTransient
    public List<MaterialAcabado> getMaterialAcabadoList() {
        return materialAcabadoList;
    }

    public void setMaterialAcabadoList(List<MaterialAcabado> materialAcabadoList) {
        this.materialAcabadoList = materialAcabadoList;
    }

    public Bodega getBdId() {
        return bdId;
    }

    public void setBdId(Bodega bdId) {
        this.bdId = bdId;
    }

    @XmlTransient
    public List<Maquinaria> getMaquinariaList() {
        return maquinariaList;
    }

    public void setMaquinariaList(List<Maquinaria> maquinariaList) {
        this.maquinariaList = maquinariaList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (proId4 != null ? proId4.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Productos)) {
            return false;
        }
        Productos other = (Productos) object;
        if ((this.proId4 == null && other.proId4 != null) || (this.proId4 != null && !this.proId4.equals(other.proId4))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Productos[ proId4=" + proId4 + " ]";
    }
    
}
