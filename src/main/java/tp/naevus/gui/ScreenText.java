package tp.naevus.gui;

import tp.naevus.drawing.BitmapContext;
import tp.naevus.drawing.Color;
import tp.naevus.representations.ImageRepresentation;

/**
 * @author      Travis Pressler (travisp471@gmail.com)
 * @version     %I%, %G%
 */
public class ScreenText extends TextCollection {
    private int screenX, screenY;
    private final BitmapContext bitmapContext;
    
    /**
      * Basic Constructor for TextCollection for text which does not change 
      * colors.
      * @param inInactive color of the text
      * @param x position of upper-left corner of the list (assuming 
      *        position is not explicitly assigned with GUIText.specX)
      * @param y position of upper-left corner of the list (assuming 
      *        position is not explicitly assigned with GUIText.specY)
    */
    public ScreenText(BitmapContext bitmapContext, Color inInactive, int x, int y) {
        this.bitmapContext = bitmapContext;
        this.inactiveColor = inInactive;
        this.activeColor = null;
        this.screenX = x;
        this.screenY = y;
    }

     /**
      * Augmented Base constructor for text which changes color. 
      * @param inInactive color of the text
      * @param inputActive color of the text when active
      * @param x position of upper-left corner of the list (assuming 
      *        position is not explicitly assigned with GUIText.specX)
      * @param y position of upper-left corner of the list (assuming 
      *        position is not explicitly assigned with GUIText.specY)
    */
    public ScreenText(final BitmapContext bitmapContext, Color inInactive, Color inputActive, 
            int x, int y){

        this.bitmapContext = bitmapContext;
        this.inactiveColor = inInactive;
        this.activeColor   = inputActive;
        this.screenX = x;
        this.screenY = y;
    }
    
    @Override
    public int getScreenX() {
        return screenX;
    }
    
    @Override
    public int getScreenY() {
        return screenY;
    }
    
    void setScreenX(int inX) {
        screenX = inX;
    }
    
    void setScreenY(int inY) {
        screenY = inY;
    }
    
    @Override
    public void overlayGUI(ImageRepresentation[][] mainImRepMatrix) {
        displayOnto(mainImRepMatrix, bitmapContext);
    }
}
