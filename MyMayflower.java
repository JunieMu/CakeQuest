import mayflower.*;

public class MyMayflower extends Mayflower 
{
    // Constructor
    public MyMayflower()
    {
        // Create a window with 900x700 resolution
        super("Project 1", 900, 700);
    }

    public void init()
    {
        Mayflower.setFullScreen(false);
        // set the default world to TitleWorld
        Mayflower.setWorld(new TitleWorld());
    }
}
