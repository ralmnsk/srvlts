package com.facultative.web.command.pagination;

import com.facultative.model.Course;
import com.facultative.model.Mark;
import com.facultative.service.CourseServiceImpl;
import com.facultative.service.ICourseService;
import com.facultative.service.IMarkService;
import com.facultative.service.MarkServiceImpl;
import javax.servlet.http.HttpServletRequest;
import static com.facultative.service.constants.Constants.*;

public interface IPagination {
    public static int getPageNumberTutorCourses(HttpServletRequest request, long userId){
        int pageNumber=getPageNumber(request,userId,PAGE_COURSE_TUTOR_NUMBER);
        return pageNumber;
    }

    public static int getPageNumberTutorMarks(HttpServletRequest request, long userId) {
        int pageNumber=getPageNumber(request,userId,PAGE_MARK_TUTOR_NUMBER);
        return pageNumber;
    }

    static int getPageNumberStudentCourses(HttpServletRequest request, long studentId) {
        int pageNumber=getPageNumber(request,studentId,PAGE_MARK_STUDENT_NUMBER);
        return pageNumber;
    }

    static int getPageNumberAllCourses(HttpServletRequest request) {
        int pageNumber=getPageNumber(request,NO_NUMBER,PAGE_ALL_COURSES_NUMBER);
        return pageNumber;
    }

                                                                            //PAGE_MARK_TUTOR_NUMBER
    public static int getPageNumber(HttpServletRequest request, long userId, String page_person_number) {
        int pageNumber=1;

        if(request.getSession().getAttribute(page_person_number)!=null){
            pageNumber=(int)request.getSession().getAttribute(page_person_number);
            if(pageNumber==0){
                pageNumber=1;
            }
        }
        if(request.getParameter(PARAMETER_MOVE)!=null){
            String move=request.getParameter(PARAMETER_MOVE);
            if(move.equals(PREVIOUS)){
                pageNumber--;
            }
            if(move.equals((NEXT))){
                pageNumber++;
            }
        }

        if(pageNumber<=0){
            pageNumber=1;
        }

        int count=0;

        if(page_person_number.equals(PAGE_MARK_TUTOR_NUMBER)){
            IMarkService<Mark> service= MarkServiceImpl.getInstance();//
            count=service.getCountMarksByTutorId(userId);//
        }
        if(page_person_number.equals(PAGE_COURSE_TUTOR_NUMBER)){
            ICourseService<Course> service= CourseServiceImpl.getInstance();
            count=service.getCountCoursesByTutorId(userId);
        }
        if(page_person_number.equals(PAGE_MARK_STUDENT_NUMBER)){
            IMarkService<Mark> service= MarkServiceImpl.getInstance();//
            count=service.getCountMarksByStudentId(userId);//
        }
        if(page_person_number.equals(PAGE_ALL_COURSES_NUMBER)){
            ICourseService<Course> service= CourseServiceImpl.getInstance();
            count=service.getCountCourses();
        }

        int pagesCount=count/ITEMS_ON_PAGE;
        if(count%ITEMS_ON_PAGE>0){
            pagesCount++;
        }

        if (pageNumber>pagesCount){
            pageNumber=pagesCount;
        }
        request.getSession().setAttribute(page_person_number,pageNumber);// PAGE_COURSE_TUTOR_NUMBER or PAGE_MARK_TUTOR_NUMBER
        request.getSession().setAttribute(PAGES_COUNT,pagesCount);
        return pageNumber;
    }

}
