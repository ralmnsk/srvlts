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


/**
 * The type Dao course.
 */
public class DaoCourseImpl implements IDaoCourse<Course> {

    private static Logger logger= LoggerFactory.getLogger(DaoCourseImpl.class);
    private static volatile IDaoCourse<Course> instance;


    /**
     * Gets instance.
     *
     * @return the instance
     */
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

    private DaoCourseImpl() {
    }

    private Connection getConnection() {
        return DataSource.getInstance().getConnection();
    }

    @Override
    public Course save(Course course){
        ResultSet generatedKeys=null;
        try (Connection connection = getConnection();
             PreparedStatement statement =
                     connection
                             .prepareStatement(SQL_QUERY_COURSE_SAVE
                                     , Statement.RETURN_GENERATED_KEYS)
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
            logger.error("Problem executing save course", ex);

        } finally {
            if (generatedKeys!=null){
                try {
                    generatedKeys.close();
                } catch (SQLException e) {
                    logger.error("Problem executing save course, generatedKey close", e);
                }
            }
        }
        return course;
    }



    @Override
    public Course get(long id) {
        Course course=null;
        Tutor tutor;
        ResultSet rs=null;
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement
                     (SQL_QUERY_COURSE_GET,Statement.RETURN_GENERATED_KEYS)
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
            logger.error("Problem executing course get", ex);
        }finally{
            if (rs!=null){
                try {
                    rs.close();
                } catch (SQLException e) {
                    logger.error("Problem executing course get", e);
                }
            }
        }
        return course;
    }

    @Override
    public Course update(Course course) {
        try (Connection connection = getConnection();
                PreparedStatement statement = connection.prepareStatement
                        (SQL_QUERY_COURSE_UPDATE)

        ){
            statement.setString(1, course.getName());
            statement.setLong(2, course.getTutor().getId());
            statement.setLong(3,course.getId());
            statement.execute();
        } catch (SQLException ex) {
            logger.error("Problem executing course update", ex);
        }
        return course;
    }

    @Override
    public void delete(long id) {
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement
                     (SQL_QUERY_COURSE_DELETE)
        )
        {
            statement.setLong(1, id);
            statement.execute();
            logger.info("Course was deleted: id:" +id);
        } catch (SQLException ex) {
            logger.error("Problem executing course delete", ex);
        }
    }


    /**
     * Gets all courses with param.
     *
     * @param tutorId    the tutor id
     * @param pageNumber the page number
     * @param param      the param
     * @return the all courses with param
     */
    private List<Course> getAllCoursesWithParam(long tutorId, int pageNumber, String param) {
        List<Course> list=null;
        ResultSet rs=null;
        try (
                Connection connection = getConnection();
                PreparedStatement statement = connection.prepareStatement
                        (param,Statement.RETURN_GENERATED_KEYS)
        )
        {
                int startCourse=(pageNumber-1)*ITEMS_ON_PAGE;
                int countCourses=ITEMS_ON_PAGE;
            if(tutorId!=IN_COURSE_ALL_NO_TUTOR_ID){
                statement.setLong(1,tutorId);
                statement.setInt(2,startCourse);
                statement.setLong(3,countCourses);
            } else{
                statement.setInt(1,startCourse);
                statement.setLong(2,countCourses);
            }
            rs = statement.executeQuery();
            while(rs.next()){
                if(list==null){
                    list=new ArrayList<>();
                }
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
            logger.error("Problem executing getAllCoursesWithParam ", ex);
        }finally {
            if (rs!=null){
                try {
                    rs.close();
                } catch (SQLException e) {
                    logger.error("Problem executing getAllCoursesWithParam ", e);
                }
            }
        }
        return list;
    }
    //for tutor to get only his own courses
    @Override
    public List<Course> getCoursesByTutorId(long tutorId,int pageNumber) {
        return getAllCoursesWithParam(tutorId,pageNumber,SQL_QUERY_COURSE_BY_TUTOR_PARAM_ID);
    }
    //for students to get all courses
    @Override
    public List<Course> getCourses(int pageNumber) {
        return getAllCoursesWithParam(IN_COURSE_ALL_NO_TUTOR_ID,pageNumber,SQL_QUERY_COURSE_ALL_NO_PARAM);
    }

    @Override
    public int getCountCoursesByTutorId(long tutorId) {
        int count=0;
        ResultSet rs=null;
        try (
                Connection connection = getConnection();
                PreparedStatement statement = connection.prepareStatement
                        (SQL_QUERY_COURSE_COUNT_BY_TUTOR_ID,Statement.RETURN_GENERATED_KEYS)
        )
        {
            statement.setLong(1,tutorId);
            rs = statement.executeQuery();
            while(rs.next()){
                count=rs.getInt(1);
            }
            rs.close();
        } catch (SQLException ex) {
            logger.error("Problem executing getCountCoursesByTutorId", ex);
        }finally {
            if (rs!=null){
                try {
                    rs.close();
                } catch (SQLException e) {
                    logger.error("Problem executing getCountCoursesByTutorId, rs close", e);
                }
            }
        }
        return count;
    }

    @Override
    public int getCountCourses() {
        int count=0;
        ResultSet rs=null;
        try (
                Connection connection = getConnection();
                PreparedStatement statement = connection.prepareStatement
                        (SQL_QUERY_COURSE_COUNT,Statement.RETURN_GENERATED_KEYS)
        )
        {
            rs = statement.executeQuery();
            while(rs.next()){
                count=rs.getInt(1);
            }
            rs.close();
        } catch (SQLException ex) {
            logger.error("Problem executing getCountCourses", ex);
        }finally {
            if (rs!=null){
                try {
                    rs.close();
                } catch (SQLException e) {
                    logger.error("Problem executing getCountCourses, rs close", e);
                }
            }
        }
        return count;
    }
}
