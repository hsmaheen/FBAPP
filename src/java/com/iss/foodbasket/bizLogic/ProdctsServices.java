/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iss.foodbasket.bizLogic;

import com.iss.foodbasket.models.Orderdetails;
import com.iss.foodbasket.models.Orders;
import com.iss.foodbasket.models.Products;
import com.iss.foodbasket.models.Users;
import com.iss.foodbasket.service.Values;
import com.sun.org.apache.bcel.internal.generic.AALOAD;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonValue;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import sun.awt.SunHints;

/**
 *
 * @author A0109314N
 */
//@Stateless
//public class ProdctsServices
//{
//     public static ArrayList<Products> getAllProducts() {
//     connection = FetchData.getConnection();
//        ArrayList<Products> prodList = new ArrayList<Products>();
//        try {
//            Statement statement = connection.createStatement();
//            ResultSet rs = statement.executeQuery("select * from products");
//            while(rs.next()) {
//             Products prod=new Products();
//             prod.setId(rs.getString("Id"));
//             prod.setPName(rs.getString("PName"));
//             prodList .add(prod);
//            }
//    
//}
@Stateless
public class ProdctsServices
{

    @EJB
    EmailService email;

    @PersistenceContext
    EntityManager em;

    public List<Products> getAll()
    {
        List<Products> prod = new ArrayList<Products>();
        String sql = "select p from Products p";
        Query p = em.createQuery(sql);
        prod = p.getResultList();
        return prod;

    }

    public Products getP(int id)
    {
        Products p = em.find(Products.class, Integer.valueOf(id));
        return p;

    }

    public boolean addToProducts(Values val)
    {

        ArrayList<Orderdetails> olist = new ArrayList<Orderdetails>();
        Date date = new Date();
        Orders o = new Orders();
        String userid = val.getUserid();
        Users user = em.find(Users.class, userid);
        o.setUserid(user);
        o.setOrderAddress(user.getAddress());
        o.setOrderDate(date);
        int count = 0;
        double totprice=0;
        for (String prodid : val.getProdids())
        {
            Orderdetails onew = new Orderdetails();
            int temp = Integer.valueOf(prodid);
            Products p = em.find(Products.class, temp);
            onew.setOrderID(o);
            onew.setProductID(p);
            String tempstr = val.getQuantities().get(count);
            int quant;
            if (tempstr.isEmpty())
            {
                quant = 0;
            } else
            {
                quant = Integer.parseInt(tempstr);
            }
            onew.setOrderQuant(quant);
            totprice=totprice+quant * p.getPricePerUnit();
            onew.setOPrice(quant * p.getPricePerUnit());
            count++;
            olist.add(onew);
            int pquant = p.getPQuanity();
            p.setPQuanity(pquant - quant);
            em.merge(p);
        }
        o.setOTotPrice((float) totprice);
        o.setOrderdetailsCollection(olist);
        try
        {
            em.persist(o);
            //--email---//
            String Header = "<head>"
                    + "<title>TODO supply a title</title>"
                    + "<meta charset=\"UTF-8\">"
                    + " <meta name=\"viewport\" content=\"width=device-width\">"
                    + " <link rel=\"stylesheet\" href=\"http://bootswatch.com/flatly/bootstrap.css\">"
                    + " <link rel=\"stylesheet\" href=\"http://bootswatch.com/assets/css/bootswatch.min.css\">"
                    + " </head>";
            String BodyStart = "<body>";
            String BodyContent = "<div class=\"bs-docs-section\">"
                    + ""
                    + "<div class=\"row\">"
                    + "<div class=\"col-lg-12\">"
                    + "<div class=\"page-header\">"
                    + "<h2 id=\"tables\">Order Confirmation</h2>"
                    + " /div>"
                    + "<div>"
                    + "<p>Customer Name:" + user.getName() + "</p>"
                    + "<p>Address of Delivery: " + user.getAddress() + "</p>"
                    + " </div>"
                    + ""
                    + "<div class=\"bs-example table-responsive\">"
                    + "<table class=\"table table-striped table-hover \">"
                    + "  <thead>"
                    + " <tr>"
                    + " <th>#</th>"
                    + "<th>Product Name</th>"
                    + " <th> Product Quantity</th>"
                    + "  <th> Unit Price </th>"
                    + " <th> Total Price </th>"
                    + " </tr>"
                    + " </thead>";
            String TableContent = "<tbody>";
            double totprices=0;
            for (Orderdetails oe : olist)
            {
                TableContent += "<tr>";
                TableContent += "<td>";
                TableContent += "<td>" + oe.getProductID().getPName() + "</td>";
                TableContent += "<td>" + oe.getOrderQuant() + "</td>";
                TableContent += "<td>" + oe.getProductID().getPricePerUnit() + "</td>";
                TableContent += "<td>" + oe.getOPrice() + "</td>";
                TableContent += "</tr>";
                totprice = totprice + oe.getOPrice();
            }
            TableContent += "</tbody>"
                    + "</table>";
            String TotalPriceContent = "<h4>Total Price : $" + totprices + "</h4>";
            String BodyEnd = "</div>"
                    + " </div>"
                    + "</div>"
                    + "</div>"
                    + "<script src=\"https://code.jquery.com/jquery-1.10.2.min.js\"></script>"
                    + " <script src=\"http://bootswatch.com/bower_components/bootstrap/dist/js/bootstrap.min.js\"></script>";

            String htmlContent = Header+ BodyStart+BodyContent+TableContent+TotalPriceContent+BodyEnd;
           // email.sendEmail_gmail(user.getName(), user.getEmail(), htmlContent);
            //--end email--//

            return true;

        } catch (Exception e)
        {
            return false;
        }

    }

}
