package com.apesdev.D;

public class Main {
    public static void main(String args[]){
        try{
            new Parser().applyConfigToBackEnd();
        }catch (Exception e){
            System.out.println("error :" + e.toString());
        }
    }
}

