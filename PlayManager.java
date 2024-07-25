
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
import java.util.Random;

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
    
    //Mino
    Mino currentMino;
    final int MINO_START_X;
    final int MINO_START_Y;
    
    //Dropping interval for Minos
    public static int dropInterval = 60; //Mino drops every 60 frames
    
    
    public PlayManager()
    {
        //Frame buildup
        left_x = (GamePanel.WIDTH/2) - (WIDTH/2); //460
        right_x = left_x + WIDTH; //820
        top_y = 50;
        bottom_y = top_y + HEIGHT; //650
        
        MINO_START_X = left_x + (WIDTH/2) - Block.SIZE;
        MINO_START_Y = top_y + Block.SIZE;
        
        //Starting Mino
        currentMino = pickMino();
        currentMino.setXY(MINO_START_X,MINO_START_Y);
        
    }
    
    //Randomises what Mino to select in general
    private Mino pickMino()
    {
       Mino mino = null; 
        
       int n = new Random().nextInt(7);
       
       switch(n)
       {
           case 0:
           mino = new Mino_L1();
           break;
           
           case 1:
           mino = new Mino_L2();
           break;
           
           case 2:
           mino = new Mino_Square();
           break;
           
           case 3:
           mino = new Mino_Bar();
           break;
           
           case 4:
           mino = new Mino_T();
           break;
           
           case 5:
           mino = new Mino_Z1();
           break;
           
           case 6:
           mino = new Mino_Z2();
           break;
       }
       
       return mino;
    }
    
    public void update()
    {
        currentMino.update();
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
        
        //Drawing the current Mino
        if (currentMino != null)
        {
            currentMino.draw(g2);
        }
        
        //Drawing pause button
        g2.setColor(Color.yellow);
        g2.setFont(g2.getFont().deriveFont(50f));
        
        if(KeyHandler.pausePressed)
        {
            x = left_x + 70;
            y = top_y + 320;
            g2.drawString("PAUSED", x, y);
        }
    }
}
