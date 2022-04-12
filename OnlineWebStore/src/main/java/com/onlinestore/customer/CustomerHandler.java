package com.onlinestore.customer;

import com.onlinestore.database.WebDatabase;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CustomerHandler {

    private static final WebDatabase db = WebDatabase.getDatabaseInstance();
    private static final CustomerHandler customerHandlerInstance = new CustomerHandler();

    private CustomerHandler() {

    }

    public static CustomerHandler getCustomerHandlerInstance() {
        return customerHandlerInstance;
    }


    public int addUserData(Customer customer) {
        
        Date date = Date.valueOf(customer.getBirthday());
        int cid;
        try {

            String sqlCommand = "insert into customer (cname,cdob,cusername,cpassword,cphone,cjob,cmail,caddress,ccredit_limit) "
                    + "Values (?,?,?,?,?,?,?,?,?) returning cid";
            PreparedStatement preStm = db.getConnection().prepareStatement(sqlCommand);
            preStm.setString(1, customer.getName());
            preStm.setDate(2, date);
            preStm.setString(3, customer.getUsername());
            preStm.setString(4, customer.getPassword());
            preStm.setString(5, customer.getPhone());
            preStm.setString(6, customer.getJob());
            preStm.setString(7, customer.getMail());
            preStm.setString(8, customer.getAddress());
            preStm.setInt(9, customer.getCreditLimit());
            ResultSet res = preStm.executeQuery();
            cid = res.getInt("cid");
            
        } catch (SQLException ex) {
            cid = -2;
        } 
        return cid;
        

    }

    public int checkLogin(Customer customer) {
        int cid = -1;
        try {
            
            String sqlCommand = "select cid from customer where cusername=? and cpassword=?";
            PreparedStatement preStm = db.getConnection().prepareStatement(sqlCommand);
            preStm.setString(1, customer.getUsername());
            preStm.setString(2, customer.getPassword());
            ResultSet res = preStm.executeQuery();
            if (! res.next()) {
                cid = addUserData(customer);
            }
            
        } catch (SQLException ex) {
           cid = -2;
        }
        
        return cid;

    }

    public int getCreditLimit(int cid) {
        int creditLimit = -1;
        try {

            String sqlCommand = "select ccredit_limit from customer where cid= ?";
            PreparedStatement preStm = db.getConnection().prepareStatement(sqlCommand);
            preStm.setInt(1, cid);
            ResultSet res = preStm.executeQuery();
            if (res.next()) {
                creditLimit = res.getInt(1);
            }
        } catch (SQLException ex) {
            creditLimit = -2;
        } 
        return creditLimit;

    }

    public void updateUserCredit(int creditUpdates, int cid) {
        try {
            String sqlCommand = "update customer set ccredit_limit =?  where cid=? ";
            PreparedStatement preStm = db.getConnection().prepareStatement(sqlCommand);
            preStm.setInt(1, creditUpdates);
            preStm.setInt(2, cid);
            preStm.executeUpdate();
        } catch (SQLException ex) {
            
        }
    }

    public static void main(String[] args) {
//        db.connectToDatabase();
//        Customer c = new Customer("asmaa", "asmaamohamed", "asmaa@gmail.com", "12346", "Engineer", "1998-6-1", "01093693045", 1000, "almarg");
//        System.out.println( customerHandler.addUserData(c));
//        Customer c1 = new Customer("asmaamoamed", "12346");
//        System.out.println(customerHandler.checkLogin(c1));
//        System.out.println(customerHandler.getCreditLimit(3));
//        customerHandler.updateUserCredit(9999, 3);
//        System.out.println(customerHandler.getCreditLimit(3));

    }

}

