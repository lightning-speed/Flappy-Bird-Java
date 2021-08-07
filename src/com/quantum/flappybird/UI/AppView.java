package com.quantum.flappybird.UI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;


public class AppView extends JPanel {
    public static Game GameR = new Game();
    public static JLabel SI = new JLabel("0");
    public JLabel res = new JLabel("");
    public JLabel l = new JLabel("");
    Graphics ge;
    @Override
    public void paintComponent(Graphics g) {
        g.drawImage(new ImageIcon("assets/images/background.png").getImage(), 0, 0, null);
        ge = g;
    }
    public AppView(){
          setLayout(null);
          add(l);
          Game.flappy.setLocation(120,50);
          this.add(Game.flappy);
          res.setFont(new Font("Arial",Font.BOLD,17));
          res.setBorder(null);
          res.setBounds(70,290,148,40);
          res.setHorizontalAlignment(SwingConstants.CENTER);
          res.setForeground(Color.WHITE);
          add(res);
          SI.setForeground(Color.WHITE);
          SI.setFont(new Font("Arial",Font.BOLD,27));
          SI.setBounds(0,40,288,30);
          SI.setHorizontalAlignment(SwingConstants.CENTER);
          l.setForeground(Color.WHITE);
          l.setFont(new Font("Arial",Font.BOLD,27));
          l.setBounds(0,40,288,30);
          l.setHorizontalAlignment(SwingConstants.CENTER);
          add(SI);
          activate();
          this.addMouseListener(new MouseListener() {
              @Override public void mouseClicked(MouseEvent e) { }
              @Override public void mousePressed(MouseEvent e) { Game.canJump = true;
                  Game.temp=0;if(Game.paused){restart();}}
              @Override public void mouseReleased(MouseEvent e) {}
              @Override public void mouseEntered(MouseEvent e) { }
              @Override public void mouseExited(MouseEvent e) { }
          });
    }
    public void activate() {
        int tempX = 650;
        int ip = 2;
        for(int i = 0;i<400;i+=2){
            Game.obs[i]=new GameBlockObject(50,340,new Color(0,230,10));
            Game.obs[i+1]=new GameBlockObject(50,340,new Color(0,230,10));
                 Game.obs[i].setLocation(tempX,(int)((Math.random()*-283348)+50)%-290);
                Game.obs[i+1].setLocation(tempX, Game.obs[i].getY()+340+110);
            tempX+=260;
            add(Game.obs[i]);
            add(Game.obs[i+1]);
        }
        Runnable game = new Runnable(){
            @Override
            public void run() {
               GameR.start();
            }
        };
        new Thread(game).start();
    }
    public void showLost(){
        l.setText("High Score: "+Game.hi);
        SI.setText("Your Score: "+SI.getText());
        SI.setBounds(SI.getX(),120,SI.getWidth(),SI.getHeight());
        res.setText("Click To Restart");
    }
    public void restart(){
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        res.setText("");
        l.setText("");
        SI.setText("0");
        GameR.reset();
        removeAll();
        add(l);
        add(SI);
        add(res);
        Game.flappy = new GameObject(26,26,"assets/images/flappy_1.png");
        Game.flappy.setLocation(120,50);
        this.add(Game.flappy);
        SI.setBounds(0,40,288,30);
        activate();
    }
}
