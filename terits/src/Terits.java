import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.PathIterator;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by Data on 3/22/2020.
 */
public class Terits extends JFrame
{
    private PropertyPanel p;
    private GraphicPanel g;
    private TeritsFileManager fileManager;
    private RecordsPanel r;
    public Terits(){
        fileManager=new TeritsFileManager();
        r=new RecordsPanel();
        setFocusable(true);
        setSize(new Dimension(630,600));
        setVisible(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        setResizable(false);
        setLayout(new BorderLayout());
        this.p=new PropertyPanel();
        this.g=new GraphicPanel();
        add(g,BorderLayout.CENTER);
        add(p,BorderLayout.EAST);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                if(g.getTeritsBoard().isStart()==true)
                    fileManager.SaveFiles(g.getTeritsBoard().getRecords(),g.getTeritsBoard().getCells(),g.getTeritsBoard().getCurrentIndex(),g.getTeritsBoard().getScore());
                System.exit(0);


            }
        });
        p.setPreviousGame(new PreviousGame() {

            public void setBoard(boolean b) {
                fileManager.getPropertyMap();
                g.getTeritsBoard().setScore(fileManager.getScore());
                g.getTeritsBoard().setCells(fileManager.getCells());
                g.getTeritsBoard().setRecords(fileManager.getHighestScores());
                g.getTeritsBoard().setCurrentIndex(fileManager.getCurrentShapeIndex());
                g.getTeritsBoard().setStart(true);
            }
        });

        p.setBooleanListener(new BooleanListener() {


            public void setStart(boolean b) {
                fileManager.getPropertyMap();
                p.setFocusable(false);
                p.setFocusable(false);
                g.setFocusable(true);
                g.getTeritsBoard().setStart(b);
                p.setFocusable();
                p.setFocusable();
                p.setFocusable(false);
                g.getTeritsBoard().setRecords(fileManager.getHighestScores());
                r.setRecords(g.getTeritsBoard().getRecords());
                g.getTeritsBoard().setStart(true);
                g.getTeritsBoard().setGameover(false);


            }


            public void setBack(boolean b) {
                p.setFocusable();
                p.setFocusable();
                p.setFocusable();
                p.setFocusable();
                p.setFocusable(false);
                g.getTeritsBoard().setBack(true);
                g.getTeritsListener().getTeritsBoard().setBack(true);


            }


            public void setRecords(boolean b) {

                ArrayList<Integer> v=new ArrayList<Integer>();
                ArrayList<Integer> c=new ArrayList<Integer>();
                fileManager.getPropertyMap();
                g.getTeritsBoard().setRecords(fileManager.getHighestScores());
                v=fileManager.getHighestScores();
                c= g.getTeritsListener().getTeritsBoard().getRecords();
                //System.out.println(c+" "+v);
                c.addAll(v);
                c.add(g.getTeritsBoard().getScore());
                //System.out.println(c);
                g.getTeritsListener().getTeritsBoard().setRecords(c);
                g.setVisible(false);
                //System.out.println(fileManager.getHighestScores()+" m");
                r.setRecords(g.getTeritsBoard().getRecords());
                r.setVisible(true);


            }


            public void setBackGame(boolean b) {
                r.setVisible(false);
            }
        });
        r.setVisible(false);
        add(r,BorderLayout.CENTER);
        add(g,BorderLayout.CENTER);
        add(p,BorderLayout.EAST);


    }


    public static void main(String[] args){
        Terits terits=new Terits();

    }

}
