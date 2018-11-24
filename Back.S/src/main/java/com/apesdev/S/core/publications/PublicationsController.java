package com.apesdev.S.core.publications;

import com.apesdev.S.core.assets.ErrorCode;
import com.apesdev.S.core.assets.Publication;
import com.apesdev.S.core.categories.Category;
import com.apesdev.S.core.counter.Counter;
import com.apesdev.S.core.database.DB;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import org.bson.Document;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

import static com.apesdev.S.core.parameter.Collection.C_CATEGORIES;
import static com.apesdev.S.core.parameter.Collection.C_COUNTER;
import static com.apesdev.S.core.parameter.Collection.C_PUBLICATIONS;
import static com.apesdev.S.core.parameter.Counters.COUNT_PUBS;
import static com.apesdev.S.core.parameter.Indexes.*;
import static com.mongodb.client.model.Updates.pull;

@RestController
@CrossOrigin("http://localhost:4200/")
@RequestMapping("/publications")
public class PublicationsController {
    private static MongoCollection pub = DB.getCollection(C_PUBLICATIONS);
    private static MongoCollection cat = DB.getCollection(C_CATEGORIES);

    @CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping("/getAll")
    public static ArrayList<Document> getAll(){
        // TODO set ?
        MongoCursor<Document> cats = cat.find().iterator();
        ArrayList<Document> res = new ArrayList<>();
        while (cats.hasNext()){
            res.addAll(getCategory(cats.next().getInteger(_ID)));
        }
        return res;
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping("/getPerCategory")
    public static ArrayList<Document> getCategory(@RequestParam(value = "cat") Integer cat_id) {
        Document query = new Document(_ID,cat_id);
        Document doc = (Document) cat.find(query).first();
        ArrayList<Document> res = new ArrayList<>();

        // get all in category
        ArrayList<Integer> pubs_id = (ArrayList<Integer>) doc.get(I_DATA);
        for(Integer p  : pubs_id){
            query = new Document(_ID, p);
            Document pubDoc = (Document) pub.find(query).first();
            res.add(pubDoc);
        }

        // get all in sub categories
        ArrayList<Integer> subcat_ids = (ArrayList<Integer>) doc.get(I_CHILDS);
        for(Integer c : subcat_ids){
            res.addAll(getCategory(c));
        }

        return res;
    }
    @CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public static ErrorCode add(@RequestParam(value = "category") String cat_id, @RequestParam(value = "title") String title, @RequestParam(value = "content") String content) {
        //TODO : find user_id per session
        Integer user_id = 0;
        int _id = Counter.increment(COUNT_PUBS);
        pub.insertOne(Publication.json(_id, user_id, title, content));
        Category.addPublication(Integer.parseInt(cat_id), _id);
        return new ErrorCode(1,"publication #" + _id + " with title \"" + title + "\" have been added.");
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping(value= "/publications/remove", method= RequestMethod.GET) /* @TODO post */
    public static ErrorCode remove(@RequestParam(value = "_id") Integer _id) {
        Document pubQuery = new Document(_ID,_id);
        pub.findOneAndDelete(pubQuery);
        Category.removePublication(_id);
        return new ErrorCode(1, "publication deleted");
    }

}
