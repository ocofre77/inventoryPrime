/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
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
@Table(name = "producto")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Producto.findAll", query = "SELECT p FROM Producto p")
    , @NamedQuery(name = "Producto.findByProdId", query = "SELECT p FROM Producto p WHERE p.prodId = :prodId")
    , @NamedQuery(name = "Producto.findByProdPeriodoCadu", query = "SELECT p FROM Producto p WHERE p.prodPeriodoCadu = :prodPeriodoCadu")
    , @NamedQuery(name = "Producto.findByProdFechaIng", query = "SELECT p FROM Producto p WHERE p.prodFechaIng = :prodFechaIng")})
public class Producto implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "PROD_ID")
    private Integer prodId;
    @Size(max = 30)
    @Column(name = "PROD_PERIODO_CADU")
    private String prodPeriodoCadu;
    @Column(name = "PROD_FECHA_ING")
    @Temporal(TemporalType.DATE)
    private Date prodFechaIng;
    @JoinColumn(name = "PRO_ID4", referencedColumnName = "PRO_ID4")
    @ManyToOne
    private Productos proId4;

    public Producto() {
    }

    public Producto(Integer prodId) {
        this.prodId = prodId;
    }

    public Integer getProdId() {
        return prodId;
    }

    public void setProdId(Integer prodId) {
        this.prodId = prodId;
    }

    public String getProdPeriodoCadu() {
        return prodPeriodoCadu;
    }

    public void setProdPeriodoCadu(String prodPeriodoCadu) {
        this.prodPeriodoCadu = prodPeriodoCadu;
    }

    public Date getProdFechaIng() {
        return prodFechaIng;
    }

    public void setProdFechaIng(Date prodFechaIng) {
        this.prodFechaIng = prodFechaIng;
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
        hash += (prodId != null ? prodId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Producto)) {
            return false;
        }
        Producto other = (Producto) object;
        if ((this.prodId == null && other.prodId != null) || (this.prodId != null && !this.prodId.equals(other.prodId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Producto[ prodId=" + prodId + " ]";
    }
    
}
