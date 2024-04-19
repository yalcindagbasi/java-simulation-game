package project.core;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ObjectHelper {

    private String username="root";
    private String password= "root";
    private String url = "jdbc:mysql://localhost:3306/proje3?allowMultiQueries=true";
    private static String driver= "com.mysql.jdbc.Driver";

    static{
        try {
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    protected Connection getConnection() throws SQLException {
        Connection connection=null;
        connection = DriverManager.getConnection(url,username,password);
        return connection;
    }
}