package com.apesdev.S.core;

import org.bson.Document;

import static com.apesdev.S.core.database.DB.getCollection;

public abstract class Collection {
    public abstract Document toDocument();
    public Collection insert(String collection){
        getCollection(collection).insertOne(toDocument());
        return this;
    }
}
