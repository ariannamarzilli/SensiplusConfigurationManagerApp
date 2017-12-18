package it.unicas.project.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Provides the connection to the SensiPlus database.
 *
 * @Author Mariangela Evangelista
 */
public final class ConnectionFactory {

    private final static String URL = "jdbc:mysql://localhost:3306/sensiDB?useSSL=false";
    private final static String USER = "root";
    private final static String PASS = ".";

    static private boolean areThereNewSettings;

    private static String url;
    private static String user;
    private static String pass;

    /**
     * Create the connection to the database
     * @return The connection to the database.
     */
    public static Connection getConnection() {
        try {
            DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
            if (areThereNewSettings) {
                return DriverManager.getConnection(url, user, pass);
            } else {
                return DriverManager.getConnection(URL, USER, PASS);
            }
        } catch (SQLException ex) {
            throw new RuntimeException("Error connecting to the database", ex);
        }
    }

    public static String getUrl() {
        return url;
    }

    public static void setUrl(String newUrl) { ConnectionFactory.url = newUrl; }

    public static String getUser() {
        return user;
    }

    public static void setUser(String newUser) {
        ConnectionFactory.user = newUser;
    }

    public static String getPass() {
        return pass;
    }

    public static void setPass(String newPass) {
        ConnectionFactory.pass = newPass;
    }

    public static boolean getAreThereNewSettings() {
        return areThereNewSettings;
    }

    public static void setAreThereNewSettings(boolean areThereNewSettings) {
        ConnectionFactory.areThereNewSettings = areThereNewSettings;
    }
}
