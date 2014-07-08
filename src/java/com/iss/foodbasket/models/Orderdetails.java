/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.iss.foodbasket.models;

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
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author A0109314N
 */
@Entity
@Table(name = "orderdetails")
@XmlRootElement
@NamedQueries(
{
    @NamedQuery(name = "Orderdetails.findAll", query = "SELECT o FROM Orderdetails o"),
    @NamedQuery(name = "Orderdetails.findByOrderDetailsID", query = "SELECT o FROM Orderdetails o WHERE o.orderDetailsID = :orderDetailsID"),
    @NamedQuery(name = "Orderdetails.findByOrderQuant", query = "SELECT o FROM Orderdetails o WHERE o.orderQuant = :orderQuant"),
    @NamedQuery(name = "Orderdetails.findByOPrice", query = "SELECT o FROM Orderdetails o WHERE o.oPrice = :oPrice")
})
public class Orderdetails implements Serializable
{
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "OrderDetailsID")
    private Integer orderDetailsID;
    @Basic(optional = false)
    @NotNull
    @Column(name = "OrderQuant")
    private int orderQuant;
    @Basic(optional = false)
    @NotNull
    @Column(name = "OPrice")
    private double oPrice;
    @JoinColumn(name = "ProductID", referencedColumnName = "ProductID")
    @ManyToOne(optional = false)
    private Products productID;
    @JoinColumn(name = "OrderID", referencedColumnName = "OrderID")
    @ManyToOne(optional = false)
    private Orders orderID;

    public Orderdetails()
    {
    }

    public Orderdetails(Integer orderDetailsID)
    {
        this.orderDetailsID = orderDetailsID;
    }

    public Orderdetails(Integer orderDetailsID, int orderQuant, double oPrice)
    {
        this.orderDetailsID = orderDetailsID;
        this.orderQuant = orderQuant;
        this.oPrice = oPrice;
    }

    public Integer getOrderDetailsID()
    {
        return orderDetailsID;
    }

    public void setOrderDetailsID(Integer orderDetailsID)
    {
        this.orderDetailsID = orderDetailsID;
    }

    public int getOrderQuant()
    {
        return orderQuant;
    }

    public void setOrderQuant(int orderQuant)
    {
        this.orderQuant = orderQuant;
    }

    public double getOPrice()
    {
        return oPrice;
    }

    public void setOPrice(double oPrice)
    {
        this.oPrice = oPrice;
    }

    public Products getProductID()
    {
        return productID;
    }

    public void setProductID(Products productID)
    {
        this.productID = productID;
    }

    public Orders getOrderID()
    {
        return orderID;
    }

    public void setOrderID(Orders orderID)
    {
        this.orderID = orderID;
    }

    @Override
    public int hashCode()
    {
        int hash = 0;
        hash += (orderDetailsID != null ? orderDetailsID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object)
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Orderdetails))
        {
            return false;
        }
        Orderdetails other = (Orderdetails) object;
        if ((this.orderDetailsID == null && other.orderDetailsID != null) || (this.orderDetailsID != null && !this.orderDetailsID.equals(other.orderDetailsID)))
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return "com.iss.foodbasket.models.Orderdetails[ orderDetailsID=" + orderDetailsID + " ]";
    }
    
}
