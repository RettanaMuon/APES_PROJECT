package CMSProject.core.categories;

import CMSProject.core.database.DB;
import com.mongodb.client.MongoCollection;
import org.bson.Document;

import static CMSProject.core.parameter.Collection.C_CATEGORIES;
import static CMSProject.core.parameter.Indexes.I_DATA;
import static CMSProject.core.parameter.Indexes._ID;
import static com.mongodb.client.model.Updates.addToSet;

public class Category {
    private static MongoCollection cat = DB.getCollection(C_CATEGORIES);

    public static void addPublication(Integer cat_id, Integer pub_id){
        Document query = new Document(_ID, cat_id);
        cat.updateOne(query, addToSet(I_DATA , pub_id));
    }
}
