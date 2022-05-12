package GUI_panel;

import javax.swing.*;
import java.awt.*;

public class BackgroundPanel extends JLayeredPane {


    @Override
    public void paintComponent(Graphics gs){
        Graphics2D g = (Graphics2D) gs;
        super.paintComponent(g);
        ImageIcon imageIcon = new ImageIcon("src/images/menuBackground.png");
        g.drawImage(imageIcon.getImage(), 0,0,this);

    }


}
