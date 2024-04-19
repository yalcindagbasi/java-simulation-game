package project.core;

import project.ui.GameFrame;
import project.ui.LoginScreen;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.concurrent.TimeUnit;

public class TimeManager extends Thread {
    private Connection connection;


    private static double zamanHizi = 1000; // Zaman hızı faktörü

    public TimeManager() {
        try {
            // Veritabanı bağlantısı oluşturma
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/proje3?allowMultiQueries=true", "root", "root");

            // Tablonun olup olmadığını kontrol etme
            if (!tableExists("zaman_tablosu")) {
                createTable();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void gameFrameGuncelle(){
        try {
            Statement statement=connection.createStatement();
            String user= LoginScreen.getUserName();
            ResultSet rs=statement.executeQuery("SELECT * FROM userinfo WHERE username="+ user);
            if(rs.next()){
                GameFrame.foodLeft.setText("Yemek: "+rs.getInt("food"));
                GameFrame.goodsLeft.setText("Eşya: "+rs.getInt("goods"));
                GameFrame.balance.setText("Bakiye: "+rs.getInt("balance"));


            }
            rs.close();
            rs=statement.executeQuery("SELECT * FROM zaman_tablosu");
            if(rs.next()){
                GameFrame.datetime.setText("Gun: "+rs.getInt("gunsayisi")+" Saat: "+rs.getInt("saat"));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void run() {
        try {
            Statement statement = connection.createStatement();

            while (true) {

                statement.executeUpdate("UPDATE zaman_tablosu SET zaman = zaman + " + 1+";");
                ResultSet rs=statement.executeQuery("SELECT zaman FROM zaman_tablosu;");
                if(rs.next()){
                    int zaman= rs.getInt("zaman");
                    statement.executeUpdate("UPDATE zaman_tablosu SET gunsayisi="+(zaman/24));
                    statement.executeUpdate("UPDATE zaman_tablosu SET saat="+(zaman%24));
                    statement.executeUpdate("UPDATE zaman_tablosu SET zamanhizi="+zamanHizi);
                }
                gameFrameGuncelle();



                Thread.sleep((long) zamanHizi);
            }
        } catch (SQLException | InterruptedException e) {
            e.printStackTrace();
        } finally {
            // Veritabanı bağlantısını kapatma
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void setZamanHizi(double hiz) {
        zamanHizi = hiz;
    }

    private boolean tableExists(String tableName) throws SQLException {
        ResultSet resultSet = connection.getMetaData().getTables(null, null, tableName, null);
        return resultSet.next();
    }

    private void createTable() throws SQLException {
        Statement statement = connection.createStatement();
        statement.executeUpdate("CREATE TABLE zaman_tablosu (zaman DOUBLE)");
        statement.executeUpdate("INSERT INTO zaman_tablosu VALUES (0)");
    }
}