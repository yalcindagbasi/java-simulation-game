package project.abs;

import javax.swing.*;

public abstract class AFrame extends JFrame {
    public void initFrame(String title,JPanel panel){
        add(panel);
        setTitle(title);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

    }
    public void initsubFrame(String title,JPanel panel){
        add(panel);
        setTitle(title);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

    }
    public abstract JPanel initPanel();
}
