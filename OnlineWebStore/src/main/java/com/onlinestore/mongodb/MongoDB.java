/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.onlinestore.mongodb;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import static com.mongodb.client.model.Filters.eq;
import com.mongodb.client.model.UpdateOptions;
import com.mongodb.client.model.Updates;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.InsertOneResult;
import com.mongodb.client.result.UpdateResult;
import org.bson.Document;
import org.bson.conversions.Bson;

/**
 *
 * @author Michael Ramez
 */
public class MongoDB {
    private final String URL = "mongodb://localhost:27017";
    private final String DBName = "customerorders";
    private final String collectionName = "orders";
    private MongoClient mongoClient;
    private MongoDatabase mongoDatabase;
    private MongoCollection<Document> mongoCollection;
    private static final MongoDB mongoInstance = new MongoDB();
    
    private MongoDB() {
    }
    
    
    
    public static MongoDB GetMongoDBInstance(){
        return mongoInstance;
    }
    
    public void ConnectToMongo(){
        mongoClient = MongoClients.create(URL);
        mongoDatabase = mongoClient.getDatabase(DBName);
        mongoCollection = mongoDatabase.getCollection(collectionName);
    }
    
    public void CloseMongoConnection(){
        mongoClient.close();
    }
    
    
    public void MongoUpsert(Bson filter, Bson update){
        UpdateOptions updateOptions = new UpdateOptions().upsert(true);
        mongoCollection.updateOne(filter, update, updateOptions);
    }
    
    
    public void MongoDeleteOne(Bson filter){
        mongoCollection.deleteOne(filter);
    }
    
    public void MongoDeleteMany(Bson filter){
        mongoCollection.deleteMany(filter);
    }
    
    
    public Document MongoFindOne(Bson filter){
        return mongoCollection.find(filter).first();
    }
    
    public MongoCursor<Document> MongoFindMany(Bson filter){
        return mongoCollection.find(filter).iterator();        
    }
    
    public static void main(String[] args) {
//        String URLmain = "mongodb://localhost:27017";
//        String DBNamemain = "productsrating";
//        String collectionNamemain = "product";
//        
//        MongoClient mongoClient = MongoClients.create(URLmain);
//        MongoDatabase mongodatabase = mongoClient.getDatabase(DBNamemain);
//        MongoCollection<Document> collection = mongodatabase.getCollection(collectionNamemain);
        //insert
//        Document insDocument = new Document().append("pid", 1).append("rating", "good");
//        InsertOneResult insResult = collection.insertOne(insDocument);
        //update   
//        Document updateDocument = new Document().append("pid", 1);
//        Bson updateBson = Updates.combine(Updates.set("rating", "bad"));
//        UpdateResult updateResult = collection.updateOne(updateDocument, updateBson);
        
        //delete       
//        Bson delBson = eq("pid", 1);
//        DeleteResult delResult = collection.deleteOne(delBson);
        //delete many
//        DeleteResult delResult = collection.deleteMany(delBson);
        
        //find       
//        Bson findBson = eq("pid", 1);
//        Document findDocument = collection.find(findBson).first();
//        System.out.println("rating is: " + findDocument.getString("rating"));
        //find many
//        MongoCursor<Document> cursor = collection.find(findBson).iterator();
//        while (cursor.hasNext()){
//            System.out.println("rating is: " + cursor.next().getString("rating"));
//        }
//        cursor.close();

    }
}
