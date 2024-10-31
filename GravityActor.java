import mayflower.*;

public class GravityActor extends Actor
{
    // constructor
    public GravityActor()
    {

    }

    public void act() {
        // always set the actor one pixel lower
        setLocation(getX(), getY() + 1);
        
        // if there is a block underneath, move the actor back up one pixel
        if(isBlocked())
            setLocation(getX(), getY() - 1);
            
        // if there is a block underneath AND the actor is below the ground, move the actor down one left 2
        // this is so the actor does not go into the moving blocks as it falls
        if(isBlocked() && getY() > 483) {
            setLocation(getX() - 2, getY() + 1);
        }
    }
    
    
    public boolean isBlocked() {
        // return true if touching a block, false otherwise
        if(isTouching(Block.class)) {
            return true;
        }
        else
            return false;
    }
    
    public boolean isFalling() {
        boolean ret;
        // move the actor down and see if it touches a block
        setLocation(getX(), getY() + 1);
        ret = isTouching(Block.class);
        setLocation(getX(), getY() - 1);
        // if the actor will land on a block, return false. If it is still falling, return true.
        return !ret;
    }

}
