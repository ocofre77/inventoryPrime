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
@Table(name = "asignar_proyecto")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "AsignarProyecto.findAll", query = "SELECT a FROM AsignarProyecto a")
    , @NamedQuery(name = "AsignarProyecto.findBySalId", query = "SELECT a FROM AsignarProyecto a WHERE a.salId = :salId")
    , @NamedQuery(name = "AsignarProyecto.findBySalNumero", query = "SELECT a FROM AsignarProyecto a WHERE a.salNumero = :salNumero")
    , @NamedQuery(name = "AsignarProyecto.findBySalFecha", query = "SELECT a FROM AsignarProyecto a WHERE a.salFecha = :salFecha")
    , @NamedQuery(name = "AsignarProyecto.findBySalTransportista", query = "SELECT a FROM AsignarProyecto a WHERE a.salTransportista = :salTransportista")
    , @NamedQuery(name = "AsignarProyecto.findBySalCantidad", query = "SELECT a FROM AsignarProyecto a WHERE a.salCantidad = :salCantidad")
    , @NamedQuery(name = "AsignarProyecto.findBySalSubtotal", query = "SELECT a FROM AsignarProyecto a WHERE a.salSubtotal = :salSubtotal")
    , @NamedQuery(name = "AsignarProyecto.findBySalTotal", query = "SELECT a FROM AsignarProyecto a WHERE a.salTotal = :salTotal")
    , @NamedQuery(name = "AsignarProyecto.findBySalTotalIva", query = "SELECT a FROM AsignarProyecto a WHERE a.salTotalIva = :salTotalIva")
    , @NamedQuery(name = "AsignarProyecto.findBySalIngresoadicional", query = "SELECT a FROM AsignarProyecto a WHERE a.salIngresoadicional = :salIngresoadicional")
    , @NamedQuery(name = "AsignarProyecto.findBySalObservaciones", query = "SELECT a FROM AsignarProyecto a WHERE a.salObservaciones = :salObservaciones")
    , @NamedQuery(name = "AsignarProyecto.findBySalTotalproyecto", query = "SELECT a FROM AsignarProyecto a WHERE a.salTotalproyecto = :salTotalproyecto")})
