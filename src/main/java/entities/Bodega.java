/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
@Table(name = "bodega")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Bodega.findAll", query = "SELECT b FROM Bodega b")
    , @NamedQuery(name = "Bodega.findByBdId", query = "SELECT b FROM Bodega b WHERE b.bdId = :bdId")
    , @NamedQuery(name = "Bodega.findByBdNombre", query = "SELECT b FROM Bodega b WHERE b.bdNombre = :bdNombre")
    , @NamedQuery(name = "Bodega.findByBdDireccion", query = "SELECT b FROM Bodega b WHERE b.bdDireccion = :bdDireccion")
    , @NamedQuery(name = "Bodega.findByBdTelefono", query = "SELECT b FROM Bodega b WHERE b.bdTelefono = :bdTelefono")
    , @NamedQuery(name = "Bodega.findByBdObservacion", query = "SELECT b FROM Bodega b WHERE b.bdObservacion = :bdObservacion")})
public class Bodega implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "BD_ID")
    private Integer bdId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "BD_NOMBRE")
    private String bdNombre;
    @Size(max = 100)
    @Column(name = "BD_DIRECCION")
    private String bdDireccion;
    @Size(max = 10)
    @Column(name = "BD_TELEFONO")
    private String bdTelefono;
    @Size(max = 1000)
    @Column(name = "BD_OBSERVACION")
    private String bdObservacion;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "bdId")
    private List<Productos> productosList;

    public Bodega() {
    }

    public Bodega(Integer bdId) {
        this.bdId = bdId;
    }

    public Bodega(Integer bdId, String bdNombre) {
        this.bdId = bdId;
        this.bdNombre = bdNombre;
    }

    public Integer getBdId() {
        return bdId;
    }

    public void setBdId(Integer bdId) {
        this.bdId = bdId;
    }

    public String getBdNombre() {
        return bdNombre;
    }

    public void setBdNombre(String bdNombre) {
        this.bdNombre = bdNombre;
    }

    public String getBdDireccion() {
        return bdDireccion;
    }

    public void setBdDireccion(String bdDireccion) {
        this.bdDireccion = bdDireccion;
    }

    public String getBdTelefono() {
        return bdTelefono;
    }

    public void setBdTelefono(String bdTelefono) {
        this.bdTelefono = bdTelefono;
    }

    public String getBdObservacion() {
        return bdObservacion;
    }

    public void setBdObservacion(String bdObservacion) {
        this.bdObservacion = bdObservacion;
    }

    @XmlTransient
    public List<Productos> getProductosList() {
        return productosList;
    }

    public void setProductosList(List<Productos> productosList) {
        this.productosList = productosList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (bdId != null ? bdId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Bodega)) {
            return false;
        }
        Bodega other = (Bodega) object;
        if ((this.bdId == null && other.bdId != null) || (this.bdId != null && !this.bdId.equals(other.bdId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return  bdNombre;
    }
    
}
