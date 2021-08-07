package com.quantum.flappybird.UI;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Window extends JFrame {
    public  static AppView view = new AppView();
    public static KeyListener FrameLis;
    public Window(){
        super("Flappy Bird");
        this.setIconImage(new ImageIcon("assets/images/icon.png").getImage());
        this.setSize(288,480);
        this.setVisible(true);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setContentPane(view);
        Window.FrameLis = new KeyListener() {
            @Override public void keyTyped(KeyEvent e) {}
            @Override public void keyPressed(KeyEvent e) {if(e.getKeyChar()==' ') Game.canJump=true;
                Game.temp=0; }
            @Override public void keyReleased(KeyEvent e) { }
        };
        this.addKeyListener(FrameLis);
    }
}
