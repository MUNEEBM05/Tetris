import java.awt.*;

//The class for the shape: ([] means a block)
/*     [][]
 *     [][]
 *     
 */   

public class Mino_Square extends Mino
{
   public Mino_Square()
    {
        create(Color.yellow);
    }
    
    public void setXY(int x, int y)
    {
        /*     
         *     o o
         *     o o
         */
        //Chose b[0] in centre as the shape can rotate and centre remains unchanged
        b[0].x = x;
        b[0].y = y;
        b[1].x = b[0].x;
        b[1].y = b[0].y + Block.SIZE;
        b[2].x = b[0].x + Block.SIZE;
        b[2].y = b[0].y;
        b[3].x = b[0].x + Block.SIZE;
        b[3].y = b[0].y + Block.SIZE;
    }
    //Square always looks the same when rotated so no change required
    public void getDirection1(){}
    
    public void getDirection2(){}
    
    public void getDirection3(){}
    
    public void getDirection4(){}
    
}
