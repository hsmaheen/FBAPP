/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.iss.foodbasket.models;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author A0109314N
 */
@Entity
@Table(name = "orders")
@XmlRootElement
@NamedQueries(
{
    @NamedQuery(name = "Orders.findAll", query = "SELECT o FROM Orders o"),
    @NamedQuery(name = "Orders.findByOrderID", query = "SELECT o FROM Orders o WHERE o.orderID = :orderID"),
    @NamedQuery(name = "Orders.findByOrderDate", query = "SELECT o FROM Orders o WHERE o.orderDate = :orderDate"),
    @NamedQuery(name = "Orders.findByOrderAddress", query = "SELECT o FROM Orders o WHERE o.orderAddress = :orderAddress"),
    @NamedQuery(name = "Orders.findByOTotPrice", query = "SELECT o FROM Orders o WHERE o.oTotPrice = :oTotPrice")
})
public class Orders implements Serializable
{
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "OrderID")
    private Integer orderID;
    @Basic(optional = false)
    @NotNull
    @Column(name = "OrderDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date orderDate;
    @Size(max = 45)
    @Column(name = "OrderAddress")
    private String orderAddress;
    @Basic(optional = false)
    @NotNull
    @Column(name = "OTotPrice")
    private float oTotPrice;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "orderID")
    private Collection<Orderdetails> orderdetailsCollection;
    @JoinColumn(name = "userid", referencedColumnName = "userid")
    @ManyToOne(optional = false)
    private Users userid;

    public Orders()
    {
    }

    public Orders(Integer orderID)
    {
        this.orderID = orderID;
    }

    public Orders(Integer orderID, Date orderDate, float oTotPrice)
    {
        this.orderID = orderID;
        this.orderDate = orderDate;
        this.oTotPrice = oTotPrice;
    }

    public Integer getOrderID()
    {
        return orderID;
    }

    public void setOrderID(Integer orderID)
    {
        this.orderID = orderID;
    }

    public Date getOrderDate()
    {
        return orderDate;
    }

    public void setOrderDate(Date orderDate)
    {
        this.orderDate = orderDate;
    }

    public String getOrderAddress()
    {
        return orderAddress;
    }

    public void setOrderAddress(String orderAddress)
    {
        this.orderAddress = orderAddress;
    }

    public float getOTotPrice()
    {
        return oTotPrice;
    }

    public void setOTotPrice(float oTotPrice)
    {
        this.oTotPrice = oTotPrice;
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

    public Users getUserid()
    {
        return userid;
    }

    public void setUserid(Users userid)
    {
        this.userid = userid;
    }

    @Override
    public int hashCode()
    {
        int hash = 0;
        hash += (orderID != null ? orderID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object)
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Orders))
        {
            return false;
        }
        Orders other = (Orders) object;
        if ((this.orderID == null && other.orderID != null) || (this.orderID != null && !this.orderID.equals(other.orderID)))
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return "com.iss.foodbasket.models.Orders[ orderID=" + orderID + " ]";
    }
    
}
