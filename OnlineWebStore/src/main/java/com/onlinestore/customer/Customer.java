package com.onlinestore.customer;

import java.sql.Date;

public class Customer {

    private Integer id, creditLimit;
    private String name;
    private String username;
    private String mail;
    private String password;
    private String job;
    private Date birthday;
    private String phone;
    private String address;
    private String rating;

    public Customer() {
    }

    public Customer(Integer id, Integer creditLimit, String name, String username, String mail, String password, String job, Date birthday, String phone, String address) {
        this.id = id;
        this.creditLimit = creditLimit;
        this.name = name;
        this.username = username;
        this.mail = mail;
        this.password = password;
        this.job = job;
        this.birthday = birthday;
        this.phone = phone;
        this.address = address;
    }

    public Customer(Integer creditLimit, String name, String username, String mail, String password, String job, Date birthday, String phone, String address) {
        this.creditLimit = creditLimit;
        this.name = name;
        this.username = username;
        this.mail = mail;
        this.password = password;
        this.job = job;
        this.birthday = birthday;
        this.phone = phone;
        this.address = address;
    }

    public Customer(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public Customer(Integer id, String name, String phone) {
        this.id = id;
        this.name = name;
        this.phone = phone;
    }

    public Customer(Integer id, Integer creditLimit, String name, String mail, String password, String job, Date birthday, String address) {
        this.id = id;
        this.creditLimit = creditLimit;
        this.name = name;
        this.mail = mail;
        this.password = password;
        this.job = job;
        this.birthday = birthday;
        this.address = address;
    }

    public Integer getId() {
        return id;
    }

    public Integer getCreditLimit() {
        return creditLimit;
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

    public Date getBirthday() {
        return birthday;
    }

    public String getPhone() {
        return phone;
    }

    public String getAddress() {
        return address;
    }

    public String getRating() {
        return rating;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setCreditLimit(Integer creditLimit) {
        this.creditLimit = creditLimit;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

}
