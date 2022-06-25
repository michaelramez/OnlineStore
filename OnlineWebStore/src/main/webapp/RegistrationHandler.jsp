<%-- 
    Document   : LoginHandler
    Created on : May 2, 2022, 4:22:29 PM
    Author     : Michael Ramez
--%>

<%@page import="com.onlinestore.twilio.TwilioService"%>
<%@page import="com.onlinestore.customer.Customer"%>
<%@page import="java.sql.Date"%>
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
            String name = request.getParameter("name");
            String username = request.getParameter("uname");
            String email = request.getParameter("email");
            String password = request.getParameter("pass");
            String phone = request.getParameter("phone");
            Date bdate = Date.valueOf(request.getParameter("bdate"));
            String address = request.getParameter("address");
            String job = request.getParameter("job");
            int creditLimit = Integer.parseInt(request.getParameter("climit"));
            Customer customer = new Customer(creditLimit, name, username, email, password, job, bdate, phone, address);
            CustomerHandler.RegistrationInfo registrationInfo = customerHandler.Register(customer);
            if (registrationInfo.isNoErrors()) {
                response.sendRedirect("PhoneValidation.html");
                TwilioService twilioService = TwilioService.GetTwilioServiceInstance();
                String verificationCode = twilioService.GenerateRandomCode();
                session.setAttribute("code", verificationCode);
                session.setAttribute("action", "register");
                session.setAttribute("customer", registrationInfo.getCustomer());
            } else {
        %>     
        <div>
            <form method="GET" action="RegistrationHandler.jsp">

                <label for="name">Name</label><br>
                <input type="text" id="name" name="name" value="<%=name%>" required>
                <br>
                <br>

                <label for="uname">User Name</label><br>
                <input type="text" id="uname" name="uname" value="<%=username%>" required>
                <%if (registrationInfo.isUserExits()) {%>
                <span>Username Already Exists</span>
                <%}%>
                <br>
                <br>

                <label for="email">Email</label><br>
                <input type="text" id="email" name="email" value="<%=email%>" required>
                <br>
                <br>

                <label for="pass">Password</label><br>
                <input type="password" id="pass" name="pass" required> 
                <br>
                <br>

                <label for="phone">Phone</label><br>
                <input type="tel" id="phone" name="phone" value="<%=phone%>" required>
                <%if (registrationInfo.isPhoneValid() == false) {%>
                <span>Invalid Phone Number</span>
                <br><br>
                <%}%>

                <label for="bdate">Date of Birth</label><br>
                <input type="date" id="bdate" name="bdate" value="<%=bdate%>" required>
                <br>
                <br>

                <label for="address">Address</label><br>
                <input type="text" id="address" name="address" value="<%=address%>">
                <br>
                <br>

                <label for="job">Job</label><br>
                <input type="text" id="job" name="job" value="<%=job%>">
                <br>
                <br>

                <label for="climit">Credit Limit</label><br>
                <input type="number" id="climit" name="climit" min="0" value="<%=creditLimit%>" required>
                <br>
                <br>
                <input type="submit" value="Register">
            </form>

        </div>
        <%}%>

    </body>
</html>
