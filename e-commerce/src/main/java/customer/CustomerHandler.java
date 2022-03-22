package customer;

import database.WebDatabase;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CustomerHandler {

    private PreparedStatement preStm;
    private String sqlCommand;
    private ResultSet res;
    private static WebDatabase db = WebDatabase.getDatabaseInstance();
    private static CustomerHandler customerHandler = new CustomerHandler();

    public static CustomerHandler getCustomerHandler() {
        return customerHandler;
    }

    public boolean addUserData(Customer data) {
        boolean result = false;
        Date date = Date.valueOf(data.getBirthday());
        try {

            sqlCommand = "insert into customer (cname,cdob,cusername,cpassword,cphone,cjob,cmail,caddress,ccredit_limit) Values (?,?,?,?,?,?,?,?,?)";
            preStm = db.getConnection().prepareStatement(sqlCommand);
            preStm.setString(1, data.getName());
            preStm.setDate(2, date);
            preStm.setString(3, data.getUsername());
            preStm.setString(4, data.getPassword());
            preStm.setString(5, data.getPhone());
            preStm.setString(6, data.getJob());
            preStm.setString(7, data.getMail());
            preStm.setString(8, data.getAddress());
            preStm.setInt(9, data.getCreditLimit());

            preStm.executeUpdate();
            System.out.println(result);
            result = true;

        } catch (SQLException ex) {
            System.err.format("SQL State: %s\n%s", ex.getSQLState(), ex.getMessage());
            System.out.println(ex.getSQLState());//aready exiists if ==23505
            System.out.println("customer.CustomerHandler.addUserData()");
            if (ex.getSQLState().equals("23505")) {
                result = false;

            }
        } finally {
            return result;
        }

    }

    public boolean checkLogin(Customer data) {
        boolean result = false;
        try {
            sqlCommand = "select cid from customer where cusername=? and cpassword=?";
            preStm = db.getConnection().prepareStatement(sqlCommand);
            preStm.setString(1, data.getUsername());
            preStm.setString(2, data.getPassword());
            res = preStm.executeQuery();
            result = res.next();
        } catch (SQLException ex) {
            System.err.format("SQL State: %s\n%s", ex.getSQLState(), ex.getMessage());
            System.out.println(ex.getSQLState());
            System.out.println("customer.CustomerHandler.checkLogin()");
        } finally {
            return result;
        }
    }

    public int getUserId(Customer customer) {
        int cid = 0;
        try {

            sqlCommand = "select cid from customer where cusername = ? and cpassword = ? ";
            preStm = db.getConnection().prepareStatement(sqlCommand);
            preStm.setString(1, customer.getUsername());
            preStm.setString(2, customer.getPassword());
            res = preStm.executeQuery();
            if (res.next()) {

                cid = res.getInt(1);
            }

        } catch (SQLException ex) {
            System.err.format("SQL State: %s\n%s", ex.getSQLState(), ex.getMessage());
            System.out.println(ex.getSQLState());
            System.out.println("customer.CustomerHandler.getUserId()");
        } finally {

            return cid;
        }
    }

    public int getCreditLimit(int cid) {
        int creditLimit = 0;
        try {

            sqlCommand = "select ccredit_limit from customer where cid= ?  ";
            preStm = db.getConnection().prepareStatement(sqlCommand);
            preStm.setInt(1, cid);
            res = preStm.executeQuery();
            while (res.next()) {
                creditLimit = res.getInt(1);
            }

        } catch (SQLException ex) {
            System.err.format("SQL State: %s\n%s", ex.getSQLState(), ex.getMessage());
            System.out.println(ex.getSQLState());
            System.out.println("customer.CustomerHandler.getCreditLimit()");
        } finally {
            return creditLimit;
        }
    }

    public void updateUserCredit(int critedUpdates, int cid) {
        try {
            sqlCommand = "update customer set ccredit_limit =?  where cid=? ";
            preStm = db.getConnection().prepareStatement(sqlCommand);
            preStm.setInt(1, critedUpdates);
            preStm.setInt(2, cid);
            preStm.executeUpdate();
        } catch (SQLException ex) {
            System.err.format("SQL State: %s\n%s", ex.getSQLState(), ex.getMessage());
            System.out.println(ex.getSQLState());
            System.out.println("customer.CustomerHandler.updateUserCredit()");
        }
    }

    public static void main(String[] args) {
        db.connectToDatabase();
        //Customer c = new Customer("asmaa", "asmaamohamed", "asmaa@gmail.com", "12346", "Engineer", "1998-6-1", "01093693045", 1000, "almarg");
        //System.out.println( customerHandler.addUserData(c));
        Customer c1 = new Customer("asmaamoamed", "12346");
        System.out.println(customerHandler.checkLogin(c1));
        System.out.println(customerHandler.getCreditLimit(3));
        customerHandler.updateUserCredit(9999,3);
       System.out.println(customerHandler.getCreditLimit(3));


    }

}
