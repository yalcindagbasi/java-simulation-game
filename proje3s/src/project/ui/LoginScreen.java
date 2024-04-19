package project.ui;
import project.core.LogOps;
import project.abs.AFrame;
import project.core.TimeManager;
import project.core.UserDB;
import project.obj.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class LoginScreen extends AFrame {
    private static User currentUser=new User();

    public static User getCurrentUser() {
        return currentUser;
    }

    static JTextField unameField = new JTextField(10);
    public static String getUserName(){
        return unameField.getText();
    }
    public LoginScreen(){
        initFrame("Giriş Ekranı",initPanel());
    }
    @Override
    public JPanel initPanel() {
        JPanel panel= new JPanel(new GridLayout(3,2));


        JLabel unameJLabel = new JLabel("Kullanıcı Adı:",JLabel.RIGHT);
        panel.add(unameJLabel);

        panel.add(unameField);

        JLabel passwLabel = new JLabel("Şifre:",JLabel.RIGHT);
        panel.add(passwLabel);
        JTextField passwField = new JTextField(10);
        panel.add(passwField);

        JButton loginButton= new JButton("Giriş Yap");
        panel.add(loginButton);

        JButton signInButton= new JButton("Kaydol");
        panel.add(signInButton);

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if(new LogOps().logInCheck(unameField.getText(),passwField.getText())){
                        JOptionPane.showMessageDialog(unameField,"Giriş başarılı. Hoşgeldin "+ unameField.getText());
                        currentUser.setUsername(unameField.getText());
                        currentUser.setUser_id(new UserDB().findIDbyUserName(currentUser.getUsername()));

                        dispose();
                        new GameFrame();

                    }
                    else{
                        JOptionPane.showMessageDialog(unameField,"Kullanıcı adı/şifre yanlış. Tekrar deneyiniz");
                    }

                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        signInButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if(new LogOps().signInUser(unameField.getText(),passwField.getText())){
                        JOptionPane.showMessageDialog(unameField,"Kayıt başarılı. Aramıza hoşgeldin "+ unameField.getText());


                    }
                    else{
                        JOptionPane.showMessageDialog(unameField,"Bu kullanıcı adı başkası tarafından kullanılıyor.");
                    }


                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });


        return panel;
    }
}
