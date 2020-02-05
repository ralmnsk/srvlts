package com.github.ralmnsk.srvlt.dao;

import com.github.ralmnsk.srvlt.model.Course;
import com.github.ralmnsk.srvlt.model.Person;
import com.github.ralmnsk.srvlt.model.UserType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DaoCourse implements DaoCourseInterface<Course> {
    private static Logger logger= LoggerFactory.getLogger(DaoCourse.class);
    private static volatile DaoCourseInterface instance;

    public static DaoCourseInterface<Course> getInstance() {
        DaoCourseInterface<Course> localInstance = instance;
        if (localInstance == null) {
            synchronized (DaoCourse.class) {
                localInstance = instance;
                if (localInstance == null) {
                    instance = localInstance = new DaoCourse();
                }
            }
        }
        return localInstance;
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
                             .prepareStatement("insert into mydb.course (name_course, id_tuitor) values (?, ?)"
                                     , Statement.RETURN_GENERATED_KEYS);
        )
        {
            statement.setString(1, course.getName());
            statement.setLong(2, course.getTuitorId());
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
        Course course=new Course();
        ResultSet rs=null;
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement
                     ("select * from mydb.course where id=?",Statement.RETURN_GENERATED_KEYS);
        )
        {
            statement.setLong(1, id);
            rs = statement.executeQuery();     //   getGeneratedKeys();
//            statement.execute();
            while(rs.next()){
                course.setId(rs.getLong(1));
                course.setName(rs.getString(2));
                course.setTuitorId(rs.getLong(3));
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
                        ("update mydb.course set name=?, id_tuitor=? where id=?");

        ){
            statement.setString(1, course.getName());
            statement.setLong(2, course.getTuitorId());
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
                     ("delete from mydb.course where id=?");
        )
        {
            statement.setLong(1, id);
            statement.execute();
            logger.info("Course was deleted: id:" +id);
        } catch (SQLException ex) {
            logger.error("Prblem executing DELETE course", ex);
        }
    }

    @Override
    public List<Course> getAllCourses(long tuitorId) {
        List<Course> list=new ArrayList<>();
        ResultSet rs=null;
        try (
                Connection connection = getConnection();
                PreparedStatement statement = connection.prepareStatement
                        ("select * from courses where id_tuitor=?",Statement.RETURN_GENERATED_KEYS);
        )
        {
            statement.setLong(1,tuitorId);
            rs = statement.executeQuery();     //   getGeneratedKeys();
            while(rs.next()){
                Course course=new Course();
                Long id=rs.getLong(1);
                course.setId(id);
                String name=rs.getString(2);
                course.setName(name);
                course.setTuitorId(tuitorId);
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
}
