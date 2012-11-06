/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 *
 * @author Nestor_Velasquez
 */
public class GamePanel extends JPanel implements Constant, ActionListener{

    public Set<Letter> letters = new CopyOnWriteArraySet<>();
    private boolean isGameOver;
    private Image gameOverImage;
    private Image backGroundImage;
    private int score = 0;
    private int lifes = 10;
    private Timer timer;
    private int countTyped = 0;
    private int level = 1;
    private int countUpdatesPoints = 0;
    private boolean pause = false;
    
    public GamePanel() {        
        setBackground(Color.black);
        setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));        
        setDoubleBuffered(true);
        setVisible(true);
        setFocusable(true);
        timer = new Timer(FIRST_DELAY,this);   
        timer.start();//It will call the @actionPerformed method every second        
    }
    
     @Override
    public void actionPerformed(ActionEvent ae) {         
         generateLetters();          
    }        
    
    public void generateLetters(){
        //Moving letters added before
        for(Letter l: letters){            
            if(l.isVisible()){                
                l.move(l.getX());
                
                if(l.isFailed()){
                    lifes--;//Rest a life if the player faile a letter.
                }               
                
            }else{
                letters.remove(l);
            }      
        }
        
        if(lifes==0){
            isGameOver = true;
        }//If the user has no more chances then gameover.
        
        //Adding a new Letter with a ramdom Y Position.
        Letter t = new Letter();        
        letters.add(t);        
        repaint();//it will call the paint method        
    }  
            
    @Override
    public void paint(Graphics grphcs) {
        super.paint(grphcs);
        grphcs.setFont(GAME_FONT);
        grphcs.setColor(FONT_GAME_COLOR);
//        backGroundImage = new ImageIcon(URL_IMAGE+"menupanelbkg.jpg").getImage();
//        grphcs.drawImage(backGroundImage, 0, 0, null);
        //Verify is a change level and a change speed agree.
        updateScore();
        
        if(isGameOver){
            gameOverImage = new ImageIcon(URL_IMAGE+"gameover.jpg").getImage();            
            grphcs.drawImage(gameOverImage, 145, 5, this);
            //grphcs.drawString("GAME OVER",145,100);
            grphcs.drawString("Score: "+score, 130, 140);
            grphcs.drawString("Level: "+level, 130, 155);
            grphcs.drawString("Press Enter to restart", 100, 200);
            //grphcs.drawString("Press Esc to exit", 100, 220);
            timer.stop();
        }else{
            for (Letter l: letters){                                
                grphcs.drawString("Score: "+score,0,15);
                grphcs.drawString("Lifes: "+lifes,320,15);
                grphcs.drawString("Level: "+level, 170, 15);
                //grphcs.drawString("Press space bar to pause", 0, 33);
                //grphcs.drawString("Press Enter to restart", 225, 33);                
                grphcs.drawLine(0, 35, 400, 35);
                grphcs.drawLine(0, 300, 400, 300);
                grphcs.drawString(l.getLetter().toUpperCase(), l.getX(),l.getY());
            }
        }        
        Toolkit.getDefaultToolkit().sync();
        grphcs.dispose();
    }
    
    public void pause(){
        if(!pause){
            timer.stop();            
        }
        else {
            timer.start();            
        }
        pause = !pause;//pause or start.
    }
    
    public void updateScore(){
        if(score!=0){
            if (countUpdatesPoints>=20){
                if(timer.getDelay()>100){
                    timer.setDelay(timer.getDelay()-100);
                    level++;
                }else{
                    if(timer.getDelay()>0){
                        timer.setDelay(timer.getDelay()-5);
                    }                   
                }
                countUpdatesPoints = 0;
                //When upgrade level the count restart.
            }
        }
    }
    
    public void restar(){
        this.isGameOver = false;
        this.letters.clear();
        this.countTyped=0;
        this.score = 0;
        this.countUpdatesPoints=0;
        this.lifes=INITIAL_LIFES;
        this.level = 1;
        this.timer.setDelay(FIRST_DELAY);
        this.timer.start();
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;        
    }

    public int getCountTyped() {
        return countTyped;        
    }

    public void setCountTyped(int countTyped) {
        this.countTyped = countTyped;
        countUpdatesPoints++;
    }
}
