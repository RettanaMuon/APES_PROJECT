package com.apesdev.S.core.login;

import com.apesdev.S.core.hash.Checker;
import com.apesdev.S.core.hash.Parameter;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("http://localhost:4200")
@RestController
public class LoginController {

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public Checker login(@RequestParam(value = "username") String username,
                         @RequestParam(value = "password") String password){
        return new Checker(username,password, Parameter.T_LOGIN);
    }
}
