import mayflower.*;

public class MoveableBlock extends Actor
{
    //Constructor
    public MoveableBlock()
    {
    }
    
    //Blocks move left repeatedly
    public void act() {
        setLocation(getX()-2, getY());
    }

}
