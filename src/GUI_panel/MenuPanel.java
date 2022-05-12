package GUI_panel;

import edu.hitsz.application.Main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuPanel {
    private JButton easyButton;
    private JButton mediumButton;
    private JButton hardButton;
    private JLabel musicLabel;
    private JPanel upperPanel;
    private JPanel midPanel;
    private JPanel lowerPanel;
    public JPanel baseMenuPanel;
    private JComboBox musicChoice;
    private JLabel welcome;
    private JPanel lPanel;
    private JPanel rPanel;
    public BackgroundPanel bgp;


    public MenuPanel(){
        musicChoice.addItem("开");
        musicChoice.addItem("关");
        bgp = new BackgroundPanel();

        bgp.setLayout(new GridLayout(3, 1));
        baseMenuPanel.remove(upperPanel);
        baseMenuPanel.remove(lowerPanel);
        baseMenuPanel.remove(midPanel);
        lPanel.setOpaque(false);
        rPanel.setOpaque(false);
        upperPanel.setOpaque(false);
        midPanel.setOpaque(false);
        lowerPanel.setOpaque(false);
        bgp.add(upperPanel);
        bgp.add(midPanel);
        bgp.add(lowerPanel);







        musicChoice.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String choice = musicChoice.getSelectedItem().toString();
                if(choice == "关"){
                    Main.GAME_SOUND = false;
                }
                else{
                    Main.GAME_SOUND = true;
                }
                System.out.println(Main.GAME_SOUND);
            }
        });
        easyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Main.GAME_LEVEL = 0;
                synchronized (Main.lock){
                    Main.lock.notify();
                }

            }
        });
        mediumButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Main.GAME_LEVEL = 1;
                synchronized (Main.lock){
                    Main.lock.notify();
                }
            }
        });
        hardButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Main.GAME_LEVEL = 2;
                synchronized (Main.lock){
                    Main.lock.notify();
                }
            }
        });
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("MenuPanel");
        frame.setContentPane(new MenuPanel().bgp);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
