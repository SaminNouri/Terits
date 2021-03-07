import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Arrays;

/**
 * Created by Data on 3/27/2020.
 */
public class TeritsListener  implements ActionListener,KeyListener {

    private TeritsBoard teritsBoard;
    int x=240,y=0,velx=0,vely=30;
    private Timer t=new Timer(270,this);
    public TeritsListener(){
        t.start();





    }


    public void actionPerformed(ActionEvent e) {
        //System.out.println(x+" "+velx);
        teritsBoard.isRowFull();
        if(teritsBoard.isBack()){

            teritsBoard.setCells(teritsBoard.getPreviousCells());
            x=240;
            teritsBoard.setX(240);
            y=0;
            teritsBoard.setY(0);
            teritsBoard.setBack(false);

        }


        if (!teritsBoard.isGameover() && teritsBoard.isStart()) {

            if (teritsBoard.inBoundsCurrentShape(teritsBoard.getCurrentShape())) {
                x=teritsBoard.getX();
                y=teritsBoard.getY();
                // System.out.println("first"+" "+currentIndex+" "+Arrays.asList(shapes).contains(currentShape));
                if (teritsBoard.inBoundsCurrentShapeXY(teritsBoard.getCurrentShape())) {
                    //System.out.println("second"+currentIndex+" "+Arrays.asList(shapes).contains(currentShape));

                    if (x + velx >= 0 && x + velx <= 360 && (y + vely <= 540) && y != 510) {

                        x += velx;
                        //System.out.println(x+" "+velx);
                        teritsBoard.setX(x);
                        teritsBoard.setVelx(velx);
                    }
                    teritsBoard.setVelx(0);
                    if (y + vely >= 0 && y + vely <= 540) {

                        y += vely;
                        teritsBoard.setY(y);
                        teritsBoard.setVely(vely);


                    } else {
                        // System.out.println("here1 "+currentIndex+" "+Arrays.asList(shapes).contains(currentShape));
                        teritsBoard.setCells(teritsBoard.getCurrentShape());
                        x = 240;
                        y = 0;
                        teritsBoard.setX(240);
                        teritsBoard.setY(0);
                        teritsBoard.setCurrentShape();
                    }
                } else {
                    //System.out.println("here2 "+currentIndex+" "+Arrays.asList(shapes).contains(currentShape));
                    teritsBoard.setCells(teritsBoard.getCurrentShape());
                    x = 240;
                    y = 0;
                    teritsBoard.setX(240);
                    teritsBoard.setY(0);
                    teritsBoard.setCurrentShape();


                }
            }

            velx = 0;
            teritsBoard.setVelx(0);
        }


    }



    public TeritsBoard getTeritsBoard() {
        return teritsBoard;
    }

    public void setTeritsBoard(TeritsBoard teritsBoard) {
        this.teritsBoard = teritsBoard;
        this.x=teritsBoard.getX();
        this.y=teritsBoard.getY();
        this.velx=teritsBoard.getVelx();
        this.vely=teritsBoard.getVely();
    }


    public void keyTyped(KeyEvent e) {

    }
    public void keyPressed(KeyEvent e) {

    }

    public void keyReleased(KeyEvent e) {

    }
}
