/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.onlinestore.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Michael Ramez
 */
public class UpdateCartServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String[] reqProductsIDs = request.getParameterValues("productid");
        String[] reqProductsQuantities = request.getParameterValues("reqQuantity");
        
        if (reqProductsIDs == null){
            session.setAttribute("products", "");
        }
        else{
            String customerProducts = "{id:"+reqProductsIDs[0] +",reqQuantity:"+reqProductsQuantities[0]+"}";
            for (int i = 1; i < reqProductsIDs.length; i++){
                 customerProducts += "," + "{id:"+reqProductsIDs[i] +",reqQuantity:"+reqProductsQuantities[i]+"}";
            }
            session.setAttribute("products", customerProducts);
        }
        
        response.sendRedirect("ViewCart.jsp");
    }

}
