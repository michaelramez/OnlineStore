/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.onlinestore.restapi;

import com.google.gson.Gson;
import com.onlinestore.stock.Product;
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author Michael Ramez
 */
@Path("/services")
public class RESTfulWebService {
    private final ProductsService productsService;

    public RESTfulWebService() {
        this.productsService = ProductsService.getProductsServiceInstance();
    }
    
    @GET
    @Path("/listCategories")
    @Produces(MediaType.APPLICATION_JSON)
    public String ListCategories(){
        List<String> categories = productsService.ListCategories();        
        return new Gson().toJson(categories);
    }
    
    @GET
    @Path("/listModels")
    @Produces(MediaType.APPLICATION_JSON)
    public String ListModelsByCategory(String category){
        List<Product> categoryModels = productsService.ListModelsByCategory(category);
        return new Gson().toJson(categoryModels);
    }
    
    @GET
    @Path("/listProduct/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public String ListProductById(@PathParam("id") int id){
//        Product productInfo = productsService.ListProductById(id);
//        return new Gson().toJson(productInfo);
    return null;
    }
}
