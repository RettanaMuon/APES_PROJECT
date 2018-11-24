package com.apesdev.S.modules.install;

import com.apesdev.D.ConfigCreator;
import com.apesdev.D.Parser;
import com.apesdev.S.core.assets.ErrorCode;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class InstallController {
    @CrossOrigin("*")
    @RequestMapping("/install/createConfig")
    public static ErrorCode createConfig(
            @RequestParam(value = "front_url") String front_url,
            @RequestParam(value = "back_url") String back_url,
            @RequestParam(value = "database_host") String database_host,
            @RequestParam(value = "database_name") String database_name
    ){
        try{
            new ConfigCreator().make(front_url, back_url, database_host, database_name);
            new Parser().applyConfigToBackEnd();
            String str = com.apesdev.S.factory.database.InitializerController.run().getMessage();
            return new ErrorCode(1, "Config file has successfully been created.\n" + str );
        }catch(Exception e){
            return new ErrorCode(-1, "Error : " + e.getMessage() + "\n.Config file has not been created.");
        }
    }
}
