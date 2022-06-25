<%-- 
    Document   : LoginHandler
    Created on : May 2, 2022, 6:33:41 PM
    Author     : Michael Ramez
--%>

<%@page import="com.onlinestore.customer.Customer"%>
<%@page import="com.onlinestore.customer.CustomerHandler"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%
            CustomerHandler customerHandler = CustomerHandler.getCustomerHandlerInstance();

            String username = request.getParameter("uname");
            String password = request.getParameter("pass");

            Customer customer = new Customer(username, password);
            CustomerHandler.LoginInfo loginInfo = customerHandler.Login(customer);
            if (loginInfo.isNoErrors()) {
                session.setAttribute("isLoggedIn", "true");
                session.setAttribute("customer", loginInfo.getCustomer());
//                session.setMaxInactiveInterval(0);
                response.sendRedirect("MainPage.jsp");

            } else {
        %>
        <form method="GET" action="LoginHandler.jsp">

            <label for="uname">User Name</label><br>
            <input type="text" id="uname" name="uname" value="<%=username%>" required>
            <br>
            <br>
            <label for="pass">Password</label><br>
            <input type="password" id="pass" name="pass" required>
            <p>Invalid Username or Password</p>
            <input type="submit" value="Login">

        </form>
        <%}%>
    </body>
</html>
