/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.awt.Color;
import java.awt.Font;

/**
 *
 * @author Nestor_Velasquez
 */
public interface Constant {
    public int X_COORDENATE = 20;
    public int Y_COORDENATE = 10;
    public int MIN_Y_POSITION = 7;
    public int Y_POSITION = (25-MIN_Y_POSITION)+1;    
    public String ALPHABET = "abcdefghijklmnopqrstuvwz";
    public int MIN_ELEMENT = 0;
    public int MAX_OF_CHOICES = (50+1)-MIN_ELEMENT;    
    public int BOARD_WIDTH = 380;
    public int TIMER_SPEED = 100;
    public int TYPE_SCORE = 5;
    public int FIRST_DELAY = 500;
    public int INITIAL_LIFES = 10;
    public Color FONT_GAME_COLOR = Color.cyan;
    public Color BKG_GAME_COLOR = Color.black;
    public Font GAME_FONT = new Font("Game Font", Font.BOLD, 12);
    public Font MENU_LABEL_FONT = new Font("Menu label font", Font.ITALIC,12);
    public String URL_IMAGE = "/home/nvelasquez/NetBeansProjects/Typing Letters/images/";
    public String TITLE = "Typping Letters";
}
