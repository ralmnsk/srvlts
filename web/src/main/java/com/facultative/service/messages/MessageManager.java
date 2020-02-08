package com.facultative.service.messages;

import com.google.protobuf.Message;

import java.util.Locale;
import java.util.ResourceBundle;
import static com.facultative.service.constants.Constants.MESSAGE_MANAGER_FILE_BASE_NAME;

public class MessageManager {
    private final static ResourceBundle resourceBundle = ResourceBundle.getBundle(MESSAGE_MANAGER_FILE_BASE_NAME, Locale.getDefault());
    private MessageManager() { }
    public static String getProperty(String key) {
        return resourceBundle.getString(key);
    }
}
