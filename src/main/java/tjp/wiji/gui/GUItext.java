package tjp.wiji.gui;

import static com.google.common.base.Preconditions.checkNotNull;

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
    
    public char charAt(int index) {
        return textString.charAt(index);
    }

    public String concat(String str) {
        String newString = textString.concat(str);
        textString = newString;
        return newString;
    }

    public boolean isEmpty() {
        return textString.isEmpty();
    }
    
    public void removeLastChar() {
        textString = textString.substring(0, textString.length() - 1); 
    }

    /** 
     * If a GUIText element is a BGthief, it will steal the color from the
     * element underneath. 
     */
    public boolean BGthief = false;
    
    /**
     * Basic constructor for a textString item.
     * @param inName the textString to be displayed
     */
    public GUItext(String text) {
        this.textString = text;
        //textCodes = Translator.translate(text);
    }
    
    public GUItext(String text, Color activeColor, Color inactiveColor) {
        this.textString = text;
        setInactiveColor(checkNotNull(inactiveColor));
        setActiveColor(checkNotNull(activeColor));
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
 
        if (isActive){
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
