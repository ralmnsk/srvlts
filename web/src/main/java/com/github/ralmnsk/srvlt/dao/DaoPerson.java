package com.github.ralmnsk.srvlt.dao;

import com.github.ralmnsk.srvlt.model.Person;
import com.github.ralmnsk.srvlt.model.UserType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;

public class DaoPerson implements DaoPersonInterface<Person> {
    private static Logger logger= LoggerFactory.getLogger(DaoPerson.class);
    private static volatile DaoPersonInterface instance;

    public static DaoPersonInterface<Person> getInstance() {
        DaoPersonInterface<Person> localInstance = instance;
        if (localInstance == null) {
            synchronized (DaoPerson.class) {
                localInstance = instance;
                if (localInstance == null) {
                    instance = localInstance = new DaoPerson();
                }
            }
        }
        return localInstance;
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
                     ("select * from mydb.user where login=?",Statement.RETURN_GENERATED_KEYS);
        )
        {
            statement.setString(1, login);
            rs = statement.executeQuery();     //   getGeneratedKeys();
//            statement.execute();
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
        return person;
    }

    @Override
    public Person save(Person person){
        ResultSet generatedKeys=null;
        try (Connection connection = getConnection();
             PreparedStatement statement =
                     connection
                             .prepareStatement("insert into mydb.user (surname, name, login, password, role) values (?, ?, ?, ?, ?)"
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
            logger.error("Prblem executing INSERT", ex);

        } finally {
            if (generatedKeys!=null){
                try {
                    generatedKeys.close();
                } catch (SQLException e) {
                    logger.error("Prblem executing INSERT, generatedKey close", e);
                }
            }
        }
        return person;
    }

    @Override
    public Person get(long id) {
        Person person=new Person();
        ResultSet rs=null;
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement
                     ("select * from mydb.user where id=?",Statement.RETURN_GENERATED_KEYS);
        )
        {
            statement.setLong(1, id);
            rs = statement.executeQuery();     //   getGeneratedKeys();
//            statement.execute();
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
        return person;
    }

    @Override
    public Person update(Person person) {
        try (Connection connection = getConnection();
                PreparedStatement statement = connection.prepareStatement
                        ("update mydb.user set surname=?, name=?, login=?, password=?, role=? where id=?");

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
                     ("delete from mydb.user where id=?");
        )
        {
            statement.setLong(1, id);
            statement.execute();
            logger.info("User was deleted: user id:" +id);
        } catch (SQLException ex) {
            logger.error("Prblem executing DELETE", ex);
        }

    }
}
