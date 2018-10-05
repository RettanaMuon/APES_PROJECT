package com.apesdev.S.factory.database;

import com.apesdev.S.core.assets.ErrorCode;
import com.apesdev.S.core.assets._IDs;
import com.apesdev.S.core.database.DB;
import com.apesdev.S.core.nodes.NodeData;
import com.apesdev.S.core.nodes._IDNode;
import com.apesdev.S.core.parameter.Collection;
import com.apesdev.S.core.parameter.Counters;
import com.apesdev.S.core.user.User;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.IndexOptions;
import org.bson.Document;

import java.util.Date;

import static com.apesdev.S.core.parameter.Collection.*;
import static com.apesdev.S.core.parameter.Common.R_ADMIN;
import static com.apesdev.S.core.parameter.Indexes.*;
import static com.apesdev.S.core.parameter.Indexes.I_SESSION;

public class core {
    public static ErrorCode createCore(){
        // Initialize users
        MongoCollection u = DB.getCollection(C_USERS);
        u.createIndex(new Document(I_UNAME, 1), new IndexOptions().unique(true));
        u.createIndex(new Document(I_PWD, 1));
        u.createIndex(new Document(I_MAIL, 1), new IndexOptions().unique(true));
        u.createIndex(new Document(I_DATA, 1));
        u.createIndex(new Document(I_PUBS, 1));
        u.createIndex(new Document(I_COMMENTS, 1));
        // Initialize Publications
        MongoCollection p = DB.getCollection(C_PUBLICATIONS);
        p.createIndex(new Document()
                .append(I_TITLE, 1)
                .append(I_CATEGORY, 1)
                .append(I_AUTHOR, 1)
                .append(I_CONTENT, 1)
                .append(I_DATE, 1)
                .append(I_COMMENTS, 1)
        );
        // Initialize Comments
        MongoCollection com = DB.getCollection(C_COMS);
        com.createIndex(new Document(I_AUTHOR, 1 ));
        com.createIndex(new Document(I_CONTENT, 1 ));
        com.createIndex(new Document(I_COMMENTS, 1 ));

        // Initialize Sessions
        MongoCollection s = DB.getCollection(C_SESSIONS);
        s.createIndex(new Document(I_UNAME,1));
        s.createIndex(new Document(I_SESSION,1));

        // Initialize Counter
        MongoCollection count = DB.getCollection(C_COUNTER);
        count.createIndex(new Document(I_NAME,1), new IndexOptions().unique(true));
        count.createIndex(new Document(I_COUNTER, 1));
        count.createIndex(new Document(I_COMS, 1));

        // Initialize Categories
        _IDNode.initializeDatabase(Collection.C_CATEGORIES, true);
        new _IDNode<_IDs>("all", null, NodeData.voidList() , Counters.COUNT_CATEGORIES).insert(Collection.C_CATEGORIES);

        // return
        return new ErrorCode(1,"Core collections : Comments, Categories, Counter, Publication, Sessions, Users created.\n");
    }

    public static ErrorCode createMockUpAccount(){
        // Create a mock-up admin user
        if(DB.getCollection(C_USERS).find(new Document(I_UNAME,"admin")).first() == null) {
            new User(
                    "admin",
                    "password",
                    "Apemin",
                    "Istrator",
                    "mock-up@admin.com",
                    new Date(),
                    R_ADMIN
            ).insert();
            return new ErrorCode(1, "Mock-up admin created.\n");
        }else{
            return new ErrorCode(2, "An admin user already exist. Mock-up admin had not been created.\n");
        }
    }


}
