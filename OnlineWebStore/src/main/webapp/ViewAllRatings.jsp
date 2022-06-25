<%-- 
    Document   : ViewAllRatings
    Created on : Jun 2, 2022, 4:11:30 PM
    Author     : Michael Ramez
--%>

<%@page import="com.onlinestore.order.OrderHandler"%>
<%@page import="com.onlinestore.customer.Customer"%>
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
            OrderHandler orderHandler = OrderHandler.GetOrderHandlerInstance();
            int pid = Integer.parseInt(request.getParameter("productid"));
            List<Customer> customerRatings = orderHandler.GetProductRatings(pid);
            for (Customer customerRating: customerRatings){
        %>
        <h2><%=customerRating.getName()%></h2>
        <p><%=customerRating.getRating()%></p>
        <%}%>
    </body>
</html>
