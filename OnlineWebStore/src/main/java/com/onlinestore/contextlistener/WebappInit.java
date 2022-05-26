/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/ServletListener.java to edit this template
 */
package com.onlinestore.contextlistener;

import com.onlinestore.database.WebDatabase;
import com.onlinestore.products.Stock;
import java.sql.SQLException;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * Web application lifecycle listener.
 *
 * @author Michael Ramez
 */
public class WebappInit implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        
        WebDatabase.getDatabaseInstance().connectToDatabase();
        Stock.getStockInstance().LoadProducts();
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        try {
            WebDatabase.getDatabaseInstance().closeDatabase();
        } catch (SQLException ex) {
            
        }
    }
}
