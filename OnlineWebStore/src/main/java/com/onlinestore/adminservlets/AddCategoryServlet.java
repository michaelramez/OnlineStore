/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.onlinestore.adminservlets;

import com.onlinestore.stock.Category;
import com.onlinestore.stock.Stock;
import java.io.IOException;
import java.io.PrintWriter;
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
public class AddCategoryServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Stock stock = Stock.getStockInstance();
        String categoryname = request.getParameter("categoryname");
        Category category = new Category(categoryname);
        try {
            stock.AddCategory(category);
        } catch (SQLException ex) {
            Logger.getLogger(AddCategoryServlet.class.getName()).log(Level.SEVERE, null, ex);
        }  
        response.sendRedirect("AddProduct.jsp");
    }
    

}
