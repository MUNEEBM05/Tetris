
import javax.swing.JFrame;
public class Main
{
    public static void main(String [] args)
    {
        JFrame window = new JFrame("Simple Tetris");
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        
        window.setLocationRelativeTo(null);
        window.setVisible(true);
        
        //Adding the Game Panel to the Main class useignt Gamepanel class
        GamePanel gp = new GamePanel();
        window.add(gp);
        //size of panel becomes size of window
        window.pack();
        
    }
}
