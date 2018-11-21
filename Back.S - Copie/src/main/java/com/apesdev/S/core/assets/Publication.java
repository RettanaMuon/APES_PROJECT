package com.apesdev.S.core.assets;

import org.bson.Document;

import java.util.ArrayList;
import java.util.Date;

import static com.apesdev.S.core.parameter.Indexes.*;

public class Publication extends Asset {
    public static Document json(int _id, int user_id, String title, String content) {
        return new Document()
                .append(_ID, _id)
                .append(I_TITLE, title)
                .append(I_CATEGORY, -1)
                .append(I_AUTHOR, user_id)
                .append(I_CONTENT, content)
                .append(I_DATE, new Date())
                .append(I_COMMENTS, new ArrayList<Integer>())
                ;
    }

    public static Document json(int _id, int user_id, Integer category ,String title, String content) {
        return new Document()
                .append(_ID, _id)
                .append(I_TITLE, title)
                .append(I_CATEGORY, category)
                .append(I_AUTHOR, user_id)
                .append(I_CONTENT, content)
                .append(I_DATE, new Date())
                .append(I_COMMENTS, new ArrayList<Integer>())
                ;
    }

    public static Document json(Document doc) {
        Integer category_id = doc.getInteger(I_CATEGORY);
        if (category_id == null)
            return json(doc.getInteger(_ID), doc.getInteger(I_AUTHOR), doc.getString(I_TITLE), doc.getString(I_CONTENT));
        return json(doc.getInteger(I_AUTHOR), category_id , doc.getString(I_TITLE), doc.getString(I_CONTENT));
    }
}