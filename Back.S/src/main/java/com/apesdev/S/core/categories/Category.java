package com.apesdev.S.core.categories;

import com.apesdev.S.core.database.DB;
import com.mongodb.client.MongoCollection;
import com.apesdev.S.core.parameter.Collection;
import com.apesdev.S.core.parameter.Indexes;
import com.mongodb.client.MongoCursor;
import org.bson.Document;

import static com.apesdev.S.core.parameter.Collection.C_COUNTER;
import static com.apesdev.S.core.parameter.Indexes.I_DATA;
import static com.mongodb.client.model.Updates.addToSet;
import static com.mongodb.client.model.Updates.pull;

public class Category {
    private static MongoCollection cat = DB.getCollection(Collection.C_CATEGORIES);

    public static void addPublication(Integer cat_id, Integer pub_id){
        Document query = new Document(Indexes._ID, cat_id);
        cat.updateOne(query, addToSet(I_DATA , pub_id));
    }

    public static void removePublication(int pub_id){
        DB.getCollection(C_COUNTER).updateMany(new Document(), pull(I_DATA, pub_id));
    }
}
