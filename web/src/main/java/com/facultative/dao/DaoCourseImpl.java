package com.facultative.dao;

import com.facultative.model.Course;
import com.facultative.model.Tutor;
import com.facultative.model.UserType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static com.facultative.service.constants.Constants.*;

public class DaoCourseImpl implements IDaoCourse<Course> {
    private static Logger logger= LoggerFactory.getLogger(DaoCourseImpl.class);
    private static volatile IDaoCourse instance;


    public static IDaoCourse<Course> getInstance() {
        if (instance == null) {
            synchronized (DaoCourseImpl.class) {
                if (instance == null) {
                    instance = new DaoCourseImpl();
                }
            }
        }
        return instance;
    }

    private Connection getConnection() throws SQLException {
        return DataSource.getInstance().getConnection();
    }

    @Override
    public Course save(Course course){
        ResultSet generatedKeys=null;
        try (Connection connection = getConnection();
             PreparedStatement statement =
                     connection
                             .prepareStatement(SQL_QUERY_COURSE_SAVE
                                     , Statement.RETURN_GENERATED_KEYS);
        )
        {
            statement.setString(1, course.getName());
            statement.setLong(2, course.getTutor().getId());
            statement.execute();
            generatedKeys = statement.getGeneratedKeys();
            if (generatedKeys.next()) {
                course.setId(generatedKeys.getLong(1));
            }
            generatedKeys.close();
            logger.info("Course was created:" + course.getId());
        } catch (SQLException ex) {
            logger.error("Prblem executing save course ", ex);

        } finally {
            if (generatedKeys!=null){
                try {
                    generatedKeys.close();
                } catch (SQLException e) {
                    logger.error("Prblem executing INSERT, generatedKey close", e);
                }
            }
        }
        return course;
    }

    @Override
    public Course get(long id) {
        Course course=null;
        Tutor tutor=null;
        ResultSet rs=null;
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement
                     (SQL_QUERY_COURSE_GET,Statement.RETURN_GENERATED_KEYS);
        )
        {
            statement.setLong(1, id);
            rs = statement.executeQuery();

            while(rs.next()){
                course=new Course();
                course.setId(rs.getLong(1));
                course.setName(rs.getString(2));
                tutor=new Tutor();
                tutor.setId(rs.getLong(3));
                tutor.setSurname(rs.getString(4));
                tutor.setName(rs.getString(5));
                tutor.setLogin(rs.getString(6));
                tutor.setPassword(rs.getString(7));
                tutor.setRole(UserType.valueOf(rs.getString(8)));
                course.setTutor(tutor);
            }
            rs.close();
        } catch (SQLException ex) {
            logger.error("Problem executing UPDATE", ex);
        }finally{
            if (rs!=null){
                try {
                    rs.close();
                } catch (SQLException e) {
                    logger.error("Problem executing UPDATE.ResultSet close", e);
                }
            }
        }
        return course;
    }

    @Override
    public Course update(Course course) {
        try (Connection connection = getConnection();
                PreparedStatement statement = connection.prepareStatement
                        (SQL_QUERY_COURSE_UPDATE);

        ){
            statement.setString(1, course.getName());
            statement.setLong(2, course.getTutor().getId());
            statement.setLong(3,course.getId());
            statement.execute();
        } catch (SQLException ex) {
            logger.error("Problem executing UPDATE", ex);
        }
        return course;
    }

    @Override
    public void delete(long id) {
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement
                     (SQL_QUERY_COURSE_DELETE);
        )
        {
            statement.setLong(1, id);
            statement.execute();
            logger.info("Course was deleted: id:" +id);
        } catch (SQLException ex) {
            logger.error("Prblem executing DELETE course", ex);
        }
    }


    public List<Course> getAllCoursesWithParam(long tutorId, String param) {
        List<Course> list=new ArrayList<>();
        ResultSet rs=null;
        try (
                Connection connection = getConnection();
                PreparedStatement statement = connection.prepareStatement
                        (param,Statement.RETURN_GENERATED_KEYS);
        )
        {
            if(tutorId!=IN_COURSE_ALL_NO_TUTOR_ID){
                statement.setLong(1,tutorId);
            }
            rs = statement.executeQuery();
            while(rs.next()){
                Course course=new Course();
                course.setId(rs.getLong(1));
                course.setName(rs.getString(2));
                Tutor tutor=new Tutor();
                tutor.setId(rs.getLong(3));
                tutor.setSurname(rs.getString(4));
                tutor.setName(rs.getString(5));
                tutor.setLogin(rs.getString(6));
                tutor.setPassword(rs.getString(7));
                tutor.setRole(UserType.valueOf(rs.getString(8)));
                course.setTutor(tutor);
                list.add(course);
            }
            rs.close();
        } catch (SQLException ex) {
            logger.error("Problem executing getAllCourses", ex);
        }finally {
            if (rs!=null){
                try {
                    rs.close();
                } catch (SQLException e) {
                    logger.error("Problem executing getAllCourses, rs close", e);
                }
            }
        }
        return list;
    }
    //for tutor to get only his own courses
    @Override
    public List<Course> getCoursesByTutorId(long tutorId) {
        return getAllCoursesWithParam(tutorId,SQL_QUERY_COURSE_BY_TUTOR_PARAM_ID);
    }
    //for students to get all courses
    @Override
    public List<Course> getCourses() {
        return getAllCoursesWithParam(IN_COURSE_ALL_NO_TUTOR_ID,SQL_QUERY_COURSE_ALL_NO_PARAM);
    }
}
