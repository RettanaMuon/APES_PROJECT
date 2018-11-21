package com.apesdev.D;

import java.util.HashMap;

public class ConfigTranslator {
    private HashMap<String, String> translator = new HashMap<>();

    public ConfigTranslator(){
        translator.put("front_url", "_URL_ROOT");
        translator.put("back_url", "_URL_BACKEND");
        translator.put("database_host", "DB_HOST");
        translator.put("database_name", "DB_NAME");
    }

    public String get(String key){
        return translator.get(key);
    }

    public HashMap<String, String> translate(HashMap<String,String> hm){
        HashMap<String, String> res = new HashMap<>();
        for (String key : hm.keySet()) {
            String translatedKey = get(key);
            if(translatedKey != null){
                res.put(translatedKey, hm.get(key));
            }else {
                res.put(key, hm.get(key));
            }
        }
        return res;
    }
}
