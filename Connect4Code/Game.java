import javax.swing.*;
import java.awt.*;
public class Game implements Runnable{
    public void run()
    {
          JFrame.setDefaultLookAndFeelDecorated(true);
          new Connect4();
    }	
}