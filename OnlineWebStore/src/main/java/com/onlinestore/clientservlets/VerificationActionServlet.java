/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.onlinestore.clientservlets;

import com.onlinestore.customer.Customer;
import com.onlinestore.customer.CustomerHandler;
import com.onlinestore.order.Order;
import java.io.IOException;
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
public class VerificationActionServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String action = (String) session.getAttribute("action");
        if (action.equals("register")) {
            Customer customer = (Customer) session.getAttribute("customer");
            CustomerHandler customerHandler = CustomerHandler.getCustomerHandlerInstance();
            try {
                customerHandler.AddCustomer(customer);
            } catch (SQLException ex) {
                Logger.getLogger(VerificationActionServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
            response.sendRedirect("MainPage.jsp");
            session.removeAttribute("customer");
        } else if (action.equals("buy")) {
            Order order = (Order) session.getAttribute("order");
            try {
                order.BuyOrder();
            } catch (SQLException ex) {
                Logger.getLogger(VerificationActionServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
            response.sendRedirect("MainPage.jsp");
            session.removeAttribute("order");
            session.removeAttribute("products");
        }
        session.removeAttribute("action");
    }

}
