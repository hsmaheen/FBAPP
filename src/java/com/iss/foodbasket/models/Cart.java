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
import javax.persistence.Id;
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
@Table(name = "cart")
@XmlRootElement
@NamedQueries(
{
    @NamedQuery(name = "Cart.findAll", query = "SELECT c FROM Cart c"),
    @NamedQuery(name = "Cart.findByCartID", query = "SELECT c FROM Cart c WHERE c.cartID = :cartID"),
    @NamedQuery(name = "Cart.findByProductID", query = "SELECT c FROM Cart c WHERE c.productID = :productID"),
    @NamedQuery(name = "Cart.findByQuantity", query = "SELECT c FROM Cart c WHERE c.quantity = :quantity"),
    @NamedQuery(name = "Cart.findByCPrice", query = "SELECT c FROM Cart c WHERE c.cPrice = :cPrice")
})
public class Cart implements Serializable
{
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "CartID")
    private Integer cartID;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ProductID")
    private int productID;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Quantity")
    private int quantity;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CPrice")
    private float cPrice;

    public Cart()
    {
    }

    public Cart(Integer cartID)
    {
        this.cartID = cartID;
    }

    public Cart(Integer cartID, int productID, int quantity, float cPrice)
    {
        this.cartID = cartID;
        this.productID = productID;
        this.quantity = quantity;
        this.cPrice = cPrice;
    }

    public Integer getCartID()
    {
        return cartID;
    }

    public void setCartID(Integer cartID)
    {
        this.cartID = cartID;
    }

    public int getProductID()
    {
        return productID;
    }

    public void setProductID(int productID)
    {
        this.productID = productID;
    }

    public int getQuantity()
    {
        return quantity;
    }

    public void setQuantity(int quantity)
    {
        this.quantity = quantity;
    }

    public float getCPrice()
    {
        return cPrice;
    }

    public void setCPrice(float cPrice)
    {
        this.cPrice = cPrice;
    }

    @Override
    public int hashCode()
    {
        int hash = 0;
        hash += (cartID != null ? cartID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object)
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Cart))
        {
            return false;
        }
        Cart other = (Cart) object;
        if ((this.cartID == null && other.cartID != null) || (this.cartID != null && !this.cartID.equals(other.cartID)))
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return "com.iss.foodbasket.models.Cart[ cartID=" + cartID + " ]";
    }
    
}
