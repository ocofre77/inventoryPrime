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
@Table(name = "equipo_proteccion")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "EquipoProteccion.findAll", query = "SELECT e FROM EquipoProteccion e")
    , @NamedQuery(name = "EquipoProteccion.findByProId2", query = "SELECT e FROM EquipoProteccion e WHERE e.proId2 = :proId2")
    , @NamedQuery(name = "EquipoProteccion.findByEquiMaterial", query = "SELECT e FROM EquipoProteccion e WHERE e.equiMaterial = :equiMaterial")
    , @NamedQuery(name = "EquipoProteccion.findByEquiDuracion", query = "SELECT e FROM EquipoProteccion e WHERE e.equiDuracion = :equiDuracion")
    , @NamedQuery(name = "EquipoProteccion.findByEquiObservacion", query = "SELECT e FROM EquipoProteccion e WHERE e.equiObservacion = :equiObservacion")})
public class EquipoProteccion implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "PRO_ID2")
    private Integer proId2;
    @Size(max = 30)
    @Column(name = "EQUI_MATERIAL")
    private String equiMaterial;
    @Size(max = 50)
    @Column(name = "EQUI_DURACION")
    private String equiDuracion;
    @Size(max = 900)
    @Column(name = "EQUI_OBSERVACION")
    private String equiObservacion;
    @JoinColumn(name = "PRO_ID4", referencedColumnName = "PRO_ID4")
    @ManyToOne
    private Productos proId4;

    public EquipoProteccion() {
    }

    public EquipoProteccion(Integer proId2) {
        this.proId2 = proId2;
    }

    public Integer getProId2() {
        return proId2;
    }

    public void setProId2(Integer proId2) {
        this.proId2 = proId2;
    }

    public String getEquiMaterial() {
        return equiMaterial;
    }

    public void setEquiMaterial(String equiMaterial) {
        this.equiMaterial = equiMaterial;
    }

    public String getEquiDuracion() {
        return equiDuracion;
    }

    public void setEquiDuracion(String equiDuracion) {
        this.equiDuracion = equiDuracion;
    }

    public String getEquiObservacion() {
        return equiObservacion;
    }

    public void setEquiObservacion(String equiObservacion) {
        this.equiObservacion = equiObservacion;
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
        hash += (proId2 != null ? proId2.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EquipoProteccion)) {
            return false;
        }
        EquipoProteccion other = (EquipoProteccion) object;
        if ((this.proId2 == null && other.proId2 != null) || (this.proId2 != null && !this.proId2.equals(other.proId2))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.EquipoProteccion[ proId2=" + proId2 + " ]";
    }
    
}
