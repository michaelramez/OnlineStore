<%-- 
    Document   : Admin
    Created on : May 11, 2022, 3:54:56 PM
    Author     : Michael Ramez
--%>

<%@page import="com.onlinestore.stock.Category"%>
<%@page import="java.util.List"%>
<%@page import="com.onlinestore.stock.Product"%>
<%@page import="java.util.Map"%>
<%@page import="com.onlinestore.stock.Stock"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <a href="AddProduct.jsp">Add Product</a>
        <%
            Stock stock = Stock.getStockInstance();
            List<Category> categories = stock.GetAllCategories();

            for (Category category : categories) {
                List<Product> products = stock.GetCategoryProducts(category.getId());
                if (!products.isEmpty()) {
        %>
        <div id=<%=category.getName()%>>
            <h1><%=category.getName()%></h1>
            <%

                for (Product p : products) {
            %>      

            <form class="add-to-cart" action="AddToCartServlet" method="GET">
                <div class="single-product">
                    <div class="product-f-image">
                        <label>Model</label>
                        <input type="text" name="editedModel" value="<%=p.getModel()%>"  required>
                        <br>
                        <br>
                        <label>Image Path</label>
                        <input type="text" name="editedImagePath" value="<%=p.getImagePath()%>" required>
                        <br>
                        <br>
                        <img src=<%= p.getImagePath()%> alt="">
                        <br>
                        <label>Category:</label>
                        <%
                            for (Category c : categories) {
                        %>
                        <input type="radio" name="editedcategoryid" value="<%=c.getId()%>" 
                               <%if (c.getId() == p.getCategory().getId()) {%> <%="checked"%> <%}%>>
                        <label><%=c.getName()%></label>
                        <%}%>
                        <br>
                        <br>
                        <label>Quantity</label>
                        <input type="number" min="1" name="editedQuantity" value="<%=p.getStockQuantity()%>" required>
                        <br>
                        <br>
                        <label>Price</label>
                        <input type="number" min="1" name ="editedPrice" value="<%=p.getPrice()%>" required>
                        <br>
                        <br>
                        <div class="product-hover">
                            <!--<a href="single-product.html" class="view-details-link"><i class="fa fa-link"></i> See details</a>-->
                            <input type="hidden" name="productid" value=<%= p.getId()%>>
                            <input type="submit" value="Edit Product" class="btn" formaction="EditProductServlet">
                            <input type="submit" value="Remove Product" formaction="RemoveProductServlet">
                        </div>
                    </div>
                </div>
            </form>
            <br>
            <br>
            <%}
                }%>        
        </div>
        <%}%>     
    </body>
</html>
