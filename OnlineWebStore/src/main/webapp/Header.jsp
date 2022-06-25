<%-- 
    Document   : Header
    Created on : May 27, 2022, 10:55:51 AM
    Author     : Michael Ramez
--%>

<%@page import="com.onlinestore.customer.Customer"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%
            String isLoggedIn = (String) session.getAttribute("isLoggedIn");
            if (isLoggedIn == null) {
        %>
        <a href="Login.html">Login</a>
        <a href="Register.html">Register</a>
        <a href="ViewCart.jsp" target="_blank">View Cart</a>
        <%} else {
            Customer customer = (Customer) session.getAttribute("customer");
        %>
        <label>Hello,</label>
        <a href="ViewProfile.jsp" target="_blank"><%= customer.getName()%></a>
        <a href="LogoutServlet">Logout</a>
        <a href="ViewCart.jsp">View Cart</a>
        <a href="BuyHistory.jsp">Buy History</a>
        <%}%>
    </body>
</html>
