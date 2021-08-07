package com.quantum.flappybird.UI;

import javax.swing.*;
import java.awt.*;


public class GameObject extends JLabel {
    public GameObject(int width, int height, String Path){
        if(Path!=null)
        this.setIcon(new ImageIcon(Path));
        this.setSize(width,height);
    }
    public void place(int x,int y){
        setLocation(x,y);
    }
    public void look(String path){
        this.setIcon(new ImageIcon(path));
    }
}
