package com.facultative.web.listener;

import javax.servlet.ServletRequestAttributeEvent;
import javax.servlet.ServletRequestAttributeListener;
import javax.servlet.annotation.WebListener;
import java.util.Locale;
import static com.facultative.service.constants.Constants.LANG;

@WebListener
public class LanguageListener implements ServletRequestAttributeListener {
    @Override
    public void attributeAdded(ServletRequestAttributeEvent srae) {
        if(srae.getServletRequest().getParameter(LANG)!=null){
            String lang=srae.getServletRequest().getParameter(LANG);
            Locale.setDefault(new Locale(lang));
        }
    }

    @Override
    public void attributeRemoved(ServletRequestAttributeEvent srae) {

    }

    @Override
    public void attributeReplaced(ServletRequestAttributeEvent srae) {

    }
}
