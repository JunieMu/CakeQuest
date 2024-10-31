import mayflower.*;

public class Strawberry extends MoveableBlock
{
    //Constructor
    public Strawberry()
    {
        setImage("img/Object/strawberry.png");
    }
    
    //The strawberry that is collected will disappear and the score will go up
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