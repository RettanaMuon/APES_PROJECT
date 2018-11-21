package com.apesdev.S.core.assets;

import org.bson.Document;

import java.util.ArrayList;

import static com.apesdev.S.core.parameter.Keys.K_PUBS;

public class PublicationArray extends Asset{
    public static Document json(ArrayList<Publication> list) {
        return new Document()
                .append("Publications", list)
                ;
    }

    public static Document json(Document doc) {
        return json((ArrayList<Publication>)doc.get(K_PUBS));
    }
}
