package com.facultative.dao;

import com.facultative.model.Course;
import com.facultative.model.Person;
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
                                     , Statement.RETURN_GENERATED_KEYS)
        )
        {
            statement.setString(1, course.getName());
            statement.setLong(2, course.getTutor().getId());
            statement.setString(3,course.getDescription());
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
        Person tutor;
        ResultSet rs=null;
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement
                     (SQL_QUERY_COURSE_GET)
        )
        {
            statement.setLong(1, id);
            rs = statement.executeQuery();

            while(rs.next()){
                course=new Course();
                course.setId(rs.getLong(1));
                course.setName(rs.getString(2));
                course.setDescription(rs.getString(3));
                tutor=new Person();
                tutor.setId(rs.getLong(4));
                tutor.setSurname(rs.getString(5));
                tutor.setName(rs.getString(6));
                tutor.setLogin(rs.getString(7));
                tutor.setPassword(rs.getString(8));
                tutor.setRole(UserType.valueOf(rs.getString(9)));
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
            statement.setString(2, course.getDescription());
            statement.setLong(3, course.getTutor().getId());
            statement.setLong(4,course.getId());
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
    private List<Course> getAllCoursesWithParam(long tutorId, int pageNumber, String param, int scale) {
        List<Course> list=new ArrayList<>();//to escape NullPointerException in Commands
        ResultSet rs=null;
        try (
                Connection connection = getConnection();
                PreparedStatement statement = connection.prepareStatement
                        (param)
        )
        {
                int startCourse=(pageNumber-1)*scale;

            if(tutorId!=IN_COURSE_ALL_NO_TUTOR_ID){
                statement.setLong(1,tutorId);
                if(pageNumber != ALL_MARKS){
                    statement.setInt(2,startCourse);
                    statement.setLong(3,scale);
                }
            } else{
                statement.setInt(1,startCourse);
                statement.setLong(2,scale);
            }
            rs = statement.executeQuery();
            while(rs.next()){
                Course course=new Course();
                course.setId(rs.getLong(1));
                course.setName(rs.getString(2));
                course.setDescription(rs.getString(3));
                Person tutor=new Person();
                tutor.setId(rs.getLong(4));
                tutor.setSurname(rs.getString(5));
                tutor.setName(rs.getString(6));
                tutor.setLogin(rs.getString(7));
                tutor.setPassword(rs.getString(8));
                tutor.setRole(UserType.valueOf(rs.getString(9)));
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
    public List<Course> getCoursesByTutorId(long tutorId,int pageNumber, int scale) {
        if(pageNumber == ALL_MARKS){
            return getAllCoursesWithParam(tutorId,pageNumber,SQL_QUERY_COURSE_BY_TUTOR_PARAM_ID, scale);
        }
        return getAllCoursesWithParam(tutorId,pageNumber,SQL_QUERY_COURSE_BY_TUTOR_PARAM_ID_LIMIT, scale);
    }
    //for students to get all courses
    @Override
    public List<Course> getCourses(int pageNumber, int scale) {
        return getAllCoursesWithParam(IN_COURSE_ALL_NO_TUTOR_ID,pageNumber,SQL_QUERY_COURSE_ALL_NO_PARAM, scale);
    }

    @Override
    public int getCountCoursesByTutorId(long tutorId) {
        int count=0;
        ResultSet rs=null;
        try (
                Connection connection = getConnection();
                PreparedStatement statement = connection.prepareStatement
                        (SQL_QUERY_COURSE_COUNT_BY_TUTOR_ID)
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
                        (SQL_QUERY_COURSE_COUNT)
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
