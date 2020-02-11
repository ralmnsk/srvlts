package com.facultative.web.command.pagination;

import com.facultative.model.Course;
import com.facultative.model.Mark;
import com.facultative.service.CourseServiceImpl;
import com.facultative.service.ICourseService;
import com.facultative.service.IMarkService;
import com.facultative.service.MarkServiceImpl;
import javax.servlet.http.HttpServletRequest;
import static com.facultative.service.constants.Constants.*;


/**
 * The type Pagination.
 */
public class Pagination {
    /**
     * Get page number tutor courses int.
     *
     * @param request the request
     * @param userId  the user id
     * @return the int
     */
    public static int getPageNumberTutorCourses(HttpServletRequest request, long userId){
        return getPageNumber(request,userId,PAGE_COURSE_TUTOR_NUMBER);
    }

    /**
     * Gets page number tutor marks.
     *
     * @param request the request
     * @param userId  the user id
     * @return the page number tutor marks
     */
    public static int getPageNumberTutorMarks(HttpServletRequest request, long userId) {
        return getPageNumber(request,userId,PAGE_MARK_TUTOR_NUMBER);
    }

    /**
     * Gets page number student courses.
     *
     * @param request   the request
     * @param studentId the student id
     * @return the page number student courses
     */
    public static int getPageNumberStudentCourses(HttpServletRequest request, long studentId) {
        return getPageNumber(request,studentId,PAGE_MARK_STUDENT_NUMBER);
    }

    /**
     * Gets page number all courses.
     *
     * @param request the request
     * @return the page number all courses
     */
    public static int getPageNumberAllCourses(HttpServletRequest request) {
        return getPageNumber(request,NO_NUMBER,PAGE_ALL_COURSES_NUMBER);
    }

    /**
     * Gets page number.
     *
     * @param request            the request
     * @param userId             the user id
     * @param page_person_number the page person number
     * @return the page number
     */
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

        int pagesCount = count/ITEMS_ON_PAGE;
        if(count%ITEMS_ON_PAGE>0){
            pagesCount++;
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

        request.getSession().setAttribute(page_person_number,pageNumber);
        request.getSession().setAttribute(PAGES_COUNT,pagesCount);
        return pageNumber;
    }

}
