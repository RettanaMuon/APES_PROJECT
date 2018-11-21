package com.apesdev.S.deployment;

public class Main {
    public static void main(String args[]){
        try{
            new Parser().readConfig();
        }catch (Exception e){
            System.out.println("error :" + e.toString());
        }
    }
}
