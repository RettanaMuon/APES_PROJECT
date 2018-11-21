package com.apesdev.S.core.categories;

import com.apesdev.S.core.assets.ErrorCode;
import com.apesdev.S.core.assets._IDs;
import com.apesdev.S.core.database.DB;
import com.apesdev.S.core.nodes.NodeData;
import com.apesdev.S.core.nodes._IDNode;
import com.apesdev.S.core.parameter.Collection;
import com.apesdev.S.core.parameter.Counters;
import com.apesdev.S.core.parameter.Indexes;
import org.bson.Document;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@CrossOrigin("localhost:4200")
@RestController
public class CategoriesController {

    @RequestMapping("/categories/addSubCategory")
    public static ErrorCode addCategory(@RequestParam(value = "name") String name,
                                        @RequestParam(value = "parent") Integer parent){
        if (parent == null){
            parent = getRoot();
        }
        new _IDNode<_IDs>(name, parent, new ArrayList(), Counters.COUNT_CATEGORIES).insert(Collection.C_CATEGORIES);
        return new ErrorCode(1, "Category added");
    }

    @RequestMapping("/categories/addRootCategory")
    public static ErrorCode addCategory(@RequestParam(value = "name") String name){
        return addCategory(name, null);
    }


    @RequestMapping("/categories/remove")
    public ErrorCode removeCategory(@RequestParam(value = "_id") Integer _id){
        Document query = new Document(Indexes._ID, _id);
        _IDNode.remove(Collection.C_CATEGORIES, _id);
        return new ErrorCode (1, "Category removed");
    }

    public static Integer getRoot(){
        Document doc = (Document) DB.getCollection(Collection.C_CATEGORIES).find(new Document(Indexes.I_PARENTS, null)).first();
        return doc.getInteger(Indexes._ID);
    }
}
