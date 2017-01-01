import javax.swing.*;
import java.awt.*;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.*;
public class Board{
    private final int NUM_COLUMNS = 7;
    private final int NUM_ROWS = 6;
	private Node[][] board;
	private Node[][] simulateBoard;
	private int piecesPlaced;
	private int simulatePiecesPlaced;
	private int[] columns;
	private int[] simulateColumns;
	private ArrayList<Node> moves;
    private Node LastPlaced;
    private Node simulateLastPlaced;
	private Graphics2D graphic;
    private UI ui;
	public Board(Graphics2D graphic, UI ui)
	{
		moves = new ArrayList<Node>();
		this.ui = ui;
        piecesPlaced = 0;
        simulatePiecesPlaced = 0;
        LastPlaced = null;
        simulateLastPlaced = null;
		this.graphic = graphic;
		board = new Node[NUM_ROWS][NUM_COLUMNS];
		simulateBoard = new Node[NUM_ROWS][NUM_COLUMNS];
		columns = new int[NUM_COLUMNS];
		simulateColumns = new int[NUM_COLUMNS];
        for(int counter = 0; counter < columns.length; counter++)
        {
            columns[counter] = 5;
            simulateColumns[counter] = 5;
        }
		for(int x = 0; x < board.length; x++)
		{
			for(int y = 0; y < board[0].length; y++)
			{
				board[x][y] = new Node(x, y, graphic);
				simulateBoard[x][y] = new Node(x, y);
			}
		}
	}
	
	public Board()
	{
			columns = new int[NUM_COLUMNS];
			for(int counter = 0; counter < columns.length; counter++)
        	{
            	columns[counter] = 5;
        	}
	        LastPlaced = null;
	        this.ui = null;
        	piecesPlaced = 0;
        	LastPlaced = null;
			this.graphic = null;
			board = new Node[NUM_ROWS][NUM_COLUMNS];
	}
    
    public boolean isValidMove(int column, boolean simulate)
    {
    	if(simulate)
    	{
    		if(simulateColumns[column] != -1)
        	{
            	return true;
        	}
    	}
    	if(!simulate)
    	{
        	if(columns[column] != -1)
        	{
            	return true;
        	}
        }
        return false;
    }
	
	public void resetSimulate()
	{
		for(int x = 0; x < NUM_ROWS; x++)
		{
			for(int y = 0; y < NUM_COLUMNS; y++)
			{
				simulateBoard[x][y].simulateSetValue(board[x][y].getValue());
			}
			simulateColumns[x] = columns[x];
		}
		simulatePiecesPlaced = piecesPlaced;
		simulateLastPlaced = LastPlaced;
	}
	
    public int getRow(int column, boolean simulate)
    {
        int toReturn = -1;
        if(simulate)
        {
        	toReturn = simulateColumns[column];
        }
        if(!simulate)
        {
        	toReturn = columns[column];
        }
        if(toReturn == -1)
        {
            return -1;
        }
        return toReturn;
    }
    
	public void place(int column, Player player, boolean simulate)
	{
		int row = getRow(column, simulate);
		if(simulate)
		{
			simulateBoard[row][column].simulateSetValue(player.getId());
        	simulateColumns[column]--;
        	simulatePiecesPlaced++;
        	simulateLastPlaced = simulateBoard[row][column];
		}
		else
		{
			LastPlaced = board[row][column];
			board[row][column].setValue(player);
        	columns[column]--;
        	piecesPlaced++;
        	if(ui != null)
       	    {
        		ui.repaint();
       	 	    ui.revalidate();
       		}
       	}
	}
	
    public boolean isFull(boolean simulate)
    {
    	int toCheck = 0;
    	if(simulate)
    	{
    		toCheck = piecesPlaced;
    	}
    	if(!simulate)
    	{
    		toCheck = simulatePiecesPlaced;
    	}
        if(toCheck == 42)
        {
            return true;
        }
        return false;
    }
	
	public boolean checkWin(boolean simulate)
	{
        int row = 0;
        int column = 0; 
        if(simulate)
        {
        	row = simulateLastPlaced.getRow();
        	column = simulateLastPlaced.getColumn();
        }
        if(!simulate)
        {
        	row = LastPlaced.getRow();
        	column = LastPlaced.getColumn();
        }
        int value = LastPlaced.getValue();
        //cheks for horizontal win
		if((checkWinHelper(row, column, 0, 1, 0, value, simulate) +  checkWinHelper(row, column, 0, -1, 0, value, simulate)) >= 3)
		{
			return true;
		}
        //checks for diagonal to the right and up and the left and down
		if((checkWinHelper(row, column, 1, 1, 0, value, simulate) +  checkWinHelper(row, column, -1, -1, 0, value, simulate)) >= 3)
		{
			return true;
		}
        //checks diagonals for light and down and left and up
		if((checkWinHelper(row, column, -1, 1, 0, value, simulate) +  checkWinHelper(row, column, 1, -1, 0, value, simulate)) >= 3)
		{
			return true;
		}
        //checks for win down a column
		if(checkWinHelper(row, column, 1, 0, 0, value, simulate) >= 3)
		{
			return true;
		}
		return false;
	}
	
	public int checkWinHelper(int row, int column, int rowAdd, int columnAdd, int howManyFound, int value, boolean simulate)
	{
		Node[][] toCheck = null;
		if(simulate)
		{
			toCheck = simulateBoard;
		}
		if(!simulate)
		{
			toCheck = board;
		}
		int rowCheck = row + rowAdd;
		int columnCheck = column + columnAdd;
		int toReturn = 0;
		int checksLeft = 4;
		if(columnCheck > 6 ||  columnCheck < 0 || rowCheck > 5 ||  rowCheck < 0)
		{
			return howManyFound;
		}
		if(toCheck[rowCheck][columnCheck].getValue() == value)
		{
			howManyFound++;
			return checkWinHelper(rowCheck, columnCheck, rowAdd, columnAdd, howManyFound, value, simulate);
		}
		return howManyFound;
	}
	
	public Node[][] getBoard()
	{
		return board;
	}
	
	public void setBoard(Node[][] newBoard)
	{
		board = newBoard;
	}
	
	public void setPiecesPlaced(int newPieces)
	{
		piecesPlaced = newPieces;
	}
	public void setColumns(int[] newColumns)
	{
		columns = newColumns;
	}
}