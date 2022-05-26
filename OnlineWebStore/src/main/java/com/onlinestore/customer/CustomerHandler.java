package com.onlinestore.customer;

import com.onlinestore.database.WebDatabase;
import com.onlinestore.twilio.TwilioService;
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
    
    public class RegistrationErrors{
        private boolean userExits, sqlError, otherError;
        TwilioService.PhoneValidationErrors phoneValidationErrors;
        private RegistrationErrors() {
            this.userExits = this.sqlError = this.otherError = true;
        }

        public boolean isOtherError() {
            return otherError;
        }

        public boolean isSqlError() {
            return sqlError;
        }
 
        public boolean isNoErrors() {
            return userExits == false && sqlError == false && otherError == false && phoneValidationErrors.isNoErrors()== true;
        }

        public boolean isUserExits() {
            return userExits;
        }

        public TwilioService.PhoneValidationErrors isPhoneInvalid() {
            return phoneValidationErrors;
        }
        
    }
    

    private void addUserData(Customer customer) {

        Date date = Date.valueOf(customer.getBirthday());
        try {

            String sqlCommand = "insert into customer (cname,cdob,cusername,cpassword,cphone,cjob,cmail,caddress,ccredit_limit)"
                    + "Values (?,?,?,?,?,?,?,?,?)";
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
            preStm.executeUpdate();


        } catch (SQLException ex) {

        }

    }

    public RegistrationErrors checkRegister(Customer customer) {
        RegistrationErrors registrationErrors = new RegistrationErrors();
        TwilioService twilioService = TwilioService.GetTwilioServiceInstance();
        registrationErrors.phoneValidationErrors = twilioService.ValidatePhoneNumber(customer.getPhone());

        try {

            String sqlCommand = "select cid from customer where cusername=?";
            PreparedStatement preStm = db.getConnection().prepareStatement(sqlCommand);
            preStm.setString(1, customer.getUsername());
            ResultSet res = preStm.executeQuery();
            if (res.next()) {          
                registrationErrors.userExits = true;
            }
        } catch (SQLException ex) {
            registrationErrors.sqlError = true;
        }catch(Exception ex){
            registrationErrors.otherError = true;
        }
        if (registrationErrors.isNoErrors()){
            addUserData(customer);
        } 
        
        return registrationErrors;
    }

    public int checkLogin(Customer customer) {
        int cid;
        try {

            String sqlCommand = "select cid from customer where cusername=? and cpassword=?";
            PreparedStatement preStm = db.getConnection().prepareStatement(sqlCommand);
            preStm.setString(1, customer.getUsername());
            preStm.setString(2, customer.getPassword());
            ResultSet res = preStm.executeQuery();
            if (res.next()) {
                cid = res.getInt("cid");
            } else {
                cid = -1;
            }

        } catch (SQLException ex) {
            cid = -2;
        }

        return cid;
    }

    public int getCreditLimit(int cid) {
        int creditLimit;
        try {

            String sqlCommand = "select ccredit_limit from customer where cid= ?";
            PreparedStatement preStm = db.getConnection().prepareStatement(sqlCommand);
            preStm.setInt(1, cid);
            ResultSet res = preStm.executeQuery();
            if (res.next()) {
                creditLimit = res.getInt("ccredit_limit");
            }
            else{
                creditLimit = -1;
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
