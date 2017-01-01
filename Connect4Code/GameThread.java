public class GameThread extends Thread
{
    private Connect4 game;
    public GameThread(Connect4 game)
    {
        this.game = game;
    }
    
    public void run(){
        try
        {
        	
                //plays move knowing that a human initiated the method with a button click
                int move = 0;
                Player player = game.getCurrentPlayer();
                move = player.action();
                game.getBoard().place(move, player, false);
                checkForEnd(player);
                game.switchCurrent();
                player = game.getCurrentPlayer();
                sleep(650);
                //if the other player is a computer, then it runs the code to get the computer's move
                //if it is a human then this code is not run and the method will be recalled once the other player clicks a button
                if(player.isComputer())
                {
                    
                    move = player.action();
                    game.getBoard().place(move, player, false);
                    checkForEnd(player);
                    game.switchCurrent();
                }
                return;
         } 
         catch(Exception e)
         {
             System.out.println("There is an error in the gameThread's run.");
             e.printStackTrace();
         }
    }
    
    public void checkForEnd(Player player)
    {
        //cehcks for if a player won
        if(game.getBoard().checkWin(false) == true)
        {
             PopupWindow window = new PopupWindow(player.getName() + " has won!!");
             return;
        }
        //checks for if there is a draw
        if(game.getBoard().isFull(false) == true)
         {
             PopupWindow window = new PopupWindow("The board is full. Draw.");
             return;
         }
    }
}