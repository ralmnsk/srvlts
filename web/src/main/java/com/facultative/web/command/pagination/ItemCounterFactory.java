package com.facultative.web.command.pagination;

import static com.facultative.service.constants.Constants.*;

public class ItemCounterFactory {
    IItemCounter getCounter (String type){
    IItemCounter counter = new ItemCounterAllCourses();
        switch (type){
            case PAGE_COURSE_TUTOR_NUMBER :
                counter = new ItemCounterTutorCourses();
                break;
            case PAGE_MARK_TUTOR_NUMBER :
                counter = new ItemCounterTutorMarks();
                break;
            case PAGE_MARK_STUDENT_NUMBER :
                counter = new ItemCounterStudentCourses();
                break;
            default :                                          //AllCourses
                break;
        }
        return counter;
    }
}
