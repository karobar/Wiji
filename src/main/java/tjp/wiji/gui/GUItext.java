package tjp.wiji.gui;

import tjp.wiji.drawing.BitmapContext;
import tjp.wiji.drawing.Color;
import tjp.wiji.representations.ImageRepresentation;
import tjp.wiji.representations.LetterRepresentation;

/**
 * A GUIText object is basically just a string with additional functionality for
 * distinguishing between mainstream textString items and ancillary textString 
 * items (usually ancillary textString is used for information related to the 
 * ChoiceList to which the GUIText is linked).
 * 
 * @author      Travis Pressler (travisp471@gmail.com)
 * @version     %I%, %G%
 */
public class GUItext extends GUIelement {
    int[] textCodes;
    String textString;
    /** If a GUIText element is a BGthief, it will steal the color from the
      *element underneath. */
    public boolean BGthief = false;
    
    public GUItext() {}
    
    /**
     * Basic constructor for a textString item.
     * @param inName the textString to be displayed
     */
    public GUItext(String text) {
        this.textString = text;
        this.activeColor = DEFAULT_ACTIVE_COLOR;
        this.inactiveColor = DEFAULT_INACTIVE_COLOR;
        //textCodes = Translator.translate(text);
    }
    
    public GUItext(String text, Color activeColor, Color inactiveColor) {
        this.textString = text;
        this.activeColor = inactiveColor;
        this.inactiveColor = activeColor;
        //textCodes = Translator.translate(text);
    }
    
    
    /**
     * Constructor for a textString item with a specified position
     * @param inName the textString to be displayed
     * @param inSpecX the x position of the textString item
     * @param inSpecY the y position of the textString item
     */
    public GUItext(String text, int specX, int specY) {
        this.textString = text;
        //textCodes = Translator.translate(text);
        this.customX = specX;
        this.customY = specY;
    }
    
    /**
     * This is dangerous.  If the GUIText is initialized with integers rather 
     * than with strings, there may be unprintable characters. 
     * @return 
     */
    public String getName(){
        if(this.textString != null) {
            return this.textString;
        }
        else {
            return "!ERROR!";
        }
    }

    @Override
    public int getLength() {
        return this.textString.length();
    }
    
    @Override
    public ImageRepresentation determineCurrImg(BitmapContext bitmapContext, 
            int currIndex, boolean isActive) {
 
        if (isActive && activeColor != null){
            return new LetterRepresentation(
                    this.getActiveColor(), 
                    Color.BLACK, 
                    getName().charAt(currIndex),
                    bitmapContext.getCharPixelWidth(),
                    bitmapContext.getCharPixelHeight());
        } else {
            return new LetterRepresentation(
                    this.getInactiveColor(), 
                    Color.BLACK,
                    getName().charAt(currIndex),
                    bitmapContext.getCharPixelWidth(),
                    bitmapContext.getCharPixelHeight());
        }
    }
    
//    public int[] getTextCodes() {
//        return this.textCodes;
//    }
}
