
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

//In tetris a tetromino is composed of 4 blocks
//This class is for representign a single block which is used for a mino
public class Block extends Rectangle
{
    public int x;
    public int y;
    public static final int SIZE = 30; // A block is 30x30
    public Color c;
    
    public Block(Color c)
    {
        this.c = c;
    }
    
    public void draw(Graphics2D g2)
    {
        int margin = 2;
        g2.setColor(c);
        g2.fillRect(x+margin,y+margin,SIZE - (margin*2),SIZE - (margin*2));
    }
}
