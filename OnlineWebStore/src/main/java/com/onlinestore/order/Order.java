/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.onlinestore.order;

import com.onlinestore.customer.CustomerHandler;
import com.onlinestore.database.WebDatabase;
import com.onlinestore.products.Product;
import com.onlinestore.products.Stock;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Michael Ramez
 */
public class Order {

    private static final WebDatabase db = WebDatabase.getDatabaseInstance();
    private static final Stock stock = Stock.getStockInstance();
    private static final CustomerHandler customerHandler = CustomerHandler.getCustomerHandlerInstance();
    private final List<OrderProduct> order;
    private final int cid;
    private int totalOrderPrice;
    private final int customerCredit;

    public Order(List<OrderProduct> order, int cid) {
        this.customerCredit = customerHandler.getCreditLimit(cid);
        this.totalOrderPrice = 0;
        this.order = order;
        this.cid = cid;
    }

    private boolean CheckOrderInStock() {
        for (OrderProduct orderedProduct : this.order) {
            Product stockProduct = stock.GetCurrentProducts().get(orderedProduct.getId());
            int reqQuantity = orderedProduct.getReqQuantity();
            int stockQuantity = stockProduct.getStockQuantity();
            if (stockQuantity < reqQuantity) {
                return false;
            }
            int totalProductPrice = reqQuantity * stockProduct.getPrice();
            totalOrderPrice += totalProductPrice;
        }
        return true;
    }

    private boolean CheckCustomerCredit() {
        return customerCredit >= totalOrderPrice;
    }

    private int CheckValidOrder() {
        int validityStatus = 0;

        if (CheckCustomerCredit() == false) {
            validityStatus = -1;
        }

        if (CheckOrderInStock() == false) {
            validityStatus = -2;
        }

        return validityStatus;
    }

    public int MakeOrder() {

        int transStatus = CheckValidOrder();
        if (transStatus == 0) {
            try {
                for (OrderProduct orderedProduct : this.order) {
                    Product stockProduct = stock.GetCurrentProducts().get(orderedProduct.getId());
                    int reqQuantity = orderedProduct.getReqQuantity();
                    int stockQuantity = stockProduct.getStockQuantity();
                    stockProduct.setStockQuantity(stockQuantity - reqQuantity);
                    db.getConnection().setAutoCommit(false);
                    stock.UpdateProductQuantity(stockProduct.getPid(), stockQuantity - reqQuantity);
                }
                customerHandler.updateUserCredit(customerCredit - totalOrderPrice, cid);
                db.getConnection().commit();
            } catch (SQLException ex) {
                transStatus = -3;
            }
        }

        return transStatus;
    }
}
