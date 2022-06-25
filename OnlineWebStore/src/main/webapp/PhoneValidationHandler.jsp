<%-- 
    Document   : PhoneValidationHandler
    Created on : May 29, 2022, 2:58:43 PM
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
            String verificationCode = (String) session.getAttribute("code");
            String sentCode = request.getParameter("code");
            System.out.println(verificationCode);
            if (verificationCode.equals(sentCode)) {
                session.removeAttribute("code");
                response.sendRedirect("VerificationActionServlet");                
            } else {
        %>
        <form action="PhoneValidationHandler.jsp" method="GET">
            <label for="code">Please enter the verification code</label>
            <input type="text" id="code" name="code" required>
            <input type="submit" value="submit">
            <span>Invalid Verification Code</span>
        </form>
        <%}%>
    </body>
</html>
