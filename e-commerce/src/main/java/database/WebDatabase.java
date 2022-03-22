/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author asmaaMohamed
 */
public class WebDatabase {

    private  final String url="jdbc:postgresql://bf133nmx4w8hkomwvnts-postgresql.services.clever-cloud.com:5432/bf133nmx4w8hkomwvnts";
    private  final String userName="u04s076qhpycfgovgv52";
    private  final String pass="qHz4iSQP2byB8k4afFOc";
    
    private Connection connection ;
    private PreparedStatement preStm = null;
   private static  WebDatabase databaseInstance =new WebDatabase();

    public WebDatabase() {
        
    }

    public static WebDatabase getDatabaseInstance() {
        return databaseInstance;
    }
    
    
    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    public PreparedStatement getPreStm() {
        return preStm;
    }

    public void setPreStm(PreparedStatement preStm) {
        this.preStm = preStm;
    }

   
   
    
    public void connectToDatabase(){
        
        try {
            Class.forName("org.postgresql.Driver");
            connection=DriverManager.getConnection(url,userName,pass);  
            System.out.println("Connection is made successfully");

        } catch (Exception ex) {
            
            System.out.println("webDatabase.WebDataBase.connect()error");
            ex.printStackTrace();
        }
          
    }
    public void closeDatabase() throws SQLException{
     connection.close();
   }
   /* public static void main(String[] args) {
        WebDatabase db =WebDatabase.databaseInstance;
        db.connectToDatabase();
    }*/
    
}
