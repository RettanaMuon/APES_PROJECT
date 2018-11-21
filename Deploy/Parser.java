package com.apesdev.S.deployment;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.*;
import java.util.HashMap;

public class Parser {
    private File root = new File(".").getParentFile().getParentFile().getParentFile().getParentFile();
    private File config = new File(root, "config");

    public HashMap<String, String> readConfig() throws IOException{
        BufferedReader buff = new BufferedReader(new FileReader(config));
        HashMap<String, String> res = new HashMap<>();
        String line;
        while ((line = buff.readLine()) != null){
            HashMap<String, String> hashedLine = hashLine(line);
            if(hashedLine != null){
                res.putAll(hashedLine);
            }
        }
        return res;
    }


    public HashMap<String, String> hashLine(String buffer){
        if(
                buffer.length() == 0  ||
                Pattern.compile("^[\\r\\n]*\\s*#").matcher(buffer).find()
        ){
            return null;
        }
        String[] split = Pattern.compile("\\s*:[\\s*:]").split(buffer);
        for(int i = 0; i < split.length; i++){
            System.out.println("|"+split[i]+"|");
        }
        if (split.length == 2) {
            HashMap<String, String> res = new HashMap<>();
            res.put(split[0], split[1]);
            return res;
        }else
            return null;
    }

}
