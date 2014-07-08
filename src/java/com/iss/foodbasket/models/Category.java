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
@Table(name = "category")
@XmlRootElement
@NamedQueries(
{
    @NamedQuery(name = "Category.findAll", query = "SELECT c FROM Category c"),
    @NamedQuery(name = "Category.findByCategoryID", query = "SELECT c FROM Category c WHERE c.categoryID = :categoryID"),
    @NamedQuery(name = "Category.findByCName", query = "SELECT c FROM Category c WHERE c.cName = :cName")
})
public class Category implements Serializable
{
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "CategoryID")
    private Integer categoryID;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "CName")
    private String cName;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "pCatID")
    private Collection<Products> productsCollection;

    public Category()
    {
    }

    public Category(Integer categoryID)
    {
        this.categoryID = categoryID;
    }

    public Category(Integer categoryID, String cName)
    {
        this.categoryID = categoryID;
        this.cName = cName;
    }

    public Integer getCategoryID()
    {
        return categoryID;
    }

    public void setCategoryID(Integer categoryID)
    {
        this.categoryID = categoryID;
    }

    public String getCName()
    {
        return cName;
    }

    public void setCName(String cName)
    {
        this.cName = cName;
    }

    @XmlTransient
    public Collection<Products> getProductsCollection()
    {
        return productsCollection;
    }

    public void setProductsCollection(Collection<Products> productsCollection)
    {
        this.productsCollection = productsCollection;
    }

    @Override
    public int hashCode()
    {
        int hash = 0;
        hash += (categoryID != null ? categoryID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object)
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Category))
        {
            return false;
        }
        Category other = (Category) object;
        if ((this.categoryID == null && other.categoryID != null) || (this.categoryID != null && !this.categoryID.equals(other.categoryID)))
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return "com.iss.foodbasket.models.Category[ categoryID=" + categoryID + " ]";
    }
    
}
