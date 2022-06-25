/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.onlinestore.clientservlets;

import com.onlinestore.customer.Customer;
import com.onlinestore.order.OrderHandler;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Michael Ramez
 */
public class UpdateRatingServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            HttpSession session = request.getSession();
            Customer customer = (Customer)session.getAttribute("customer");
            int pid = Integer.parseInt(request.getParameter("productid"));
            OrderHandler orderHandler = OrderHandler.GetOrderHandlerInstance();
            String rating = request.getParameter("rating");
            orderHandler.AddRating(pid, customer, rating);
            response.sendRedirect("BuyHistory.jsp");
    }

}
