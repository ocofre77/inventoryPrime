/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
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
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Vanessa
 */
@Entity
@Table(name = "devoluciones")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Devoluciones.findAll", query = "SELECT d FROM Devoluciones d")
    , @NamedQuery(name = "Devoluciones.findByDevId", query = "SELECT d FROM Devoluciones d WHERE d.devId = :devId")
    , @NamedQuery(name = "Devoluciones.findByDevNumero", query = "SELECT d FROM Devoluciones d WHERE d.devNumero = :devNumero")
    , @NamedQuery(name = "Devoluciones.findByDevFecha", query = "SELECT d FROM Devoluciones d WHERE d.devFecha = :devFecha")
    , @NamedQuery(name = "Devoluciones.findByDevCantidad", query = "SELECT d FROM Devoluciones d WHERE d.devCantidad = :devCantidad")
    , @NamedQuery(name = "Devoluciones.findByDevSubtotal", query = "SELECT d FROM Devoluciones d WHERE d.devSubtotal = :devSubtotal")
    , @NamedQuery(name = "Devoluciones.findByDevTotal", query = "SELECT d FROM Devoluciones d WHERE d.devTotal = :devTotal")
    , @NamedQuery(name = "Devoluciones.findByDevRazon", query = "SELECT d FROM Devoluciones d WHERE d.devRazon = :devRazon")
    , @NamedQuery(name = "Devoluciones.findByDevTotalIva", query = "SELECT d FROM Devoluciones d WHERE d.devTotalIva = :devTotalIva")})
public class Devoluciones implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "DEV_ID")
    private Integer devId;
    @Size(max = 15)
    @Column(name = "DEV_NUMERO")
    private String devNumero;
    @Basic(optional = false)
    @NotNull
    @Column(name = "DEV_FECHA")
    @Temporal(TemporalType.DATE)
    private Date devFecha;
    @Basic(optional = false)
    @NotNull
    @Column(name = "DEV_CANTIDAD")
    private BigDecimal devCantidad;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "DEV_SUBTOTAL")
    private BigDecimal devSubtotal;
    @Column(name = "DEV_TOTAL")
    private BigDecimal devTotal;
    @Size(max = 800)
    @Column(name = "DEV_RAZON")
    private String devRazon;
    @Column(name = "DEV_TOTAL_IVA")
    private BigDecimal devTotalIva;
    @JoinColumn(name = "SAL_ID", referencedColumnName = "SAL_ID")
    @ManyToOne
    private AsignarProyecto salId;

    public Devoluciones() {
    }

    public Devoluciones(Integer devId) {
        this.devId = devId;
    }

    public Devoluciones(Integer devId, Date devFecha, BigDecimal devCantidad) {
        this.devId = devId;
        this.devFecha = devFecha;
        this.devCantidad = devCantidad;
    }
    
     public Devoluciones(Integer devId, Date devFecha, BigDecimal devCantidad, String devRazon, String devNumero, AsignarProyecto salId) {
        this.devId = devId;
        this.devFecha = devFecha;
        this.devCantidad = devCantidad;
        this.devRazon = devRazon;
        this.devNumero = devNumero;
        this.salId = salId;
    }
     public Devoluciones(Integer devId, Date devFecha, BigDecimal devCantidad, BigDecimal precio, String devRazon, String devNumero, AsignarProyecto salId) {
        this.devId = devId;
        this.devFecha = devFecha;
        this.devCantidad = devCantidad;
        this.devSubtotal = precio;
        this.devRazon = devRazon;
        this.devNumero = devNumero;
        this.salId = salId;
    }

    
    
    

    public Integer getDevId() {
        return devId;
    }

    public void setDevId(Integer devId) {
        this.devId = devId;
    }

    public String getDevNumero() {
        return devNumero;
    }

    public void setDevNumero(String devNumero) {
        this.devNumero = devNumero;
    }
    
    public Date getDevFecha() {
        return devFecha;
    }

    public void setDevFecha(Date devFecha) {
        this.devFecha = devFecha;
    }

    public BigDecimal getDevCantidad() {
        return devCantidad;
    }

    public void setDevCantidad(BigDecimal devCantidad) {
        this.devCantidad = devCantidad;
    }

    public BigDecimal getDevSubtotal() {
        return devSubtotal;
    }

    public void setDevSubtotal(BigDecimal devSubtotal) {
        this.devSubtotal = devSubtotal;
    }

    public BigDecimal getDevTotal() {
        return devTotal;
    }

    public void setDevTotal(BigDecimal devTotal) {
        this.devTotal = devTotal;
    }

    public String getDevRazon() {
        return devRazon;
    }

    public void setDevRazon(String devRazon) {
        this.devRazon = devRazon;
    }

    public BigDecimal getDevTotalIva() {
        return devTotalIva;
    }

    public void setDevTotalIva(BigDecimal devTotalIva) {
        this.devTotalIva = devTotalIva;
    }

    public AsignarProyecto getSalId() {
        return salId;
    }

    public void setSalId(AsignarProyecto salId) {
        this.salId = salId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (devId != null ? devId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Devoluciones)) {
            return false;
        }
        Devoluciones other = (Devoluciones) object;
        if ((this.devId == null && other.devId != null) || (this.devId != null && !this.devId.equals(other.devId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Devoluciones[ devId=" + devId + " ]";
    }
    
}
