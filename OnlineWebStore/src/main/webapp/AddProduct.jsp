<%-- 
    Document   : AddProduct
    Created on : May 27, 2022, 11:24:27 AM
    Author     : Michael Ramez
--%>

<%@page import="com.onlinestore.stock.Stock"%>
<%@page import="com.onlinestore.stock.Category"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <form method="GET" action="AddCategoryServlet">
            <label for="categoryname">Add Category</label>
            <input type="text" id="categoryname" name="categoryname" required>
            <input type="submit" value="Add Category">
        </form>   
        <br>
        <form method="GET" action="AddProductServlet">
            <%
                Stock stock = Stock.getStockInstance();
                List<Category> categories = stock.GetAllCategories();
            %>
            <label>Categories</label>
            <%
                for (Category category : categories) {
            %>
            <input type="radio" name="categoryid" value="<%=category.getId()%>" required>
            <label><%=category.getName()%></label>
            <%}%>
            <br>
            <br>
            <label for="model">Model</label><br>
            <input type="text" id="model" name="model" required>
            <br>
            <br>

            <label for="quantity">Quantity</label><br>
            <input type="number" min="1" id="quantity" name="quantity" required>
            <br>
            <br>

            <label for="price">Price</label><br>
            <input type="number" min="1000" id="price" name="price" required>
            <br>
            <br>

            <label for="imagepath">Path</label><br>
            <input type="text" id="imagepath" name="imagepath" required>
            <br>
            <br>
            <input type="submit" value="Save">
        </form>
    </body>
</html>
