/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.onlinestore.order;

import com.onlinestore.customer.CustomerHandler;
import com.onlinestore.stock.Stock;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Michael Ramez
 */
public class Order {

//    private final WebDatabase dbInstance = WebDatabase.getDatabaseInstance();
    private static final Stock stock = Stock.getStockInstance();
    private final CustomerHandler customerHandler = CustomerHandler.getCustomerHandlerInstance();
    private final OrderHandler orderHandler = OrderHandler.GetOrderHandlerInstance();
    private final List<OrderProduct> order;
    private final int cid;
    private int totalOrderPrice;
    private int customerCredit;
    private OrderErrors orderErrors;

    public Order(List<OrderProduct> order, int cid) {
        this.totalOrderPrice = 0;
        this.order = order;
        this.cid = cid;
    }

    public class OrderErrors {

        private boolean outOfStock, insufficientCredit;
        private String outOfStockPname;

        private OrderErrors() {
            this.outOfStock = this.insufficientCredit = false;
        }

        public boolean isOutOfStock() {
            return outOfStock;
        }

        public boolean isInsufficientCredit() {
            return insufficientCredit;
        }
        
        public boolean isNoErrors() {
            return outOfStock == false && insufficientCredit == false;
        }

        public String getOutOfStockPname() {
            return outOfStockPname;
        }        
    }

    private void CheckOrderInStock() throws SQLException {

        for (OrderProduct orderedProduct : this.order) {
            int orderedProductId = orderedProduct.getId();
            int reqQuantity = orderedProduct.getReqQuantity();
            int stockQuantity = stock.GetProductQuantity(orderedProductId);
            if (stockQuantity < reqQuantity) {
                orderErrors.outOfStockPname = stock.GetProductName(orderedProductId);
                orderErrors.outOfStock = true;
            }
            int totalProductPrice = reqQuantity * stock.GetProductPrice(orderedProductId);
            totalOrderPrice += totalProductPrice;
        }
    }

    private void CheckCustomerCredit() throws SQLException {
        this.customerCredit = customerHandler.getCreditLimit(cid);
        if (customerCredit < totalOrderPrice){
            orderErrors.insufficientCredit = true;
        }
    }

    public OrderErrors CheckOrderValidity() throws SQLException {
        orderErrors = new OrderErrors();
        CheckOrderInStock();
        CheckCustomerCredit();
        return orderErrors;
    }

    public void BuyOrder() throws SQLException {
        for (OrderProduct orderedProduct : this.order) {
            int orderedProductId = orderedProduct.getId();
            int reqQuantity = orderedProduct.getReqQuantity();
            int stockQuantity = stock.GetProductQuantity(orderedProductId);
            stock.UpdateProductQuantity(orderedProductId, stockQuantity - reqQuantity);
            orderHandler.InsertOrderProduct(orderedProduct, cid);
        }
        customerHandler.updateCustomerCredit(customerCredit - totalOrderPrice, cid);
        
    }
   

}
