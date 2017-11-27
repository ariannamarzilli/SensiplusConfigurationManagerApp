package it.unicas.project.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Provides the connection to the SensiPlus database.
 */
public final class ConnectionFactory {

    private static String URL = "jdbc:mysql://localhost:3306/shark92_plus?useSSL=false";
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

    public static String getURL() {
        return URL;
    }

    public static void setURL(String URL) {
        ConnectionFactory.URL = URL;
    }

    public static String getUSER() {
        return USER;
    }

    public static void setUSER(String USER) {
        ConnectionFactory.USER = USER;
    }

    public static String getPASS() {
        return PASS;
    }

    public static void setPASS(String PASS) {
        ConnectionFactory.PASS = PASS;
    }
}
