/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.onlinestore.order;

/**
 *
 * @author Michael Ramez
 */
public class OrderProduct {
    private Integer id, reqQuantity;
    
    public OrderProduct(Integer id, Integer reqQuantity) {
        this.id = id;
        this.reqQuantity = reqQuantity;
    }

    public Integer getId() {
        return id;
    }

    public Integer getReqQuantity() {
        return reqQuantity;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setReqQuantity(Integer reqQuantity) {
        this.reqQuantity = reqQuantity;
    }


    
}
