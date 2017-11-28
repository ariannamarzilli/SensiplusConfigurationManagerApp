package it.unicas.project.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Provides the connection to the SensiPlus database.
 */
public final class ConnectionFactory {

    private static String URL = "jdbc:mysql://localhost:3306/sensiDB?useSSL=false";
    private static String USER = "root";
    private static String PASS = ".";

    /**
     * Create the connection to the database
     * @return The connection to the database.
     */
    public static Connection getConnection() {
        try {
            DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
            return DriverManager.getConnection(URL, USER, PASS);
        } catch (SQLException ex) {
            throw new RuntimeException("Error connecting to the database", ex);
        }
    }

}
