package com.apesdev.S.core.counter;

import com.apesdev.S.core.Collection;
import com.apesdev.S.core.database.DB;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.FindOneAndUpdateOptions;
import com.mongodb.client.model.IndexOptions;
import com.mongodb.client.model.ReturnDocument;
import org.bson.Document;

import static com.apesdev.S.core.parameter.Collection.C_COUNTER;
import static com.apesdev.S.core.parameter.Indexes.*;

public class Counter extends Collection {
    private int counter;
    private String name;
    private String comment;
    public Counter(String name, int counter,String comment){
        Document query = new Document(I_NAME, name);
        if(DB.exist(C_COUNTER,query)){
            Document foundCounter = (Document)DB.getCollection(C_COUNTER).find(query).first();
            this.name = foundCounter.getString(I_NAME);
            this.counter = foundCounter.getInteger(I_COUNTER);
            this.comment = foundCounter.getString(I_COMS);
        }else {
            this.name = name;
            this.comment = comment;
            this.counter = counter;
        }
    }

    public Counter(String name, String comment){
        this(name, 0 ,comment);
    }

    public Counter(String name){
        this(name,0,"");
    }

    public Counter update(){
        DB.getCollection(C_COUNTER)
                .findOneAndUpdate(
                        new Document(I_NAME, name),
                        toDocument()
                        );
        return this;
    }

    public int increment(){
        counter ++;
        update();
        return counter;
    }

    public static int increment(String name){
        if(!DB.exist(C_COUNTER,new Document(I_NAME,name))){
            new Counter(name).insert();
            return 0;
        }else {
             Document doc = (Document) DB.getCollection(C_COUNTER)
                    .findOneAndUpdate(
                            new Document(I_NAME, name),
                            new Document("$inc",
                                    new Document(I_COUNTER, 1)
                            ),
                            new FindOneAndUpdateOptions().returnDocument(ReturnDocument.AFTER)
                    );
             return doc.getInteger(I_COUNTER);
        }
    }

    @Override
    public Counter insert(String collection){
        if(!DB.exist(collection, toDocument()))
            DB.getCollection(collection).insertOne(toDocument());
        return this;
    }

    public void insert() {
        insert(C_COUNTER);
    }


    @Override
    public Document toDocument() {
        return new Document()
                .append(I_NAME, name)
                .append(I_COUNTER, counter)
                .append(I_COMMENTS, comment)
                ;
    }
}
