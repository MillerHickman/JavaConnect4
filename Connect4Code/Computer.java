public class Computer extends Player
{
    private final int AI_IDENTIFIER = -1;
    private final int OPONENT_IDENTIFIER = 1;
    private Connect4 game;
	public Computer(Connect4 game, int id, String name, Board board)
	{
		this.game = game;
        computer = true;
		this.id = id;
		this.name = name;
        this.board = board;
	}
	public int action()
	{
		int currentPlayer = 1;
        return findMove(game.getPlayers()[currentPlayer], 1, currentPlayer);
	}
    
    private int findMove(Player player, int depth, int currentPlayer)
    { 
        ScoreTracker one = new ScoreTracker((double)(player.getId() * -7), player.getId() * -1);
        ScoreTracker two = new ScoreTracker();
        int bestMove = -1; 
        int drop = 0;
        for(drop = 0; drop < 7; drop++)
        { 
            board.resetSimulate();
            if(board.isValidMove(drop, true) == true) 
            {    
                 board.place(drop, player, true);
                 if(board.checkWin(true) == true)
                 { 
                   	 return drop; 
               	 }
            }
        }
        for(drop = 0; drop < 7; drop++)
        {
            board.resetSimulate();
            if(board.isValidMove(drop, true) == true) 
            {    
                 board.place(drop, player, true);
            }
            else
            {
                continue;
            }
            if(currentPlayer == 1)
            {
                currentPlayer = 0;
            }
            else if(currentPlayer == 0)
            {
                 currentPlayer = 1;
            }
            player = game.getPlayers()[currentPlayer];
            if(board.isValidMove(drop, true) == true) 
            {    
                board.place(drop, player, true);
                if(board.checkWin(true) == true)
                { 
                 	return drop; 
               	}
		     }
             if(currentPlayer == 1)
             {
                currentPlayer = 0;
             }
             else if(currentPlayer == 0)
             {
                currentPlayer = 1;
             }
             two = findMoveHelper(game.getPlayers()[currentPlayer], depth--, currentPlayer); 
             two.setScore(two.getScore() + player.getId());
             if(two.getWin() * player.getId() > one.getWin() * player.getId() || 
             (two.getScore() * player.getId() >= one.getScore() * player.getId() && two.getWin() == one.getWin()))
             {
                  bestMove = drop;
                  one = two;                
             }
         }
         	bestMove = ((int) (Math.random() * 7));
         	while(!board.isValidMove(bestMove, false))
         	{
         		bestMove = ((int) (Math.random() * 7));
         	}
            return bestMove; 
    }  
    
    private ScoreTracker findMoveHelper(Player player, int depth, int currentPlayer)
    {
        ScoreTracker one = new ScoreTracker();
        ScoreTracker two = new ScoreTracker();
        for(int drop = 0; drop < 7; drop++)
        {
            if(board.isValidMove(drop, true) == true)
            { 
                board.place(drop, player, true);
                if(board.checkWin(false) == true)
                { 
                    one.setWin(player.getId());
                    one.setScore(7.0 * player.getId());
                    return one;
                }
            }            
        }
        one.setWin(player.getId() * -1);
        two.setScore(0.0); 
        if(currentPlayer == 1)
        {
            currentPlayer = 0;
        }
        else if(currentPlayer == 0)
        {
            currentPlayer = 1;
        }       
        for(int drop = 0; drop < 7; drop++)
        { 
            if(board.isValidMove(drop, true) == false)
            {
                one.setScore(one.getScore() - player.getId()); 
            }
            if(depth > 1)
            { 
                board.place(drop, player, true);
                two = findMoveHelper(game.getPlayers()[currentPlayer], depth--, currentPlayer);            
                if(two.getWin() == player.getId())
                { 
                    two.setScore(two.getScore() / 7.0); 
                    return two;
                }
                else
                {
                     one.setScore(one.getScore() + two.getScore());
                     if(two.getWin() != one.getWin()) 
                     {
                        one.setWin(0); 
                     }
                }             
             }
             else
             {
                one.setWin(0);
             }
        }
        one.setScore(one.getScore() / 49.0);
        return one;
     }            
    
}
