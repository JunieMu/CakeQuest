import mayflower.*;

public class AnimatedActor extends GravityActor
{
    // declare instance variables for the Animation object and the Timer
    private Animation animation;
    private Timer animationTimer;
    
    public AnimatedActor()
    {
        // instantiate timer to the desired time
        animationTimer = new Timer(1000);
    }
    
    public void setAnimation(Animation a) {
        // initiate animation with the passed-in object
        animation = a;
    }
    
    public void act() {
        // if the timer is done, restart the timer and set the next frame
        if(animationTimer.isDone() && animation != null) {
            animationTimer.reset();
            MayflowerImage nextFrame = animation.getNextFrame();
            setImage(nextFrame);
        }
        
        // call the act method of the GravityActor class
        super.act();
    }
}
