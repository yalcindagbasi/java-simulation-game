package project.ui;

import project.abs.AFrame;
import project.core.PlotDB;
import project.core.UserDB;
import project.obj.Plot;

import javax.swing.*;
import java.awt.*;

public class PlotInfo extends AFrame {
    public static String[] players={"Ali","Veli","Melih"};
    String plot_grid;
    public PlotInfo(int row,int col){
        plot_grid= row + 1 +String.valueOf(col+1);
        initsubFrame("Arazi Bilgisi",initPanel());

    }
    @Override
    public JPanel initPanel() {
        JPanel mainPanel=new JPanel(new GridLayout(1,3));

        JPanel plotInfoPanel=new JPanel(new GridLayout(0,1));
        plotInfoPanel.setBorder(BorderFactory.createTitledBorder("Arazi Bilgisi"));
        mainPanel.add(plotInfoPanel);

        Plot currentPlot= new PlotDB().getPlotInfo(plot_grid);

        JLabel pOwner= new JLabel("Sahibi: "+ new UserDB().findUsernamebyID(currentPlot.getOwner_id()));
        plotInfoPanel.add(pOwner);
        JLabel pId=new JLabel("Adres: Blok "+currentPlot.getAdress());
        plotInfoPanel.add(pId);
        JLabel pStatus= new JLabel("Durum: "+currentPlot.getType());
        plotInfoPanel.add(pStatus);
        System.out.println(currentPlot.getSatisDurumu());
        if(currentPlot.getSatisDurumu().equals(Plot.satisDurumuEnum.satilik)){
            JButton buyButton= new JButton("Satın Al: "+currentPlot.getPrice());
            plotInfoPanel.add(buyButton);
        }
        else if(currentPlot.getSatisDurumu().equals(Plot.satisDurumuEnum.kiralik)){
            JButton rentButton= new JButton("Kirala: "+currentPlot.getRent());
            plotInfoPanel.add(rentButton);
        }
        else{
            JLabel notSaleLabel=new JLabel("Bu arazi satılık/kiralık değil.");
            plotInfoPanel.add(notSaleLabel);
        }



        if(currentPlot.getType()!=Plot.typeEnum.BOS){
            JPanel bInfoRightPanel=new JPanel(new BorderLayout());
            mainPanel.add(bInfoRightPanel);
            JPanel buildingInfoPanel=new JPanel(new GridLayout(0,1));
            buildingInfoPanel.setBorder(BorderFactory.createTitledBorder("İşletme Bilgisi"));
            bInfoRightPanel.add(buildingInfoPanel,BorderLayout.CENTER);
            JLabel bType=new JLabel("Mağaza");
            buildingInfoPanel.add(bType);
            JLabel bOwner= new JLabel("Sahibi: ");
            buildingInfoPanel.add(bOwner);
            JLabel bHiring=new JLabel("İşe Alım Durumu: Evet");
            buildingInfoPanel.add(bHiring);
            JLabel bJobInfo=new JLabel("İş Bilgisi: 8 Saat 200TL");
            buildingInfoPanel.add(bJobInfo);
            JButton bApply=new JButton("İşe Başvur");
            buildingInfoPanel.add(bApply);
            JList bWorkersList=new JList(players);
            JScrollPane bWorkersScroll=new JScrollPane(bWorkersList);
            bWorkersScroll.setBorder(BorderFactory.createTitledBorder("Çalışan Listesi"));
            bInfoRightPanel.add(bWorkersScroll,BorderLayout.SOUTH);
        }


        if(currentPlot.getOwner_id()==LoginScreen.getCurrentUser().getUser_id()){
            JPanel ownerPanel=new JPanel(new GridLayout(0,1));
            ownerPanel.setBorder(BorderFactory.createTitledBorder("İşletme Yönetimi"));
            mainPanel.add(ownerPanel);
            JLabel oIncome=new JLabel("Günlük Gelir: 2400");
            ownerPanel.add(oIncome);
            JLabel oExpense=new JLabel("Günlük Gider: 1800");
            ownerPanel.add(oExpense);
            JLabel oNet=new JLabel("Net: 600");
            ownerPanel.add(oNet);
            if(currentPlot.getType()!=Plot.typeEnum.BOS){
                JButton oShut=new JButton("İşletmeyi Kapat");
                ownerPanel.add(oShut);
            }
            else{
                JButton oOpen=new JButton("İşletme Aç");
                ownerPanel.add(oOpen);
            }
            JButton oSell=new JButton("Araziyi Sat");
            ownerPanel.add(oSell);
        }




        return mainPanel;
    }
}
