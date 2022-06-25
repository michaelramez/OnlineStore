<%-- 
    Document   : BuyHistory
    Created on : Jun 2, 2022, 2:50:10 PM
    Author     : Michael Ramez
--%>

<%@page import="com.onlinestore.stock.Product"%>
<%@page import="com.onlinestore.customer.Customer"%>
<%@page import="com.onlinestore.order.OrderHandler"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%
            Customer customer = (Customer)session.getAttribute("customer");
            int cid = customer.getId();
            OrderHandler orderHandler = OrderHandler.GetOrderHandlerInstance();
            List<Product> buyHistory = orderHandler.GetBuyHistory(cid);
            
            for (Product p : buyHistory) {
            String customerRating = orderHandler.GetCustomerRating(cid, p.getId());
        %>
            <form action="AddToCartServlet" method="GET">
                <div class="single-product">
                    <div class="product-f-image">
                        <h2><p><%= p.getModel()%></p></h2>

                        <p>Quantity: <%=p.getStockQuantity()%></p>
                        <div class="product-hover">
                            <!--<a href="single-product.html" class="view-details-link"><i class="fa fa-link"></i> See details</a>-->
                            <input type="hidden" name="productid" value=<%= p.getId()%>>
                            <textarea id="rating" name="rating" rows="5" cols="40"><%=customerRating%></textarea>
                            <br>
                            <input type="submit" value="Update Rating" class="btn" formaction="UpdateRatingServlet">
                            <input type="submit" value="View all ratings" class="btn" formaction="ViewAllRatings.jsp">
                        </div>
                    </div>
                </div>
            </form>
            <%}%>        
    </body>
</html>
