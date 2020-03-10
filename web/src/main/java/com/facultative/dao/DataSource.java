package com.facultative.dao;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.beans.PropertyVetoException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;


/**
 * The type Data source.
 */
public class DataSource {

    private static DataSource dataSource;
    private ComboPooledDataSource cpds;
    private static Logger logger= LoggerFactory.getLogger(DataSource.class);

    private DataSource() {
        Properties properties=new Properties();
        cpds=new ComboPooledDataSource();
        try {
            cpds.setDriverClass("com.mysql.cj.jdbc.Driver");
            properties.load(getClass().getClassLoader()
                    .getResourceAsStream("dao.properties"));
            cpds.setJdbcUrl(properties.getProperty("jdbc"));
            cpds.setUser(properties.getProperty("user"));
            cpds.setPassword(properties.getProperty("password"));
            cpds.setMinPoolSize(3);
            cpds.setAcquireIncrement(2);
            cpds.setMaxPoolSize(10);
            cpds.setMaxStatements(180);
        } catch (PropertyVetoException | IOException e) {
            logger.error("DataSource problem:",e);
        }
    }

    /**
     * Get instance data source. The data source instance is a singleton.
     *
     * @return the data source
     */
    public static DataSource getInstance(){
        if (dataSource == null) {
            synchronized (DaoPersonImpl.class) {
                if (dataSource == null) {
                    dataSource = new DataSource();
                }
            }
        }
        return dataSource;
    }

    /**
     * Gets connection.
     *
     * @return the connection
     */
    public Connection getConnection() throws SQLException {
            return this.cpds.getConnection();
    }
}
