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
    public int direction = 1; //There are 4 directions
    boolean leftCollision, rightCollision, bottomCollision; //To manage the mino movements
    
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
    
    public void setXY(int x, int y){}
    
    public void updateXY(int direction)
    {
        checkRotationCollision();
        
        if (leftCollision == false && rightCollision == false && bottomCollision == false)
        {
            this.direction = direction;
            b[0].x = tempB[0].x;
            b[0].y = tempB[0].y;
            b[1].x = tempB[1].x;
            b[1].y = tempB[1].y;
            b[2].x = tempB[2].x;
            b[2].y = tempB[2].y;
            b[3].x = tempB[3].x;
            b[3].y = tempB[3].y;
        }
    }
    
    public void getDirection1(){}
    public void getDirection2(){}
    public void getDirection3(){}
    public void getDirection4(){}
    
    public void checkMovementCollision()
    {
        leftCollision = false;
        rightCollision = false;
        bottomCollision = false;
        
        //Checking the frame collision
        
        //left collision
        for (int i=0; i<b.length; i++)
        {
            if (b[i].x == PlayManager.left_x)
            {
                leftCollision = true;
            }
        }
        
        //right collision
        for (int i=0; i<b.length; i++)
        {
            if (b[i].x + Block.SIZE == PlayManager.right_x)
            {
                rightCollision = true;
            }
        }
        
        //bottom collision
        for (int i=0; i<b.length; i++)
        {
            if (b[i].y + Block.SIZE == PlayManager.bottom_y)
            {
                bottomCollision = true;
            }
        }
    }
    public void checkRotationCollision()
    {
        leftCollision = false;
        rightCollision = false;
        bottomCollision = false;
        
        //Checking the frame collision
        
        //left collision
        for (int i=0; i<b.length; i++)
        {
            if (tempB[i].x < PlayManager.left_x)
            {
                leftCollision = true;
            }
        }
        
        //right collision
        for (int i=0; i<b.length; i++)
        {
            if (tempB[i].x + Block.SIZE > PlayManager.right_x)
            {
                rightCollision = true;
            }
        }
        
        //bottom collision
        for (int i=0; i<b.length; i++)
        {
            if (tempB[i].y + Block.SIZE > PlayManager.bottom_y)
            {
                bottomCollision = true;
            }
        }
    }
    
    public void update()
    {
        if (KeyHandler.upPressed) //For rotating
        {
            switch(direction)
            {
                case 1:
                getDirection2();
                break;
                
                case 2:
                getDirection3();
                break;
                
                case 3:
                getDirection4();
                break;
                
                case 4:
                getDirection1();
                break;
            }
            KeyHandler.upPressed = false;
        }
        
        checkMovementCollision();
        
        if (KeyHandler.downPressed)
        {
            if (bottomCollision == false)
            {
                b[0].y += Block.SIZE;
                b[1].y += Block.SIZE;
                b[2].y += Block.SIZE;
                b[3].y += Block.SIZE;
                
                autoDropCounter = 0;
            }
            
            KeyHandler.downPressed = false;
        }
        
        if (KeyHandler.leftPressed)
        {
            if (leftCollision == false)
            {
                b[0].x -= Block.SIZE;
                b[1].x -= Block.SIZE;
                b[2].x -= Block.SIZE;
                b[3].x -= Block.SIZE;
            }    
            
            KeyHandler.leftPressed = false;
        }
        
        if (KeyHandler.rightPressed)
        {
            if (rightCollision == false)
            {
                b[0].x += Block.SIZE;
                b[1].x += Block.SIZE;
                b[2].x += Block.SIZE;
                b[3].x += Block.SIZE;
            }
            
            KeyHandler.rightPressed = false;            
        }
        
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
