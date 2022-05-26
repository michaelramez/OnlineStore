<%-- 
    Document   : MainPage
    Created on : Apr 13, 2022, 11:20:07 PM
    Author     : Michael Ramez
--%>

<%@page import="java.util.Map"%>
<%@page import="com.onlinestore.products.Product"%>
<%@page import="com.onlinestore.products.Stock"%>
<%@page import="com.onlinestore.database.WebDatabase"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <a href="Login.html">Login</a>
        <a href="Register.html">Register</a>
        <%

            Stock productStock = Stock.getStockInstance();
            Map<Integer, Product> products = productStock.GetCurrentProducts();

            for (Product p : products.values()) {
        %>      

        <form class="add-to-cart" action="ViewCartServlet" method="GET">
            <div class="single-product">
                <div class="product-f-image">

                    <h2><a href="single-product.html"><%= p.getModel()%></a></h2>
                    <img src=<%= p.getImagePath()%> alt=""><br>
                    <label for="quantity">Quantity</label>

                    <select name="reqQuantity" id="quantity">
                        <%
                            for (int i = 1; i <= p.getStockQuantity(); i++) {
                        %>

                        <option value=<%=i%>><%=i%></option>

                        <%}%>
                    </select>
                    <div class="product-hover">
                        <!--<a href="single-product.html" class="view-details-link"><i class="fa fa-link"></i> See details</a>-->
                        <input type="hidden" name="productid" value=<%= p.getPid()%>>
                        <input type="submit" value="Add to cart" class="btn">
                    </div>
                </div>

                <div class="product-carousel-price">
                    <p>Price:<%= p.getPrice()%></p>
                </div> 
            </div>
        </form>
        <%}%>
    </body>
</html>
