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
@Table(name = "herramienta")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Herramienta.findAll", query = "SELECT h FROM Herramienta h")
    , @NamedQuery(name = "Herramienta.findByHerId", query = "SELECT h FROM Herramienta h WHERE h.herId = :herId")
    , @NamedQuery(name = "Herramienta.findByHerEstado", query = "SELECT h FROM Herramienta h WHERE h.herEstado = :herEstado")
    , @NamedQuery(name = "Herramienta.findByHerFechaMantenimiento", query = "SELECT h FROM Herramienta h WHERE h.herFechaMantenimiento = :herFechaMantenimiento")
    , @NamedQuery(name = "Herramienta.findByHerRazonMantenimiento", query = "SELECT h FROM Herramienta h WHERE h.herRazonMantenimiento = :herRazonMantenimiento")
    , @NamedQuery(name = "Herramienta.findByHerFecCadaMante", query = "SELECT h FROM Herramienta h WHERE h.herFecCadaMante = :herFecCadaMante")
    , @NamedQuery(name = "Herramienta.findByHerProAdicionales", query = "SELECT h FROM Herramienta h WHERE h.herProAdicionales = :herProAdicionales")})
public class Herramienta implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "HER_ID")
    private Integer herId;
    @Size(max = 100)
    @Column(name = "HER_ESTADO")
    private String herEstado;
    @Column(name = "HER_FECHA_MANTENIMIENTO")
    @Temporal(TemporalType.DATE)
    private Date herFechaMantenimiento;
    @Size(max = 200)
    @Column(name = "HER_RAZON_MANTENIMIENTO")
    private String herRazonMantenimiento;
    @Size(max = 100)
    @Column(name = "HER_FEC_CADA_MANTE")
    private String herFecCadaMante;
    @Size(max = 2000)
    @Column(name = "HER_PRO_ADICIONALES")
    private String herProAdicionales;
    @JoinColumn(name = "PRO_ID4", referencedColumnName = "PRO_ID4")
    @ManyToOne
    private Productos proId4;

    public Herramienta() {
    }

    public Herramienta(Integer herId) {
        this.herId = herId;
    }

    public Integer getHerId() {
        return herId;
    }

    public void setHerId(Integer herId) {
        this.herId = herId;
    }

    public String getHerEstado() {
        return herEstado;
    }

    public void setHerEstado(String herEstado) {
        this.herEstado = herEstado;
    }

    public Date getHerFechaMantenimiento() {
        return herFechaMantenimiento;
    }

    public void setHerFechaMantenimiento(Date herFechaMantenimiento) {
        this.herFechaMantenimiento = herFechaMantenimiento;
    }

    public String getHerRazonMantenimiento() {
        return herRazonMantenimiento;
    }

    public void setHerRazonMantenimiento(String herRazonMantenimiento) {
        this.herRazonMantenimiento = herRazonMantenimiento;
    }

    public String getHerFecCadaMante() {
        return herFecCadaMante;
    }

    public void setHerFecCadaMante(String herFecCadaMante) {
        this.herFecCadaMante = herFecCadaMante;
    }

    public String getHerProAdicionales() {
        return herProAdicionales;
    }

    public void setHerProAdicionales(String herProAdicionales) {
        this.herProAdicionales = herProAdicionales;
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
        hash += (herId != null ? herId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Herramienta)) {
            return false;
        }
        Herramienta other = (Herramienta) object;
        if ((this.herId == null && other.herId != null) || (this.herId != null && !this.herId.equals(other.herId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Herramienta[ herId=" + herId + " ]";
    }
    
}
