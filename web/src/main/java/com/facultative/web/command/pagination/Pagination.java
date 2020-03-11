package com.facultative.web.command.pagination;

import javax.servlet.http.HttpServletRequest;

public class Pagination {

    private Parameter getParameter(int cursorPosition, int count, int scale){ //item = cursorPosition scale pageNumber pagesCount

        if(cursorPosition >count){
            cursorPosition = count;
        } else if (cursorPosition <= 0){
            cursorPosition = 1;
        }

        int pagesCount = count/scale;// 44 / 20    44 - items in database, 20 - item scale on the page
        if(count%scale > 0){             // 44 % 20 > 0
            pagesCount++;               // 2 + 1 = 3 pages
        }

        int pageNumber = cursorPosition / scale; // 1 / 20 = 0
        if(cursorPosition%scale > 0){           // 1 % 20 = 1 >0
            pageNumber++;                   // 0 + 1 = 1
        }

        if (pageNumber > pagesCount){
            pageNumber = pagesCount;
        }

        if(pageNumber <= 0){
            pageNumber = 1;
        }
        if(pagesCount <= 0){
            pagesCount = 1;
        }

        return new Parameter(cursorPosition,pageNumber,pagesCount);
    }


    public int getPageNumber(HttpServletRequest request, long userId, String pagePersonNumber) {
        //scale
        Scale scaleFinder = new Scale();
        int scale = scaleFinder.getScale(request);//20 items on the page

        //cursor position
        CursorPosition cursorPositionFinder = new CursorPosition();
        int cursorPosition = cursorPositionFinder.getCursorPosition(request, pagePersonNumber, scale);

        //count of item
        ItemCounterFactory itemCounterFactory = new ItemCounterFactory();
        IItemCounter counter = itemCounterFactory.getCounter(pagePersonNumber);
        int count = counter.getItemCount(userId);

        //get pagination parameter that returns  cursorPosition, pageNumber=count, pagesCount=scale
        Parameter parameter = getParameter(cursorPosition,count,scale);

        //set pagination parameters in session
        cursorPositionFinder.setCursorPosition(request,pagePersonNumber,cursorPosition); //cursor for each type of entity in the database
        ParameterSaver saver = new ParameterSaver(request,scale,pagePersonNumber, parameter.getPageNumber(), parameter.getPagesCount());
        saver.save();

        return parameter.getPageNumber();
    }
}
