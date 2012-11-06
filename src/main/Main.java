package main;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

/**
 * @author Nestor_Velasquez
 */
public class Main extends JFrame implements Constant {

    JPanel menuPanel;
    JLabel label;
    JLabel startLabel;
    JButton menuButton;
    GamePanel gamePanel;
    ImageIcon image;
        
    public Main(){
        startLabel = new JLabel("Click Play to begin game!", SwingConstants.CENTER);
        startLabel.setFont(MENU_LABEL_FONT);
        startLabel.setForeground(Color.cyan);
        
        menuPanel = new JPanel();        
        menuButton = new JButton(new ImageIcon(URL_IMAGE+"play.jpg"));
        menuButton.setBorder(BorderFactory.createEmptyBorder());
        menuButton.setContentAreaFilled(false);
        menuButton.setToolTipText("Play");
        image = new ImageIcon(URL_IMAGE+"menupanelbkg.jpg");
        label = new JLabel(image);
        
        menuPanel.setBackground(BKG_GAME_COLOR);
        menuPanel.setLayout(new BorderLayout()); 
        menuPanel.setForeground(FONT_GAME_COLOR);
        menuPanel.add(menuButton, BorderLayout.LINE_END);
        menuPanel.add(startLabel,BorderLayout.LINE_START);
        menuPanel.setSize(30, 30);
        
        menuButton.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e) {
                menuButtonClick(e);
            }            
        });        
        
        getContentPane().add(menuPanel, BorderLayout.NORTH);
        getContentPane().add(label, BorderLayout.CENTER);
        //getContentPane().add(gamePanel, BorderLayout.CENTER);
        
        setTitle(TITLE);
        setSize(400,400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
        setResizable(false);
        
    }
    
    private void menuButtonClick(MouseEvent e){
        gamePanel = new GamePanel();
        
        gamePanel.addKeyListener(new KeyAdapter(){

            @Override
            public void keyPressed(KeyEvent e) {
                panelKeyPressed(e);
            }
            
        });
        getContentPane().add(gamePanel, BorderLayout.CENTER);
        
        startLabel.setText(TITLE);
        
        label.setVisible(false);
        menuButton.setVisible(false);
        
        ImageIcon pauseImage = new ImageIcon(URL_IMAGE+"pause.jpg");
        ImageIcon restartImage = new ImageIcon(URL_IMAGE+"restart.jpg");        
        JButton pauseButton = new JButton(pauseImage);
        JButton restartButton = new JButton(restartImage);
        
        restartButton.setToolTipText("Click or press Enter the game");
        pauseButton.setToolTipText("Click or Press Space Bar to pause and resume the game");
        restartButton.setBorder(BorderFactory.createEmptyBorder());
        pauseButton.setBorder(BorderFactory.createEmptyBorder());
        restartButton.setContentAreaFilled(false);
        pauseButton.setContentAreaFilled(false);
        
        pauseButton.addMouseListener(new MouseAdapter(){

            @Override
            public void mouseClicked(MouseEvent me) {
                pauseMouseClick(me);
            }
            
        });
        
        restartButton.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent me) {
                restartMouseClick(me);
            }
        
        });        
        
        menuPanel.add(restartButton, BorderLayout.LINE_START);
        menuPanel.add(startLabel, BorderLayout.CENTER);
        menuPanel.add(pauseButton, BorderLayout.LINE_END);
    }
    
    private void panelKeyPressed(KeyEvent e){
        Letter letter = new Letter(Character.toString(e.getKeyChar()));
        int code = e.getKeyCode();
        switch(code){
            case KeyEvent.VK_ENTER: {
                gamePanel.restar();                
                break;
            }
            
            case KeyEvent.VK_SPACE: {
                gamePanel.pause();
                break;
            }
            default: {
                if(gamePanel.letters.contains(letter)){
                    gamePanel.letters.remove(letter);
                    gamePanel.setCountTyped(gamePanel.getCountTyped()+1);
                    gamePanel.setScore(gamePanel.getCountTyped()*TYPE_SCORE);
                    break;
                }
            }            
        }        
    }
    
    private void pauseMouseClick(MouseEvent me){
        gamePanel.pause();
    }
    
    private void restartMouseClick(MouseEvent me){
        gamePanel.restar();
    }
    
    public static void main(String[] args) {
        new Main();
    }
}
