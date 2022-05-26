/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package customer;

import database.WebDatabase;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author asmaaMohamed
 */
public class CustomerHandler {
    
    private final WebDatabase db=WebDatabase.getDatabaseInstance();
    private PreparedStatement preStm;
    private String sqlCommand;
    private  ResultSet res;
    
    
    
    
    public int addUserData(Customer data){
       int result = 0;
        try {
            
            sqlCommand = "insert into customer (cname,cdob,cusername,cpassword,cphone,cjob,cmail,caddress,cinterests,ccredit_limit) Values (?,?,?,?)";
            preStm =db.getConnection().prepareStatement(sqlCommand);
            preStm.setString(1, data.getName());
            preStm.setString(2, data.getBirthdaya());
            preStm.setString(3, data.getCustomerName());
            preStm.setString(4, data.getPassword());
            preStm.setString(5, data.getPhone());
            preStm.setString(6, data.getJob());
            preStm.setString(7, data.getMail());
            preStm.setString(8, data.getAddress());
            preStm.setString(9, data.getInterests());//set only string
            preStm.setInt(10, data.getCreditLimit());
            preStm.setString(11, data.getPassword());
            
            result =preStm.executeUpdate();
            System.out.println(result);
          
        } catch (SQLException ex) {
            Logger.getLogger(CustomerHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
   }
    
     public boolean checkLogin (Customer data) throws SQLException {
        
        
            sqlCommand="select id from customer where cusername=? and cpassword=?";
            preStm=db.getConnection().prepareStatement(sqlCommand);
            preStm.setString(1, data.getCustomerName());
            preStm.setString(2, data.getPassword());
            res=preStm.executeQuery(); 
            
            return res.next() == false;
        
    }
    
}
