package com.facultative.web.listener;

import javax.servlet.ServletRequestAttributeEvent;
import javax.servlet.ServletRequestAttributeListener;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpServletRequest;
import java.util.Locale;

import static com.facultative.service.constants.Constants.*;

//@WebListener
public class LanguageListener implements ServletRequestAttributeListener {
    @Override
    public void attributeAdded(ServletRequestAttributeEvent event) {

        if(((HttpServletRequest) event.getServletRequest()).getQueryString() != null){
            String oldQuery=((HttpServletRequest) event.getServletRequest()).getQueryString().toString();
            ((HttpServletRequest) event.getServletRequest()).getSession().setAttribute("oldQuery",oldQuery);
        }

        if(event.getServletRequest().getParameter(LANG)!=null){
            String lang=event.getServletRequest().getParameter(LANG);
            if(lang.equals(LANG_EN)||lang.equals(LANG_RU)){
                Locale.setDefault(new Locale(lang));
//                String str1=((HttpServletRequest) event.getServletRequest()).getQueryString().toString();
                String str2=((HttpServletRequest) event.getServletRequest()).getRequestURL().toString();
                String str=str2+"?"+((HttpServletRequest) event.getServletRequest()).getSession().getAttribute("oldQuery");
                System.out.println(str);
            }
        }
    }

    @Override
    public void attributeRemoved(ServletRequestAttributeEvent event) {

    }

    @Override
    public void attributeReplaced(ServletRequestAttributeEvent event) {

    }
}
