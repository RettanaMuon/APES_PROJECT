package CMSProject.core.database;

        import org.springframework.web.bind.annotation.RequestMapping;
        import org.springframework.web.bind.annotation.RestController;

@RestController
public class DatabaseInitializerController {

    @RequestMapping("/dbInitializer")
    public void user(){
        DatabaseInitializer.run();
    }
}
