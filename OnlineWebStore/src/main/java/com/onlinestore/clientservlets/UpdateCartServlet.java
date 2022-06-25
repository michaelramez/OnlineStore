/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.onlinestore.clientservlets;

import com.onlinestore.order.OrderProduct;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
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
        List<OrderProduct> reqProducts = new ArrayList<>();

        if (reqProductsIDs != null && reqProductsQuantities!=null) {
            for (int i = 0; i < reqProductsIDs.length; i++) {
                reqProducts.add(new OrderProduct(Integer.parseInt(reqProductsIDs[i]), Integer.parseInt(reqProductsQuantities[i])));
            }
        }
        session.setAttribute("products", reqProducts);
        response.sendRedirect("ViewCart.jsp");
    }
}
