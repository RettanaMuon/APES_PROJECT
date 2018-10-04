package CMSProject.core.nodes;

import CMSProject.core.assets.Asset;
import org.bson.Document;

import java.util.ArrayList;

import static CMSProject.core.parameter.Indexes.I_DATA;

public class  NodeData<T extends Asset> {
    public Document generate(Document doc){
        return T.json(doc);
    }
    public static Document doc(Object o) { return new Document (I_DATA, o); }
    public static Document voidList () { return doc(new ArrayList<>()); }
}
