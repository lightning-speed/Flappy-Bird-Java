package com.quantum.flappybird.UI;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class GameBlockObject extends JButton {
    public GameBlockObject(int width,int heigth,Color color){
        this.setSize(width,heigth);
        this.setBackground(color);
        this.setEnabled(false);
        this.setBorder(BorderFactory.createBevelBorder(0));
    }
    public void place(int x,int y){
        setLocation(x,y);
    }
}
