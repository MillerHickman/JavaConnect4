public abstract class Player{
	public int id;
	public String name;
    public Board board;
    public boolean computer;
    public int nextMove;
	public int getId()
	{
		return id;
	}
	public String getName()
	{
		return name;
	}
    public boolean isComputer()
    {
        return computer;
    }
	public abstract int action();
	
	public void setNextMove(int move)
    {
        nextMove = move;
    }
}