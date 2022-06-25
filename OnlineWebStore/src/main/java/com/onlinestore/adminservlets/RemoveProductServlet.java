/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.onlinestore.adminservlets;

import com.onlinestore.stock.Stock;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Michael Ramez
 */
public class RemoveProductServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int productId = Integer.parseInt(request.getParameter("productid"));
        Stock stock = Stock.getStockInstance();
        try {
            stock.RemoveProduct(productId);
        } catch (SQLException ex) {
            Logger.getLogger(RemoveProductServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        response.sendRedirect("Admin.jsp");
    }

 

}
