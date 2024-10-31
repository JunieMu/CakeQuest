import mayflower.*;

public class InfoWorld extends World {
    
    public InfoWorld() 
    {
        // set the information image screen
        setBackground("img/BG/cake.jpeg");
    }
    
    public void act()
    {
        // once the user presses enter, the first level will start
        if(Mayflower.isKeyDown(Keyboard.KEY_ENTER)){
            Mayflower.setWorld(new Level1World());
        }
    }
    
}