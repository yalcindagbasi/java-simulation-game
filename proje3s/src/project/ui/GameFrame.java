package project.ui;

import project.abs.AFrame;
import project.core.TimeManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;

public class GameFrame extends AFrame {
    int rows= 4;
    int cols=3;
    int width=1600;
    int height=900;

    public static JLabel foodLeft= new JLabel("Yemek:");
    public static JLabel goodsLeft= new JLabel("Eşya:");
    public static JLabel balance= new JLabel("Para:");
    public static JLabel datetime= new JLabel("TarihSaat");
    public void statGuncelle(){
    }
    public static String[] players={"Ali","Veli","Melih"};

    public static String[] getPlayers() {
        return players;
    }

    JButton[][] plotBT = new JButton[cols][rows];
    JPanel[][] plotPN = new JPanel[cols][rows];
    JLabel[][] plotLB = new JLabel[cols][rows];
    public GameFrame(){
        initFrame("MetaLand",initPanel());
        new AdminPanel();
    }

    @Override
    public JPanel initPanel() {
        JPanel mainPanel= new JPanel(new BorderLayout());

        TimeManager thread=new TimeManager();
        thread.start();
        thread.setZamanHizi(1000);

        JPanel gridPanel= new JPanel(new GridLayout(rows,cols));
        gridPanel.setPreferredSize(new Dimension((width/3)*2,height));
        JPanel rightPanel= new JPanel(new GridLayout(2,1));
        rightPanel.setPreferredSize(new Dimension(width/3,height));
        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, gridPanel, rightPanel);
        mainPanel.add(splitPane,BorderLayout.CENTER);
        for(int j =0;j<rows;j++)
            for(int i=0;i<cols;i++){
                plotBT[i][j]= new JButton("Sahibi: admin");
                plotBT[i][j].setOpaque(false);
                plotBT[i][j].setContentAreaFilled(false);
                plotBT[i][j].setBorderPainted(false);




                plotPN[i][j]= new JPanel(new BorderLayout());
                plotPN[i][j].setBackground(Color.green);
                plotPN[i][j].setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY,100/rows));
                plotLB[i][j]=new JLabel("Blok "+ (j+1) + (i+1) +"   Boş Arazi");
                plotLB[i][j].setHorizontalAlignment(SwingConstants.CENTER);
                gridPanel.add(plotPN[i][j]);


                plotPN[i][j].add(plotLB[i][j],BorderLayout.NORTH);
                plotPN[i][j].add(plotBT[i][j],BorderLayout.CENTER);

                int finalJ = j;
                int finalI = i;
                plotBT[i][j].addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {

                        new PlotInfo(finalJ,finalI);
                    }
                });

            }

        JPanel statsPanel= new JPanel(new GridLayout(1,0));
        mainPanel.add(statsPanel,BorderLayout.SOUTH);
        JLabel task= new JLabel("Şu Anda: İştesin(Kalan: 3 Saat)");
        statsPanel.add(task);



        statsPanel.add(foodLeft);

        statsPanel.add(goodsLeft);

        statsPanel.add(balance);

        statsPanel.add(datetime);

        JPanel actionPanel= new JPanel(new GridLayout(0,2));
        rightPanel.add(actionPanel);

        JComboBox playersCombo= new JComboBox(players);
        actionPanel.add(playersCombo);
        JButton showPlayerInfoButton= new JButton("Göster");
        actionPanel.add(showPlayerInfoButton);

        JButton quitJobButton=new JButton("İstifa Et");
        actionPanel.add(quitJobButton);

        JTextArea logText= new JTextArea("Burada kayıtlar tutulur\nmuhtemelen.");
        JScrollPane logScroll= new JScrollPane(logText);
        rightPanel.add(logScroll);


        JPanel changeSpeedPanel=new JPanel(new GridLayout(1,0));
        statsPanel.add(changeSpeedPanel);

        JButton speed01x= new JButton("||");
        speed01x.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TimeManager.setZamanHizi(10000);
            }
        });
        changeSpeedPanel.add(speed01x);

        JButton speed1x= new JButton(">");
        speed1x.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TimeManager.setZamanHizi(1000);
            }
        });
        changeSpeedPanel.add(speed1x);

        JButton speed2x= new JButton(">>");
        speed2x.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TimeManager.setZamanHizi(500);
            }
        });
        changeSpeedPanel.add(speed2x);

        JButton speed4x= new JButton(">>>");
        speed4x.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TimeManager.setZamanHizi(250);
            }
        });
        changeSpeedPanel.add(speed4x);

        JButton speed16x= new JButton(">>>>");
        speed16x.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TimeManager.setZamanHizi(62.5);
            }
        });
        changeSpeedPanel.add(speed16x);


        return mainPanel;
    }
}
