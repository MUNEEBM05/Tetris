import java.awt.*;
import java.util.Random;
import java.util.ArrayList;
import javax.swing.border.Border;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
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
    
    Mino nextMino;
    final int NEXTMINO_X;
    final int NEXTMINO_Y;
    public static ArrayList<Block> staticBlocks  = new ArrayList<>();
    
    //Dropping interval for Minos
    public static int dropInterval = 60; //Mino drops every 60 frames
    boolean gameOver;
    
    //Special effects like sound
    boolean effectCounterOn;
    int effectCounter;
    ArrayList<Integer> effectY = new ArrayList<>();
    
    //Score
    int level = 1;
    int lines;
    int score;
    
    
    public PlayManager()
    {
        //Frame buildup
        left_x = (GamePanel.WIDTH/2) - (WIDTH/2); //460
        right_x = left_x + WIDTH; //820
        top_y = 50;
        bottom_y = top_y + HEIGHT; //650
        
        MINO_START_X = left_x + (WIDTH/2) - Block.SIZE;
        MINO_START_Y = top_y + Block.SIZE;
        
        NEXTMINO_X = right_x + 175;
        NEXTMINO_Y = top_y + 500;
        
        //Starting Mino
        currentMino = pickMino();
        currentMino.setXY(MINO_START_X,MINO_START_Y);
        
        
        nextMino = pickMino();
        nextMino.setXY(NEXTMINO_X,NEXTMINO_Y);
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
        //Checking if current mino is active
        if (currentMino.active == false)
        {
            //any non active minos are put on the ArrayList staticBlocks
            staticBlocks.add(currentMino.b[0]);
            staticBlocks.add(currentMino.b[1]);
            staticBlocks.add(currentMino.b[2]);
            staticBlocks.add(currentMino.b[3]);
            
            //Checking if game has ended
            if (currentMino.b[0].x == MINO_START_X && currentMino.b[0].y == MINO_START_Y)
            {
                gameOver = true;
                GamePanel.music.stop();
            }
            
            currentMino.deactivating = false;
            
            //replace current mino with next one
            currentMino = nextMino;
            currentMino.setXY(MINO_START_X, MINO_START_Y);
            nextMino = pickMino();
            nextMino.setXY(NEXTMINO_X,NEXTMINO_Y);
            
            //Checks if mino line can be deleted
            checkDelete();
        }
        else
        {
            currentMino.update();
        }
    }
    
    private void checkDelete()
    {
        int x = left_x;
        int y = top_y;
        int blockCount = 0;
        int lineCount = 0;
        
        
        while (x<right_x && y<bottom_y)
        {
            for (int i=0; i<staticBlocks.size(); i++)
            {
                if (staticBlocks.get(i).x == x && staticBlocks.get(i).y == y)
                {
                    blockCount++;
                }
            }
            
            x+= Block.SIZE;
            
            if (x == right_x)
            {
                //If block hits 12 we can delete the line as row is filled
                if (blockCount == 12)
                {
                    effectCounterOn = true;
                    effectY.add(y);
                    
                    for (int i = staticBlocks.size()-1; i>-1; i--)
                    {
                        //remove all blocks in that row
                        if (staticBlocks.get(i).y == y)
                        {
                            staticBlocks.remove(i);
                        }
                    }
                    
                    lineCount++;
                    lines++;
                    
                    //Once score reaches specified number level gets higher
                    //Speeds increases
                    if (lines % 10 == 0 && dropInterval >1)
                    {
                        level++;
                        if (dropInterval >10)
                        {
                            dropInterval -= 10;
                        }
                        else
                        {
                            dropInterval -= 1;
                        }
                    }
                    
                    
                    //Have to shift blocks down
                    for (int i=0; i<staticBlocks.size(); i++)
                    {
                        if (staticBlocks.get(i).y < y)
                        {
                            staticBlocks.get(i).y += Block.SIZE;
                        }
                    }
                }
                
                blockCount = 0;
                x = left_x;
                y+= Block.SIZE;
            }
        }
        
        //Score counter
        if (lineCount > 0)
        {
            GamePanel.se.play(1, false);
            int singleLineScore = 10 * level;
            score += singleLineScore * lineCount;
        }
    }
    
    public void draw(Graphics2D g2)
    {
        Random random = new Random();
        Color[] colors = {Color.cyan, Color.yellow, Color.magenta, Color.green, Color.red, Color.blue, Color.orange};
        
        //Play area frame buildup
        g2.setColor(Color.cyan);
        g2.setStroke(new BasicStroke(4f));
        
        //The alterations to dimensions are so theres a boundary 
        g2.drawRect(left_x-4,top_y-4,WIDTH+8,HEIGHT+8);
        
        //A smaller box that gives a warning on the next block 
        int x = right_x + 100;
        int y = bottom_y - 200;
        g2.setColor(Color.cyan);
        g2.drawRect(x,y,200,200);
        g2.setFont(new Font("Ariel",Font.PLAIN,30));
        g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        g2.setColor(colors[random.nextInt(colors.length)]);
        g2.drawString("NEXT",x+60,y+60);
        
        //Scoreboard
        g2.setColor(Color.cyan);
        g2.drawRect(x, top_y, 250, 300);
        x += 40;
        y = top_y + 90;
        g2.setColor(colors[random.nextInt(colors.length)]);
        g2.drawString("LEVEL " + level + ":", x, y);
        y += 70;
        g2.setColor(colors[random.nextInt(colors.length)]);
        g2.drawString("LINES: " + lines, x, y);
        y += 70;
        g2.setColor(colors[random.nextInt(colors.length)]);
        g2.drawString("SCORE: " + score, x, y);
        
        
        //Drawing the current Mino
        if (currentMino != null)
        {
            currentMino.draw(g2);
        }
        
        //Drawing the next mino
        nextMino.draw(g2);
        
        //Drawing the static blocks at the bottom of the area
        for (int i=0; i<staticBlocks.size(); i++)
        {
            staticBlocks.get(i).draw(g2);
        }
        
        //Effects
        if (effectCounterOn)
        {
            effectCounter++;
            g2.setColor(colors[random.nextInt(colors.length)]);
            for (int i=0; i<effectY.size(); i++)
            {
                g2.fillRect(left_x,effectY.get(i), WIDTH, Block.SIZE);
            }
            
            if (effectCounter == 10)
            {
                effectCounterOn = false;
                effectCounter = 0;
                effectY.clear();
            }
        }
        
        //Drawing pause button and Game Over button
        Color Gold = new Color(199, 153, 12);
        g2.setColor(Gold);
        g2.setFont(g2.getFont().deriveFont(50f));
        
        if(gameOver)
        {
            x = left_x + 25;
            y = top_y + 320;
            g2.drawString("GAME OVER", x, y);
        }
        else if(KeyHandler.pausePressed)
        {
            x = left_x + 70;
            y = top_y + 320;
            g2.drawString("PAUSED", x, y);
        }
        
        //Game Title
        x = 55;
        y = top_y + 320;
        g2.setColor(colors[random.nextInt(colors.length)]);
        g2.setFont(new Font("Verana",Font.ITALIC, 40));
        g2.drawString("TETRIS PROJECT", x, y);
    }
}
