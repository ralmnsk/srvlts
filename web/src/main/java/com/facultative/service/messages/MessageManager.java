package com.facultative.service.messages;

import java.util.Locale;
import java.util.ResourceBundle;

import static com.facultative.service.constants.Constants.MESSAGE_MANAGER_FILE_BASE_NAME;

/**
 * The type Message manager. It gets messages from property file.
 */
public class MessageManager {
    private static ResourceBundle resourceBundle = ResourceBundle.getBundle(MESSAGE_MANAGER_FILE_BASE_NAME);
    private MessageManager() { }

    /**
     * Gets property.
     *
     * @param key the key
     * @return the property
     */
    public static String getProperty(String key) {
        return resourceBundle.getString(key);
    }

    /**
     * Set locale.
     *
     * @param locale the locale
     */
    public static void setLocale(Locale locale){
        resourceBundle = ResourceBundle.getBundle(MESSAGE_MANAGER_FILE_BASE_NAME,locale);
    }
}
