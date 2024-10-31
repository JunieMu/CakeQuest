import mayflower.*;

public class FallingBlock extends Actor
{
    //Constructor
    public FallingBlock()
    {
    }
    
    //gets the meteors in Level3 to fall
    public void act() {
        setLocation(getX() - 3, getY() + 1);
        if(getY() > 550) {
            setLocation(getX(), -100);
        }
    }

}
