package com.facultative.dao;

import com.facultative.model.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import static com.facultative.service.constants.Constants.*;


/**
 * The type Dao mark.
 */
public class DaoMarkImpl implements IDaoMark<Mark> {
    private static Logger logger= LoggerFactory.getLogger(DaoMarkImpl.class);
    private static volatile IDaoMark instance;


    /**
     * Gets instance.
     *
     * @return the instance
     */
    public static IDaoMark<Mark> getInstance() {
        if (instance == null) {
            synchronized (DaoMarkImpl.class) {
                if (instance == null) {
                    instance = new DaoMarkImpl();
                }
            }
        }
        return instance;
    }

    private Connection getConnection() throws SQLException {
        return DataSource.getInstance().getConnection();
    }

    @Override
    public Mark save(Mark mark){
        ResultSet generatedKeys=null;
        try (Connection connection = getConnection();
             PreparedStatement statement =
                     connection
                             .prepareStatement(SQL_QUERY_MARK_SAVE
                                     , Statement.RETURN_GENERATED_KEYS);
        )
        {
            statement.setLong(1, mark.getCourse().getId());
            statement.setLong(2, mark.getStudent().getId());
            statement.execute();
            generatedKeys = statement.getGeneratedKeys();
            if (generatedKeys.next()) {
                mark.setId(generatedKeys.getLong(1));
            }
            generatedKeys.close();
            logger.info("Mark was created:" + mark.getId());
        } catch (SQLException ex) {
            logger.error("Problem executing save mark ", ex);

        } finally {
            if (generatedKeys!=null){
                try {
                    generatedKeys.close();
                } catch (SQLException e) {
                    logger.error("Problem executing save mark, generatedKey close", e);
                }
            }
        }
        return mark;
    }

