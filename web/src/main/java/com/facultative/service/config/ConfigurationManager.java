package com.facultative.service.config;

import java.util.Locale;
import java.util.ResourceBundle;

import static com.facultative.service.constants.Constants.CONFIGURATION_MANAGER_FILE_BASE_NAME;

public class ConfigurationManager {
    private final static ResourceBundle resourceBundle = ResourceBundle.getBundle(CONFIGURATION_MANAGER_FILE_BASE_NAME, Locale.getDefault());

    private ConfigurationManager() { }
    public static String getProperty(String key) {
        return resourceBundle.getString(key);
    }
}
