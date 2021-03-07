import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;
import java.awt.*;
import java.awt.event.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.IOException;

/**
 * Created by Data on 3/23/2020.
 */
public class PropertyPanel extends JPanel implements KeyListener,ActionListener
{
    private Timer t=new Timer(1,this);
    private JButton start;
    private JButton back;
    private JButton music;
    private JButton mute;
    private JButton lastGame;
    private JButton highScores;
    private  SimpleAudioPlayer audioPlayer;
    private BooleanListener booleanListener;
    private PreviousGame previousGame;




    public PropertyPanel(){
        setFocusable(false);
        highScores=new JButton("   Rescords!   ");
        music=new JButton("Play Music");
        start=new JButton("Start Game!");
        back=new JButton("     Back      ");
        mute=new JButton("Mute Music");
        lastGame=new JButton("Previous Game");
        start.setFocusable(false);
        back.setFocusable(false);
        setLayout(new BorderLayout());
        setFocusable(true);
        setSize(new Dimension(200,600));
        setVisible(true);
        addKeyListener(this);
        start.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                booleanListener.setStart(true);
                setFocusable(false);
                start.setFocusable(false);
                back.setFocusable(false);

            }
        });
        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                booleanListener.setBack(true);
                setFocusable(false);
                start.setFocusable(false);
                back.setFocusable(false);

            }
        });
        music.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{

                    audioPlayer =
                            new SimpleAudioPlayer();

                    audioPlayer.play();

                }catch(Exception e1){e1.printStackTrace();}
            }
        });
        mute.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    audioPlayer.stop();
                } catch (Exception e1) {}

            }
        });
        lastGame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                previousGame.setBoard(true);
            }
        });
        highScores.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                booleanListener.setRecords(true);
            }
        });



        this.setLayout(new GridBagLayout());
        GridBagConstraints gc=new GridBagConstraints();
        gc.gridx = 0;
        gc.gridy = 0;
        gc.weightx = 2D;
        gc.weighty = 0.01D;
        gc.insets=new Insets(2,0,0,0);
        gc.anchor = GridBagConstraints.FIRST_LINE_END    ;
        add(start, gc);
        gc.gridx = 0;
        gc.gridy = 1;
        gc.weightx = 2D;
        gc.weighty = 0.01D;
        gc.insets=new Insets(2,0,0,0);
        gc.anchor = GridBagConstraints.FIRST_LINE_END   ;
        add(back, gc);
        gc.gridx = 0;
        gc.gridy = 2;
        gc.weightx = 2D;
        gc.weighty = 0.01D;
        gc.insets=new Insets(2,0,0,0);
        gc.anchor = GridBagConstraints.FIRST_LINE_END  ;
        add(music, gc);
        gc.gridx = 0;
        gc.gridy = 3;
        gc.weightx = 2D;
        gc.weighty = 0.01D;
        gc.insets=new Insets(2,0,0,0);
        gc.anchor = GridBagConstraints.FIRST_LINE_END  ;
        add(mute, gc);
        gc.gridx = 0;
        gc.gridy = 4;
        gc.weightx = 2D;
        gc.weighty = 0.01D;
        gc.insets=new Insets(2,0,0,0);
        gc.anchor = GridBagConstraints.FIRST_LINE_END  ;
        add(lastGame, gc);
        gc.gridx = 0;
        gc.gridy = 5;
        gc.gridwidth=100;
        gc.weightx = 2D;
        gc.weighty = 0.01D;
        gc.insets=new Insets(2,0,0,0);
        gc.anchor = GridBagConstraints.FIRST_LINE_END  ;
        add(highScores, gc);

        setBorder(BorderFactory.createTitledBorder("terits"));



    }



    public void setFocusable(){
        start.setFocusable(false);
        back.setFocusable(false);
    }

    @Override
    public void actionPerformed(ActionEvent e) {


    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    public void setBooleanListener(BooleanListener booleanListener) {
        this.booleanListener = booleanListener;
    }

    public void setPreviousGame(PreviousGame previousGame) {
        this.previousGame = previousGame;
    }
}
