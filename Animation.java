import mayflower.*;

public class Animation
{
    // instance variables for animation object
    private MayflowerImage[] frames;
    private int framerate;
    private int currentFrame;
    
    /**
     * Constructor for objects of class Animation
     */
    public Animation(int fr, String[] imgFiles)
    {
        // initialize frames array with MayFlowerImages of each picture
        framerate = fr;
        frames = new MayflowerImage[imgFiles.length];
        for(int i = 0; i < frames.length; i++) {
            frames[i] = new MayflowerImage(imgFiles[i]);
        }
        currentFrame = 0;
    }
    
    public int getFrameRate() {
        // getter method for framerate
        return framerate;
    }
    
    public MayflowerImage getNextFrame() {
        // to move from the current frame to the next
        MayflowerImage currentImg = frames[currentFrame];
        currentFrame++;
        // if current frame is the last one, recycle to the first frame
        currentFrame %= frames.length;
        return currentImg;
    }

    
    public void scale(int w, int h) {
        // resize images
        for(int i = 0; i < frames.length; i++) {
            frames[i].scale(w, h);
        }
    }
    
    public void setTransparency(int percent) {
        // change transparency of images
        for(int i = 0; i < frames.length; i++) {
            frames[i].setTransparency(percent);
        }
    }
    
    public void mirrorHorizontally() {
        // flip the images horizontally
        for(int i = 0; i < frames.length; i++) {
            frames[i].mirrorHorizontally();
        }
    }
    
    public void setBounds(int x, int y, int w, int h) {
        // crop images
        for(int i = 0; i < frames.length; i++) {
            frames[i].crop(x, y, w, h);
        }
    }
}
