package CMSProject.core.login;

import CMSProject.core.hash.Checker;
import org.springframework.web.bind.annotation.*;

import static CMSProject.core.hash.Parameter.*;

@CrossOrigin("http://localhost:4200")
@RestController
public class LoginController {

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public Checker login(@RequestParam(value = "username") String username,
                        @RequestParam(value = "password") String password){
        return new Checker(username,password,T_LOGIN);
    }
}
