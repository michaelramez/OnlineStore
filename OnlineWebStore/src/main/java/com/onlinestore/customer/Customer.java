
package com.onlinestore.customer;


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

    public Customer(String Username, String password) {
        this.username = Username;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public String getUsername() {
        return username;
    }

    public String getMail() {
        return mail;
    }

    public String getPassword() {
        return password;
    }

    public String getJob() {
        return job;
    }

    public String getBirthday() {
        return birthday;
    }

    public String getPhone() {
        return phone;
    }

    public int getCreditLimit() {
        return creditLimit;
    }

    public String getAddress() {
        return address;
    }

}
