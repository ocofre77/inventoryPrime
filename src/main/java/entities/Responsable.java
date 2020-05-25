/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Vanessa
 */
@Entity
@Table(name = "responsable")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Responsable.findAll", query = "SELECT r FROM Responsable r")
    , @NamedQuery(name = "Responsable.findByResId", query = "SELECT r FROM Responsable r WHERE r.resId = :resId")
    , @NamedQuery(name = "Responsable.findByResNombre", query = "SELECT r FROM Responsable r WHERE r.resNombre = :resNombre")
    , @NamedQuery(name = "Responsable.findByResCedula", query = "SELECT r FROM Responsable r WHERE r.resCedula = :resCedula")
    , @NamedQuery(name = "Responsable.findByResCargo", query = "SELECT r FROM Responsable r WHERE r.resCargo = :resCargo")
    , @NamedQuery(name = "Responsable.findByResTelefono", query = "SELECT r FROM Responsable r WHERE r.resTelefono = :resTelefono")
    , @NamedQuery(name = "Responsable.findByResDireccion", query = "SELECT r FROM Responsable r WHERE r.resDireccion = :resDireccion")
    , @NamedQuery(name = "Responsable.findByResCorreo", query = "SELECT r FROM Responsable r WHERE r.resCorreo = :resCorreo")})
public class Responsable implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "RES_ID")
    private Integer resId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "RES_NOMBRE")
    private String resNombre;
    @Basic(optional = false)
    @NotNull
    @Column(name = "RES_CEDULA")
    private long resCedula;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "RES_CARGO")
    private String resCargo;
    @Column(name = "RES_TELEFONO")
    private Long resTelefono;
    @Size(max = 800)
    @Column(name = "RES_DIRECCION")
    private String resDireccion;
    @Size(max = 200)
    @Column(name = "RES_CORREO")
    private String resCorreo;
    @OneToMany(mappedBy = "resId")
    private List<AsignarProyecto> asignarProyectoList;

    public Responsable() {
    }

    public Responsable(Integer resId) {
        this.resId = resId;
    }

    public Responsable(Integer resId, String resNombre, long resCedula, String resCargo) {
        this.resId = resId;
        this.resNombre = resNombre;
        this.resCedula = resCedula;
        this.resCargo = resCargo;
    }

    public Integer getResId() {
        return resId;
    }

    public void setResId(Integer resId) {
        this.resId = resId;
    }

    public String getResNombre() {
        return resNombre;
    }

    public void setResNombre(String resNombre) {
        this.resNombre = resNombre;
    }

    public long getResCedula() {
        return resCedula;
    }

    public void setResCedula(long resCedula) {
        this.resCedula = resCedula;
    }

    public String getResCargo() {
        return resCargo;
    }

    public void setResCargo(String resCargo) {
        this.resCargo = resCargo;
    }

    public Long getResTelefono() {
        return resTelefono;
    }

    public void setResTelefono(Long resTelefono) {
        this.resTelefono = resTelefono;
    }

    public String getResDireccion() {
        return resDireccion;
    }

    public void setResDireccion(String resDireccion) {
        this.resDireccion = resDireccion;
    }

    public String getResCorreo() {
        return resCorreo;
    }

    public void setResCorreo(String resCorreo) {
        this.resCorreo = resCorreo;
    }

    @XmlTransient
    public List<AsignarProyecto> getAsignarProyectoList() {
        return asignarProyectoList;
    }

    public void setAsignarProyectoList(List<AsignarProyecto> asignarProyectoList) {
        this.asignarProyectoList = asignarProyectoList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (resId != null ? resId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Responsable)) {
            return false;
        }
        Responsable other = (Responsable) object;
        if ((this.resId == null && other.resId != null) || (this.resId != null && !this.resId.equals(other.resId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Responsable[ resId=" + resId + " ]";
    }
    
}
