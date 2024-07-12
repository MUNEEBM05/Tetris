
import java.util.HashMap;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JLabel;
import java.awt.*;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Rectangle;

//All the differently shaped tetrominos will be extended from this class
public class Mino
{
    
    //We have a array as a tetromino consists of 4 minos 
    public Block b [] = new Block [4];
    public Block tempB [] = new Block [4];
    
    public void create(Color c)
    {
        b[0] = new Block(c);
        b[1] = new Block(c);
        b[2] = new Block(c);
        b[3] = new Block(c);
        tempB[0] = new Block(c);
        tempB[1] = new Block(c);
        tempB[2] = new Block(c);
        tempB[3] = new Block(c);
    }
    
    public void setXY(int x, int y)
    {
        
    }
    
    public void updateXY(int direction)
    {
        
    }
    
    public void update()
    {
        
    }
    
    public void draw(Graphics2D g2)
    {
        
    }
}
