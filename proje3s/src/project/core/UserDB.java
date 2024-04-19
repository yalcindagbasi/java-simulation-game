package project.core;

import javax.swing.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class UserDB extends ObjectHelper{


    public int[] getPlayerStats(String username) throws SQLException {
        Connection connection= getConnection();
        Statement statement=connection.createStatement();
        ResultSet rs= statement.executeQuery("SELECT food,goods,balance FROM userinfo WHERE username='"+username+"'");
        int[] arr = new int[3];
        if(rs.next()){
            arr[2]=rs.getInt("balance");
            arr[1]=rs.getInt("goods");
            arr[0]=rs.getInt("food");
        }
        statement.close();
        connection.close();
        return arr;
    }
    public void setPlayerStats(String username,int food,int goods,int balance)
    {

        try {
            Connection connection= getConnection();
            Statement statement=connection.createStatement();
            statement.executeUpdate("UPDATE userinfo  SET food='"+food+"',goods='"+goods+"',balance='"+balance+"' WHERE username='"+username+"'");
            statement.close();
            connection.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
    public String[] getPlayerList() {
            try {
                Connection connection= getConnection();
                Statement statement=connection.createStatement();
                ResultSet rs= statement.executeQuery("SELECT username FROM userinfo");
                String[] players=new String[20];
                for(int i=0;rs.next();i++){
                    players[i]=rs.getString("username");
                    System.out.println(players[i]);
                }
                statement.close();
                connection.close();
                return players;
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
    }
    public String findUsernamebyID(int ID){
        try {
            Connection connection = getConnection();
            Statement statement=connection.createStatement();
            ResultSet rs=statement.executeQuery("SELECT username FROM userinfo where id='"+ID+"'");
            if(rs.next()){
                String username=rs.getString("username");
                statement.close();
                connection.close();
                return username;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }
    public int findIDbyUserName(String username){
        try {
            Connection connection = getConnection();
            Statement statement=connection.createStatement();
            ResultSet rs=statement.executeQuery("SELECT id FROM userinfo where username='"+username+"'");
            if(rs.next()){
                int userid=rs.getInt("id");
                statement.close();
                connection.close();
                return userid;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return 0;
    }
}
