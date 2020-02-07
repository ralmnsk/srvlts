package com.facultative.service.config;

public class Runner {

    public static void main(String[] a){
        System.out.println(ConfigurationManager.getProperty("path.page.login"));
    }
}
