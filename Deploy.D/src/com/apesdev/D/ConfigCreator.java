package com.apesdev.D;

public class ConfigCreator {
    private static ConfigCreator ourInstance = new ConfigCreator();

    public static ConfigCreator getInstance() {
        return ourInstance;
    }

    private ConfigCreator() {
    }
}
