package CMSProject.core.publications;

import CMSProject.core.counter.Counter;
import CMSProject.core.nodes._IDNode;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static CMSProject.core.parameter.Collection.C_PUBS;
import static CMSProject.core.parameter.Counters.COUNT_PUBS;
import static CMSProject.core.parameter.Counters.COUNT_PUBS_COMMENT;


@CrossOrigin("localhost:4200")
@RestController
public class PublicationDatabaseInitializer {
    @RequestMapping("/publications/init")
    public static void run(){
        new Counter(COUNT_PUBS, COUNT_PUBS_COMMENT);
        _IDNode.initializeDatabase(C_PUBS, true);
    }
}
