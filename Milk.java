import mayflower.*;

public class Milk extends MoveableBlock
{
    //Constructor
    public Milk()
    {
        setImage("img/Object/milk.png");
    }
    
    //The milk that is collected will disappear and the score will go up
    public void act() {
        super.act();
        if(isTouching(Cat.class)) {
            Object a = getOneIntersectingObject(Cat.class);
            Cat c = (Cat) a;
            World w = getWorld();
            w.removeObject(this);
            c.increaseScore(1);
        }
    }

    
}