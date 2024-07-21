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
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;

public class KeyHandler implements KeyListener
{
    public static boolean upPressed, downPressed, leftPressed, rightPressed;
    
    @Override
    public void keyTyped(KeyEvent e){}
    
    @Override
    public void keyPressed(KeyEvent e)
    {
        int code = e.getKeyCode();
        
        if (code == KeyEvent.VK_W)
        {
            upPressed = true;
        }
        if (code == KeyEvent.VK_A)
        {
            leftPressed = true;
        }
        if (code == KeyEvent.VK_S)
        {
            downPressed = true;
        }
        if (code == KeyEvent.VK_D)
        {
            rightPressed = true;
        }
        
    }
    
    @Override
    public void keyReleased(KeyEvent e){}
}
