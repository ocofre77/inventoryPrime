/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Vanessa
 */
@Entity
@Table(name = "vehiculos")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Vehiculos.findAll", query = "SELECT v FROM Vehiculos v")
    , @NamedQuery(name = "Vehiculos.findByVehId", query = "SELECT v FROM Vehiculos v WHERE v.vehId = :vehId")
    , @NamedQuery(name = "Vehiculos.findByVehPlaca", query = "SELECT v FROM Vehiculos v WHERE v.vehPlaca = :vehPlaca")
    , @NamedQuery(name = "Vehiculos.findByVehAnio", query = "SELECT v FROM Vehiculos v WHERE v.vehAnio = :vehAnio")
    , @NamedQuery(name = "Vehiculos.findByVehColor", query = "SELECT v FROM Vehiculos v WHERE v.vehColor = :vehColor")})
public class Vehiculos implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "VEH_ID")
    private Integer vehId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "VEH_PLACA")
    private String vehPlaca;
    @Size(max = 5)
    @Column(name = "VEH_ANIO")
    private String vehAnio;
    @Size(max = 30)
    @Column(name = "VEH_COLOR")
    private String vehColor;
    @JoinColumn(name = "PRO_ID4", referencedColumnName = "PRO_ID4")
    @ManyToOne
    private Productos proId4;

    public Vehiculos() {
    }

    public Vehiculos(Integer vehId) {
        this.vehId = vehId;
    }

    public Vehiculos(Integer vehId, String vehPlaca) {
        this.vehId = vehId;
        this.vehPlaca = vehPlaca;
    }

    public Integer getVehId() {
        return vehId;
    }

    public void setVehId(Integer vehId) {
        this.vehId = vehId;
    }

    public String getVehPlaca() {
        return vehPlaca;
    }

    public void setVehPlaca(String vehPlaca) {
        this.vehPlaca = vehPlaca;
    }

    public String getVehAnio() {
        return vehAnio;
    }

    public void setVehAnio(String vehAnio) {
        this.vehAnio = vehAnio;
    }

    public String getVehColor() {
        return vehColor;
    }

    public void setVehColor(String vehColor) {
        this.vehColor = vehColor;
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
        hash += (vehId != null ? vehId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Vehiculos)) {
            return false;
        }
        Vehiculos other = (Vehiculos) object;
        if ((this.vehId == null && other.vehId != null) || (this.vehId != null && !this.vehId.equals(other.vehId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Vehiculos[ vehId=" + vehId + " ]";
    }
    
}
