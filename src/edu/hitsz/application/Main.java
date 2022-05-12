package edu.hitsz.application;

import GUI_panel.BoardPanel;
import GUI_panel.MenuPanel;

import javax.swing.*;
import java.awt.*;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

/**
 * 程序入口
 * @author hitsz
 */
public class Main {

    public static final int WINDOW_WIDTH = 512;

    public static final int WINDOW_HEIGHT = 768;

    public static boolean GAME_SOUND = true;
    public static int GAME_LEVEL = 0;
    public static int SCORE = 0;
    public static final Object lock = new Object();

    public static void main(String[] args) throws IOException, InterruptedException {


        System.out.println("Hello Aircraft War");

        // 获得屏幕的分辨率，初始化 Frame
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        JFrame frame = new JFrame("Aircraft War");
        frame.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        frame.setResizable(false);
        //设置窗口的大小和位置,居中放置
        frame.setBounds(((int) screenSize.getWidth() - WINDOW_WIDTH) / 2, 0,
                WINDOW_WIDTH, WINDOW_HEIGHT);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);



        synchronized (lock){
            MenuPanel menuPanel = new MenuPanel();
            frame.setContentPane(menuPanel.bgp);
            frame.setVisible(true);

            lock.wait();

            frame.remove(menuPanel.baseMenuPanel);
            AbstractGame game = new GameEasy();
            switch (GAME_LEVEL){
                case 0:
                    game = new GameEasy();
                    break;
                case 1:
                    game = new GameMedium();
                    break;
                case 2:
                    game = new GameHard();
                    break;
                default:
            }
            frame.setContentPane(game);
            frame.setVisible(true);
            game.action();

            lock.wait();

            frame.remove(game);
            BoardPanel boardPanel = new BoardPanel();
            frame.setContentPane(boardPanel.baseBoardPanel);
            frame.setVisible(true);

        }

    }
}
