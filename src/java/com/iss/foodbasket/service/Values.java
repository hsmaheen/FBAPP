/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.iss.foodbasket.service;

import java.util.List;

/**
 *
 * @author A0109314N
 */
public class Values
{
    private String userid;
    private List<String> prodids;
    private List<String> quantities;

    /**
     * @return the userid
     */
    public String getUserid()
    {
        return userid;
    }

    /**
     * @param userid the userid to set
     */
    public void setUserid(String userid)
    {
        this.userid = userid;
    }

    /**
     * @return the prodids
     */
    public List<String> getProdids()
    {
        return prodids;
    }

    /**
     * @param prodids the prodids to set
     */
    public void setProdids(List<String> prodids)
    {
        this.prodids = prodids;
    }

    /**
     * @return the quantities
     */
    public List<String> getQuantities()
    {
        return quantities;
    }

    /**
     * @param quantities the quantities to set
     */
    public void setQuantities(List<String> quantities)
    {
        this.quantities = quantities;
    }
    
    
}
