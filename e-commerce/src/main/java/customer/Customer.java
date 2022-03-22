
package customer;

import java.util.ArrayList;

public class Customer {

    private String name;
    private String username;
    private String mail;
    private String password;
    private String job;
    private String birthday;
    private String phone;
    private int creditLimit;
    private String address;
    private static Customer customer=new Customer();
    

    public Customer() {
    }

    //this constructor for singn up 11 arg

    public Customer(String name, String username, String mail, String password, String job, String birthday, String phone, int creditLimit, String address) {
        this.name = name;
        this.username = username;
        this.mail = mail;
        this.password = password;
        this.job = job;
        this.birthday = birthday;
        this.phone = phone;
        this.creditLimit = creditLimit;
        this.address = address;

    }
// costructor for login 

    public Customer(String Username, String password) {
        this.username = Username;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getCreditLimit() {
        return creditLimit;
    }

    public void setCreditLimit(int creditLimit) {
        this.creditLimit = creditLimit;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public static Customer getCustomer() {
        return customer;
    }


}
