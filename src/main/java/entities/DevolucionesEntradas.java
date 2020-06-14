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
@Table(name = "devoluciones_entradas")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DevolucionesEntradas.findAll", query = "SELECT d FROM DevolucionesEntradas d")
    , @NamedQuery(name = "DevolucionesEntradas.findByDeveId", query = "SELECT d FROM DevolucionesEntradas d WHERE d.deveId = :deveId")
    , @NamedQuery(name = "DevolucionesEntradas.findByDeveFecha", query = "SELECT d FROM DevolucionesEntradas d WHERE d.deveFecha = :deveFecha")
    , @NamedQuery(name = "DevolucionesEntradas.findByDeveCantidad", query = "SELECT d FROM DevolucionesEntradas d WHERE d.deveCantidad = :deveCantidad")
    , @NamedQuery(name = "DevolucionesEntradas.findByDeveRazon", query = "SELECT d FROM DevolucionesEntradas d WHERE d.deveRazon = :deveRazon")
    , @NamedQuery(name = "DevolucionesEntradas.findByDeveNumero", query = "SELECT d FROM DevolucionesEntradas d WHERE d.deveNumero = :deveNumero")
    , @NamedQuery(name = "DevolucionesEntradas.findByDevePUnitario", query = "SELECT d FROM DevolucionesEntradas d WHERE d.devePUnitario = :devePUnitario")
    , @NamedQuery(name = "DevolucionesEntradas.findByDeveSubtotal", query = "SELECT d FROM DevolucionesEntradas d WHERE d.deveSubtotal = :deveSubtotal")
    , @NamedQuery(name = "DevolucionesEntradas.findByDeveTotal", query = "SELECT d FROM DevolucionesEntradas d WHERE d.deveTotal = :deveTotal")})
public class DevolucionesEntradas implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "DEVE_ID")
    private Integer deveId;
    @Column(name = "DEVE_FECHA")
    @Temporal(TemporalType.DATE)
    private Date deveFecha;
    @Column(name = "DEVE_CANTIDAD")
    private Integer deveCantidad;
    @Size(max = 500)
    @Column(name = "DEVE_RAZON")
    private String deveRazon;
    @Size(max = 15)
    @Column(name = "DEVE_NUMERO")
    private String deveNumero;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "DEVE_P_UNITARIO")
    private BigDecimal devePUnitario;
    @Column(name = "DEVE_SUBTOTAL")
    private BigDecimal deveSubtotal;
    @Column(name = "DEVE_TOTAL")
    private BigDecimal deveTotal;
    @JoinColumn(name = "ENT_ID", referencedColumnName = "ENT_ID")
    @ManyToOne
    private Entradas entId;

    public DevolucionesEntradas() {
    }

    public DevolucionesEntradas(Integer deveId) {
        this.deveId = deveId;
    }
     public DevolucionesEntradas(Integer deveId, Date deveFecha, Integer deveCantidad, String deveNumero, BigDecimal devePUnitario, BigDecimal deveSubtotal) {
        this.deveId = deveId;
        this.deveFecha = deveFecha;
        this.deveCantidad = deveCantidad;
        this.deveNumero = deveNumero;
        this.devePUnitario = devePUnitario;
        this.deveSubtotal = deveSubtotal;
    }

    public DevolucionesEntradas(Integer deveId, Date deveFecha, Integer deveCantidad, String deveNumero, BigDecimal devePUnitario, BigDecimal deveSubtotal, Entradas entId) {
        this.deveId = deveId;
        this.deveFecha = deveFecha;
        this.deveCantidad = deveCantidad;
        this.deveNumero = deveNumero;
        this.devePUnitario = devePUnitario;
        this.deveSubtotal = deveSubtotal;
        this.entId = entId;
    }
    

    public Integer getDeveId() {
        return deveId;
    }

    public void setDeveId(Integer deveId) {
        this.deveId = deveId;
    }

    public Date getDeveFecha() {
        return deveFecha;
    }

    public void setDeveFecha(Date deveFecha) {
        this.deveFecha = deveFecha;
    }

    public Integer getDeveCantidad() {
        return deveCantidad;
    }

    public void setDeveCantidad(Integer deveCantidad) {
        this.deveCantidad = deveCantidad;
    }

    public String getDeveRazon() {
        return deveRazon;
    }

    public void setDeveRazon(String deveRazon) {
        this.deveRazon = deveRazon;
    }

    public String getDeveNumero() {
        return deveNumero;
    }

    public void setDeveNumero(String deveNumero) {
        this.deveNumero = deveNumero;
    }

    public BigDecimal getDevePUnitario() {
        return devePUnitario;
    }

    public void setDevePUnitario(BigDecimal devePUnitario) {
        this.devePUnitario = devePUnitario;
    }

    public BigDecimal getDeveSubtotal() {
        return deveSubtotal;
    }

    public void setDeveSubtotal(BigDecimal deveSubtotal) {
        this.deveSubtotal = deveSubtotal;
    }

    public BigDecimal getDeveTotal() {
        return deveTotal;
    }

    public void setDeveTotal(BigDecimal deveTotal) {
        this.deveTotal = deveTotal;
    }

    public Entradas getEntId() {
        return entId;
    }

    public void setEntId(Entradas entId) {
        this.entId = entId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (deveId != null ? deveId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof DevolucionesEntradas)) {
            return false;
        }
        DevolucionesEntradas other = (DevolucionesEntradas) object;
        if ((this.deveId == null && other.deveId != null) || (this.deveId != null && !this.deveId.equals(other.deveId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.DevolucionesEntradas[ deveId=" + deveId + " ]";
    }
    
}
