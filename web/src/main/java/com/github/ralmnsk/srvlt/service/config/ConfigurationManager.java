package com.github.ralmnsk.srvlt.service.config;

import java.util.Locale;
import java.util.ResourceBundle;

public class ConfigurationManager {
    private final static ResourceBundle resourceBundle = ResourceBundle.getBundle("config", Locale.getDefault());
    // класс извлекает информацию из файла resources_config.properties
    private ConfigurationManager() { }
    public static String getProperty(String key) {
        return resourceBundle.getString(key);
    }
}
