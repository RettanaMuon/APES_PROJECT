package com.apesdev.S.modules.dashboard;

import com.apesdev.S.core.database.DB;
import com.mongodb.client.MongoCursor;
import org.bson.Document;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@CrossOrigin("http://localhost:4200")
@RestController
public class DashboardController {

    @RequestMapping("/dashboard")
    public static Document get(){
        ArrayList<Document> res = new ArrayList();
        MongoCursor cursor =  DB.getCollection(DashboardParameter.C_DASHBOARD).find().iterator();
        while(cursor.hasNext()){
            res.add((Document)cursor.next());
        }
        return new Document(DashboardParameter.MODULE_NAME, res);
    }
}
