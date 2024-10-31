import mayflower.*;

public class TitleWorld extends World {
    
    public TitleWorld() 
    {
        // set the title screen image
        setBackground("img/BG/birthday.jpeg");
    }
    
    public void act()
    {
        // once the user presses enter, the next world will load with the game info
        if(Mayflower.isKeyDown(Keyboard.KEY_ENTER)){
            Mayflower.setWorld(new InfoWorld());
        }
    }
    
}