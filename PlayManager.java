
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


//The class that handles basic gameplay elements
//Elements include: scoreboard, deleting bottom line, drawing play area, etc.
public class PlayManager
{
    //The main play area of the game
    
    //Chose these measurments as a basic tetris block will be 30x30
    final int WIDTH = 360;
    final int HEIGHT = 600;
    
    //Indicate directions
    public static int left_x;
    public static int right_x;
    public static int top_y;
    public static int bottom_y;
    
    public PlayManager()
    {
        //Frame buildup
        left_x = (GamePanel.WIDTH/2) - (WIDTH/2); //460
        right_x = left_x + WIDTH; //820
        top_y = 50;
        bottom_y = top_y + HEIGHT; //650
    }
    
    public void update()
    {
        
    }
    
    public void draw(Graphics2D g2)
    {
        //Play area frame buildup
        g2.setColor(Color.white);
        g2.setStroke(new BasicStroke(4f));
        
        //The alterations to dimensions are so theres a boundary 
        g2.drawRect(left_x-4,top_y-4,WIDTH+8,HEIGHT+8);
        
        //A smaller box that gives a warning on the next block 
        int x = right_x + 100;
        int y = bottom_y - 200;
        g2.drawRect(x,y,200,200);
        g2.setFont(new Font("Ariel",Font.PLAIN,30));
        g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        g2.drawString("NEXT",x+60,y+60);
        
    }
}
