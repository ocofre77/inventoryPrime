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
@Table(name = "proveedor")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Proveedor.findAll", query = "SELECT p FROM Proveedor p")
    , @NamedQuery(name = "Proveedor.findByProvId", query = "SELECT p FROM Proveedor p WHERE p.provId = :provId")
    , @NamedQuery(name = "Proveedor.findByProvNombre", query = "SELECT p FROM Proveedor p WHERE p.provNombre = :provNombre")
    , @NamedQuery(name = "Proveedor.findByProvRuc", query = "SELECT p FROM Proveedor p WHERE p.provRuc = :provRuc")
    , @NamedQuery(name = "Proveedor.findByProvDireccion", query = "SELECT p FROM Proveedor p WHERE p.provDireccion = :provDireccion")
    , @NamedQuery(name = "Proveedor.findByProvTelefono", query = "SELECT p FROM Proveedor p WHERE p.provTelefono = :provTelefono")
    , @NamedQuery(name = "Proveedor.findByProvCorreo", query = "SELECT p FROM Proveedor p WHERE p.provCorreo = :provCorreo")})
public class Proveedor implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "PROV_ID")
    private Integer provId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "PROV_NOMBRE")
    private String provNombre;
    @Size(max = 100)
    @Column(name = "PROV_RUC")
    private String provRuc;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 800)
    @Column(name = "PROV_DIRECCION")
    private String provDireccion;
    @Size(max = 10)
    @Column(name = "PROV_TELEFONO")
    private String provTelefono;
    @Size(max = 200)
    @Column(name = "PROV_CORREO")
    private String provCorreo;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "provId")
    private List<Entradas> entradasList;

    public Proveedor() {
    }

    public Proveedor(Integer provId) {
        this.provId = provId;
    }

    public Proveedor(Integer provId, String provNombre, String provDireccion) {
        this.provId = provId;
        this.provNombre = provNombre;
        this.provDireccion = provDireccion;
    }

    public Integer getProvId() {
        return provId;
    }

    public void setProvId(Integer provId) {
        this.provId = provId;
    }

    public String getProvNombre() {
        return provNombre;
    }

    public void setProvNombre(String provNombre) {
        this.provNombre = provNombre;
    }

    public String getProvRuc() {
        return provRuc;
    }

    public void setProvRuc(String provRuc) {
        this.provRuc = provRuc;
    }

    public String getProvDireccion() {
        return provDireccion;
    }

    public void setProvDireccion(String provDireccion) {
        this.provDireccion = provDireccion;
    }

    public String getProvTelefono() {
        return provTelefono;
    }

    public void setProvTelefono(String provTelefono) {
        this.provTelefono = provTelefono;
    }

    public String getProvCorreo() {
        return provCorreo;
    }

    public void setProvCorreo(String provCorreo) {
        this.provCorreo = provCorreo;
    }

    @XmlTransient
    public List<Entradas> getEntradasList() {
        return entradasList;
    }

    public void setEntradasList(List<Entradas> entradasList) {
        this.entradasList = entradasList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (provId != null ? provId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Proveedor)) {
            return false;
        }
        Proveedor other = (Proveedor) object;
        if ((this.provId == null && other.provId != null) || (this.provId != null && !this.provId.equals(other.provId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Proveedor[ provId=" + provId + " ]";
    }
    
}
