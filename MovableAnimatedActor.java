import mayflower.*;

public class MovableAnimatedActor extends AnimatedActor

{   
    // instance variables for different action animations
    private Animation walkRight;
    private String currentAction;
    private Animation fallRight;
    private Animation jumpRight;
    private String direction;
    
    public MovableAnimatedActor() {
        // instantiate instance variables with default values
        walkRight = null;
        currentAction = null;
        direction = "right";
    }

    public void act() {
        String newAction = "walkRight";
        
        // assign dimensions and location to variables for convenience
        int x = getX();
        int y = getY();
        int w = getWidth();
        int h = getHeight();
 
        // if the down key is pressed
        if(Mayflower.isKeyDown(Keyboard.KEY_DOWN)) {
            // move the actor down
            setLocation(x, y + 1);
            // if it is touching a block, move the actor up
            if(isBlocked())
                setLocation(x, y - 1);
            // assign newAction to the new action
            newAction = "walkDown";
        }        
        
        // if the actor is falling, assign newAction to the new action
        if(isFalling()) {
            newAction = "fallRight";         
        }
        
        // if the up key is pressed
        if(Mayflower.isKeyDown(Keyboard.KEY_UP) && y > 0) {
            // move the cat upwards gradually
            for(int i = 0; i < 25; i++) {
                setLocation(x, y - 10);
            }           
            // assign newAction to the new action
            newAction = "jumpRight";
        }
        
        // call the act method of AnimatedActor
        super.act();
        
        // if the new action is not the current action, set the animation to the new one
        if(newAction != null && !newAction.equals(currentAction)) {
            if(newAction.equals("walkRight"))
                setAnimation(walkRight);
            if(newAction.equals("fallRight"))
                setAnimation(fallRight);
            if(newAction.equals("jumpRight"))
                setAnimation(jumpRight);
            newAction = currentAction;
        }
    }
    
    // set the correct/new animation
    public void setAnimation(Animation a) {
        super.setAnimation(a);
    }
    
    // assign the passed-in Animation to its respective variable
    public void setWalkRightAnimation(Animation ani) {
        walkRight = ani;
    }
    
    public void setFallRightAnimation(Animation ani) {
        fallRight = ani;
    }
    
    public void setJumpRightAnimation(Animation ani) {
        jumpRight = ani;
    }
    
}
