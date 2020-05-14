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
 * @author Orlando
 */
@Entity
@Table(name = "producto_proveedor")
@NamedQueries({
    @NamedQuery(name = "ProductoProveedor.findAll", query = "SELECT p FROM ProductoProveedor p"),
    @NamedQuery(name = "ProductoProveedor.findByProvId", query = "SELECT p FROM ProductoProveedor p WHERE p.provId = :provId"),
    @NamedQuery(name = "ProductoProveedor.findByProvCodigoPro", query = "SELECT p FROM ProductoProveedor p WHERE p.provCodigoPro = :provCodigoPro"),
    @NamedQuery(name = "ProductoProveedor.findByProdCodigoPro", query = "SELECT p FROM ProductoProveedor p WHERE p.prodCodigoPro = :prodCodigoPro")
})
@XmlRootElement
public class ProductoProveedor implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Column(name="PROV_ID")
    private Integer provId;
    @Column(name="PROD_COD_PRO")
    private String prodCodigoPro;
    @Column(name="PROV_COD_PRO")
    private String provCodigoPro;

 
    public ProductoProveedor() {
    }

    public ProductoProveedor(Integer provId, String proCodigoPro, String provCodigoPro) {
        this.provId = provId;
        this.prodCodigoPro = proCodigoPro;
        this.provCodigoPro = provCodigoPro;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
   
    public Integer getProvId() {
        return provId;
    }

    public void setProvId(Integer provId) {
        this.provId = provId;
    }

    public String getProCodigoPro() {
        return prodCodigoPro;
    }

    public void setProCodigoPro(String proCodigopro) {
        this.prodCodigoPro = proCodigopro;
    }

    public String getProvCodigoProv() {
        return provCodigoPro;
    }

    public void setProvCodigoProv(String provCodigoPro) {
        this.provCodigoPro = provCodigoPro;
    }
   

}
