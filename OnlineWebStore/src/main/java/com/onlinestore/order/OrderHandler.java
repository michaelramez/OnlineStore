/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.onlinestore.order;

import com.mongodb.client.MongoCursor;
import static com.mongodb.client.model.Filters.and;
import static com.mongodb.client.model.Filters.eq;
import com.mongodb.client.model.Updates;
import com.onlinestore.customer.Customer;
import com.onlinestore.database.WebDatabase;
import com.onlinestore.mongodb.MongoDB;
import com.onlinestore.stock.Product;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.bson.Document;
import org.bson.conversions.Bson;

/**
 *
 * @author Michael Ramez
 */
public class OrderHandler {

    private static final OrderHandler orderHandler = new OrderHandler();
    private final WebDatabase dbInstance = WebDatabase.getDatabaseInstance();
    private final MongoDB mongoDB = MongoDB.GetMongoDBInstance();

    public static OrderHandler GetOrderHandlerInstance() {
        return orderHandler;
    }

    public void InsertOrderProduct(OrderProduct orderProduct, int cid) throws SQLException {
        String sqlCommand = "insert into orders(odate, oquantity, pid, cid) values (now(),?,?,?)";
        try ( PreparedStatement preparedStatement = dbInstance.GetPreparedStatement(sqlCommand)) {
            preparedStatement.setInt(1, orderProduct.getReqQuantity());
            preparedStatement.setInt(2, orderProduct.getId());
            preparedStatement.setInt(3, cid);
            preparedStatement.executeUpdate();
        }
    }

    public List<Product> GetBuyHistory(int cid) throws SQLException {
        String sqlCommand = "select distinct * from orders, product where orders.pid = product.pid and cid = ?";
        PreparedStatement preparedStatement = dbInstance.GetPreparedStatement(sqlCommand);
        preparedStatement.setInt(1, cid);
        ResultSet resultSet = preparedStatement.executeQuery();
        List<Product> buyHistory = new ArrayList<>();
        while (resultSet.next()) {
            Product product = new Product();
            int id = resultSet.getInt("pid");
            String model = resultSet.getString("pmodel");
            int quantity = resultSet.getInt("pquantity");
            product.setId(id);
            product.setModel(model);
            product.setStockQuantity(quantity);
            buyHistory.add(product);
        }
        return buyHistory;
    }

    public List<Customer> GetProductRatings(int pid) {
        List<Customer> customers = new ArrayList<>();
        Bson filter = eq("pid", pid);
        MongoCursor<Document> cursor = mongoDB.MongoFindMany(filter);
        while (cursor.hasNext()) {
            Customer customer = new Customer();
            Document document = cursor.next();
            String name = document.getString("cname");
            String rating = document.getString("rating");
            customer.setName(name);
            customer.setRating(rating);
            customers.add(customer);
        }
        return customers;
    }

    public String GetCustomerRating(int cid, int pid) {
        Bson filter = and(eq("pid", pid), eq("cid", cid));
        Document document = mongoDB.MongoFindOne(filter);
        if (document == null) {
            return "";
        } else {
            return document.getString("rating");
        }

    }

    public void AddRating(int pid, Customer customer, String rating) {
        Bson filter = and(eq("cid", customer.getId()), eq("pid", pid));
        Bson updates = Updates.combine(Updates.set("rating", rating), Updates.set("cname", customer.getName()));
        mongoDB.MongoUpsert(filter, updates);
    }

}
