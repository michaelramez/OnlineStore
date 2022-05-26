<%-- 
    Document   : test
    Created on : Apr 3, 2022, 4:24:43 PM
    Author     : Michael Ramez
--%>

<%@page import="java.lang.reflect.Type"%>
<%@page import="com.google.gson.reflect.TypeToken"%>
<%@page import="java.util.List"%>
<%@page import="com.onlinestore.order.OrderProduct"%>
<%@page import="java.util.Enumeration"%>
<%@page import="com.google.gson.Gson"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%
//          
            String jsonStr = request.getParameter("products");
            Type listType = new TypeToken<List<OrderProduct>>(){}.getType();
            List<OrderProduct> myList = new Gson().fromJson(jsonStr, listType);
            for (OrderProduct item : myList){
                out.print(item.getId()+" " + item.getReqQuantity());
            }
        %>
    </body>
</html>
