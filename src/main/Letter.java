package main;

import java.awt.Component;
/**
 *
 * @author Nestor_Velasquez
 */
public class Letter extends Component implements Constant{
    
    private int x;
    private int y;    
    private String letter;    
    private boolean visible = true;
    private boolean failed = false;    
    
    public Letter(String letter){
        this.letter = letter;
    }
    
    public Letter(){
        int pos;
        double index;
        
        //We look for a ramdom number to look for the letter.
        index = Math.random() * MAX_OF_CHOICES;
        index = (MIN_ELEMENT + index);
        pos = (int) index;
        
        try{
            letter = ALPHABET.substring(pos,(pos+1));
            //Get the letter in the specified posittion.
        }catch(Exception e){
            //if possition does not exist return nothing.
            letter = "";
        }
        
        //Looking for a ramdom Y position.
        index = Math.random() * Y_POSITION;
        index = MIN_Y_POSITION + index;
        y = (int) index;

        y = (y * Y_COORDENATE);
        x = 0;
        //setFocusable(true);
    }    

    @Override
    public boolean equals(Object o) {
        if (o instanceof Letter){
        Letter let = (Letter) o;
            if(letter.equalsIgnoreCase(let.getLetter())){
                    return true;
            }
        }
        return false;
        //no duplicates.
    }

    
    public void move(int x) {
        //this code moove the letter 10 pixels to rigth.        
        if(!"".equals(letter)){
            if(x >= BOARD_WIDTH){
                failed = true;
                visible = false;
            }
            this.x = (x + X_COORDENATE);
                    
        }        
    }
        
    public String getLetter() {
        return letter;
    }
    
    public int getX() {
        return x;
    }
    
    public int getY() {
        return y;
    }   

    public boolean isVisible() {
        return visible;
    }

    public boolean isFailed() {
        return failed;
    }    
}
