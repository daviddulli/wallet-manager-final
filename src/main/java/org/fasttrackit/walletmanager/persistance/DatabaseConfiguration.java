package org.fasttrackit.walletmanager.persistance;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DatabaseConfiguration {
    public static Connection getConnection() throws SQLException, IOException, ClassNotFoundException {
        InputStream applicationProperties = DatabaseConfiguration.class.getClassLoader()
                .getResourceAsStream("application.properties");

        try {
            Properties properties = new Properties();
            properties.load(applicationProperties);

            Class.forName("com.mysql.cj.jdbc.Driver");

            return DriverManager.getConnection(
                    properties.getProperty("url"),
                    properties.getProperty("username"),
                    properties.getProperty("password"));
        } finally {
            // closing input stream to allow memory cleanup
            if (applicationProperties != null) {
                applicationProperties.close();
            }
        }
    }


}
