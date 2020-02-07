package com.facultative.web.listener;

import javax.servlet.ServletRequestAttributeEvent;
import javax.servlet.ServletRequestAttributeListener;
import javax.servlet.annotation.WebListener;
import java.util.Locale;
import java.util.ResourceBundle;

import static com.facultative.service.constants.Constants.MESSAGE_MANAGER_FILE_BASE_NAME;

//@WebListener
public class LanguageListener implements ServletRequestAttributeListener {
    @Override
    public void attributeAdded(ServletRequestAttributeEvent srae) {
        if(srae.getServletRequest().getParameter("lang")!=null){
            String lang=srae.getServletRequest().getParameter("lang");
            Locale.setDefault(new Locale(lang));
//            System.out.println(Locale.getDefault());
//            ResourceBundle.getBundle(MESSAGE_MANAGER_FILE_BASE_NAME, Locale.getDefault());
        }
    }

    @Override
    public void attributeRemoved(ServletRequestAttributeEvent srae) {

    }

    @Override
    public void attributeReplaced(ServletRequestAttributeEvent srae) {

    }
}
