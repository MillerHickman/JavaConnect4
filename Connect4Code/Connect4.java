import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.Graphics2D;
import java.util.*;
public class Connect4
{
    private final int FRAME_WIDTH = 750;
    private final int FRAME_HEIGHT = 700;
    private JFrame frame;
    private Player[] players;
    private int current;
    private Board board;
    public Connect4()
    {
        
		BufferedImage image = new BufferedImage(FRAME_WIDTH, FRAME_HEIGHT, 1); 
		Graphics2D g2d = image.createGraphics();
		g2d.setColor(new Color(0, 0, 255));
		g2d.fillRect(0, 0, FRAME_WIDTH, FRAME_HEIGHT);
		UI ui = new UI(image);		
		board = new Board(g2d, ui);
        players = new Player[2];
        players[0] = new Human(1, "Player 1", board);
        players[1] = new Computer(this, -1, "CPU", board);
		current = 0;
		frame = new JFrame();
		for(int column = 0; column < 7; column++)
        {
            Arrow x = new Arrow(board, column, this);
            ui.add(x);
        }
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(FRAME_WIDTH, FRAME_HEIGHT);
		frame.getContentPane().add(ui);
		frame.setVisible(true);	 
     }
    
    public Board getBoard()
    {
        return board;
    }
    
    public void newThread()
    {
    	GameThread newGT = new GameThread(this);
    	newGT.start();
    }
    
    public void switchCurrent()
    {
        if(current == 0)
        {
            current = 1;
        }
        else if(current == 1)
        {
            current = 0;
        }
    }
    public Player[] getPlayers()
    {
    	return players;
    }
    public Player getCurrentPlayer()
	{
		return players[current];
	}	
}