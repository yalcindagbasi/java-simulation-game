package project.core;

import project.obj.Plot;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class PlotDB extends ObjectHelper {
    public void createPlots(int rows, int cols) {

        try {
            String sqlfindAdmin="SELECT id from userinfo where username='admin'";
            Connection connection = getConnection();
            Statement statement = connection.createStatement();
            ResultSet rs= statement.executeQuery(sqlfindAdmin);
            int adminID=1;
            if(rs.next()){
                adminID=rs.getInt("id");
            }

            String sqlString = "truncate plots;";
            for (int j = 0; j < rows; j++)
                for (int i = 0; i < cols; i++) {
                    String s = String.valueOf(j + 1) + String.valueOf(i + 1);
                    sqlString += ("\nINSERT INTO plots(owner_id,plot_type,plot_grid) VALUES('"+adminID+"','empty','" + s + "');");
                }
            System.out.println(sqlString);

            statement.executeUpdate(sqlString);
            statement.close();


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }

    public Plot getPlotInfo(String plot_grid) {
        Plot plot = new Plot();
        try {
            System.out.println(plot_grid);
            Connection connection = getConnection();
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM plots WHERE plot_grid='" + plot_grid + "'");
            System.out.println("success");
            if (rs.next()) {

                plot.setAdress(rs.getString("plot_grid"));
                System.out.println(plot.getAdress());
                plot.setPlot_id(rs.getInt("plot_id"));
                plot.setOwner_id(rs.getInt("owner_id"));
                switch (rs.getString("plot_type")) {
                    case "BOS":
                        plot.setType(Plot.typeEnum.BOS);
                        break;
                    case "MARKET":
                        plot.setType(Plot.typeEnum.MARKET);
                        break;
                    case "MAGAZA":
                        plot.setType(Plot.typeEnum.MAGAZA);
                        break;
                    case "EMLAKCI":
                        plot.setType(Plot.typeEnum.EMLAKCI);
                        break;
                    default:
                        plot.setType(Plot.typeEnum.BOS);
                        break;
                }
                System.out.println(rs.getString("satisdurumu"));
                switch (rs.getString("satisdurumu")) {
                    case "hayir":
                        plot.setSatisDurumu(Plot.satisDurumuEnum.hayir);
                        break;
                    case "satilik":
                        plot.setSatisDurumu(Plot.satisDurumuEnum.satilik);
                        break;
                    case "kiralik":
                        plot.setSatisDurumu(Plot.satisDurumuEnum.kiralik);
                        break;
                    default:
                        plot.setSatisDurumu(Plot.satisDurumuEnum.hayir);
                        break;
                }
                plot.setPrice(rs.getInt("cost"));

            }
            statement.close();
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return plot;
    }
}
