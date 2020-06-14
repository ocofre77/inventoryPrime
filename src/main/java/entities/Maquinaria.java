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
@Table(name = "maquinaria")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Maquinaria.findAll", query = "SELECT m FROM Maquinaria m")
    , @NamedQuery(name = "Maquinaria.findByMaqId", query = "SELECT m FROM Maquinaria m WHERE m.maqId = :maqId")
    , @NamedQuery(name = "Maquinaria.findByMaqEstado", query = "SELECT m FROM Maquinaria m WHERE m.maqEstado = :maqEstado")
    , @NamedQuery(name = "Maquinaria.findByMaqFechaMantenimiento", query = "SELECT m FROM Maquinaria m WHERE m.maqFechaMantenimiento = :maqFechaMantenimiento")
    , @NamedQuery(name = "Maquinaria.findByMaqRazonMante", query = "SELECT m FROM Maquinaria m WHERE m.maqRazonMante = :maqRazonMante")
    , @NamedQuery(name = "Maquinaria.findByMaqFecCadaMante", query = "SELECT m FROM Maquinaria m WHERE m.maqFecCadaMante = :maqFecCadaMante")
    , @NamedQuery(name = "Maquinaria.findByMaqPlaca", query = "SELECT m FROM Maquinaria m WHERE m.maqPlaca = :maqPlaca")
    , @NamedQuery(name = "Maquinaria.findByMaqProdAdiciona", query = "SELECT m FROM Maquinaria m WHERE m.maqProdAdiciona = :maqProdAdiciona")})
public class Maquinaria implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "MAQ_ID")
    private Integer maqId;
    @Size(max = 40)
    @Column(name = "MAQ_ESTADO")
    private String maqEstado;
    @Column(name = "MAQ_FECHA_MANTENIMIENTO")
    @Temporal(TemporalType.DATE)
    private Date maqFechaMantenimiento;
    @Size(max = 300)
    @Column(name = "MAQ_RAZON_MANTE")
    private String maqRazonMante;
    @Size(max = 100)
    @Column(name = "MAQ_FEC_CADA_MANTE")
    private String maqFecCadaMante;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "MAQ_PLACA")
    private String maqPlaca;
    @Size(max = 200)
    @Column(name = "MAQ_PROD_ADICIONA")
    private String maqProdAdiciona;
    @JoinColumn(name = "PRO_ID4", referencedColumnName = "PRO_ID4")
    @ManyToOne
    private Productos proId4;

    public Maquinaria() {
    }

    public Maquinaria(Integer maqId) {
        this.maqId = maqId;
    }

    public Maquinaria(Integer maqId, String maqPlaca) {
        this.maqId = maqId;
        this.maqPlaca = maqPlaca;
    }

    public Integer getMaqId() {
        return maqId;
    }

    public void setMaqId(Integer maqId) {
        this.maqId = maqId;
    }

    public String getMaqEstado() {
        return maqEstado;
    }

    public void setMaqEstado(String maqEstado) {
        this.maqEstado = maqEstado;
    }

    public Date getMaqFechaMantenimiento() {
        return maqFechaMantenimiento;
    }

    public void setMaqFechaMantenimiento(Date maqFechaMantenimiento) {
        this.maqFechaMantenimiento = maqFechaMantenimiento;
    }

    public String getMaqRazonMante() {
        return maqRazonMante;
    }

    public void setMaqRazonMante(String maqRazonMante) {
        this.maqRazonMante = maqRazonMante;
    }

    public String getMaqFecCadaMante() {
        return maqFecCadaMante;
    }

    public void setMaqFecCadaMante(String maqFecCadaMante) {
        this.maqFecCadaMante = maqFecCadaMante;
    }

    public String getMaqPlaca() {
        return maqPlaca;
    }

    public void setMaqPlaca(String maqPlaca) {
        this.maqPlaca = maqPlaca;
    }

    public String getMaqProdAdiciona() {
        return maqProdAdiciona;
    }

    public void setMaqProdAdiciona(String maqProdAdiciona) {
        this.maqProdAdiciona = maqProdAdiciona;
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
        hash += (maqId != null ? maqId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Maquinaria)) {
            return false;
        }
        Maquinaria other = (Maquinaria) object;
        if ((this.maqId == null && other.maqId != null) || (this.maqId != null && !this.maqId.equals(other.maqId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Maquinaria[ maqId=" + maqId + " ]";
    }
    
}
