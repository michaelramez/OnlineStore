/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.onlinestore.twilio;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Michael Ramez
 */
public class TwilioMessages extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

//    public static final String ACCOUNT_SID = System.getenv("ACa28f4ca4bf8073178586c3d9555b8551");
//    public static final String AUTH_TOKEN = System.getenv("5747312d1b8bdd43dddcbab098992ea6");
    Twilio.init ("ACa28f4ca4bf8073178586c3d9555b8551", "5747312d1b8bdd43dddcbab098992ea6");
    Message message = Message.creator(
            new com.twilio.type.PhoneNumber("+201273413220"),
            new com.twilio.type.PhoneNumber("+18108181439"),
            "test")
            .create();

    System.out.println (message.getSid());

    


    }

}
