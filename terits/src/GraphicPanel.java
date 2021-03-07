import org.omg.CORBA.PUBLIC_MEMBER;

import javax.swing.*;
import javax.swing.plaf.synth.SynthTextAreaUI;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.beans.PropertyChangeListener;
import java.io.BufferedReader;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

/**
 * Created by Data on 3/22/2020.
 */
public class GraphicPanel extends JPanel implements ActionListener,KeyListener
{
    private TeritsBoard teritsBoard;
    private TeritsListener teritsListener;
    private Timer t=new Timer(1,this);

    public GraphicPanel(){
        teritsBoard=new TeritsBoard();
        teritsListener=new TeritsListener();
        teritsListener.setTeritsBoard(teritsBoard);
        t.start();
        setFocusable(true);
        setSize(new Dimension(450,600));
        setVisible(true);
        addKeyListener(teritsListener);
        setLayout(new BorderLayout());

    }

    public void paint(Graphics g){
        int[] X=teritsBoard.getCurrentShape().getX();
        int[] Y=teritsBoard.getCurrentShape().getY();
        super.paint(g);
        String imageURL = "extraFiles\\beutif.jpg";
        Image image = Toolkit.getDefaultToolkit().getImage(imageURL);
        g.drawImage(image, 0, 0, this);
        for(int row=0;row<=390;row+=30) {

            for (int col = 0; col <= 570; col += 30) {

                g.setColor(Color.BLACK);
                g.drawLine(0, col, 390, col);
                g.drawLine(row, 0, row, 570);


            }
        }

        g.fillRect(395,380,100,30);
        g.setColor(Color.WHITE);
        g.setFont(new Font("score:"+teritsBoard.getScore(), Font.BOLD, 20));
        g.drawString("score:"+teritsBoard.getScore(),400,400);
        g.setColor(Color.black);

        g.fillRect(395,430,100,30);
        g.setColor(Color.WHITE);
        g.setFont(new Font("lines : "+teritsBoard.getDl(), Font.BOLD, 20));
        g.drawString("lines :"+teritsBoard.getDl(),400,450);

        for(int row=0;row<=360;row+=30){

            for(int col=0;col<=540;col+=30){
                if(teritsBoard.getCells()[row/30][col/30]!=null) {

                    drawRec(row,col,teritsBoard.getCells()[row/30][col/30],g);
                }





            }




        }


        t.start();
        for(int col=0; col<X.length;col++){
            for(int row=0;row<Y.length;row++){
                int xd = X[col] + teritsBoard.getX();
                int yd = Y[row] + teritsBoard.getY();
                Color c=teritsBoard.getCurrentShape().getColor();
                if(teritsBoard.getCurrentShape().getParts(col,row)!=0)
                {
                    drawRec(xd,yd,c,g);


                }


            }
        }

        for(int col=0; col<X.length;col++) {
            for (int row = 0; row < Y.length; row++) {
                int xd = X[col] + teritsBoard.getX();
                int yd = Y[row] + teritsBoard.getY();
                Color c = teritsBoard.getCurrentShape().getColor();
                if (teritsBoard.getCurrentShape().getParts(col, row) != 0) {


                    if (teritsBoard.getCells()[xd / 30][yd / 30] != null) {
                        if(!teritsBoard.getRecords().contains(teritsBoard.getScore()))
                            teritsBoard.getRecords().add(teritsBoard.getScore());
                        g.setFont(new Font("Game Over", Font.BOLD, 50));
                        g.drawString("Game Over!", 100, 300);
                        teritsBoard.setGameover(true);
                        teritsListener.setTeritsBoard(teritsBoard);
                    }




                }
            }


        }}

    public void drawRec(int x,int y,Color c,Graphics g){
        g.setColor(c);
        g.fillRect(x,y,30,30);
        g.setColor(Color.BLACK);
        g.drawRect(x,y,30,30);

    }


    public TeritsListener getTeritsListener() {
        return teritsListener;
    }

    public void setTeritsListener(TeritsListener teritsListener) {
        this.teritsListener = teritsListener;
    }

    public void actionPerformed(ActionEvent e) {
        repaint();
        InputMap imap=this.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
        imap.put(KeyStroke.getKeyStroke(KeyEvent.VK_UP,0,false),("rotate"));
        imap.put(KeyStroke.getKeyStroke(KeyEvent.VK_RIGHT,0,false),("right"));
        imap.put(KeyStroke.getKeyStroke(KeyEvent.VK_LEFT,0,false),("left"));
        ActionMap amap=this.getActionMap();
        amap.put("rotate", new AbstractAction() {

            public void actionPerformed(ActionEvent e) {
                if(Arrays.asList(teritsBoard.getShapes()).contains(teritsBoard.getCurrentShape()))
                {
                    setTeritsBoard(teritsListener.getTeritsBoard());
                    if(teritsBoard.inBoundsCurrentShape(teritsBoard.getRotatedShapes()[teritsBoard.getCurrentIndex()]) && teritsBoard.inBoundsCurrentShapeXY(teritsBoard.getRotatedShapes()[teritsBoard.getCurrentIndex()] ) ){

                        teritsBoard.setCurrentShape((teritsBoard.getRotatedShapes()[teritsBoard.getCurrentIndex()]));
                        teritsListener.setTeritsBoard(teritsBoard);
                        repaint();
                    }}
                else if(Arrays.asList(teritsBoard.getRotatedShapes()).contains(teritsBoard.getCurrentShape()))
                {
                    setTeritsBoard(teritsListener.getTeritsBoard());
                    if(teritsBoard.inBoundsCurrentShape(teritsBoard.getShapes()[teritsBoard.getCurrentIndex()]) && teritsBoard.inBoundsCurrentShapeXY(teritsBoard.getShapes()[teritsBoard.getCurrentIndex()] ) ){

                        teritsBoard.setCurrentShape((teritsBoard.getShapes()[teritsBoard.getCurrentIndex()]));
                        teritsListener.setTeritsBoard(teritsBoard);
                        repaint();
                    }}
                else
                {

                }

            }
        });
        amap.put("right", new AbstractAction() {

            public void actionPerformed(ActionEvent e) {
                teritsBoard.setVelx(30);
                teritsListener.setTeritsBoard(teritsBoard);
                if(!(teritsBoard.inBoundsCurrentShape(teritsBoard.getCurrentShape()) && teritsBoard.inBoundsCurrentShapeXY(teritsBoard.getCurrentShape()))) {
                    teritsBoard.setVelx(0);
                    teritsListener.setTeritsBoard(teritsBoard);
                }
            }
        });
        amap.put("left", new AbstractAction() {

            public void actionPerformed(ActionEvent e) {
                teritsBoard.setVelx(-30);
                teritsListener.setTeritsBoard(teritsBoard);
                if(!(teritsBoard.inBoundsCurrentShape(teritsBoard.getCurrentShape()) && teritsBoard.inBoundsCurrentShapeXY(teritsBoard.getCurrentShape()))) {
                    teritsBoard.setVelx(0);
                    teritsListener.setTeritsBoard(teritsBoard);
                }
            }
        });
        setTeritsBoard(teritsListener.getTeritsBoard());


    }

    public TeritsBoard getTeritsBoard() {
        return teritsBoard;
    }

    public void setTeritsBoard(TeritsBoard teritsBoard) {
        this.teritsBoard = teritsBoard;
    }


    public void keyTyped(KeyEvent e) {

    }

    public void keyPressed(KeyEvent e) {

    }

    public void keyReleased(KeyEvent e) {

    }
}