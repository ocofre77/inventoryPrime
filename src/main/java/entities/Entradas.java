/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Vanessa
 */
@Entity
@Table(name = "entradas")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Entradas.findAll", query = "SELECT e FROM Entradas e")
    , @NamedQuery(name = "Entradas.findByEntId", query = "SELECT e FROM Entradas e WHERE e.entId = :entId")
    , @NamedQuery(name = "Entradas.findByEntCodigo", query = "SELECT e FROM Entradas e WHERE e.entCodigo = :entCodigo")
    , @NamedQuery(name = "Entradas.findByEntTransportista", query = "SELECT e FROM Entradas e WHERE e.entTransportista = :entTransportista")
    , @NamedQuery(name = "Entradas.findByEntLugarLlegada", query = "SELECT e FROM Entradas e WHERE e.entLugarLlegada = :entLugarLlegada")
    , @NamedQuery(name = "Entradas.findByEntCantidad", query = "SELECT e FROM Entradas e WHERE e.entCantidad = :entCantidad")
    , @NamedQuery(name = "Entradas.findByEntPrecioUni", query = "SELECT e FROM Entradas e WHERE e.entPrecioUni = :entPrecioUni")
    , @NamedQuery(name = "Entradas.findByEntFecha", query = "SELECT e FROM Entradas e WHERE e.entFecha = :entFecha")
    , @NamedQuery(name = "Entradas.findByEntSubtotal", query = "SELECT e FROM Entradas e WHERE e.entSubtotal = :entSubtotal")
    , @NamedQuery(name = "Entradas.findByEntNumero", query = "SELECT e FROM Entradas e WHERE e.entNumero = :entNumero")
    , @NamedQuery(name = "Entradas.findByEntTotal", query = "SELECT e FROM Entradas e WHERE e.entTotal = :entTotal")
    , @NamedQuery(name = "Entradas.findByEntTotaliva", query = "SELECT e FROM Entradas e WHERE e.entTotaliva = :entTotaliva")})
