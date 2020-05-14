/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
@Table(name = "proyecto")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Proyecto.findAll", query = "SELECT p FROM Proyecto p")
    , @NamedQuery(name = "Proyecto.findByProyId", query = "SELECT p FROM Proyecto p WHERE p.proyId = :proyId")
    , @NamedQuery(name = "Proyecto.findByProyNombre", query = "SELECT p FROM Proyecto p WHERE p.proyNombre = :proyNombre")
    , @NamedQuery(name = "Proyecto.findByProyDireccion", query = "SELECT p FROM Proyecto p WHERE p.proyDireccion = :proyDireccion")
    , @NamedQuery(name = "Proyecto.findByProyFechInicio", query = "SELECT p FROM Proyecto p WHERE p.proyFechInicio = :proyFechInicio")
    , @NamedQuery(name = "Proyecto.findByProyFechFin", query = "SELECT p FROM Proyecto p WHERE p.proyFechFin = :proyFechFin")
    , @NamedQuery(name = "Proyecto.findByProyEstado", query = "SELECT p FROM Proyecto p WHERE p.proyEstado = :proyEstado")
    , @NamedQuery(name = "Proyecto.findByProyObservacion", query = "SELECT p FROM Proyecto p WHERE p.proyObservacion = :proyObservacion")})
public class Proyecto implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "PROY_ID")
    private Integer proyId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "PROY_NOMBRE")
    private String proyNombre;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "PROY_DIRECCION")
    private String proyDireccion;
    @Basic(optional = false)
    @NotNull
    @Column(name = "PROY_FECH_INICIO")
    @Temporal(TemporalType.DATE)
    private Date proyFechInicio;
    @Basic(optional = false)
    @NotNull
    @Column(name = "PROY_FECH_FIN")
    @Temporal(TemporalType.DATE)
    private Date proyFechFin;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "PROY_ESTADO")
    private String proyEstado;
    @Size(max = 2000)
    @Column(name = "PROY_OBSERVACION")
    private String proyObservacion;
    @OneToMany(mappedBy = "proyId")
    private List<AsignarProyecto> asignarProyectoList;
    @OneToMany(mappedBy = "proyId")
    private List<Cotizacion> cotizacionList;

    public Proyecto() {
    }

    public Proyecto(Integer proyId) {
        this.proyId = proyId;
    }

    public Proyecto(Integer proyId, String proyNombre, String proyDireccion, Date proyFechInicio, Date proyFechFin, String proyEstado) {
        this.proyId = proyId;
        this.proyNombre = proyNombre;
        this.proyDireccion = proyDireccion;
        this.proyFechInicio = proyFechInicio;
        this.proyFechFin = proyFechFin;
        this.proyEstado = proyEstado;
    }

    public Integer getProyId() {
        return proyId;
    }

    public void setProyId(Integer proyId) {
        this.proyId = proyId;
    }

    public String getProyNombre() {
        return proyNombre;
    }

    public void setProyNombre(String proyNombre) {
        this.proyNombre = proyNombre;
    }

    public String getProyDireccion() {
        return proyDireccion;
    }

    public void setProyDireccion(String proyDireccion) {
        this.proyDireccion = proyDireccion;
    }

    public Date getProyFechInicio() {
        return proyFechInicio;
    }

    public void setProyFechInicio(Date proyFechInicio) {
        this.proyFechInicio = proyFechInicio;
    }

    public Date getProyFechFin() {
        return proyFechFin;
    }

    public void setProyFechFin(Date proyFechFin) {
        this.proyFechFin = proyFechFin;
    }

    public String getProyEstado() {
        return proyEstado;
    }

    public void setProyEstado(String proyEstado) {
        this.proyEstado = proyEstado;
    }

    public String getProyObservacion() {
        return proyObservacion;
    }

    public void setProyObservacion(String proyObservacion) {
        this.proyObservacion = proyObservacion;
    }

    @XmlTransient
    public List<AsignarProyecto> getAsignarProyectoList() {
        return asignarProyectoList;
    }

    public void setAsignarProyectoList(List<AsignarProyecto> asignarProyectoList) {
        this.asignarProyectoList = asignarProyectoList;
    }

    @XmlTransient
    public List<Cotizacion> getCotizacionList() {
        return cotizacionList;
    }

    public void setCotizacionList(List<Cotizacion> cotizacionList) {
        this.cotizacionList = cotizacionList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (proyId != null ? proyId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Proyecto)) {
            return false;
        }
        Proyecto other = (Proyecto) object;
        if ((this.proyId == null && other.proyId != null) || (this.proyId != null && !this.proyId.equals(other.proyId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Proyecto[ proyId=" + proyId + " ]";
    }
    
}
