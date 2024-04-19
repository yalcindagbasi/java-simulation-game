package project.core;
import java.sql.*;

public class LogOps extends ObjectHelper {
    int signInFood= 100;
    int signInGoods=100;
    int signInBalance=100;
    public boolean logInCheck(String username,String password) throws SQLException {
        Connection connection= getConnection();
        Statement statement=connection.createStatement();
        ResultSet rs=statement.executeQuery("SELECT * FROM userinfo WHERE username='"+username+"' and password='"+password+"'");
        if(rs.next()) {
            statement.close();
            connection.close();
            return true;

        }
        else{
            statement.close();
            connection.close();
            return false;
        }



    }
    public boolean userNameCheck(String username) throws SQLException {
        Connection connection= getConnection();
        Statement statement=connection.createStatement();

        ResultSet rs=statement.executeQuery("SELECT * FROM userinfo WHERE username='"+username+"'");

        if(rs.next()) {
            statement.close();
            connection.close();
            return true;

        }
        else{
            statement.close();
            connection.close();
            return false;
        }
    }
    public boolean signInUser(String username, String password) throws SQLException {
        if(userNameCheck(username))
            return false;
        else{
            Connection connection= getConnection();
            Statement statement=connection.createStatement();

            statement.executeUpdate("INSERT INTO userinfo (username,password,food,goods,balance) VALUES ('"+username+"','"+password+"','"+signInFood+"','"+signInGoods+"','"+signInBalance+"')");

            statement.close();
            connection.close();
            return true;
        }

    }
}
