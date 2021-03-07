import java.awt.event.ActionListener;
import java.util.EventListener;


public interface BooleanListener extends EventListener{
    public void setStart(boolean b);
    public void setBack(boolean b);
    public void setRecords(boolean b);
    public void setBackGame(boolean b);
}
