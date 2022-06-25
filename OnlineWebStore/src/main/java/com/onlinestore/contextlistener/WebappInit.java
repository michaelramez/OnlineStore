/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/ServletListener.java to edit this template
 */
package com.onlinestore.contextlistener;

import com.onlinestore.database.WebDatabase;
import com.onlinestore.mongodb.MongoDB;
import com.onlinestore.twilio.TwilioService;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * Web application lifecycle listener.
 *
 * @author Michael Ramez
 */
public class WebappInit implements ServletContextListener {
    private final WebDatabase dbInstance = WebDatabase.getDatabaseInstance();
    private final TwilioService twilioService = TwilioService.GetTwilioServiceInstance();
    private final MongoDB mongodb = MongoDB.GetMongoDBInstance();
    
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        
        try {
            dbInstance.connectToDB();
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(WebappInit.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        twilioService.InitTwilioService();
        mongodb.ConnectToMongo();
        
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        try {
            dbInstance.closeDBConnection();
        } catch (SQLException ex) {
            Logger.getLogger(WebappInit.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        twilioService.DestroyTwilioService();
    }
}
