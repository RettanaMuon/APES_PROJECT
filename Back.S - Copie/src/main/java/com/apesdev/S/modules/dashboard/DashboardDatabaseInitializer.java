package com.apesdev.S.modules.dashboard;

import com.apesdev.S.core.assets.Link;
import com.apesdev.S.core.counter.Counter;
import com.apesdev.S.core.database.DB;
import com.apesdev.S.core.nodes._IDNode;
import com.mongodb.client.MongoCollection;
import org.bson.Document;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.apesdev.S.core.parameter.Common.*;
import static com.apesdev.S.modules.dashboard.DashboardParameter.*;
import static com.apesdev.S.core.parameter.Indexes.*;

@RestController
public class DashboardDatabaseInitializer {

    @RequestMapping("/dashboard/init")
    public static void run(){
        new Counter(ID_COUNTER, ID_COUNTER_COMMENT).insert();

        MongoCollection d = DB.getCollection(C_DASHBOARD);
        d.createIndex(new Document(I_DATA,1));
        d.createIndex(new Document(I_PARENTS,1));
        d.createIndex(new Document(I_CHILDS,1));

        _IDNode<Link> home = new _IDNode<Link>(Link.json(_URL_ROOT, "home"), ID_COUNTER).insert(C_DASHBOARD);
        _IDNode<Link> subHome = new _IDNode<Link>(home.get_id(),  Link.json(_URL_ROOT + "sub", "subHome"), ID_COUNTER).insert(C_DASHBOARD);
        _IDNode<Link> subHome2 = new _IDNode<Link>(home.get_id(),  Link.json(_URL_ROOT + "sub2", "subHome2"), ID_COUNTER).insert(C_DASHBOARD);
        _IDNode<Link> subsub = new _IDNode<Link>(subHome.get_id(),  Link.json(_URL_ROOT + "subsub", "subsub"), ID_COUNTER).insert(C_DASHBOARD);

    }
}
