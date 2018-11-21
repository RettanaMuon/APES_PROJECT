package com.apesdev.S.core.assets;

import org.bson.Document;



import static com.apesdev.S.core.parameter.Indexes.I_NAME;
import static com.apesdev.S.core.parameter.Indexes.I_URL;

public class Link  extends Asset {
    public static Document json(String url, String name){
        return new Document()
                .append(I_URL,url)
                .append(I_NAME,name)
                ;
    }

    public static Document json (Document doc){
        return json(doc.getString(I_URL),doc.getString(I_NAME));
    }
}
