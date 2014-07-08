/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iss.foodbasket.service;

import com.iss.foodbasket.ajax.Status;
import javax.ejb.EJB;
import javax.ws.rs.Path;
import com.iss.foodbasket.bizLogic.CustomerService;
import com.iss.foodbasket.bizLogic.EmailService;
import com.iss.foodbasket.bizLogic.HashedPasswordGenerator;
import com.iss.foodbasket.bizLogic.ProdctsServices;
import com.iss.foodbasket.models.Products;
import com.iss.foodbasket.models.Users;
import java.util.List;
import javax.json.JsonArray;
import javax.json.JsonValue;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import java.lang.String;
import java.util.ArrayList;
import javax.json.JsonObject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.core.Context;

/**
 *
 * @author A0109314N
 */
@Path("/User")
public class CustomerWebService
{
    @EJB EmailService email;
    @EJB
    CustomerService cuservice;
    @EJB
    ProdctsServices pservice;
    List<Products> prod;

    @Path("/register")
    @POST
    @Consumes("application/json")

    public Status registercustomer(Users u)
    {

        boolean result = cuservice.registerCustomer(u);
        if (result == true)
        {
            Status s = new Status();
            s.setError(false);
            return s;
        } else
        {
            Status s = new Status();
            s.setError(true);
            s.setErrorMessage("Sorry this username is already taken");
            return s;
        }

    }

    //SEE this later...place approp..
    @Path("/ptest")
    @GET
    @Produces("application/json")
    public List<Products> checkin()
    {
        prod = pservice.getAll();
        return prod;

    }

    @Path("/getP/{productID}")
    @GET
    @Consumes("application/json")
    @Produces("application/json")
    public Products getProduct(@PathParam("productID") int productID)
    {
        Products p = pservice.getP(productID);
        return p;

    }

    @Path("/order")
    @POST
    @Consumes("application/json")
    public Status storeProducts(@Context HttpServletRequest req,JsonArray products)
    {   
        Values val=new Values();
        List<String> prodidlist=new ArrayList<String>();
        List<String> quantlist=new ArrayList<String>();
        HttpSession session= req.getSession(true);
        String a = session.getAttribute("userid").toString();

        String productid;
        String quantity;
        for (JsonValue pobj : products)
        {
            JsonObject jobj = (JsonObject) pobj;
            productid = jobj.getString("Product ID");
            quantity=jobj.getString("Quantity");
            prodidlist.add(productid);
            quantlist.add(quantity);
   
        }
        val.setUserid(a);
        val.setProdids(prodidlist);
        val.setQuantities(quantlist);
        boolean result=pservice.addToProducts(val);
        if (result == true)
        {
            Status s = new Status();
            s.setError(false);
            return s;
        } 
        else
        {
            Status s = new Status();
            s.setError(true);
            s.setErrorMessage("Your Order was unfortunately not placed due to technical issues");
            return s;
        }
        
        


    }

}
