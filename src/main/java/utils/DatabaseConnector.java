package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnector {
    public static Connection getConnection() throws SQLException {
        //String url = "jdbc:postgresql://evelina.ddns.net:5432/db1";
        String url = "jdbc:postgresql://localhost/db1";
        String user = "evelina";
        String password = "0000";
        return DriverManager.getConnection(url, user, password);
    }
}
