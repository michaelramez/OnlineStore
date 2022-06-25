<%-- 
    Document   : MainPage
    Created on : Apr 13, 2022, 11:20:07 PM
    Author     : Michael Ramez
--%>

<%@page import="com.onlinestore.stock.Category"%>
<%@page import="java.util.List"%>
<%@page import="com.onlinestore.stock.Product"%>
<%@page import="com.onlinestore.stock.Stock"%>
<%@page import="com.onlinestore.customer.Customer"%>
<%@page import="java.util.Map"%>
<%@page import="com.onlinestore.database.WebDatabase"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <jsp:include page="Header.jsp"/>
        <%
            Stock stock = Stock.getStockInstance();
            List<Category> categories = stock.GetAllCategories();

            for (Category category : categories) {
                List<Product> products = stock.GetCategoryProducts(category.getId());
                if (!products.isEmpty()) {
        %>
        <div id=<%=category.getName()%>>
            <h1><%=category.getName()%></h1>
            <%

                for (Product p : products) {
            %>      

            <form class="add-to-cart" target="_blank" action="AddToCartServlet" method="GET">
                <div class="single-product">
                    <div class="product-f-image">
                        <h2><p><%= p.getModel()%></p></h2>
                        <img src=<%= p.getImagePath()%> alt=""><br>
                        <label>Quantity</label>

                        <select name="reqQuantity">
                            <%
                                for (int i = 1; i <= p.getStockQuantity(); i++) {
                            %>

                            <option value=<%=i%>><%=i%></option>

                            <%}%>
                        </select>
                        <br>
                        <div class="product-carousel-price">
                            <p>Price:<%= p.getPrice()%></p>
                        </div> 
                        <div class="product-hover">
                            <!--<a href="single-product.html" class="view-details-link"><i class="fa fa-link"></i> See details</a>-->
                            <input type="hidden" name="productid" value=<%= p.getId()%>>
                            <input type="submit" value="Add to cart" class="btn">
                        </div>
                    </div>
                </div>
            </form>
            <%}
                }%>        
        </div>
        <%}%>
    </body>
</html>
