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
@Table(name = "material_acabado")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MaterialAcabado.findAll", query = "SELECT m FROM MaterialAcabado m")
    , @NamedQuery(name = "MaterialAcabado.findByProId3", query = "SELECT m FROM MaterialAcabado m WHERE m.proId3 = :proId3")
    , @NamedQuery(name = "MaterialAcabado.findByMatTipoDeAcabado", query = "SELECT m FROM MaterialAcabado m WHERE m.matTipoDeAcabado = :matTipoDeAcabado")
    , @NamedQuery(name = "MaterialAcabado.findByMatColor", query = "SELECT m FROM MaterialAcabado m WHERE m.matColor = :matColor")
    , @NamedQuery(name = "MaterialAcabado.findByMatObservacion", query = "SELECT m FROM MaterialAcabado m WHERE m.matObservacion = :matObservacion")})
public class MaterialAcabado implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "PRO_ID3")
    private Integer proId3;
    @Size(max = 60)
    @Column(name = "MAT_TIPO_DE_ACABADO")
    private String matTipoDeAcabado;
    @Size(max = 30)
    @Column(name = "MAT_COLOR")
    private String matColor;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 900)
    @Column(name = "MAT_OBSERVACION")
    private String matObservacion;
    @JoinColumn(name = "PRO_ID4", referencedColumnName = "PRO_ID4")
    @ManyToOne
    private Productos proId4;

    public MaterialAcabado() {
    }

    public MaterialAcabado(Integer proId3) {
        this.proId3 = proId3;
    }

    public MaterialAcabado(Integer proId3, String matObservacion) {
        this.proId3 = proId3;
        this.matObservacion = matObservacion;
    }

    public Integer getProId3() {
        return proId3;
    }

    public void setProId3(Integer proId3) {
        this.proId3 = proId3;
    }

    public String getMatTipoDeAcabado() {
        return matTipoDeAcabado;
    }

    public void setMatTipoDeAcabado(String matTipoDeAcabado) {
        this.matTipoDeAcabado = matTipoDeAcabado;
    }

    public String getMatColor() {
        return matColor;
    }

    public void setMatColor(String matColor) {
        this.matColor = matColor;
    }

    public String getMatObservacion() {
        return matObservacion;
    }

    public void setMatObservacion(String matObservacion) {
        this.matObservacion = matObservacion;
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
        hash += (proId3 != null ? proId3.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof MaterialAcabado)) {
            return false;
        }
        MaterialAcabado other = (MaterialAcabado) object;
        if ((this.proId3 == null && other.proId3 != null) || (this.proId3 != null && !this.proId3.equals(other.proId3))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.MaterialAcabado[ proId3=" + proId3 + " ]";
    }
    
}
