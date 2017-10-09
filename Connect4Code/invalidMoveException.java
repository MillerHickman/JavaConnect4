public class invalidMoveException extends Exception
{
    public void error()
    {
        PopupWindow window = new PopupWindow("This move is invalid.");
    }
}
