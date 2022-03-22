/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.onlinestore.twilio;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Call;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URI;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Michael Ramez
 */
public class TwilioPhoneCalls extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //    public static final String ACCOUNT_SID = System.getenv("TWILIO_ACCOUNT_SID");
        //    public static final String AUTH_TOKEN = System.getenv("TWILIO_AUTH_TOKEN");

        Twilio.init("ACa28f4ca4bf8073178586c3d9555b8551", "5747312d1b8bdd43dddcbab098992ea6");
        Call call = Call.creator(
                new com.twilio.type.PhoneNumber("+201273413220"),
                new com.twilio.type.PhoneNumber("+18108181439"),
                URI.create("http://www.example.com/sipdial.xml"))
                .create();

        System.out.println(call.getSid());

    }

}
