package CMSProject.core.database;
import CMSProject.core.counter.Counter;
import CMSProject.core.user.User;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.IndexOptions;
import org.bson.Document;

import java.util.Date;

import static CMSProject.core.parameter.Collection.*;
import static CMSProject.core.parameter.Common.*;
import static CMSProject.core.database.DB.*;
import static CMSProject.core.parameter.Indexes.*;

/**
 * Class of database initializer
 */
public class DatabaseInitializer {
    /**
     * Main function of DatabaseInitializer. Call of the creators of the class.
     */
    public static void run(){
        createCollections();
        // Create a first admin user
        if(getCollection(C_USERS).find(new Document(I_UNAME,"admin")).first() == null) {
            new User(
                    "admin",
                    "password",
                    "Admin",
                    "Istrator",
                    "mock-up@admin.com",
                    new Date(),
                    R_ADMIN
            ).insert();
        }
    }

    /**
     * Create a list of Collection and create Indexes for each collection with their options.
     */
    private static void createCollections(){
        MongoCollection u = getCollection(C_USERS);
        u.createIndex(new Document(I_UNAME, 1), new IndexOptions().unique(true));
        u.createIndex(new Document(I_PWD, 1));
        u.createIndex(new Document(I_MAIL, 1), new IndexOptions().unique(true));
        u.createIndex(new Document(I_DATA, 1));
        u.createIndex(new Document(I_PUBS, 1));
        u.createIndex(new Document(I_COMMENTS, 1));

        MongoCollection p = getCollection(C_PUBS);
        p.createIndex(new Document(I_AUTHOR, 1 ));
        p.createIndex(new Document(I_CONTENT, 1 ));
        p.createIndex(new Document(I_COMMENTS, 1 ));

        MongoCollection com = getCollection(C_COMS);
        com.createIndex(new Document(I_AUTHOR, 1 ));
        com.createIndex(new Document(I_CONTENT, 1 ));
        com.createIndex(new Document(I_COMMENTS, 1 ));

        MongoCollection s = getCollection(C_SESSIONS);
        s.createIndex(new Document(I_UNAME,1));
        s.createIndex(new Document(I_SESSION,1));

        Counter.initializeDatabase();
    }

}
