import javax.swing.*;
import java.awt.*;
import javax.imageio.ImageIO;
import java.awt.Graphics2D;
public class Node{
	private final int RADIUS = 50;
	private final int COLUMN_MULT = 100;
	private final int COLUMN_ADD = 50;
    private final int ROW_MULT = 100;
    private final int ROW_ADD = 100;
	private int value;
    private int column;
    private int row;
    private Graphics2D graphic;
	public Node(int row, int column, Graphics2D graphic)
	{
		this.graphic = graphic;
		this.row = row;
		this.column = column;
		value = 0;
		draw();
	}
	public Node(int row, int column)
	{
		this.row = row;
		this.column = column;
		value = 0;
	}
	public void setValue(Player player)
	{
		value = player.getId();
		draw();
	}
	public void setValue(int newValue)
	{
		value = newValue;
		draw();
	}
	public void simulateSetValue(int newValue)
	{
		value = newValue;
	}
    public int getRow()
    {
        return row;
    }
    public int getColumn()
    {
        return column;
    }
	public int getValue()
	{
		return value;
	}
	public void draw() 
    {
        if(value == 0)
        {
            graphic.setColor(Color.white);
        }
        if(value == 1)
        {
            graphic.setColor(Color.black);
        }
        if(value == -1)
        {
            graphic.setColor(Color.red);
        }
        graphic.fillOval((COLUMN_MULT * column) + COLUMN_ADD, (ROW_MULT * row) + ROW_ADD, RADIUS, RADIUS);
    }
}