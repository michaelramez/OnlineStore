
import java.util.ArrayList;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author asmaaMohamed
 */
public class Customer {
    private int customeeId;
    private  String name;
    private String customerName;
    private String mail;
    private  String password;
    private  String job;
    private  String birthdaya;
    private  String phone;
    private  int creditLimit;
    private  String address;
    private ArrayList<String> interests;

    //this constructor for singn up 11 arg
    public Customer(int id, String name, String customerName, String mail, String password, String job, String birthdaya, String phone, int creditLimit, String address, ArrayList<String> interests) {
        this.customeeId = id;
        this.name = name;
        this.customerName = customerName;
        this.mail = mail;
        this.password = password;
        this.job = job;
        this.birthdaya = birthdaya;
        this.phone = phone;
        this.creditLimit = creditLimit;
        this.address = address;
        this.interests = interests;
    }
// costructor for login 
    public Customer(String customerName, String password) {
        this.customerName = customerName;
        this.password = password;
    }

    public int getId() {
        return customeeId;
    }

    public void setId(int id) {
        this.customeeId = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
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

    public String getBirthdaya() {
        return birthdaya;
    }

    public void setBirthdaya(String birthdaya) {
        this.birthdaya = birthdaya;
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

    public ArrayList<String> getInterests() {
        return interests;
    }

    public void setInterests(ArrayList<String> interests) {
        this.interests = interests;
    }
    
    
    
}
