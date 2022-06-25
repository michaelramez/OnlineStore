<%-- 
    Document   : ViewProfile
    Created on : May 3, 2022, 2:25:45 PM
    Author     : Michael Ramez
--%>

<%@page import="com.onlinestore.customer.CustomerHandler"%>
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
            Customer customer = (Customer) session.getAttribute("customer");
            CustomerHandler customerHandler = CustomerHandler.getCustomerHandlerInstance();
            Customer CustomerInfo = customerHandler.getCustomerInfo(customer.getId());
        %>
        <form method="GET" action="UpdateCustomerServlet">

            <label for="name">Name</label><br>
            <input type="text" id="name" name="name" value="<%=CustomerInfo.getName()%>" required>
            <br>
            <br>
            <label for="uname">User Name</label><br>
            <input type="text" id="uname" value="<%=CustomerInfo.getUsername()%>" disabled>
            <br>
            <br>
            <label for="email">Email</label><br>
            <input type="text" id="email" name="email" value="<%=CustomerInfo.getMail()%>" required>
            <br>
            <br>
            <label for="phone">Phone</label><br>
            <input type="text" id="phone" value="<%=CustomerInfo.getPhone()%>" disabled>
            <br>
            <br>
            <label for="bdate">Date of Birth</label><br>
            <input type="date" id="bdate" name="bdate" value="<%=CustomerInfo.getBirthday()%>" required>
            <br>
            <br>
            <label for="address">Address</label><br>
            <input type="text" id="address" name="address" value="<%=CustomerInfo.getAddress()%>" required>
            <br>
            <br>
            <label for="job">Job</label><br>
            <input type="text" id="job" name="job" value="<%=CustomerInfo.getJob()%>" required>
            <br>
            <br>
            <label for="climit">Credit Limit</label><br>
            <input type="number" id="climit" name="climit" value="<%=CustomerInfo.getCreditLimit()%>" required>
            <br>
            <br>
            <input type="submit" value="Update Data">
        </form>
    </body>
</html>
