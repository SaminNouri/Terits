import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;

/**
 * Created by Data on 3/25/2020.
 */
public class RecordsPanel extends JPanel implements ActionListener{
    private ArrayList<Integer> records;
    public RecordsPanel(){
        setFocusable(true);
        setSize(new Dimension(495,600));
        setVisible(false);
        this.setLayout(new BorderLayout());
        this.setBorder(BorderFactory.createTitledBorder("records"));
    }

    public void paint(Graphics g) {

        super.paint(g);
        String imageURL = "extraFiles\\beutif.jpg";
        Image image = Toolkit.getDefaultToolkit().getImage(imageURL);
        // g.drawImage(image, 0, 0, this);
        String str=getString();
        g.setColor(Color.black);
        g.setFont(new Font(str, Font.BOLD, 20));
        g.drawString(str,50,50);
    }

    public ArrayList<Integer> getRecords() {
        return records;
    }

    public void setRecords(ArrayList<Integer> records) {
        this.records = records;

    }

    public String getString() {
        String str="";
        HashSet<Integer> set=new HashSet<Integer>();
        // Collections.sort(records,Collections.reverseOrder());
        // String str=records.toString();

        for(int num:records){
            set.add(num);
        }
        ArrayList<Integer> list=new ArrayList<Integer>();
        for(int num:set){
            list.add(num);
        }
        Collections.sort(list,Collections.reverseOrder());
        ArrayList<Integer> m=new ArrayList<Integer>();
        int counter=0;
        for(int num:list){
            m.add(num);
            counter++;
            if(counter==10)
                break;
        }
        return m.toString();

    }

    public void actionPerformed(ActionEvent e) {

    }
}
