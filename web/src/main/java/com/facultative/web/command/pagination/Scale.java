package com.facultative.web.command.pagination;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import static com.facultative.service.constants.Constants.ITEMS_ON_PAGE;
import static com.facultative.service.constants.Constants.SCALE;

public class Scale {

    private static Logger logger= LoggerFactory.getLogger(Scale.class);

    public  int getScale(HttpServletRequest request) { //get count of items on a page
        int scale = ITEMS_ON_PAGE;
        try {
            if (request.getSession().getAttribute(SCALE) != null) {
                scale = (int) request.getSession().getAttribute(SCALE);
            }
            if (request.getParameter(SCALE) != null) {
                scale = Integer.parseInt(request.getParameter(SCALE));
            }
        } catch (NumberFormatException e){
            logger.error("Problem scale setting. Default scale was set 10 items on the page.", e);
            scale = ITEMS_ON_PAGE;
        }
        if((scale != 10) && (scale != 20) && (scale != 50)){
            scale = 10; //in case of incorrect scale happened set scale value equals 10
        }
        return scale;
    }
}
