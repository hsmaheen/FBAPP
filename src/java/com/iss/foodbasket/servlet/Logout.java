/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iss.foodbasket.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author A0109314N
 */
@WebServlet("/logout")
public class Logout extends HttpServlet
{

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        req.logout();
        resp.sendRedirect("Login.html");
        Cookie cookie[] = req.getCookies();
        String name="userid";
        for(Cookie c:cookie)
        {
            if(c.getName().equals(name))
            {
                c.setMaxAge(0);
            }
            
        }
        HttpSession session=req.getSession();
        session.invalidate();

    }

}
