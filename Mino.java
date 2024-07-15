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
    int autoDropCounter = 0;
    
    
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
        autoDropCounter ++; //Counter increases by 1 every frame
        
        if (autoDropCounter == PlayManager.dropInterval)
        {
            b[0].y += Block.SIZE;
            b[1].y += Block.SIZE;
            b[2].y += Block.SIZE;
            b[3].y += Block.SIZE;
            autoDropCounter = 0;
        }
    }
    
    public void draw(Graphics2D g2)
    {   
        int margin = 2;
        
        g2.setColor(b[0].c);
        g2.fillRect(b[0].x + margin,b[0].y + margin,Block.SIZE - (margin*2), Block.SIZE - (margin*2));
        g2.fillRect(b[1].x + margin,b[1].y + margin,Block.SIZE - (margin*2), Block.SIZE - (margin*2));
        g2.fillRect(b[2].x + margin,b[2].y + margin,Block.SIZE - (margin*2), Block.SIZE - (margin*2));
        g2.fillRect(b[3].x + margin,b[3].y + margin,Block.SIZE - (margin*2), Block.SIZE - (margin*2));
    }
}
