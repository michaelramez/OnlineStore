/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */

$(document).ready(function() {
    $("#searchClick").click(function() {
        cat = $("#cat").val();
        maxPrice = $("#maxPrice").val();
        
        $.ajax({
            type : "POST",
            url : "displayProducts.jsp",
            data : "cat=" + cat + "&maxPrice=" + maxPrice,
         success : function(data) {
                $("#response").html(data);
         }
        });
    });     
});      