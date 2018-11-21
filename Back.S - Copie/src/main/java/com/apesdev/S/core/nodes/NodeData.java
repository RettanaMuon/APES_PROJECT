package com.apesdev.S.core.nodes;

import com.apesdev.S.core.assets.Asset;
import org.bson.Document;

import java.util.ArrayList;

import static com.apesdev.S.core.parameter.Indexes.I_DATA;

public class  NodeData<T extends Asset> {
    public Document generate(Document doc){
        return T.json(doc);
    }
    public static Document doc(Object o) { return new Document (I_DATA, o); }
    public static Document voidList () { return doc(new ArrayList<>()); }
}
