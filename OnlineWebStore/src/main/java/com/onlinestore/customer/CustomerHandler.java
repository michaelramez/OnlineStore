package com.onlinestore.customer;

import com.onlinestore.database.WebDatabase;
import com.onlinestore.twilio.TwilioService;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CustomerHandler {

    private static final CustomerHandler customerHandlerInstance = new CustomerHandler();
    private final WebDatabase dbInstance = WebDatabase.getDatabaseInstance();

    private CustomerHandler() {
    }

    public static CustomerHandler getCustomerHandlerInstance() {
        return customerHandlerInstance;
    }

    public class RegistrationInfo {

        private boolean userExists;
        private TwilioService.PhoneValidationInfo phoneValidationInfo;
        private Customer customer;

        private RegistrationInfo() {
            this.userExists = false;
        }

        public boolean isUserExits() {
            return userExists;
        }

        public boolean isPhoneValid() {
            return phoneValidationInfo.isNoErrors();
        }

        public boolean isNoErrors() {
            return userExists == false && phoneValidationInfo.isNoErrors() == true;
        }

        public Customer getCustomer() {
            return customer;
        }

    }

    public class LoginInfo {

        private boolean userNotExist;
        private Customer customer;

        private LoginInfo() {
            userNotExist = false;
        }

        public boolean isUserNotExist() {
            return userNotExist;
        }

        public Customer getCustomer() {
            return customer;
        }

        public boolean isNoErrors() {
            return userNotExist == false;
        }
    }

    public void AddCustomer(Customer customer) throws SQLException {

        String sqlCommand = "insert into customer (cname,cdob,cusername,cpassword,cphone,cjob,cmail,caddress,ccredit_limit)"
                + "Values (?,?,?,?,?,?,?,?,?)";
        try ( PreparedStatement preparedStatement = dbInstance.GetPreparedStatement(sqlCommand)) {
            preparedStatement.setString(1, customer.getName());
            preparedStatement.setDate(2, customer.getBirthday());
            preparedStatement.setString(3, customer.getUsername());
            preparedStatement.setString(4, customer.getPassword());
            preparedStatement.setString(5, customer.getPhone());
            preparedStatement.setString(6, customer.getJob());
            preparedStatement.setString(7, customer.getMail());
            preparedStatement.setString(8, customer.getAddress());
            preparedStatement.setInt(9, customer.getCreditLimit());
            preparedStatement.executeUpdate();
        }

    }

    public RegistrationInfo Register(Customer customer) throws SQLException {
        RegistrationInfo registrationInfo = new RegistrationInfo();
        TwilioService twilioService = TwilioService.GetTwilioServiceInstance();
        registrationInfo.phoneValidationInfo = twilioService.ValidatePhoneNumber(customer.getPhone());

        String sqlCommand = "select cusername from customer where cusername=?";
        try ( PreparedStatement preparedStatement = dbInstance.GetPreparedStatement(sqlCommand)) {
            preparedStatement.setString(1, customer.getUsername());
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                registrationInfo.userExists = true;
            }
            if (registrationInfo.isNoErrors()) {
                registrationInfo.customer = customer;
            }
        }
        return registrationInfo;
    }

    public LoginInfo Login(Customer customer) throws SQLException {
        LoginInfo loginInfo = new LoginInfo();
        String sqlCommand = "select cid,cname,cphone from customer where cusername=? and cpassword=?";
        try ( PreparedStatement preparedStatement = dbInstance.GetPreparedStatement(sqlCommand)) {
            preparedStatement.setString(1, customer.getUsername());
            preparedStatement.setString(2, customer.getPassword());
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                int cid = resultSet.getInt("cid");
                String name = resultSet.getString("cname");
                String phone = resultSet.getString("cphone");
                loginInfo.customer = new Customer(cid, name, phone);
            } else {
                loginInfo.userNotExist = true;
            }
        }
        return loginInfo;
    }

    public int getCreditLimit(int cid) throws SQLException {
        String sqlCommand = "select ccredit_limit from customer where cid= ?";
        int creditLimit;
        try ( PreparedStatement preparedStatement = dbInstance.GetPreparedStatement(sqlCommand)) {
            preparedStatement.setInt(1, cid);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            creditLimit = resultSet.getInt("ccredit_limit");
        }
        return creditLimit;
    }

    public Customer getCustomerInfo(int cid) throws SQLException {
        String sqlCommand = "select * from customer where cid = ?";
        Customer customer;
        try ( PreparedStatement preparedStatement = dbInstance.GetPreparedStatement(sqlCommand)) {
            preparedStatement.setInt(1, cid);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            String name = resultSet.getString("cname");
            String username = resultSet.getString("cusername");
            String email = resultSet.getString("cmail");
            String password = resultSet.getString("cpassword");
            String phone = resultSet.getString("cphone");
            Date bdate = resultSet.getDate("cdob");
            String address = resultSet.getString("caddress");
            String job = resultSet.getString("cjob");
            int creditLimit = resultSet.getInt("ccredit_limit");
            customer = new Customer(creditLimit, name, username, email, password, job, bdate, phone, address);
        }
        return customer;
    }

    public void updateCustomerCredit(int creditUpdates, int cid) throws SQLException {
        String sqlCommand = "update customer set ccredit_limit =?  where cid=?";
        try ( PreparedStatement preparedStatement = dbInstance.GetPreparedStatement(sqlCommand)) {
            preparedStatement.setInt(1, creditUpdates);
            preparedStatement.setInt(2, cid);
            preparedStatement.executeUpdate();
        }
    }

    public void UpdateCustomer(Customer customer) throws SQLException {
        String sqlCommand = "update customer set cname=?,cdob=?,cjob=?,cmail=?,caddress=?,ccredit_limit=? where cid=?";
        try ( PreparedStatement preparedStatement = dbInstance.GetPreparedStatement(sqlCommand)) {
            preparedStatement.setString(1, customer.getName());
            preparedStatement.setDate(2, customer.getBirthday());
            preparedStatement.setString(3, customer.getJob());
            preparedStatement.setString(4, customer.getMail());
            preparedStatement.setString(5, customer.getAddress());
            preparedStatement.setInt(6, customer.getCreditLimit());
            preparedStatement.setInt(7, customer.getId());
            preparedStatement.executeUpdate();
        }
    }

    public static void main(String[] args) {

    }

}
