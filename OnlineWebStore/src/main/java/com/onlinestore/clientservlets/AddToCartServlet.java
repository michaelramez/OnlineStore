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
public class AddToCartServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        int reqProductID = Integer.parseInt(request.getParameter("productid"));
        int reqProductQuantity = Integer.parseInt(request.getParameter("reqQuantity"));
        List<OrderProduct> reqProducts = (List<OrderProduct>) session.getAttribute("products");
        if (reqProducts == null){
            reqProducts = new ArrayList<>();
        }
        boolean isFound = false;
        for (OrderProduct reqProduct : reqProducts){
            if (reqProduct.getId() == reqProductID){
                reqProduct.setReqQuantity(reqProductQuantity);
                isFound = true;
            }
        }        
        if (! isFound){
            reqProducts.add(new OrderProduct(reqProductID, reqProductQuantity));
        }       
        session.setAttribute("products", reqProducts);
        response.sendRedirect("ViewCart.jsp");
    }

}
