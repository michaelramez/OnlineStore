<%-- 
    Document   : BuyCart
    Created on : Apr 26, 2022, 12:45:43 AM
    Author     : Michael Ramez
--%>

<%@page import="com.onlinestore.twilio.TwilioService"%>
<%@page import="com.onlinestore.customer.Customer"%>
<%@page import="com.onlinestore.order.OrderProduct"%>
<%@page import="com.onlinestore.order.Order"%>
<%@page import="java.util.ArrayList"%>
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
            String[] reqProductsIDs = request.getParameterValues("productid");
            String[] reqProductsQuantities = request.getParameterValues("reqQuantity");
            List<OrderProduct> reqProducts = new ArrayList<>();
            if (reqProductsIDs == null || reqProductsQuantities == null) {
                session.setAttribute("products", reqProducts);
                response.sendRedirect("ViewCart.jsp");
            } else {
                for (int i = 0; i < reqProductsIDs.length; i++) {
                    reqProducts.add(new OrderProduct(Integer.parseInt(reqProductsIDs[i]), Integer.parseInt(reqProductsQuantities[i])));
                }
                session.setAttribute("products", reqProducts);
                Customer customer = (Customer) session.getAttribute("customer");
                int cid = customer.getId();
                Order order = new Order(reqProducts, cid);
                Order.OrderErrors orderErrors = order.CheckOrderValidity();
                if (orderErrors.isNoErrors()) {
                    TwilioService twilioService = TwilioService.GetTwilioServiceInstance();
                    String verificationCode = twilioService.GenerateRandomCode();
                    session.setAttribute("code", verificationCode);
                    session.setAttribute("action", "buy");
                    session.setAttribute("order", order);
                    response.sendRedirect("PhoneValidation.html");
                } else {
                    if (orderErrors.isInsufficientCredit()) {
        %>

        <p>Sorry you don't have enough credit to buy required products</p>
        <%}
            if (orderErrors.isOutOfStock()) {%>
        <p>Sorry product <%=orderErrors.getOutOfStockPname()%> is out of stock right now</p>
        <%}
                }
            }%>
    </body>
</html>
