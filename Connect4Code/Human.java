public class Human extends Player
{
	public Human(int id, String name, Board board)
	{
        nextMove = 0;
        computer = false;
		this.id = id;
		this.name = name;
        this.board = board;
	}
	public int action()
	{
        return nextMove;
	}
    public void setNextMove(int move)
    {
        nextMove = move;
    }
}