public class AsignarProyecto implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "SAL_ID")
    private Integer salId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 15)
    @Column(name = "SAL_NUMERO")
    private String salNumero;
    @Basic(optional = false)
    @NotNull
    @Column(name = "SAL_FECHA")
    @Temporal(TemporalType.DATE)
    private Date salFecha;
    @Size(max = 40)
    @Column(name = "SAL_TRANSPORTISTA")
    private String salTransportista;
    @Basic(optional = false)
    @NotNull
    @Column(name = "SAL_CANTIDAD")
    private BigDecimal salCantidad;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "SAL_SUBTOTAL")
    private BigDecimal salSubtotal;
    @Column(name = "SAL_TOTAL")
    private BigDecimal salTotal;
    @Column(name = "SAL_TOTAL_IVA")
    private BigDecimal salTotalIva;
    @Column(name = "SAL_INGRESOADICIONAL")
    private BigDecimal salIngresoadicional;
    @Size(max = 1000)
    @Column(name = "SAL_OBSERVACIONES_")
    private String salObservaciones;
    @Column(name = "SAL_TOTALPROYECTO")
    private BigDecimal salTotalproyecto;
    @JoinColumn(name = "PROY_ID", referencedColumnName = "PROY_ID")
    @ManyToOne
    private Proyecto proyId;
    @JoinColumn(name = "PRO_ID4", referencedColumnName = "PRO_ID4")
    @ManyToOne
    private Productos proId4;
    @JoinColumn(name = "RES_ID", referencedColumnName = "RES_ID")
    @ManyToOne
    private Responsable resId;
    @OneToMany(mappedBy = "salId")
    private List<Devoluciones> devolucionesList;

    public AsignarProyecto() {
    }

    public AsignarProyecto(Integer salId) {
        this.salId = salId;
    }

    public AsignarProyecto(Integer salId, String salNumero, Date salFecha, BigDecimal salCantidad) {
        this.salId = salId;
        this.salNumero = salNumero;
        this.salFecha = salFecha;
        this.salCantidad = salCantidad;
    }

      public AsignarProyecto(Integer salId, Date salFecha, String salTransportista, BigDecimal salCantidad, String salObservaciones, Proyecto proyId, Productos proId4, Responsable resId) {
        this.salId = salId;
        this.salFecha = salFecha;
        this.salTransportista = salTransportista;
        this.salCantidad = salCantidad;
        this.salObservaciones = salObservaciones;
        this.proyId = proyId;
        this.proId4 = proId4;
        this.resId = resId;
    }

    public AsignarProyecto(Integer salId, Date salFecha, String salTransportista, BigDecimal salCantidad, String salObservaciones, BigDecimal salSubtotal, Proyecto proyId, Productos proId4, Responsable resId) {
        this.salId = salId;
        this.salFecha = salFecha;
        this.salTransportista = salTransportista;
        this.salCantidad = salCantidad;
        this.salObservaciones = salObservaciones;
        this.salSubtotal = salSubtotal;
        this.proyId = proyId;
        this.proId4 = proId4;
        this.resId = resId;
    }
    public Integer getSalId() {
        return salId;
    }

    public void setSalId(Integer salId) {
        this.salId = salId;
    }

    public String getSalNumero() {
        return salNumero;
    }

    public void setSalNumero(String salNumero) {
        this.salNumero = salNumero;
    }

    public Date getSalFecha() {
        return salFecha;
    }

    public void setSalFecha(Date salFecha) {
        this.salFecha = salFecha;
    }

    public String getSalTransportista() {
        return salTransportista;
    }

    public void setSalTransportista(String salTransportista) {
        this.salTransportista = salTransportista;
    }

    public BigDecimal getSalCantidad() {
        return salCantidad;
    }

    public void setSalCantidad(BigDecimal salCantidad) {
        this.salCantidad = salCantidad;
    }

    public BigDecimal getSalSubtotal() {
        return salSubtotal;
    }

    public void setSalSubtotal(BigDecimal salSubtotal) {
        this.salSubtotal = salSubtotal;
    }

    public BigDecimal getSalTotal() {
        return salTotal;
    }

    public void setSalTotal(BigDecimal salTotal) {
        this.salTotal = salTotal;
    }

    public BigDecimal getSalTotalIva() {
        return salTotalIva;
    }

    public void setSalTotalIva(BigDecimal salTotalIva) {
        this.salTotalIva = salTotalIva;
    }

    public BigDecimal getSalIngresoadicional() {
        return salIngresoadicional;
    }

    public void setSalIngresoadicional(BigDecimal salIngresoadicional) {
        this.salIngresoadicional = salIngresoadicional;
    }

    public String getSalObservaciones() {
        return salObservaciones;
    }

    public void setSalObservaciones(String salObservaciones) {
        this.salObservaciones = salObservaciones;
    }

    public BigDecimal getSalTotalproyecto() {
        return salTotalproyecto;
    }

    public void setSalTotalproyecto(BigDecimal salTotalproyecto) {
        this.salTotalproyecto = salTotalproyecto;
    }

    public Proyecto getProyId() {
        return proyId;
    }

    public void setProyId(Proyecto proyId) {
        this.proyId = proyId;
    }

    public Productos getProId4() {
        return proId4;
    }

    public void setProId4(Productos proId4) {
        this.proId4 = proId4;
    }

    public Responsable getResId() {
        return resId;
    }

    public void setResId(Responsable resId) {
        this.resId = resId;
    }

    @XmlTransient
    public List<Devoluciones> getDevolucionesList() {
        return devolucionesList;
    }

    public void setDevolucionesList(List<Devoluciones> devolucionesList) {
        this.devolucionesList = devolucionesList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (salId != null ? salId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AsignarProyecto)) {
            return false;
        }
        AsignarProyecto other = (AsignarProyecto) object;
        if ((this.salId == null && other.salId != null) || (this.salId != null && !this.salId.equals(other.salId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.AsignarProyecto[ salId=" + salId + " ]";
    }

    public void setSalNumero(int i) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
