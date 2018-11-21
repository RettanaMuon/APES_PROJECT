package com.apesdev.S.core.assets;

import org.bson.Document;

import static com.apesdev.S.core.parameter.Keys.K_CONTENT;
import static com.apesdev.S.core.parameter.Keys.K_PUB_ID;

public class Comment extends Asset {
    public static Document json(int pub_id, String content){
        return new Document()
                .append(K_PUB_ID, pub_id)
                .append(K_CONTENT, content)
                ;
    }
    public static Document json(Document doc){
        return json(doc.getInteger(K_PUB_ID), doc.getString(K_CONTENT));
    }
}
