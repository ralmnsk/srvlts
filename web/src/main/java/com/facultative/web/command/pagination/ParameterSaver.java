package com.facultative.web.command.pagination;

import javax.servlet.http.HttpServletRequest;

import static com.facultative.service.constants.Constants.PAGES_COUNT;
import static com.facultative.service.constants.Constants.SCALE;

public class ParameterSaver {

    private HttpServletRequest request;
    private int scale;
    private String pagePersonNumber;
    private int pageNumber;
    private int pagesCount;

    public ParameterSaver(HttpServletRequest request, int scale, String pagePersonNumber, int pageNumber, int pagesCount) {
        this.request = request;
        this.scale = scale;
        this.pagePersonNumber = pagePersonNumber;
        this.pageNumber = pageNumber;
        this.pagesCount = pagesCount;
    }

    public void save(){
        request.getSession().setAttribute(SCALE,scale);
        request.getSession().setAttribute(pagePersonNumber,pageNumber);
        request.getSession().setAttribute(PAGES_COUNT,pagesCount);
    }
}
