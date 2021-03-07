import java.awt.*;
import java.util.Arrays;

/**
 * Created by Data on 3/23/2020.
 */
public class TeritShape
{
    private int[][] parts;
    private Color color;
    private int[] X;
    private int[] Y;
    public TeritShape (int[][] num,Color color){
        this.color=color;
        this.parts=num;
        setXY();
    }

    private void setXY() {
        X=new int[parts.length];
        Y=new int[parts[0].length];
        for(int x=0;x<parts.length;x++){



            X[x]=x*30;





        }
        for(int y=0;y<parts[0].length;y++){


            Y[y]=y*30;



        }



    }

    public int[] getX() {
        return X;
    }

    public int[] getY() {
        return Y;
    }

    public void drawShape(Graphics g){

    }

    public void setY() {

    }

    public int[][] getParts() {
        return parts;
    }
    public int getParts(int x,int y) {
        return parts[x][y];
    }

    public Color getColor() {
        return color;
    }
}
