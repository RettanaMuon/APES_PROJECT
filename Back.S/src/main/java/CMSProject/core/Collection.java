package CMSProject.core;

import org.bson.Document;

import static CMSProject.core.database.DB.getCollection;

public abstract class Collection {
    public abstract Document toDocument();
    public Collection insert(String collection){
        getCollection(collection).insertOne(toDocument());
        return this;
    }
}
