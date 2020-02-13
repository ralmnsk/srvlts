package com.facultative.web.command.pagination;

import com.facultative.model.Course;
import com.facultative.model.Mark;
import com.facultative.service.CourseServiceImpl;
import com.facultative.service.ICourseService;
import com.facultative.service.IMarkService;
import com.facultative.service.MarkServiceImpl;
import javax.servlet.http.HttpServletRequest;
import static com.facultative.service.constants.Constants.*;


public class Pagination {
    public static int getPageNumberTutorCourses(HttpServletRequest request, long userId){
        return getPageNumber(request,userId,PAGE_COURSE_TUTOR_NUMBER);
    }

    public static int getPageNumberTutorMarks(HttpServletRequest request, long userId) {
        return getPageNumber(request,userId,PAGE_MARK_TUTOR_NUMBER);
    }

    public static int getPageNumberStudentCourses(HttpServletRequest request, long studentId) {
        return getPageNumber(request,studentId,PAGE_MARK_STUDENT_NUMBER);
    }

    public static int getPageNumberAllCourses(HttpServletRequest request) {
        return getPageNumber(request,NO_NUMBER,PAGE_ALL_COURSES_NUMBER);
    }

    public static int getPageNumber(HttpServletRequest request, long userId, String page_person_number) {
        int pageNumber = 1;
        int cursorPosition = 1;

        cursorPosition = getCursorPosition(request, page_person_number);
        int scale = getScale(request);//20 items on the page

        if(request.getParameter(PAGE_NUMBER) != null){
            int pageNumberForCursor = Integer.parseInt(request.getParameter(PAGE_NUMBER));
            cursorPosition = pageNumberForCursor*scale;
        }

        if(request.getParameter(PARAMETER_MOVE)!=null){
            String move=request.getParameter(PARAMETER_MOVE);
            if(move.equals(PREVIOUS)){
                pageNumber--;
                cursorPosition=cursorPosition-scale;
            }
            if(move.equals((NEXT))){
                pageNumber++;
                cursorPosition=cursorPosition+scale;
            }
        }

        int count=0;

        if(page_person_number.equals(PAGE_MARK_TUTOR_NUMBER)){
            IMarkService<Mark> service = MarkServiceImpl.getInstance();
            count = service.getCountMarksByTutorId(userId);
        }
        if(page_person_number.equals(PAGE_COURSE_TUTOR_NUMBER)){
            ICourseService<Course> service = CourseServiceImpl.getInstance();
            count = service.getCountCoursesByTutorId(userId);
        }
        if(page_person_number.equals(PAGE_MARK_STUDENT_NUMBER)){
            IMarkService<Mark> service = MarkServiceImpl.getInstance();
            count = service.getCountMarksByStudentId(userId);
        }
        if(page_person_number.equals(PAGE_ALL_COURSES_NUMBER)){
            ICourseService<Course> service = CourseServiceImpl.getInstance();
            count = service.getCountCourses();
        }

        if(cursorPosition >count){
            cursorPosition = count;
        } else if (cursorPosition <= 0){
            cursorPosition = 1;
        }

        int pagesCount = count/scale;// 44/20    44 - items in database
        if(count%scale > 0){             // 44%20>0
            pagesCount++;               // 2+1=3 pages
        }
        pageNumber = cursorPosition / scale; //1/20 = 0
        if(cursorPosition%scale > 0){           //1 >0
            pageNumber++;                   //0 + 1 = 1
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

        setCursorPosition(request,page_person_number,cursorPosition);
        request.getSession().setAttribute(SCALE,scale);
        request.getSession().setAttribute(page_person_number,pageNumber);
        request.getSession().setAttribute(PAGES_COUNT,pagesCount);
        return pageNumber;
    }

    public static int getCursorPosition(HttpServletRequest request, String page_person_number) {
        int cursorPosition = 1;
        String cursorPositionAttribute=new String("");

        if(page_person_number.equals(PAGE_MARK_TUTOR_NUMBER)){
            cursorPositionAttribute = "cursorPositionMark";
        }
        if(page_person_number.equals(PAGE_COURSE_TUTOR_NUMBER)){
            cursorPositionAttribute = "cursorPositionCourseYours";
        }
        if(page_person_number.equals(PAGE_MARK_STUDENT_NUMBER)){
            cursorPositionAttribute = "cursorPositionMark";
        }
        if(page_person_number.equals(PAGE_ALL_COURSES_NUMBER)){
            cursorPositionAttribute = "cursorPositionCourseAvailable";
        }
        if (!cursorPositionAttribute.equals(NONE_STRING)){
            if (request.getSession().getAttribute(cursorPositionAttribute) != null){
                cursorPosition=(int)request.getSession().getAttribute(cursorPositionAttribute);
            }
            if(cursorPosition <= 0){
                cursorPosition = 1;
            }
        }
        return cursorPosition;
    }

    public static boolean setCursorPosition(HttpServletRequest request, String page_person_number, int cursorPosition) {

        String cursorPositionAttribute=new String("");

        if(page_person_number.equals(PAGE_MARK_TUTOR_NUMBER)){
            cursorPositionAttribute = "cursorPositionMark";
        }
        if(page_person_number.equals(PAGE_COURSE_TUTOR_NUMBER)){
            cursorPositionAttribute = "cursorPositionCourseYours";        //Your courses (Tutor)
        }
        if(page_person_number.equals(PAGE_MARK_STUDENT_NUMBER)){
            cursorPositionAttribute = "cursorPositionMark";
        }
        if(page_person_number.equals(PAGE_ALL_COURSES_NUMBER)){
            cursorPositionAttribute = "cursorPositionCourseAvailable";       //Available courses
        }
        if (!cursorPositionAttribute.equals("")){
            request.getSession().setAttribute(cursorPositionAttribute, cursorPosition);
            return true;
        }

        return false;
    }

    public static int getScale(HttpServletRequest request) {
        int scale = ITEMS_ON_PAGE;
        if(request.getSession().getAttribute(SCALE) != null){
            scale = (int)request.getSession().getAttribute(SCALE);
        }
        if(request.getParameter(SCALE) != null){
            scale = Integer.parseInt(request.getParameter(SCALE));
        }
        return scale;
    }

}
