import mayflower.*;

public class Level3World extends World {
    // declare instance variables
    private Cat cat;
    private String[][] tiles;
    
    public Level3World() 
    {
        // set the background image to the volcano image
        setBackground("img/BG/volcano.jpeg");
        
        // make the Icon image for the score
        Icon scoreIcon = new Icon("img/Object/chocolate.png");
        // create the three heart images
        Icon[] hearts = new Icon[3];
        
        // add the score icon and text onto the screen
        addObject(scoreIcon, 0, 100);
        showText("0/10", 100, 150);
        
        // add the three heart objects onto the screen
        for(int i = 0; i < 3; i++) {
            Icon heart = new Icon("img/Hearts/heart1.png");
            hearts[i] = heart;
            addObject(heart, i * 100, 0);
        }
        
        // create a new cat 
        // pass in the same heart objects so that they can be changed/updated later in the Cat Class
        cat = new Cat(hearts);
        // add the cat onto the world
        addObject(cat, 300, 350);
               
        // initialize the tiles array with the correct number of columns and rows
        tiles = new String[7][60];
        // call the buildWorld method that adds the objects onto the screen
        buildWorld();
    }
    
    public void act()
    {
        // if the cat has lost all its lives, set the world to GAME OVER
        if(cat.isGameOver()) {
            Mayflower.setWorld(new GameOverWorldLose());
        }
        
        // if the cat has got all the ingredients, move on to the next level
        if(cat.getScore() >= 10) {
            Mayflower.setWorld(new GameOverWorldWin());
        }
    }
    
    public void buildWorld() {
        // set the default values of all the tiles to an empty string
        for(int r = 0; r < tiles.length; r++) {
            for(int c = 0; c < tiles[r].length; c++) {
                tiles[r][c] = "";
            }
        }
        
        // set all the tiles in the 7th row to "ground"
        for(int c = 0; c < tiles[6].length; c++) {
            tiles[6][c] = "ground";
        }
        
        for(int r = 0; r < tiles.length; r++) {
            for(int c = 0; c < tiles[r].length; c++) {
                // if the tile is "ground", add a new block at the corresponding location
                VolcanoBlock lavaRock = new VolcanoBlock();
                if(tiles[r][c].equals("ground"))
                    addObject(lavaRock, c * 125, r * 95);
                
                // create a random number for "randomness"
                int num = (int) ((Math.random()) * 25);
                // if the random number is less than 4, set the tile to the default empty string and remove that block there
                // this will create random holes in the ground
                if(num < 4 && c != 0){
                    tiles[r][c] = "";
                    removeObject(lavaRock);
                }
                
                // create a randomY variable so that the ingredient can only spawn in the 4th or 5th row
                int randomY = (int) (Math.random() * 2) + 4;
                // if the random number is 10/11 and is at an empty tile, add a strawberry in the 4th or 5th row 
                if((num == 10 || num == 11) && tiles[randomY][c].equals("")) {
                    addObject(new Chocolate(), c * 125, randomY * 95);
                    tiles[randomY][c] = "chocolate";
                }
                
                // if the random number is greater than 20, there is a ground block, and the tile above it is empty
                // add the hazard
                if(num > 20 && tiles[r][c].equals("ground") && tiles[r-1][c].equals("")) {
                    addObject(new Spike(), c * 125, (r-1) * 105);
                    tiles[r-1][c] = "spike";
                }
                
                // if random number is 8, add a random falling asteroid
                if(num == 8) {
                    addObject(new Asteroid(), c * 125, -100);
                    tiles[0][c] = "asteroid";
                }
            }  
        }
    }
}