package CMSProject.core.publications;

import CMSProject.core.assets.ErrorCode;
import CMSProject.core.assets.Publication;
import CMSProject.core.assets.PublicationArray;
import CMSProject.core.categories.CategoriesController;
import CMSProject.core.categories.Category;
import CMSProject.core.database.DB;
import CMSProject.core.nodes._IDNode;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.sun.xml.internal.bind.v2.TODO;
import org.bson.Document;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;

import static CMSProject.core.parameter.Collection.C_CATEGORIES;
import static CMSProject.core.parameter.Collection.C_PUBLICATIONS;
import static CMSProject.core.parameter.Collection.C_PUBS;
import static CMSProject.core.parameter.Indexes.*;
import static CMSProject.core.parameter.Keys.K_PUBS;
import static com.mongodb.client.model.Updates.*;

@CrossOrigin("http://localhost:4200")
@RestController
public class PublicationsController {
    private static MongoCollection pub = DB.getCollection(C_PUBLICATIONS);
    private static MongoCollection cat = DB.getCollection(C_CATEGORIES);

    @RequestMapping("/publication/init")
    public static void initialize(){
        MongoCollection c = DB.getCollection(C_PUBLICATIONS);
        c.createIndex(new Document()
                .append(I_TITLE, 1)
                .append(I_CATEGORY, 1)
                .append(I_AUTHOR, 1)
                .append(I_CONTENT, 1)
                .append(I_DATE, 1)
                .append(I_COMMENTS, 1)
        );
    }

    @RequestMapping("/publication/get")
    public static ArrayList<Document> getCategory(@RequestParam(value = "_id") Integer _id) {
        Document query = new Document(_ID,_id);
        Document queryResult = (Document) cat.find(query).first();
        ArrayList<Document> res = new ArrayList<>();
        ArrayList<Integer> data = (ArrayList<Integer>)((Document) queryResult.get(I_DATA)).get(I_DATA);
        ArrayList<Integer> childs = (ArrayList<Integer>) ((Document) cat.find(query).first()).get(I_CHILDS);
        for(Integer i : data){
            Document dataToAdd = (Document)pub.find(new Document(_ID,i)).first();
            if(dataToAdd != null)
                res.add(dataToAdd);
        }
        for(Integer i : childs){
            res.addAll(getCategory(i));
        }
        return res;
    }

    @RequestMapping(value = "/publication/add", method = RequestMethod.POST)
    public static ErrorCode add(@RequestParam(value = "category") Integer cat_id, @RequestParam(value = "title") String title, @RequestParam(value = "content") String content) {
        //TODO : find user_id per session
        Integer user_id = 0;
        pub.insertOne(Publication.json(user_id, title, content));
        Category.addPublication(cat_id, user_id);
        return new ErrorCode(1,"ok");
    }
/*
    public static ErrorCode remove(@RequestParam(value = "_id") Integer _id) {
        Document pubQuery = new Document(_ID,_id);
        Document deletedDoc = (Document) pub.findOneAndDelete(pubQuery);
        Document catQuery = new Document(deletedDoc.getInteger());
        cat.updateOne(catQuery);
        return new ErrorCode(1, "publication deleted");
    }
    */
}
