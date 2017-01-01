import javax.swing.*;
import java.awt.*;
public class PopupWindow extends JFrame
{
	private final int FRAME_WIDTH = 200;
	private final int FRAME_HEIGHT = 200;
    public PopupWindow(String text)
    {
        JPanel panel = new JPanel();
        JLabel label = new JLabel(text);
        panel.add(label);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(FRAME_WIDTH, FRAME_HEIGHT);
		this.getContentPane().add(panel);
		this.setVisible(true);	 
    }
}