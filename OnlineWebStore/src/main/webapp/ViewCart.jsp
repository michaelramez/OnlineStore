<%-- 
    Document   : AddToCart
    Created on : Apr 16, 2022, 12:45:58 AM
    Author     : Michael Ramez
--%>

<%@page import="com.onlinestore.stock.Product"%>
<%@page import="com.onlinestore.stock.Stock"%>
<%@page import="com.google.gson.Gson"%>
<%@page import="java.lang.reflect.Type"%>
<%@page import="com.google.gson.reflect.TypeToken"%>
<%@page import="java.util.List"%>
<%@page import="com.onlinestore.order.OrderProduct"%>
<%@page import="java.util.Map"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
        <script>
            $(document).ready(function () {
                $(".remove").click(function () {
                    $(this).parent().remove();
                });
            });
        </script>
    </head>
    <body>        
            <%

                List<OrderProduct> reqProducts = (List<OrderProduct>) session.getAttribute("products");
                if (reqProducts == null || reqProducts.isEmpty()) {

            %>
            <p>Your cart is empty</p>
            <%}else{%>
            <form class="add-to-cart"  method="GET">
            <%
                int totalPrice = 0;
                Stock stock = Stock.getStockInstance();
                for (OrderProduct reqProduct : reqProducts) {
                    int reqProductId = reqProduct.getId();
                    Product p = stock.GetProductById(reqProductId);
                    totalPrice += p.getPrice() * reqProduct.getReqQuantity();
            %>

            <div class="single-product">
                <div class="product-f-image">

                    <h2><p><%= p.getModel()%></p></h2>
                    <img src=<%= p.getImagePath()%> alt=""><br>
                    
                    <label for="quantity">Quantity</label>                    
                    <select name="reqQuantity" id="quantity">
                        <%
                            for (int i = 1; i <= p.getStockQuantity(); i++) {
                        %>

                        <option value=<%=i%> <%if (i == reqProduct.getReqQuantity()) {%><%="selected"%><%}%> ><%=i%></option>

                        <%}%>
                    </select>
                    <div class="product-hover">
                        <input type="hidden" name="productid" value=<%= p.getId()%>>
                    </div>
                </div>
                <div class="product-carousel-price">
                    <p>Price:<%= p.getPrice()%></p>
                </div>
                <button class="remove">Remove</button>
            </div>


            <%}%>
            <p>Total Price: <%=totalPrice%></p>
            <input type="submit" value="Update Cart" formaction="UpdateCartServlet">
            <input type="submit" value="Buy Cart" formaction="BuyCart.jsp">
            </form>
            <%}%>
    </body>
</html>
