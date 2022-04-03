<%-- 
    Document   : test
    Created on : Apr 3, 2022, 4:24:43 PM
    Author     : Michael Ramez
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%
            out.print(request.getParameter("name"));
        %>
    </body>
</html>
