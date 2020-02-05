package com.github.ralmnsk.srvlt.service.config;

import java.util.Locale;
import java.util.ResourceBundle;

public class Runner {

    public static void main(String[] a){
        System.out.println(ConfigurationManager.getProperty("path.page.login"));
    }
}
