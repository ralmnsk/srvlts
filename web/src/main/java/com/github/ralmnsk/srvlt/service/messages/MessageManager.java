package com.github.ralmnsk.srvlt.service.messages;

import com.google.protobuf.Message;

import java.util.Locale;
import java.util.ResourceBundle;

public class MessageManager {
    private final static ResourceBundle resourceBundle = ResourceBundle.getBundle("messages", Locale.getDefault());
    private MessageManager() { }
    public static String getProperty(String key) {
        return resourceBundle.getString(key);
    }
//    public static void main(String[] args){
//        System.out.println(MessageManager.getProperty("message.student"));
//    }
}
