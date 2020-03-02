package com.facultative.web.command.pagination;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.servlet.http.HttpServletRequest;
import static com.facultative.service.constants.Constants.*;

public class CursorPosition {

    private static Logger logger= LoggerFactory.getLogger(CursorPosition.class);

    public  int getCursorPosition(HttpServletRequest request, String pagePersonNumber, int scale) {
        int cursorPosition = 1;
        String cursorPositionAttribute = getCursorPositionAttribute(pagePersonNumber);

            if (request.getSession().getAttribute(cursorPositionAttribute) != null){
                try {
                    cursorPosition = (int) request.getSession().getAttribute(cursorPositionAttribute);
                } catch (NumberFormatException e){
                    logger.error("Problem getCursorPosition setting. Default cursorPosition was set 1.", e);
                    cursorPosition = 1;
                }
            }

            if(cursorPosition <= 0){
                cursorPosition = 1;
            }

        int pageNumberForCursor;
        if(request.getParameter(PAGE_NUMBER) != null){
                try {
                    pageNumberForCursor = Integer.parseInt(request.getParameter(PAGE_NUMBER));
                } catch (NumberFormatException e){
                    logger.error("Problem pageNumberForCursor setting. Default pageNumberForCursor was set 1.", e);
                    pageNumberForCursor = 1;
                }
                cursorPosition = pageNumberForCursor*scale;
            }

        return cursorPosition;
    }

    public  void setCursorPosition(HttpServletRequest request, String pagePersonNumber, int cursorPosition) {
        String cursorPositionAttribute = getCursorPositionAttribute(pagePersonNumber);
        request.getSession().setAttribute(cursorPositionAttribute, cursorPosition);
    }

    private String getCursorPositionAttribute(String pagePersonNumber){
        String cursorPositionAttribute;
        switch(pagePersonNumber){
            case PAGE_MARK_TUTOR_NUMBER :
                cursorPositionAttribute = CURSOR_POSITION_MARK;
                break;
            case PAGE_MARK_STUDENT_NUMBER :
                cursorPositionAttribute = CURSOR_POSITION_MARK;
                break;
            case PAGE_COURSE_TUTOR_NUMBER :
                cursorPositionAttribute = CURSOR_POSITION_COURSE_YOURS;
                break;
            default :
                cursorPositionAttribute = CURSOR_POSITION_COURSE_AVAILABLE;
                break;
        }

        return cursorPositionAttribute;
    }
}
