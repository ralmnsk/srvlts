package com.facultative.web.command.pagination;

import com.facultative.model.Course;
import com.facultative.service.CourseServiceImpl;
import com.facultative.service.ICourseService;

public class ItemCounterAllCourses implements IItemCounter {
    @Override
    public int getItemCount(long userId) {
        ICourseService<Course> service = CourseServiceImpl.getInstance();
        return service.getCountCourses();
    }
}
