package com.facultative.web.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import java.io.IOException;
import java.util.Locale;

import static com.facultative.service.constants.Constants.*;

@WebFilter(urlPatterns= {"/*"},
        initParams =@WebInitParam(name=ENCODING, value = "UTF-8"))
public class EncodingFilter implements Filter {
    private String code;
    @Override
    public void init(FilterConfig filterConfig) {
        code = filterConfig.getInitParameter(ENCODING);
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        String codeRequest = request.getCharacterEncoding();
        if (code != null && !code.equalsIgnoreCase(codeRequest)) {
            request.setCharacterEncoding(code);
            response.setCharacterEncoding(code);
        }
        //Language
        if(request.getParameter(LANG) != null ){
            String lang=request.getParameter(LANG);
            if(lang.equals(LANG_EN) || lang.equals(LANG_RU)) {
                Locale.setDefault(new Locale(lang));
            }
        }

        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
    }
}
