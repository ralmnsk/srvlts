package com.facultative.dao;

import com.facultative.model.UserType;
import com.facultative.model.Person;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.sql.*;

import static com.facultative.service.constants.Constants.*;

public class DaoPersonImpl implements IDaoPerson<Person> {
    private static Logger logger= LoggerFactory.getLogger(DaoPersonImpl.class);
    private static volatile IDaoPerson instance;

    public static IDaoPerson<Person> getInstance() {
        if (instance == null) {
            synchronized (DaoPersonImpl.class) {
                if (instance == null) {
                    instance = new DaoPersonImpl();
                }
            }
        }
        return instance;
    }

    private Connection getConnection() throws SQLException {
        return DataSource.getInstance().getConnection();
    }

    @Override
    public Person getByLogin(String login) {
        Person person=new Person();
        ResultSet rs=null;
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement
                     (SQL_QUERY_PERSON_GET_BY_LOGIN,Statement.RETURN_GENERATED_KEYS);
        )
        {
            statement.setString(1, login);
            rs = statement.executeQuery();
            while(rs.next()){
                person.setId(rs.getLong(1));
                person.setSurname(rs.getString(2));
                person.setName(rs.getString(3));
                person.setLogin(rs.getString(4));
                person.setPassword(rs.getString(5));
                UserType type= UserType.valueOf(rs.getString(6));
                person.setRole(type);
            }
            rs.close();
        } catch (SQLException ex) {
            logger.error("Problem executing getByLogin", ex);
        }finally{
            if (rs!=null){
                try {
                    rs.close();
                } catch (SQLException e) {
                    logger.error("Problem executing getByLogin.ResultSet close", e);
                }
            }
        }
        return person;
    }

    @Override
    public Person save(Person person){
        ResultSet generatedKeys=null;
        try (Connection connection = getConnection();
             PreparedStatement statement =
                     connection
                             .prepareStatement(SQL_QUERY_PERSON_SAVE
                                     , Statement.RETURN_GENERATED_KEYS);
        )
        {
            statement.setString(1, person.getSurname());
            statement.setString(2, person.getName());
            statement.setString(3, person.getLogin());
            statement.setString(4, person.getPassword());
            statement.setString(5, person.getRole().toString());
            statement.execute();
            generatedKeys = statement.getGeneratedKeys();
            if (generatedKeys.next()) {
                person.setId(generatedKeys.getLong(1));
            }
            generatedKeys.close();
            logger.info("User was created:" + person.getId()+" login: "+ person.getLogin());
        } catch (SQLException ex) {
            logger.error("Prblem executing save", ex);

        } finally {
            if (generatedKeys!=null){
                try {
                    generatedKeys.close();
                } catch (SQLException e) {
                    logger.error("Problem executing save, generatedKey close", e);
                }
            }
        }
        return person;
    }

    @Override
    public Person get(long id) {
        Person person=null;
        ResultSet rs=null;
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement
                     (SQL_QUERY_PERSON_GET,Statement.RETURN_GENERATED_KEYS);
        )
        {
            statement.setLong(1, id);
            rs = statement.executeQuery();
            while(rs.next()){
                person=new Person();
                person.setId(rs.getLong(1));
                person.setSurname(rs.getString(2));
                person.setName(rs.getString(3));
                person.setLogin(rs.getString(4));
                person.setPassword(rs.getString(5));
                UserType type= UserType.valueOf(rs.getString(6));
                person.setRole(type);
            }
            rs.close();
        } catch (SQLException ex) {
            logger.error("Problem executing get", ex);
        }finally{
            if (rs!=null){
                try {
                    rs.close();
                } catch (SQLException e) {
                    logger.error("Problem executing get.ResultSet close", e);
                }
            }
        }
        return person;
    }

    @Override
    public Person update(Person person) {
        try (Connection connection = getConnection();
                PreparedStatement statement = connection.prepareStatement
                        (SQL_QUERY_PERSON_UPDATE);

        ){
            statement.setString(1, person.getSurname());
            statement.setString(2, person.getName());
            statement.setString(3, person.getLogin());
            statement.setString(4, person.getPassword());
            statement.setString(5, person.getRole().toString());
            statement.setLong(6,person.getId());
            statement.execute();
        } catch (SQLException ex) {
            logger.error("Problem executing UPDATE", ex);
        }
        return person;
    }

    @Override
    public void delete(long id) {
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement
                     (SQL_QUERY_PERSON_DELETE);
        )
        {
            statement.setLong(1, id);
            statement.execute();
            logger.info("User was deleted: user id:" +id);
        } catch (SQLException ex) {
            logger.error("Problem executing DELETE", ex);
        }

    }
}
