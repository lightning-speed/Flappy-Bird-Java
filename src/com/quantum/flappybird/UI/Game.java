package com.quantum.flappybird.UI;

import com.quantum.flappybird.FileManager;
import com.quantum.flappybird.Sound;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Game {
    public static boolean paused = false;
    public static GameObject flappy = new GameObject(26,26,"assets/images/flappy_1.png");
    public static  GameBlockObject obs[] = new GameBlockObject[400];
    int frames = 10;
    public static boolean canJump = false;
    public static int hi = 0;
    public static int temp = 0;
    int ipt = 0;
    public static double score = 0;
    Timer t2;
    Timer t;
    public void start() {
        hi = Integer.parseInt(FileManager.read("user_data/hi.dat").replace("\n",""));
        Runnable ga = new Runnable(){
            @Override
            public void run() {
                for(;!paused;) {
                    try {
                        Thread.sleep(10);
                        if(!paused) {
                            Sound s = new Sound();
                            s.play("assets/sound/wing.wav");
                        }

                    } catch (InterruptedException interruptedException) {
                        interruptedException.printStackTrace();
                    }
                }
            }
        };
        new Thread(ga).start();

        t = new Timer(30, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
             if(!paused) {
                 MoveObs();
                 AppView.SI.setText("" + (int) score);
                 score += 0.015;
             }
            }
        });
        t.start();

        t2 = new Timer(250, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!paused) {
                    if (ipt % 3 == 0){ flappy.look("assets/images/flappy_1.png");}
                    if (ipt % 3 == 1) flappy.look("assets/images/flappy_2.png");
                    if (ipt % 3 == 2) flappy.look("assets/images/flappy_3.png");
                    ipt++;
                }
            }
        });
        t2.start();
        for (;!paused;) {
            try {
                Thread.sleep(frames);
                if(!paused)
                update();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
    public void update(){
          check();
          DoJump();
          gravity();
          if(score>hi){hi = (int)score;writeHi();}
          Window.view.repaint();
    }
    public void DoJump(){
         if(canJump){
             if(flappy.getY()>0)
             flappy.place(flappy.getX(),flappy.getY()-7);
             temp++;
         }
         if(temp>=21){
             canJump = false;
             temp = 0;
         }
    }
    public void gravity(){
        if(flappy.getY()<420){
            flappy.setLocation(flappy.getX(),flappy.getY()+4);
        }
    }
    int tep = 0;
    public void MoveObs() {
        for (int i = tep; i < 400; i++) {
            obs[i].place(obs[i].getX() - 4, obs[i].getY());
            if(obs[i].getX()<-70){tep++;}
        }
    }
    public void check(){
       if(obs[tep].getY()+332>=flappy.getY()&&flappy.getX()>=obs[tep].getX()&&flappy.getX()<=obs[tep].getX()+40){
           paused=true;
           lost();
       }
       if(obs[tep].getY()+346+110<=flappy.getY()+20&&flappy.getX()>=obs[tep].getX()&&flappy.getX()<=obs[tep].getX()+50){
           paused=true;
           lost();
       }
    }
    void lost(){
        Runnable ga = new Runnable(){
            @Override
            public void run() {
                Sound s = new Sound();
                s.play("assets/sound/hit.wav");

            }
        };
        new Thread(ga).start();
        Window.view.showLost();
        t.stop();
        t2.stop();
    }
    public void reset(){
        tep = 0;
        temp = 0;
        canJump = false;
        score = 0;
        paused = false;
    }
    public void writeHi(){
        FileManager.write("user_data/hi.dat",hi+"");
    }
}