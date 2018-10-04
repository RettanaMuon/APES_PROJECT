package CMSProject.core.assets;

import org.bson.Document;

import java.util.ArrayList;
import java.util.Date;

import static CMSProject.core.parameter.Indexes.I_AUTHOR;
import static CMSProject.core.parameter.Indexes.I_CONTENT;
import static CMSProject.core.parameter.Indexes.I_DATE;
import static CMSProject.core.parameter.Keys.K_PUBS;

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
