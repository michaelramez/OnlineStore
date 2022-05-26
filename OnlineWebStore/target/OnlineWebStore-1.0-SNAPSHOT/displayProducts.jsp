<%-- 
    Document   : displayProducts
    Created on : Apr 4, 2022, 8:40:45 PM
    Author     : asmaaMohamed
--%>

<%@page import="java.util.HashMap"%>
<%@page import="com.onlinestore.database.WebDatabase"%>
<%@page import="java.util.Map"%>
<%@page import="com.onlinestore.products.Product"%>
<%@page import="com.onlinestore.products.Stock"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@include file="header.jsp" %>

<%  
    
    WebDatabase db=WebDatabase.getDatabaseInstance();
    db.connectToDatabase();      

    Stock productStock = Stock.getStockInstance();
    
    productStock.LoadProducts(); ////only for test
    Map<Integer, Product> products = productStock.GetCurrentProducts();
//    Map<Integer,Product>displayedProduct=new HashMap<Integer, Product>();
    
    String cat = request.getParameter("cat");
    String maxprice = request.getParameter("maxPrice");
     System.out.println(maxprice);
//   if (cat.equalsIgnoreCase("All")&& maxprice.isEmpty()&&maxprice==null){
//    displayedProduct=products;
//   }
//   else{
//        for (Product p : products.values()){
//            if(p.getType()==cat && p.getPrice()<=Integer.parseInt(maxprice)){
//                         displayedProduct.put(p.getId(),p);
//
//            }
//        
//        }
//   
//   
//   }
//     
           
   
    for (Product p :products.values()) {
%>
 
<div class="single-product">
    <div class="product-f-image">
        <img src=<%= p.getImagePath() %> alt="">
        <div class="product-hover">
            <a href="#" class="add-to-cart-link"><i class="fa fa-shopping-cart"></i> Add to cart</a>
            <a href="single-product.html" class="view-details-link"><i class="fa fa-link"></i> See details</a>
        </div>
    </div>

        <h2><a href="single-product.html"><%= p.getModel() %></a></h2>

    <div class="product-carousel-price">
        <ins><%= p.getPrice() %></ins>
    </div> 
</div>
<%}%>









<%@include file="footer.jsp" %>