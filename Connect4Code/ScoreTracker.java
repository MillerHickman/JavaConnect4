public class ScoreTracker
{
      public double score; // used to store how good a move is
      public int win; // is nonzero when a win is imminent

    ScoreTracker()
    {
        score = 0.0;
        win = 0;
    }
    ScoreTracker(double score, int win)
    {
         this.score = score;
         this.win = win;
    }
    public double getScore()
    {
        return score;
    }
    public void setScore(double newScore)
    {
        score = newScore;
    }
    public int getWin()
    {
        return win;
    }
    public void setWin(int newWin)
    {
        win = newWin;
    }
}
