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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
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
@Table(name = "dar_baja")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DarBaja.findAll", query = "SELECT d FROM DarBaja d")
    , @NamedQuery(name = "DarBaja.findByDbId", query = "SELECT d FROM DarBaja d WHERE d.dbId = :dbId")
    , @NamedQuery(name = "DarBaja.findByDbNombrepro", query = "SELECT d FROM DarBaja d WHERE d.dbNombrepro = :dbNombrepro")
    , @NamedQuery(name = "DarBaja.findByDbRazones", query = "SELECT d FROM DarBaja d WHERE d.dbRazones = :dbRazones")})
public class DarBaja implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "DB_ID")
    private Integer dbId;
    @Size(max = 80)
    @Column(name = "DB_NOMBREPRO")
    private String dbNombrepro;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 9000)
    @Column(name = "DB_RAZONES")
    private String dbRazones;
    @JoinColumn(name = "PRO_ID4", referencedColumnName = "PRO_ID4")
    @ManyToOne
    private Productos proId4;

    public DarBaja() {
    }

    public DarBaja(Integer dbId) {
        this.dbId = dbId;
    }

    public DarBaja(Integer dbId, String dbRazones) {
        this.dbId = dbId;
        this.dbRazones = dbRazones;
    }

    public Integer getDbId() {
        return dbId;
    }

    public void setDbId(Integer dbId) {
        this.dbId = dbId;
    }

    public String getDbNombrepro() {
        return dbNombrepro;
    }

    public void setDbNombrepro(String dbNombrepro) {
        this.dbNombrepro = dbNombrepro;
    }

    public String getDbRazones() {
        return dbRazones;
    }

    public void setDbRazones(String dbRazones) {
        this.dbRazones = dbRazones;
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
        hash += (dbId != null ? dbId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof DarBaja)) {
            return false;
        }
        DarBaja other = (DarBaja) object;
        if ((this.dbId == null && other.dbId != null) || (this.dbId != null && !this.dbId.equals(other.dbId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.DarBaja[ dbId=" + dbId + " ]";
    }
    
}
