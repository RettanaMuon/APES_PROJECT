package CMSProject.core.categories;

import CMSProject.core.assets.ErrorCode;
import CMSProject.core.assets._IDs;
import CMSProject.core.database.DB;
import CMSProject.core.nodes.NodeData;
import CMSProject.core.nodes._IDNode;
import org.bson.Document;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import static CMSProject.core.parameter.Collection.C_CATEGORIES;
import static CMSProject.core.parameter.Counters.COUNT_CATEGORIES;
import static CMSProject.core.parameter.Indexes.I_PARENTS;
import static CMSProject.core.parameter.Indexes._ID;

@CrossOrigin("localhost:4200")
@RestController
public class CategoriesController {

    @RequestMapping("/categories/init")
    public static void initialize(){
        _IDNode.initializeDatabase(C_CATEGORIES, true);
        new _IDNode<_IDs>("all", null, NodeData.voidList() , COUNT_CATEGORIES).insert(C_CATEGORIES);
    }

    @RequestMapping("/categories/addSubCategory")
    public static ErrorCode addCategory(@RequestParam(value = "name") String name,
                                 @RequestParam(value = "parent") Integer parent){
        if (parent == null){
            parent = getRoot();
        }
        new _IDNode<_IDs>(name, parent, NodeData.voidList() , COUNT_CATEGORIES).insert(C_CATEGORIES);
        return new ErrorCode(1, "Category added");
    }

    @RequestMapping("/categories/addRootCategory")
    public static ErrorCode addCategory(@RequestParam(value = "name") String name){
        return addCategory(name, null);
    }


    @RequestMapping("/categories/remove")
    public ErrorCode removeCategory(@RequestParam(value = "_id") Integer _id){
        Document query = new Document(_ID, _id);
        _IDNode.remove(C_CATEGORIES, _id);
        return new ErrorCode (1, "Category removed");
    }

    public static Integer getRoot(){
        Document doc = (Document) DB.getCollection(C_CATEGORIES).find(new Document(I_PARENTS, null)).first();
        return doc.getInteger(_ID);
    }
}