public class Entradas implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ENT_ID")
    private Integer entId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "ENT_CODIGO")
    private String entCodigo;
    @Size(max = 100)
    @Column(name = "ENT_TRANSPORTISTA")
    private String entTransportista;
    @Size(max = 100)
    @Column(name = "ENT_LUGAR_LLEGADA")
    private String entLugarLlegada;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ENT_CANTIDAD")
    private BigDecimal entCantidad;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(name = "ENT_PRECIO_UNI")
    private BigDecimal entPrecioUni;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ENT_FECHA")
    @Temporal(TemporalType.DATE)
    private Date entFecha;
    @Column(name = "ENT_SUBTOTAL")
    private BigDecimal entSubtotal;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 15)
    @Column(name = "ENT_NUMERO")
    private String entNumero;
    @Column(name = "ENT_TOTAL")
    private BigDecimal entTotal;
    @Column(name = "ENT_TOTALIVA")
    private BigDecimal entTotaliva;
    @OneToMany(mappedBy = "entId")
    private List<DevolucionesEntradas> devolucionesEntradasList;
    @JoinColumn(name = "PROV_ID", referencedColumnName = "PROV_ID")
    @ManyToOne(optional = false)
    private Proveedor provId;
    @JoinColumn(name = "PRO_ID4", referencedColumnName = "PRO_ID4")
    @ManyToOne
    private Productos proId4;

    public Entradas() {
    }

    public Entradas(Integer entId) {
        this.entId = entId;
    }

    public Entradas(Integer entId, String entCodigo, BigDecimal entCantidad, BigDecimal entPrecioUni, Date entFecha, String entNumero) {
        this.entId = entId;
        this.entCodigo = entCodigo;
        this.entCantidad = entCantidad;
        this.entPrecioUni = entPrecioUni;
        this.entFecha = entFecha;
        this.entNumero = entNumero;
    }

     public Entradas(Integer entId, Proveedor provId, Productos proId4, String entCodigo, String entFactura, String entTransportista, String entLugarLlegada, BigDecimal entCantidad, BigDecimal entPrecioUni, Date entFecha) {
        this.entId = entId;
        this.provId = provId;
        this.proId4 = proId4;
        this.entCodigo = entCodigo;
        this.entTransportista = entTransportista;
        this.entLugarLlegada = entLugarLlegada;
        this.entCantidad = entCantidad;
        this.entPrecioUni = entPrecioUni;
        this.entFecha = entFecha;       
    }

    public Entradas(Integer entId,Proveedor provId, Productos proId4, String entCodigo, String entFactura, String entTransportista, String entLugarLlegada, BigDecimal entCantidad, BigDecimal entPrecioUni, Date entFecha, BigDecimal entSubtotal) {
        this.entId = entId;
        this.entCodigo = entCodigo;
        this.entTransportista = entTransportista;
        this.entLugarLlegada = entLugarLlegada;
        this.entCantidad = entCantidad;
        this.entPrecioUni = entPrecioUni;
        this.entFecha = entFecha;
        this.entSubtotal = entSubtotal;
        this.provId = provId;
        this.proId4 = proId4;
    }
    
    public Integer getEntId() {
        return entId;
    }

    public void setEntId(Integer entId) {
        this.entId = entId;
    }

    public String getEntCodigo() {
        return entCodigo;
    }

    public void setEntCodigo(String entCodigo) {
        this.entCodigo = entCodigo;
    }

    public String getEntTransportista() {
        return entTransportista;
    }

    public void setEntTransportista(String entTransportista) {
        this.entTransportista = entTransportista;
    }

    public String getEntLugarLlegada() {
        return entLugarLlegada;
    }

    public void setEntLugarLlegada(String entLugarLlegada) {
        this.entLugarLlegada = entLugarLlegada;
    }

    public BigDecimal getEntCantidad() {
        return entCantidad;
    }

    public void setEntCantidad(BigDecimal entCantidad) {
        this.entCantidad = entCantidad;
    }

    public BigDecimal getEntPrecioUni() {
        return entPrecioUni;
    }

    public void setEntPrecioUni(BigDecimal entPrecioUni) {
        this.entPrecioUni = entPrecioUni;
    }

    public Date getEntFecha() {
        return entFecha;
    }

    public void setEntFecha(Date entFecha) {
        this.entFecha = entFecha;
    }

    public BigDecimal getEntSubtotal() {
        return entSubtotal;
    }

    public void setEntSubtotal(BigDecimal entSubtotal) {
        this.entSubtotal = entSubtotal;
    }

    public String getEntNumero() {
        return entNumero;
    }

    public void setEntNumero(String entNumero) {
        this.entNumero = entNumero;
    }

    public BigDecimal getEntTotal() {
        return entTotal;
    }

    public void setEntTotal(BigDecimal entTotal) {
        this.entTotal = entTotal;
    }

    public BigDecimal getEntTotaliva() {
        return entTotaliva;
    }

    public void setEntTotaliva(BigDecimal entTotaliva) {
        this.entTotaliva = entTotaliva;
    }

    @XmlTransient
    public List<DevolucionesEntradas> getDevolucionesEntradasList() {
        return devolucionesEntradasList;
    }

    public void setDevolucionesEntradasList(List<DevolucionesEntradas> devolucionesEntradasList) {
        this.devolucionesEntradasList = devolucionesEntradasList;
    }

    public Proveedor getProvId() {
        return provId;
    }

    public void setProvId(Proveedor provId) {
        this.provId = provId;
    }

    public Productos getProId4() {
        return proId4;
    }

    public void setProId4(Productos proId4) {
        this.proId4 = proId4;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (entId != null ? entId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Entradas)) {
            return false;
        }
        Entradas other = (Entradas) object;
        if ((this.entId == null && other.entId != null) || (this.entId != null && !this.entId.equals(other.entId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Entradas[ entId=" + entId + " ]";
    }
    
}
