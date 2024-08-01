import java.awt.*;
import javax.swing.JFrame;
import javax.swing.JPanel;

//The main game panel for the game
public class GamePanel extends JPanel implements Runnable
{
    //heights and widths of the panel 
    public static final int WIDTH = 1280;
    public static final int HEIGHT = 720;
    
    //This is the final variable used to set the FPS
    final int FPS = 60;
    
    //To allow multitaksing along the game
    Thread gameThread;
    PlayManager pm;
    
    //For the music
    public static Sound music = new Sound();
    public static Sound se = new Sound();
    
    public GamePanel()
    {
        this.setPreferredSize(new Dimension(WIDTH,HEIGHT));
        this.setBackground(Color.black);
        this.setLayout(null);
        
        //Adding keyListener
        this.addKeyListener(new KeyHandler());
        this.setFocusable(true);
        
        pm = new PlayManager();
        
        
    }
    
    public void launchGame()
    {
        gameThread = new Thread(this);
        gameThread.start();
        
        music.play(0, true);
        music.loop();
    }
    
    @Override
    public void run()
    {
        //Game loop which is to contiuously update the game screen
        //This is becasue multiple things are happening at once
        double drawInterval = 1000000000/FPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;
        
        while (gameThread != null)
        {
            currentTime = System.nanoTime();
            
            delta += (currentTime - lastTime) / drawInterval;
            lastTime = currentTime;
            
            //This will occur 60 times per second which is the intended FPS
            if (delta >=1)
            {
                //This continuously updates the game page
                update();
                repaint();
                delta--;
            }
        }
    }
    
    private void update()
    {
        if (KeyHandler.pausePressed == false && pm.gameOver == false)
        {
            pm.update();
        }
        
    }
    
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;
        pm.draw(g2);
    }
}