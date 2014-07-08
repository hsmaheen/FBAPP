/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.iss.foodbasket.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author A0109314N
 */
@WebServlet("/test")
public class LoginServlet extends HttpServlet 
{

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //super.doPost(req, resp); //To change body of generated methods, choose Tools | Templates.
        
        String userid=req.getParameter("userid");
        String pswrd=req.getParameter("password");
        try
        {
            req.login(userid, pswrd);
            //resp.sendRedirect("index.html");
            HttpSession session=req.getSession();
            session.setAttribute("userid", userid);
        }
        catch(ServletException e)
        {
            resp.setStatus(HttpServletResponse.SC_UNAUTHORIZED );
            //resp.sendRedirect("login_1.html");
        }
                
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //super.doGet(req, resp); //To change body of generated methods, choose Tools | Templates.
        resp.sendRedirect("Login.html");
    }
    
    
    
    
}
