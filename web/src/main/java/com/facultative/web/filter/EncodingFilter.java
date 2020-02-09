package com.facultative.web.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Locale;

import static com.facultative.service.constants.Constants.*;

@WebFilter(urlPatterns= {"/*"},
        initParams =@WebInitParam(name=ENCODING, value = "UTF-8"))
public class EncodingFilter implements Filter {
    private String code;
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        code = filterConfig.getInitParameter(ENCODING);
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        String codeRequest = request.getCharacterEncoding();
        if (code != null && !code.equalsIgnoreCase(codeRequest)) {
            request.setCharacterEncoding(code);
            response.setCharacterEncoding(code);
        }

        String url = ((HttpServletRequest) request).getRequestURL()
                +(( ((HttpServletRequest) request).getQueryString() != null ) ? QUESTION + ((HttpServletRequest) request).getQueryString()
                : EMPTY_STRING );
        String oldUrl=url;
        if(((HttpServletRequest) request).getSession().getAttribute(OLD_URL) != null){
            oldUrl=((HttpServletRequest) request).getSession().getAttribute(OLD_URL).toString();
        }
            ((HttpServletRequest) request).getSession().setAttribute(OLD_URL,url);
            System.out.println(oldUrl);
        if(request.getParameter(LANG) != null ){
            String lang=request.getParameter(LANG);
            if(lang.equals(LANG_EN) || lang.equals(LANG_RU)) {
                oldUrl=oldUrl.replace(QUESTION_LANG_EQ+lang,EMPTY_STRING);
                oldUrl=oldUrl.replace(MOVE_EQ_NEXT,EMPTY_STRING);
                oldUrl=oldUrl.replace(MOVE_EQ_PREVIOUS,EMPTY_STRING);
                ((HttpServletRequest) request).getSession().setAttribute(OLD_URL,oldUrl);
                Locale.setDefault(new Locale(lang));
                System.out.println(oldUrl);
            }
        }

        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
        code=null;
    }
}
