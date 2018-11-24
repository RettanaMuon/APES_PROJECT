package com.apesdev.D;

import java.io.*;
import java.nio.file.NoSuchFileException;
import java.util.Set;
import java.util.regex.*;
import java.util.HashMap;

public class Parser {
    private File root = new File("").getAbsoluteFile();
    private File config = new File(root, "config");
    private File BackEndParameter = new File(root, "Back.S\\src\\main\\java\\com\\apesdev\\S\\core\\parameter\\Common.java");


    public HashMap<String, String> readConfig() throws IOException{
        if(!config.exists()){
            throw new NoSuchFileException("File config is missing in root folder.");
        }
        BufferedReader buff = new BufferedReader(new FileReader(config));
        HashMap<String, String> res = new HashMap<>();
        String line;
        while ((line = buff.readLine()) != null){
            HashMap<String, String> hashedLine = hashConfigLine(line);
            if(hashedLine != null){
                res.putAll(hashedLine);
            }
        }
        if(buff != null)
            buff.close();
        return res;
    }


    public HashMap<String, String> hashConfigLine(String buffer){
        if(
                buffer.length() == 0  ||
                Pattern.compile("^[\\r\\n]*\\s*#").matcher(buffer).find()
        ){
            return null;
        }
        String[] split = Pattern.compile("\\s*:[\\s*:]").split(buffer);
        if (split.length == 2) {
            HashMap<String, String> res = new HashMap<>();
            res.put(split[0], split[1]);
            return res;
        }else
            return null;
    }

    public void applyConfigToBackEnd() throws IOException{
        File f = BackEndParameter;
        HashMap<String,String> configMap = new ConfigTranslator().translate(readConfig());
        if(!f.exists()){
            throw new NoSuchFileException("File config is missing in root folder.");
        }
        BufferedWriter buff = new BufferedWriter(new FileWriter(BackEndParameter));
        buff.write(
        "package com.apesdev.S.core.parameter;\n" +
            "\n" +
            "/**\n" +
            " * This file is generated by Parser's deployer. Please modify the root/config file and regenerate this file." +
            " * List of user parameters aiming to make a customizable deployment.\n" +
            " */\n" +
            "public class Common {\n" +
            "     public static final String\n"
        );
        Set<String> keys = configMap.keySet();
        int i = 1;
        String coma = ",";
        for(String k : keys){
            if (i == keys.size()){
                coma = "";
            }
            buff.write("            " + k + " = \"" + configMap.get(k) + "\"" + coma + "\n");
            i++;
        }
        buff.write(
                "           ;\n" +
                        "}"
        );
        if (buff != null)
            buff.close();

    }

    public HashMap<String, String> readBackEndLine(String buffer){
        if(
                buffer.length() == 0  ||
                Pattern.compile("^[\\r\\n]*\\s*//").matcher(buffer).find()
        ){
            return null;
        }else{
            Pattern p =  Pattern.compile("\\s*=[\\s]*");
            if(p.matcher(buffer).find()){
                String[] split = p.split(buffer);
                if (split.length == 2) {
                    HashMap<String, String> res = new HashMap<>();
                    char [] str = split[1].toCharArray();
                    for(int i = 0; i < str.length ; i++){
                        if(str[i] == '"' || str[i] == ','){
                            str[i] = ' ';
                        }
                    }
                    res.put(split[0].trim(), String.valueOf(str).trim());
                    return res;
                }
            }
            return null;
        }

    }
}
