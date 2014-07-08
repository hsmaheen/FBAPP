/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iss.foodbasket.bizLogic;

import com.iss.foodbasket.models.Groups;
import com.iss.foodbasket.models.Users;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author A0109314N
 */
@Stateless
public class CustomerService {
 
 @EJB HashedPasswordGenerator hpassword;
 @EJB EmailService eservice;
    

    @PersistenceContext
    private EntityManager em;

    public boolean checkUser(String userid) {
        boolean result = false;
        Users user = em.find(Users.class, userid);
        if (null != user) {
            result = true;
        }
        return result;
    }

    public boolean registerCustomer(Users user) {
        boolean result = false;
        String userid = user.getUserid();
        if (checkUser(userid)) {
            return result;
        } 
        else 
        {
            String password=hpassword.generateHash(user.getPassword());
            //String content="Thank you for registering with us";
            //eservice.sendEmail_gmail(user.getName().toString(), user.getEmail().toString(),content);
            user.setPassword(password);
            String role = "member";
            user.setMembership("Normal");
            user.setUserType(role);
            Groups g=new Groups();
            g.setUserid(userid);
            g.setGroupname(role);
            em.persist(user);
            em.persist(g);
            result = true;
            return result;
        }
    }
  

}

//}
