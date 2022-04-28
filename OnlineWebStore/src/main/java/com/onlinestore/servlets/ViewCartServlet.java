/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.onlinestore.servlets;

import com.onlinestore.products.Product;
import com.onlinestore.products.Stock;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Michael Ramez
 */
public class ViewCartServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();
        String reqProductID = request.getParameter("productid");
        String reqProductQuantity = request.getParameter("reqQuantity");
        String customerProducts = (String) session.getAttribute("products");
        
        if (customerProducts == null) {
            session.setAttribute("products", "{id:"+reqProductID+",reqQuantity:"+reqProductQuantity+"}");
        } else {
            customerProducts += "," + "{id:"+reqProductID+",reqQuantity:"+reqProductQuantity+"}";
            session.setAttribute("products", customerProducts);
        }
        response.sendRedirect("ViewCart.jsp");
    }

}
