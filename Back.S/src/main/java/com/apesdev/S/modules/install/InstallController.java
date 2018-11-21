package com.apesdev.S.modules.install;

import com.apesdev.D.ConfigCreator;
import com.apesdev.D.Parser;
import com.apesdev.S.core.assets.ErrorCode;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import static com.apesdev.S.core.parameter.Common._URL_ROOT;

@RestController
@CrossOrigin(_URL_ROOT)
public class InstallController {
    @CrossOrigin(_URL_ROOT + "/install")
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
            return new ErrorCode(1, "Config file has successfully been created.");
        }catch(Exception e){
            return new ErrorCode(-1, "Error : " + e.getMessage() + "\n.Config file has not been created.");
        }
    }
}
