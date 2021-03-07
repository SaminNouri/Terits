import org.omg.CORBA.PUBLIC_MEMBER;

import javax.swing.*;
import javax.swing.plaf.synth.SynthTextAreaUI;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

/**
 * Created by Data on 3/22/2020.
 */
public class TeritsBoard
{
    private int currentIndex=0;
    private int score=-1;
    private int stepCounter=0;
    private TeritShape currentShape;
    private  TeritShape[] shapes;
    private TeritShape[] rotatedShapes;
    private int x=240,y=0,velx=0,vely=30;
    private Color[][] cells=new Color[13][19];
    private boolean start=false;
    private boolean gameover=false;
    private ArrayList<Color[][]> steps;
    private boolean back=false;
    private TeritShape previousShape;
    private Color[][] previousCells;
    private ArrayList<Integer> records;
    private boolean rotated=false;
    private int dl=0;

    public boolean isBack() {
        return back;
    }

    public void setShapes(TeritShape[] shapes) {
        this.shapes = shapes;
    }

    public TeritShape[] getShapes() {
        return shapes;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getVely() {
        return vely;
    }

    public int getVelx() {
        return velx;
    }

    public TeritShape[] getRotatedShapes() {
        return rotatedShapes;
    }

    public int getDl() {
        return dl;
    }

    public ArrayList<Color[][]> getSteps() {
        return steps;
    }

    public void setSteps(ArrayList<Color[][]> steps) {
        this.steps = steps;
    }

    public TeritShape getPreviousShape() {
        return previousShape;
    }

    public void setPreviousShape(TeritShape previousShape) {
        this.previousShape = previousShape;
    }

    public Color[][] getPreviousCells() {
        return previousCells;
    }

    public void setPreviousCells(Color[][] previousCells) {
        this.previousCells = previousCells;
    }

    public void setDl(int dl) {
        this.dl = dl;
    }

    public void setRotatedShapes(TeritShape[] rotatedShapes) {
        this.rotatedShapes = rotatedShapes;
    }

    public void setVelx(int velx) {
        this.velx = velx;
    }

    public void setVely(int vely) {
        this.vely = vely;
    }

    public void setBack(boolean back) {
        this.back = back;

    }

    public void setStart(boolean start) {
        this.start = start;

    }

    public void setGameover(boolean gameover) {
        this.gameover = gameover;

    }

    public  TeritsBoard(){
        if(records==null)
            records=new ArrayList<>();

        steps=new ArrayList<>();
        Arrays.asList(new int[]{});
        shapes=new TeritShape[7];
        rotatedShapes=new TeritShape[7];
        setShapes();
        setCurrentShape();

    }





    public boolean inBoundsCurrentShape(TeritShape currentShape){
        int[] X=currentShape.getX();
        int[] Y=currentShape.getY();
        boolean b=false;
        for(int col=0; col<X.length;col++){
            for(int row=0;row<Y.length;row++){
                int xd=X[col]+x;
                int yd=Y[row]+y;

                Color c=currentShape.getColor();
                if(currentShape.getParts(col,row)!=0)
                {
                    //System.out.println("col: "+col+"row: "+row+"xd: "+xd+"yd: "+yd+" ");

                    if(!(xd+velx>=0 && xd+velx<=360 ) ) {
                        //System.out.println("col: "+col+"row: "+row+"xd: "+xd+"yd: "+yd+" f1"+" velx:"+velx);
                        return false;
                    }
                    if(!(yd+vely>=0 && yd+vely<=570) ) {

                        return false;
                    }
                    if( yd==540)
                    {
                        //System.out.println(yd);
                        //System.out.println(Arrays.toString(currentShape.getX()));
                        // System.out.println(Arrays.toString(currentShape.getY()));
                        b=true;
                        ;
                    }


                }


            }
        }
        if(b==true)
        {
            b=false;
            //System.out.println(Arrays.toString(currentShape.getX())+" "+Arrays.toString(currentShape.getY())+" "+y);
            this.currentShape=currentShape;
            setCells(currentShape);
            x = 240;
            y = 0;
            setCurrentShape();
        }

        return true;


    }






    public void setCurrentShape() {
        score++;
        //scoreListener.setScore(score);
        Color[][] c=makeACopy(cells);
        previousCells=c;
        previousShape=currentShape;
        Random r = new Random();
        currentIndex= r.nextInt(7) + 0;
        currentShape=shapes[currentIndex];
    }
    public Color[][] makeACopy(Color[][] color){
        Color[][] copy=new Color[color.length][color[0].length];
        for(int r=0;r<cells.length;r++){
            for(int c=0;c<cells[0].length;c++){

                copy[r][c]=color[r][c];

            }

        }
        return copy;


    }

    private void setShapes(){
        this.shapes[0]=new TeritShape(new int[][]{{1,1,1,1}},new Color(191,255,0));
        this.rotatedShapes[0]=new TeritShape(new int[][]{{1},{1},{1},{1}},new Color(191,255,0));
        this.shapes[1]=new TeritShape(new int[][]{{0,1},{1,1},{1,0}},new Color(60,196,70));
        this.rotatedShapes[1]=new TeritShape(new int[][]{{1,1,0},{0,1,1}},new Color(60,196,70));
        this.shapes[2]=new TeritShape(new int[][]{{1,1,1},{0,0,1}},new Color(0,255,148));
        this.rotatedShapes[2]=new TeritShape(new int[][]{{0,1},{0,1},{1,1}},new Color(0,255,148));
        this.shapes[3]=new TeritShape(new int[][]{{1,0},{1,1},{0,1}},new Color(24,136,4));
        this.rotatedShapes[3]=new TeritShape(new int[][]{{0,1,1},{1,1,0}},new Color(24,136,4));
        this.shapes[4]=new TeritShape(new int[][]{{0,0,1},{1,1,1}},new Color(41,233,7));
        this.rotatedShapes[4]=new TeritShape(new int[][]{{1,0},{1,0},{1,1}},new Color(41,233,7));
        this.shapes[5]=new TeritShape(new int[][]{{0,1},{1,1},{1,0}},Color.YELLOW);
        this.rotatedShapes[5]=new TeritShape(new int[][]{{1,1,0},{0,1,1}},Color.YELLOW);
        this.shapes[6]=new TeritShape(new int[][]{{0,1},{1,1},{0,1}},new Color(154,248,16));
        this.rotatedShapes[6]=new TeritShape(new int[][]{{0,1,0},{1,1,1}},new Color(154,248,16));



    }

    public boolean isStart() {
        return start;
    }


    public void isRowFull(){


        for(int row=540;row>=0;row-=30) {
            boolean flag=true;
            for (int col = 0; col <= 360; col += 30) {
                if (cells[col / 30][row / 30] == null) {

                    flag=false;

                }


            }
            if(flag==true){
                // scoreListener.setScore(score);
                setFulledCells(row);
            }
        }






    }
    public void setFulledCells(int r){
        dl++;
        score+=10;
        for(int row=r;row>=30;row-=30) {
            for (int col = 0; col <= 360; col += 30) {
                if (cells[col / 30][row / 30] != null) {


                    cells[col/30][row/30]=cells[col/30][(row/30)-1];

                }


            }

        }


    }




    public void drawRec(int x,int y,Color c,Graphics g){
        g.setColor(c);
        g.fillRect(x,y,30,30);
        g.setColor(Color.BLACK);
        g.drawRect(x,y,30,30);

    }



    public boolean inBoundsCurrentShapeXY(TeritShape currentShape){
        if(!inBoundsCurrentShape(currentShape))
            return false;
        int[] X=currentShape.getX();
        int[] Y=currentShape.getY();
        for(int col=0; col<X.length;col++){
            for(int row=0;row<Y.length;row++){
                int xd=X[col]+x;
                int yd=Y[row]+y;

                Color c=currentShape.getColor();
                if(currentShape.getParts(col,row)!=0)
                {
                    //System.out.println(xd+" "+yd+" "+velx+" "+vely);

                    if (cells[(xd+velx ) / 30][(yd+vely ) / 30] != null) {
                        return false;
                    }



                }


            }
        }


        return true;


    }

    public boolean inBoundsCurrentShapeY(TeritShape currentShape){
        int[] X=currentShape.getX();
        int[] Y=currentShape.getY();
        boolean b=false;
        for(int col=0; col<X.length;col++){
            for(int row=0;row<Y.length;row++){
                int xd=X[col]+x;
                int yd=Y[row]+y;

                Color c=currentShape.getColor();
                if(currentShape.getParts(col,row)!=0)
                {
                    //System.out.println("col: "+col+"row: "+row+"xd: "+xd+"yd: "+yd+" ");


                    if(!(yd+vely>=0 && yd+vely<=570) ) {

                        return false;
                    }
                    if( yd==540)
                    {
                        //System.out.println(Arrays.toString(currentShape.getX()));
                        // System.out.println(Arrays.toString(currentShape.getY()));
                        b=true;
                        ;
                    }


                }


            }
        }
        if(b==true)
        {
            b=false;
            //System.out.println(Arrays.toString(currentShape.getX())+" "+Arrays.toString(currentShape.getY())+" "+y);
            this.currentShape=currentShape;
            setCells(currentShape);
            x = 240;
            y = 0;
            setCurrentShape();
        }

        return true;


    }






    public boolean inBoundsCurrentShapeX(TeritShape currentShape){
        int[] X=currentShape.getX();
        int[] Y=currentShape.getY();
        boolean b=false;
        for(int col=0; col<X.length;col++){
            for(int row=0;row<Y.length;row++){
                int xd=X[col]+x;
                int yd=Y[row]+y;

                Color c=currentShape.getColor();
                if(currentShape.getParts(col,row)!=0)
                {
                    //System.out.println("col: "+col+"row: "+row+"xd: "+xd+"yd: "+yd+" ");

                    if(!(xd+velx>=0 && xd+velx<=360 ) ) {
                        //System.out.println("col: "+col+"row: "+row+"xd: "+xd+"yd: "+yd+" f1"+" velx:"+velx);
                        return false;
                    }



                }


            }
        }
        return true;


    }

    public boolean isSetCellsAllowed(TeritShape currentShape){
        int[] X=currentShape.getX();
        int[] Y=currentShape.getY();
        //System.out.println(Arrays.toString(X)+" "+Arrays.toString(Y)+" "+"here");
        for(int col=0; col<X.length;col++){
            for(int row=0;row<Y.length;row++){
                int xd=X[col]+x;
                int yd=Y[row]+y;

                Color c=currentShape.getColor();
                if(currentShape.getParts(col,row)!=0 )
                {
                    if(cells[xd/30][yd/30]!=null)
                        return false;



                }


            }
        }

        return true;



    }





    public boolean setCells(TeritShape currentShape){
        if(!isSetCellsAllowed(currentShape)) {
            if(Arrays.asList(shapes).contains(currentShape))
                currentShape=rotatedShapes[currentIndex];
            if(Arrays.asList(rotatedShapes).contains(currentShape))
                currentShape=shapes[currentIndex];

            return false;

        }

        this.currentShape=currentShape;
        int[] X=currentShape.getX();
        int[] Y=currentShape.getY();
        // System.out.println(Arrays.toString(X)+" "+Arrays.toString(Y)+" "+"here");
        for(int col=0; col<X.length;col++){
            for(int row=0;row<Y.length;row++){
                int xd=X[col]+x;
                int yd=Y[row]+y;

                Color c=currentShape.getColor();
                if(currentShape.getParts(col,row)!=0 )
                {
                    if(cells[xd/30][yd/30]!=null)
                        return false;

                    cells[xd/30][yd/30]=currentShape.getColor();
                    //System.out.println(xd+" "+yd+" "+cells[xd/30][yd/30]);

                }


            }
        }
        isRowFull();

        return true ;


    }

    public int getCurrentIndex() {
        return currentIndex;
    }

    public void setCurrentIndex(int currentIndex) {
        this.currentIndex = currentIndex;
        currentShape=shapes[currentIndex];
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public TeritShape getCurrentShape() {
        return currentShape;
    }

    public void setCurrentShape(TeritShape currentShape) {
        this.currentShape = currentShape;
    }

    public Color[][] getCells() {
        return cells;
    }

    public void setCells(Color[][] cells) {
        this.cells = cells;
    }

    public boolean isGameover() {
        return gameover;
    }

    public ArrayList<Integer> getRecords() {
        return records;
    }

    public void setRecords(ArrayList<Integer> records) {
        this.records = records;
    }
}
