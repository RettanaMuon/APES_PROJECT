package com.apesdev.D;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class ConfigCreator {
    private File root = new File("").getAbsoluteFile();
    private File config = new File(root, "config");
    public void make(String front_url,String back_url, String database_host, String database_name ) throws IOException {
        BufferedWriter buff = new BufferedWriter(new FileWriter(config));
        buff.write(
                "################################################################\n" +
                    "#### Configuration file                                     ####\n" +
                    "#### APES CMS                                               ####\n" +
                    "################################################################\n" +
                    "## Commons\n"
        );
        buff.write(
                "front_url: " + front_url + "\n" +
                    "back_url: " + back_url + "\n" +
                    "database_host: " + database_host + "\n" +
                    "database_name: " + database_name + "\n"
        );
        if(buff != null){
            buff.close();
        }
    }
}
