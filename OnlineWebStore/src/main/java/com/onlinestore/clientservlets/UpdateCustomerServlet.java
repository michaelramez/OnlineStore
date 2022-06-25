/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.onlinestore.clientservlets;

import com.onlinestore.customer.Customer;
import com.onlinestore.customer.CustomerHandler;
import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Michael Ramez
 */
public class UpdateCustomerServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        Date bdate = Date.valueOf(request.getParameter("bdate"));
        String address = request.getParameter("address");
        String job = request.getParameter("job");
        int creditLimit = Integer.parseInt(request.getParameter("climit"));
        HttpSession httpSession = request.getSession();
        Customer sessionCustomer = (Customer) httpSession.getAttribute("customer");
        int cid = sessionCustomer.getId();
        Customer customer = new Customer(cid, creditLimit, name, email, job, job, bdate, address);
        CustomerHandler customerHandler = CustomerHandler.getCustomerHandlerInstance();
        try {
            customerHandler.UpdateCustomer(customer);
        } catch (SQLException ex) {
            Logger.getLogger(UpdateCustomerServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        sessionCustomer.setName(name);
        response.sendRedirect("ViewProfile.jsp");
    }

}
