
package com.apesdev.S.core.comments;

import com.apesdev.S.core.assets.Comment;
import com.apesdev.S.core.assets.ErrorCode;
import com.apesdev.S.core.database.DB;
import com.apesdev.S.core.nodes._IDNode;
import com.mongodb.client.MongoCollection;
import org.bson.Document;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

import static com.apesdev.S.core.parameter.Collection.C_COMMENTS;
import static com.apesdev.S.core.parameter.Collection.C_PUBLICATIONS;
import static com.apesdev.S.core.parameter.Counters.COUNT_COMMENTS;
import static com.apesdev.S.core.parameter.Indexes.*;
import static com.apesdev.S.core.parameter.Keys.K_PUB_ID;
import static com.mongodb.client.model.Updates.addToSet;
import static com.mongodb.client.model.Updates.pull;

@CrossOrigin("http://localhost:4200")
@RequestMapping("comments")
@RestController
public class CommentsController {
    private static MongoCollection com = DB.getCollection(C_COMMENTS);

    @RequestMapping("/add")
    public static ErrorCode add(@RequestParam(value = "publication") String pub,
                                @RequestParam(value = "user"       ) String user,
                                @RequestParam(value = "parent"     ) String parent,
                                @RequestParam(value = "content"    ) String content) {
        Integer pub_id = Integer.parseInt(pub);
        Integer user_id = Integer.parseInt(user);
        Integer parent_id = Integer.parseInt(parent);
        int _id = new _IDNode<Comment>(parent_id,Comment.json(pub_id, content),COUNT_COMMENTS).insert(C_COMMENTS).get_id();

        Document query = new Document(_ID, pub_id);
        /* Add comment_id to publication only if this comment isn't a subcomment (ie. a comment of a comment) */
        if(parent_id == -1)
            DB.getCollection(C_PUBLICATIONS).updateOne(query, addToSet(I_COMMENTS, _id));
        return new ErrorCode(1, "Comment #"+ _id + " of " + user_id + " have been added.\n" );
    }

    @RequestMapping("/get")
    public static Document get(@RequestParam(value = "_id") String comment_id){
        Integer _id = Integer.parseInt(comment_id);
        Document query = new Document(_ID, _id);
        Document res = (Document) com.find(query).first();
        return res;
    }

    public static ErrorCode remove(int comment_id){
        String str= "";
        // Remove the comment in the DB
        Document query = new Document(_ID, comment_id);
        Document deletedDoc = (Document) com.findOneAndDelete(query);
        // Remove the comment referenced in the publication
        Document data = (Document) deletedDoc.get(I_DATA);
        Integer pub_id = data.getInteger(K_PUB_ID);
        query = new Document (_ID, pub_id);
        DB.getCollection(C_PUBLICATIONS).findOneAndUpdate(query, pull(I_DATA, pub_id));
        // Remove the childs comments TODO : remove or not?
        ArrayList<Integer> childs = (ArrayList<Integer>) deletedDoc.get(I_CHILDS);
        for(Integer c : childs){
            str += "Sub"+remove(c).getMessage();
        }
        return new ErrorCode(1, str + "Comment #" + comment_id + " have been removed.\n");
    }

    @RequestMapping("/remove")
    public static ErrorCode remove(@RequestParam(value = "comment") String comment){
        Integer comment_id = Integer.parseInt(comment);
        return remove(comment_id);
    }

}
