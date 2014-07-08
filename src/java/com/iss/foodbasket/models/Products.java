/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.iss.foodbasket.models;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
 * @author A0109314N
 */
@Entity
@Table(name = "products")
@XmlRootElement
@NamedQueries(
{
    @NamedQuery(name = "Products.findAll", query = "SELECT p FROM Products p"),
    @NamedQuery(name = "Products.findByProductID", query = "SELECT p FROM Products p WHERE p.productID = :productID"),
    @NamedQuery(name = "Products.findByPName", query = "SELECT p FROM Products p WHERE p.pName = :pName"),
    @NamedQuery(name = "Products.findByPDescription", query = "SELECT p FROM Products p WHERE p.pDescription = :pDescription"),
    @NamedQuery(name = "Products.findByPricePerUnit", query = "SELECT p FROM Products p WHERE p.pricePerUnit = :pricePerUnit"),
    @NamedQuery(name = "Products.findByPQuanity", query = "SELECT p FROM Products p WHERE p.pQuanity = :pQuanity"),
    @NamedQuery(name = "Products.findByPImg", query = "SELECT p FROM Products p WHERE p.pImg = :pImg")
})
public class Products implements Serializable
{
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ProductID")
    private Integer productID;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "PName")
    private String pName;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "PDescription")
    private String pDescription;
    @Basic(optional = false)
    @NotNull
    @Column(name = "PricePerUnit")
    private float pricePerUnit;
    @Basic(optional = false)
    @NotNull
    @Column(name = "PQuanity")
    private int pQuanity;
    @Size(max = 100)
    @Column(name = "PImg")
    private String pImg;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "productID")
    private Collection<Orderdetails> orderdetailsCollection;
    @JoinColumn(name = "PCatID", referencedColumnName = "CategoryID")
    @ManyToOne(optional = false)
    private Category pCatID;

    public Products()
    {
    }

    public Products(Integer productID)
    {
        this.productID = productID;
    }

    public Products(Integer productID, String pName, String pDescription, float pricePerUnit, int pQuanity)
    {
        this.productID = productID;
        this.pName = pName;
        this.pDescription = pDescription;
        this.pricePerUnit = pricePerUnit;
        this.pQuanity = pQuanity;
    }

    public Integer getProductID()
    {
        return productID;
    }

    public void setProductID(Integer productID)
    {
        this.productID = productID;
    }

    public String getPName()
    {
        return pName;
    }

    public void setPName(String pName)
    {
        this.pName = pName;
    }

    public String getPDescription()
    {
        return pDescription;
    }

    public void setPDescription(String pDescription)
    {
        this.pDescription = pDescription;
    }

    public float getPricePerUnit()
    {
        return pricePerUnit;
    }

    public void setPricePerUnit(float pricePerUnit)
    {
        this.pricePerUnit = pricePerUnit;
    }

    public int getPQuanity()
    {
        return pQuanity;
    }

    public void setPQuanity(int pQuanity)
    {
        this.pQuanity = pQuanity;
    }

    public String getPImg()
    {
        return pImg;
    }

    public void setPImg(String pImg)
    {
        this.pImg = pImg;
    }

    @XmlTransient
    public Collection<Orderdetails> getOrderdetailsCollection()
    {
        return orderdetailsCollection;
    }

    public void setOrderdetailsCollection(Collection<Orderdetails> orderdetailsCollection)
    {
        this.orderdetailsCollection = orderdetailsCollection;
    }

    public Category getPCatID()
    {
        return pCatID;
    }

    public void setPCatID(Category pCatID)
    {
        this.pCatID = pCatID;
    }

    @Override
    public int hashCode()
    {
        int hash = 0;
        hash += (productID != null ? productID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object)
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Products))
        {
            return false;
        }
        Products other = (Products) object;
        if ((this.productID == null && other.productID != null) || (this.productID != null && !this.productID.equals(other.productID)))
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return "com.iss.foodbasket.models.Products[ productID=" + productID + " ]";
    }
    
}
