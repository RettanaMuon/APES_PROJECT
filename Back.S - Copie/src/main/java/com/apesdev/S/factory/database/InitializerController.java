package com.apesdev.S.factory.database;

import com.apesdev.S.core.assets.ErrorCode;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.apesdev.S.factory.database.MockUp.createAccount;
import static com.apesdev.S.factory.database.Core.createCore;
import static com.apesdev.S.factory.database.MockUp.createCategory;

@RestController
public class InitializerController {
    @RequestMapping("/factory/database")
    public static ErrorCode run (){
        String s = "";
        s+= createCore().getMessage();
        s+= createAccount().getMessage();
        s+=createCategory().getMessage();

        return new ErrorCode(1,s);
    }
}
