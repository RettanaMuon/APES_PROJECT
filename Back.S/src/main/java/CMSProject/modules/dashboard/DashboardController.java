package CMSProject.modules.dashboard;

import CMSProject.core.database.DB;
import com.mongodb.client.MongoCursor;
import org.bson.Document;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

import static CMSProject.modules.dashboard.DashboardParameter.C_DASHBOARD;
import static CMSProject.modules.dashboard.DashboardParameter.MODULE_NAME;

@CrossOrigin("http://localhost:4200")
@RestController
public class DashboardController {

    @RequestMapping("/dashboard")
    public static Document get(){
        ArrayList<Document> res = new ArrayList();
        MongoCursor cursor =  DB.getCollection(C_DASHBOARD).find().iterator();
        while(cursor.hasNext()){
            res.add((Document)cursor.next());
        }
        return new Document(MODULE_NAME, res);
    }
}
