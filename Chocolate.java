import mayflower.*;

public class Chocolate extends MoveableBlock
{
    //Constructor
    public Chocolate()
    {
        setImage("img/Object/chocolate.png");
    }
    
    //The chocolate that is collected will disappear and the score will go up
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