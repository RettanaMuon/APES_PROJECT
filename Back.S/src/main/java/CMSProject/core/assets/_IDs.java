package CMSProject.core.assets;

import org.bson.Document;

import java.util.ArrayList;

import static CMSProject.core.parameter.Indexes.I_DATA;


public class _IDs extends Asset {

    public static Document json(ArrayList<Integer> _IDs){
        return new Document()
                .append(I_DATA, _IDs)
                ;
    }

    public static Document json (Document doc){
        return json((ArrayList<Integer>)doc.get(I_DATA));
    }
}
