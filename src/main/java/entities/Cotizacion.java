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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Vanessa
 */
@Entity
@Table(name = "cotizacion")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Cotizacion.findAll", query = "SELECT c FROM Cotizacion c")
    , @NamedQuery(name = "Cotizacion.findByCotId", query = "SELECT c FROM Cotizacion c WHERE c.cotId = :cotId")
    , @NamedQuery(name = "Cotizacion.findByCotNumero", query = "SELECT c FROM Cotizacion c WHERE c.cotNumero = :cotNumero")
    , @NamedQuery(name = "Cotizacion.findByCotFecha", query = "SELECT c FROM Cotizacion c WHERE c.cotFecha = :cotFecha")
    , @NamedQuery(name = "Cotizacion.findByCotPreUni", query = "SELECT c FROM Cotizacion c WHERE c.cotPreUni = :cotPreUni")
    , @NamedQuery(name = "Cotizacion.findByCotCantidad", query = "SELECT c FROM Cotizacion c WHERE c.cotCantidad = :cotCantidad")
    , @NamedQuery(name = "Cotizacion.findByCotSubtotal", query = "SELECT c FROM Cotizacion c WHERE c.cotSubtotal = :cotSubtotal")
    , @NamedQuery(name = "Cotizacion.findByCotTotal", query = "SELECT c FROM Cotizacion c WHERE c.cotTotal = :cotTotal")
    , @NamedQuery(name = "Cotizacion.findByCotTotalIva", query = "SELECT c FROM Cotizacion c WHERE c.cotTotalIva = :cotTotalIva")})
public class Cotizacion implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "COT_ID")
    private Integer cotId;
    @Size(max = 15)
    @Column(name = "COT_NUMERO")
    private String cotNumero;
    @Column(name = "COT_FECHA")
    @Temporal(TemporalType.DATE)
    private Date cotFecha;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "COT_PRE_UNI")
    private BigDecimal cotPreUni;
    @Column(name = "COT_CANTIDAD")
    private Integer cotCantidad;
    @Column(name = "COT_SUBTOTAL")
    private BigDecimal cotSubtotal;
    @Column(name = "COT_TOTAL")
    private BigDecimal cotTotal;
    @Column(name = "COT_TOTAL_IVA")
    private BigDecimal cotTotalIva;
    @JoinColumn(name = "PRO_ID4", referencedColumnName = "PRO_ID4")
    @ManyToOne
    private Productos proId4;
    @JoinColumn(name = "PROY_ID", referencedColumnName = "PROY_ID")
    @ManyToOne
    private Proyecto proyId;

    public Cotizacion() {
    }

    public Cotizacion(Integer cotId) {
        this.cotId = cotId;
    }

      public Cotizacion(Integer cotId, Date cotFecha, BigDecimal cotPreUni, Integer cotCantidad, BigDecimal cotSubtotal, Productos proId4, Proyecto proyId) {
        this.cotId = cotId;
        this.cotFecha = cotFecha;
        this.cotPreUni = cotPreUni;
        this.cotCantidad = cotCantidad;
        this.cotSubtotal = cotSubtotal;
        this.proId4 = proId4;
        this.proyId = proyId;
    }

    public Integer getCotId() {
        return cotId;
    }

    public void setCotId(Integer cotId) {
        this.cotId = cotId;
    }

    public String getCotNumero() {
        return cotNumero;
    }

    public void setCotNumero(String cotNumero) {
        this.cotNumero = cotNumero;
    }

    public Date getCotFecha() {
        return cotFecha;
    }

    public void setCotFecha(Date cotFecha) {
        this.cotFecha = cotFecha;
    }

    public BigDecimal getCotPreUni() {
        return cotPreUni;
    }

    public void setCotPreUni(BigDecimal cotPreUni) {
        this.cotPreUni = cotPreUni;
    }

    public Integer getCotCantidad() {
        return cotCantidad;
    }

    public void setCotCantidad(Integer cotCantidad) {
        this.cotCantidad = cotCantidad;
    }

    public BigDecimal getCotSubtotal() {
        return cotSubtotal;
    }

    public void setCotSubtotal(BigDecimal cotSubtotal) {
        this.cotSubtotal = cotSubtotal;
    }

    public BigDecimal getCotTotal() {
        return cotTotal;
    }

    public void setCotTotal(BigDecimal cotTotal) {
        this.cotTotal = cotTotal;
    }

    public BigDecimal getCotTotalIva() {
        return cotTotalIva;
    }

    public void setCotTotalIva(BigDecimal cotTotalIva) {
        this.cotTotalIva = cotTotalIva;
    }

    public Productos getProId4() {
        return proId4;
    }

    public void setProId4(Productos proId4) {
        this.proId4 = proId4;
    }

    public Proyecto getProyId() {
        return proyId;
    }

    public void setProyId(Proyecto proyId) {
        this.proyId = proyId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (cotId != null ? cotId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Cotizacion)) {
            return false;
        }
        Cotizacion other = (Cotizacion) object;
        if ((this.cotId == null && other.cotId != null) || (this.cotId != null && !this.cotId.equals(other.cotId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Cotizacion[ cotId=" + cotId + " ]";
    }
    
}
