import javax.swing.*;
import java.awt.*;
import javax.swing.GroupLayout;
import java.awt.image.BufferedImage;
public class UI extends JPanel
{
    private final int WIDTH = 750;
    private final int HEIGHT = 700;
	private BufferedImage image;
	public UI(BufferedImage image)
	{
		this.image = image;
		this.setLayout(null);
	}
	public UI()
	{
	}
	
	@Override
    public void paintComponent(Graphics g) 
    {
        super.paintComponent(g);
		g.drawImage(image, 0, 0, WIDTH, HEIGHT, this);
    }
    
}
    