package com.apesdev.S.factory.database;

import com.apesdev.S.core.assets.ErrorCode;
import com.apesdev.S.core.counter.Counter;
import com.apesdev.S.core.database.DB;
import com.apesdev.S.core.nodes._IDNode;
import com.apesdev.S.core.parameter.Collection;
import com.apesdev.S.core.parameter.Counters;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.IndexOptions;
import org.bson.Document;

import static com.apesdev.S.core.parameter.Collection.*;
import static com.apesdev.S.core.parameter.Indexes.*;
import static com.apesdev.S.core.parameter.Indexes.I_SESSION;
import static com.apesdev.S.factory.database.MockUp.createCategory;

public class Core {
    public static ErrorCode createCore(){
        String str = "";
        // Initialize users
        MongoCollection u = DB.getCollection(C_USERS);
        u.createIndex(new Document(I_UNAME, 1), new IndexOptions().unique(true));
        u.createIndex(new Document(I_PWD, 1));
        u.createIndex(new Document(I_MAIL, 1), new IndexOptions().unique(true));
        u.createIndex(new Document(I_DATA, 1));
        u.createIndex(new Document(I_PUBS, 1));
        u.createIndex(new Document(I_COMMENTS, 1));
        str+="Users collection has been created\n";

        // Initialize Publications
        new Counter(Counters.COUNT_PUBS, Counters.COUNT_PUBS_COMMENT);
        _IDNode.initializeDatabase(Collection.C_PUBS, true);
        str+="Publications collection has been created.\n";

        // Initialize Comments
        MongoCollection com = DB.getCollection(C_COMS);
        com.createIndex(new Document(I_AUTHOR, 1 ));
        com.createIndex(new Document(I_CONTENT, 1 ));
        com.createIndex(new Document(I_COMMENTS, 1 ));
        str+="Comments collection has been created.\n";

        // Initialize Sessions
        MongoCollection s = DB.getCollection(C_SESSIONS);
        s.createIndex(new Document(I_UNAME,1));
        s.createIndex(new Document(I_SESSION,1));
        str+="Sessions collection has been created.\n";

        // Initialize Counter
        MongoCollection count = DB.getCollection(C_COUNTER);
        count.createIndex(new Document(I_NAME,1), new IndexOptions().unique(true));
        count.createIndex(new Document(I_COUNTER, 1));
        count.createIndex(new Document(I_COMS, 1));
        str+="Counter collection has been created.\n";

        // Initialize Categories
        _IDNode.initializeDatabase(Collection.C_CATEGORIES, true);
        str+="Categories collection has been created.\n";
        str+=createCategory().getMessage();
        // return
        return new ErrorCode(1,str);
    }



}
