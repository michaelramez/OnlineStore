/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.onlinestore.adminservlets;

import com.onlinestore.stock.Category;
import com.onlinestore.stock.Product;
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
public class EditProductServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            int productId = Integer.parseInt(request.getParameter("productid"));
            int editedQuantity = Integer.parseInt(request.getParameter("editedQuantity"));
            int editedPrice = Integer.parseInt(request.getParameter("editedPrice"));
            String editedImagePath = request.getParameter("editedImagePath");
            String editedModel = request.getParameter("editedModel");
            int editedCategoryid = Integer.parseInt(request.getParameter("editedcategoryid"));
            Stock stock = Stock.getStockInstance();
            stock.UpdateProduct(new Product(productId, editedQuantity, editedPrice, editedModel, editedImagePath, new Category(editedCategoryid)));
            response.sendRedirect("Admin.jsp");
        } catch (SQLException ex) {
            Logger.getLogger(EditProductServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    

}
