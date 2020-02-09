package com.facultative.service.messages;

import java.util.Locale;
import java.util.ResourceBundle;

import static com.facultative.service.constants.Constants.MESSAGE_MANAGER_FILE_BASE_NAME;

/**
 * The type Message manager.
 */
public class MessageManager {
    private final static ResourceBundle resourceBundle = ResourceBundle.getBundle(MESSAGE_MANAGER_FILE_BASE_NAME, Locale.getDefault());
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
}
