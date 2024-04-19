package project.ui;

import project.abs.AFrame;
import project.core.PlotDB;
import project.core.UserDB;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AdminPanel extends AFrame {
    public AdminPanel(){
        initsubFrame("Yönetici Paneli",initPanel());
    }
    @Override
    public JPanel initPanel() {
        JPanel adminPanel= new JPanel(new GridLayout(2,1));
        JPanel northPanel= new JPanel(new BorderLayout());
        adminPanel.add(northPanel);
        JPanel southPanel= new JPanel(new BorderLayout());
        adminPanel.add(southPanel);

        JList playerList= new JList(new UserDB().getPlayerList());


        JScrollPane scrollpList=new JScrollPane(playerList);
        northPanel.add(scrollpList,BorderLayout.CENTER);
        JPanel playerPanel=new JPanel(new GridLayout(0,2));
        northPanel.add(playerPanel,BorderLayout.EAST);
        northPanel.setBorder(BorderFactory.createTitledBorder("Oyuncu Bilgisi"));
        southPanel.setBorder(BorderFactory.createTitledBorder("Arazi Bilgisi"));

        JLabel lBalance= new JLabel("Bakiye:");
        playerPanel.add(lBalance);
        JTextField pBalance= new JTextField();
        playerPanel.add(pBalance);

        JLabel lFood= new JLabel("Yemek:");
        playerPanel.add(lFood);
        JTextField pFood= new JTextField();
        playerPanel.add(pFood);

        JLabel lGoods= new JLabel("Eşya:");
        playerPanel.add(lGoods);
        JTextField pGoods= new JTextField();
        playerPanel.add(pGoods);

        JButton updateButton= new JButton("Kaydet");
        playerPanel.add(updateButton);
        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new UserDB().setPlayerStats(playerList.getSelectedValue().toString(),Integer.parseInt(pFood.getText()),Integer.parseInt(pGoods.getText()),Integer.parseInt(pBalance.getText()));
            }
        });

        JButton resetGameButton=new JButton("Oyunu Sıfırla");
        playerPanel.add(resetGameButton);
        resetGameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new PlotDB().createPlots(4,3);
                System.exit(1);
            }
        });

        playerList.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if(e.getValueIsAdjusting()){
                    try {
                        int arr[]= new UserDB().getPlayerStats(playerList.getSelectedValue().toString());
                        pBalance.setText(String.valueOf(arr[2]));
                        pGoods.setText(String.valueOf(arr[1]));
                        pFood.setText(String.valueOf(arr[0]));
                    } catch (SQLException ex) {
                        throw new RuntimeException(ex);
                    }


                }
            }
        });


        JList plotList= new JList();
        JScrollPane scrollPlotList= new JScrollPane(plotList);
        southPanel.add(scrollPlotList,BorderLayout.CENTER);
        JPanel plotPanel=new JPanel();
        southPanel.add(plotPanel,BorderLayout.EAST);
        return adminPanel;
    }
}
