package com.apesdev.S.core.database;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import static com.apesdev.S.core.parameter.Common.*;

/**
 * Class of database accessors.
 */
public class DB {
    /* Singleton */
    private static MongoClient client = null;

    /* DB Acess */
    public static MongoClient getClient() {
        if (client == null) {
            client = MongoClients.create(DB_HOST);
        }
        return client;
    }

    public static MongoDatabase getDb(){
        return getClient().getDatabase(DB_NAME);
    }

    public static MongoCollection getCollection (String collection){
        return getDb().getCollection(collection);
    }

    public static boolean exist(String collection, Document doc){
        if(getCollection(collection).find(doc).first() == null)
            return false;
        return true;
    }
}