    @Override
    public Mark get(long id) {
        Mark mark=null;
        Tutor tutor=null;
        Course course=null;
        Student student=null;
        ResultSet rs=null;
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement
                     (SQL_QUERY_MARK_GET,Statement.RETURN_GENERATED_KEYS);
        )
        {
            statement.setLong(1, id);
            rs = statement.executeQuery();

            while(rs.next()){
                mark=new Mark();
                mark.setId(rs.getLong(1));
                mark.setMark(rs.getInt(2));
                mark.setReview(rs.getString(3));

                course=new Course();
                course.setId(rs.getLong(4));
                course.setName(rs.getString(5));

                tutor=new Tutor();
                tutor.setId(rs.getLong(6));
                tutor.setSurname(rs.getString(7));
                tutor.setName(rs.getString(8));
                student=new Student();
                student.setId(rs.getLong(9));
                mark.setStudent(student);
                course.setTutor(tutor);

                mark.setCourse(course);
            }
            rs.close();
        } catch (SQLException ex) {
            logger.error("Problem executing get mark", ex);
        }finally{
            if (rs!=null){
                try {
                    rs.close();
                } catch (SQLException e) {
                    logger.error("Problem executing get mark ResultSet close", e);
                }
            }
        }
        return mark;
    }

    @Override
    public Mark update(Mark mark) {
        try (Connection connection = getConnection();
                PreparedStatement statement = connection.prepareStatement
                        (SQL_QUERY_MARK_UPDATE);
        ){
            statement.setInt(1, mark.getMark());
            statement.setString(2, mark.getReview());
            statement.setLong(3,mark.getId());
            statement.execute();
        } catch (SQLException ex) {
            logger.error("Problem executing mark update", ex);
        }
        return mark;
    }

    @Override
    public void delete(long id) {
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement
                     (SQL_QUERY_MARK_DELETE);
        )
        {
            statement.setLong(1, id);
            statement.execute();
            logger.info("Mark was deleted: id:" +id);
        } catch (SQLException ex) {
            logger.error("Problem executing delete mark", ex);
        }
    }

    //for tutor to get only his own courses
    @Override
    public List<Mark> getMarksByTutorId(long tutorId, int pageNumber) {
            List<Mark> list=null;
            ResultSet rs=null;
            try (
                    Connection connection = getConnection();
                    PreparedStatement statement = connection.prepareStatement
                            (SQL_QUERY_MARK_ALL_BY_TUTOR_ID,Statement.RETURN_GENERATED_KEYS);
            )
            {
                int startMark=(pageNumber-1)*ITEMS_ON_PAGE;
                int countMark=ITEMS_ON_PAGE;
                statement.setLong(1,tutorId);
                statement.setInt(2,startMark);
                statement.setLong(3,countMark);

                rs = statement.executeQuery();
                while(rs.next()){
                    if(list==null){
                        list=new ArrayList<Mark>();
                    }
                    Course course=new Course();
                    course.setId(rs.getLong(1));
                    course.setName(rs.getString(2));
                    Mark mark=new Mark();
                    mark.setId(rs.getLong(4));
                    mark.setMark(rs.getInt(5));
                    mark.setReview(rs.getString(6));

                    Student student=new Student();
                    student.setId(rs.getLong(7));
                    student.setSurname(rs.getString(8));
                    student.setName(rs.getString(9));

                    mark.setCourse(course);
                    mark.setStudent(student);

                    list.add(mark);
                }
                rs.close();
            } catch (SQLException ex) {
                logger.error("Problem executing getMarksByTutorId", ex);
            }finally {
                if (rs!=null){
                    try {
                        rs.close();
                    } catch (SQLException e) {
                        logger.error("Problem executing getMarksByTutorId, rs close", e);
                    }
                }
            }
            return list;
        }
    //for students to get all courses
    @Override
    public List<Mark> getMarksByStudentId(long studentId, int pageNumber) {
        List<Mark> list=null;
        ResultSet rs=null;
        try (
                Connection connection = getConnection();
                PreparedStatement statement = connection.prepareStatement
                        (SQL_QUERY_MARK_ALL_BY_STUDENT_ID,Statement.RETURN_GENERATED_KEYS);
        )
        {
            int startMark=(pageNumber-1)*ITEMS_ON_PAGE;
            int countMark=ITEMS_ON_PAGE;
            statement.setLong(1,studentId);
            statement.setInt(2,startMark);
            statement.setLong(3,countMark);

            rs = statement.executeQuery();
            while(rs.next()){
                if(list==null){
                    list=new ArrayList<Mark>();
                }
                Course course=new Course();
                course.setId(rs.getLong(1));
                course.setName(rs.getString(2));

                Tutor tutor=new Tutor();
                tutor.setId(rs.getLong(3));
                tutor.setSurname(rs.getString(4));
                tutor.setName(rs.getString(5));

                Mark mark=new Mark();
                mark.setId(rs.getLong(6));
                mark.setMark(rs.getInt(7));
                mark.setReview(rs.getString(8));

                course.setTutor(tutor);
                mark.setCourse(course);

                list.add(mark);
            }
            rs.close();
        } catch (SQLException ex) {
            logger.error("Problem executing getMarksByStudentId", ex);
        }finally {
            if (rs!=null){
                try {
                    rs.close();
                } catch (SQLException e) {
                    logger.error("Problem executing getMarksByStudentId, rs close", e);
                }
            }
        }
        return list;
    }

    @Override
    public int getCountMarksByTutorId(long tutorId) {
        int count=0;
        ResultSet rs=null;
        try (
                Connection connection = getConnection();
                PreparedStatement statement = connection.prepareStatement
                        (SQL_QUERY_MARK_BY_TUTOR_ID,Statement.RETURN_GENERATED_KEYS);
        )
        {
            statement.setLong(1,tutorId);
            rs = statement.executeQuery();
            while(rs.next()){
                count=rs.getInt(1);
            }
            rs.close();
        } catch (SQLException ex) {
            logger.error("Problem executing getCountMarksByTutorId", ex);
        }finally {
            if (rs!=null){
                try {
                    rs.close();
                } catch (SQLException e) {
                    logger.error("Problem executing getCountMarksByTutorId, rs close", e);
                }
            }
        }
        return count;
    }

    @Override
    public int getCountMarksByStudentId(long userId) {
        int count=0;
        ResultSet rs=null;
        try (
                Connection connection = getConnection();
                PreparedStatement statement = connection.prepareStatement
                        (SQL_QUERY_MARK_BY_STUDENT_ID,Statement.RETURN_GENERATED_KEYS);
        )
        {
            statement.setLong(1,userId);
            rs = statement.executeQuery();
            while(rs.next()){
                count=rs.getInt(1);
            }
            rs.close();
        } catch (SQLException ex) {
            logger.error("Problem executing getCountMarksByStudentId", ex);
        }finally {
            if (rs!=null){
                try {
                    rs.close();
                } catch (SQLException e) {
                    logger.error("Problem executing getCountMarksByStudentId, rs close", e);
                }
            }
        }
        return count;
    }
}
