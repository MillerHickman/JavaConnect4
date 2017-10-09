import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.*;
import javax.imageio.ImageIO;
public class Arrow extends JButton 
{
 	private final int COLUMN_ADD = 60;
    private final int COLUMN_MULT = 100;
    private final int ROW_LOCATION = 40;
    private Board board;
    private int column;
    private Connect4 game;
	public Arrow(Board board_, int column_, Connect4 game_) 
	{
		super();
        game = game_;
        board = board_;
        column = column_;
        BufferedImage bi = null;
        try
		{
			//bi = ImageIO.read(new File("/SMB/13699/hs2.lan.summit.k12.nj.us/students/13699/Desktop/Arrow.png"));
	
bi = ImageIO.read(new File("Arrow.png"));	}
		catch(IOException e)
		{
            System.out.println("could not read image in");
		}
        ImageIcon image = new ImageIcon(bi);
        this.setIcon(image);
        this.setEnabled(true);
        this.setBounds((COLUMN_MULT * column) + COLUMN_ADD ,ROW_LOCATION, 30, 30);
        this.addActionListener(new ActionListener() { 
        public void actionPerformed(ActionEvent e) { 
            if(board.isValidMove(column, false))
            {
                game.getCurrentPlayer().setNextMove(column);
                game.newThread();
            }
            else
            {
                PopupWindow window = new PopupWindow("This move is invalid.");
            }
        } 
        });
    }
    
    
}