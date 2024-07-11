
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

//The main game panel for the game
public class GamePanel extends JPanel
{
    //heights and widths of the panel 
    public static final int WIDTH = 1280;
    public static final int HEIGHT = 720;
    
    //This is the final variable used to set the FPS
    final int FPS = 60;
    
    
    public GamePanel()
    {
        this.setPreferredSize(new Dimension(WIDTH,HEIGHT));
        this.setBackground(Color.black);
        this.setLayout(null);
        
        
    }
}
