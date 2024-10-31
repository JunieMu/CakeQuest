 import mayflower.*;

public class Cat extends MovableAnimatedActor
{
    // declare instance variables needed for the Animation objects, score, lives, invulnerable, and Timers.
    private Animation walkR;
    private int score;
    private int lives;
    private int prevLives;
    private Icon[] hearts;
    private String[] heartImgs;
    private Timer livesTimer;
    private int counter;
    private boolean invulnerable;
    private Timer invulnerableTimer;
    
    // constructor with heart icons
    public Cat(Icon[] h) 
    {
        // instantiate instance variables
        score = 0;
        lives = 3;
        prevLives = lives;
        // get the array of Icon objects for the 3 lives
        hearts = h;
        counter = 0;
        
        // make String arrays for the file paths of the animations images (hearts, walking, falling, jumping) 
        heartImgs = new String[10];
        for(int i = 1; i <= 10; i++) {
            heartImgs[i-1] = "img/Hearts/heart" + i + ".png";
        }
        
        String[] walkImgs = new String[10];
        for(int i = 1; i <= 10; i++) {
            walkImgs[i-1] = "img/cat/Walk (" + i + ").png";
        }
        
        // create the walkR Animation object
        walkR = new Animation(50, walkImgs);
        // resize and crop the images
        walkR.scale(100, 87);
        walkR.setBounds(18, 5, 54, 80);
        
        String[] fallImgs = new String[8];
        for(int i = 1; i <= 8; i++) {
            fallImgs[i-1] = "img/cat/Fall (" + i + ").png";
        }
        
        // create the fallR Animation object
        Animation fallR = new Animation(50, fallImgs);
        // resize and crop the images
        fallR.scale(100, 87);
        fallR.setBounds(12, 5, 54, 80);
        
        String[] jumpImgs = new String[8];
        for(int i = 1; i <= 8; i++) {
            jumpImgs[i-1] = "img/cat/Jump (" + i + ").png";
        }
        
        // create the jumpR Animation object
        Animation jumpR = new Animation(50, jumpImgs);
        // resize and crop the images
        jumpR.scale(100, 87);
        jumpR.setBounds(12, 5, 54, 80);
        
        // set the different animations to their respective variables in MovableAnimatedActor
        setWalkRightAnimation(walkR);
        setFallRightAnimation(fallR);
        setJumpRightAnimation(jumpR);
    }
    
    public void act()
    {
        // call act method of the parent class
        super.act();
        
        // if the cat falls below the floor or off the screen
        if(getY() > 700 || getX() < 0) {
            // reset the cat's location and decrease by 1 life
            setLocation(300, 150);
            decreaseLives(1);
        }
        
        // if the cat is vulnerable and touching a hazard
        if(!isInvulnerable() && (isTouching(TreeStump.class) || isTouching(Asteroid.class) || isTouching(Spike.class) || isTouching(Stone.class))) {
            // set the cat to invulnerable
            setInvulnerable();
            // reset the cat's location and decrease by 1 life
            setLocation(300, 150);
            decreaseLives(1);
        }
        
        // animate the heart breaking after losing a life
        // if livesTimer is instantiated, and has reached its goal
        if(livesTimer != null && livesTimer.isDone()) {
            World t = getWorld();
            livesTimer.reset();
            
            // remove the Icon object (image) of the last heart
            t.removeObject(hearts[lives]);
            // set Icon object (image) of the last heart to the next frame
            hearts[lives] = new Icon(heartImgs[counter]);
            // add the Icon object (image) onto the screen
            t.addObject(hearts[lives], (lives) * 100, 0);
            
            // if the animation has finished running ONCE, stop the timer and reset the counter
            if(counter == 9) {
                livesTimer = null;
                counter = 0;
            }
            // increase the counter (indicates the current frame)
            counter++;
        }
    }
    
    // increases the cat's score by the inputted amount and updates the screen
    public void increaseScore(int amount) {
        score += amount;
        updateText();
    }
    
    // decreses the cat's lives by the inputted amount and updates the screen
    public void decreaseLives(int amount) {
        lives -= amount;
        updateText();
    }
    
    // getter method for score
    public int getScore() {
        return score;
    }
    
    // getter method for lives
    public int getLives() {
        return lives;
    }
    
    // updates the score and the lives
    private void updateText() {
        World w = getWorld();
        // remove the old score text and show the new score
        w.removeText(100, 150);
        w.showText(score + "/10", 100, 150);
        
        // if the lives has gone down, start the livesTimer to animate the heart breaking
        if(lives < prevLives) {
            livesTimer = new Timer(10000000);
            // set the previous counter of lives to the updated number
            prevLives = lives;
        }
    }
    
    public void setInvulnerable() {
        // set invulnerable to true and start the invulnerableTimer
        invulnerable = true;
        invulnerableTimer = new Timer(2000);
    }
    
    public boolean isInvulnerable() {
        // if invulnerable timer is done running, the cat is vulnerable again and stop the timer
        if(invulnerableTimer != null && invulnerableTimer.isDone()) {
            invulnerable = false;
            invulnerableTimer = null;
        }
        
        // return boolean value
        return invulnerable;
    }
    
    public boolean isGameOver() {
        // if the cat's lives reach zero, return true, otherwise, false
        if(lives == 0) {
            return true;
        }
        
        return false;
    }
    
}
