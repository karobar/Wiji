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
    public final static Color DEFAULT_ANCILLARY_TEXT_COLOR = Color.GRAY;
    /** 
     * If a GUIText element is a BGthief, it will steal the color from the
     * element underneath. 
     */
    public boolean BGthief = false;
    private boolean isAncillary;
    
    int[] textCodes;

    String textString;

    /**
     * Basic constructor for a textString item.
     * @param inName the textString to be displayed
     */
    public GUItext(String text) {
        this.textString = text;
        //textCodes = Translator.translate(text);
    }
    
    /**
     * Basic constructor for an ancillary text item.
     * @param inName the textString to be displayed
     */
    public GUItext(String text, boolean isAncillary) {
        this.textString = text;
        this.isAncillary = true;
    }

    /**
     * Basic constructor for an ancillary text item with a color.
     * @param inName the textString to be displayed
     */
    public GUItext(String text, Color color, boolean isAncillary) {
        this.textString = text;
        setCustomInactiveColor(checkNotNull(color));
        setCustomActiveColor(checkNotNull(color));
        this.isAncillary = true;
    }
    
    public GUItext(String text, Color activeColor, Color inactiveColor) {
        this.textString = text;
        setCustomInactiveColor(checkNotNull(inactiveColor));
        setCustomActiveColor(checkNotNull(activeColor));
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
     * Constructor for a textString item with a specified position and is ancillary
     * @param inName the textString to be displayed
     * @param inSpecX the x position of the textString item
     * @param inSpecY the y position of the textString item
     */
    public GUItext(String text, int specX, int specY, boolean isAncillary) {
        this.textString = text;
        this.customX = specX;
        this.customY = specY;
        this.isAncillary = true;
    }
    
    public char charAt(int index) {
        return textString.charAt(index);
    }
    
    public String concat(String str) {
        String newString = textString.concat(str);
        textString = newString;
        return newString;
    }
    
    public void insertChar(char insertChar, int index) {
        if (index >= 0 && index < textString.length()) {
            String leftPart = textString.substring(0, index);
            String rightPart = textString.substring(index);
            textString = leftPart + insertChar + rightPart;
        }
    }
    
    public void removeAll(char ch) {
        textString = removeAlls(ch, textString);
    }
    
    private String removeAlls(char ch, String str) {
        for(int i = 0; i < str.length(); i++) {
            boolean found = str.charAt(i) == ch;
            if(found && i == str.length() - 1) {
                return "";
            } else if(found) {
                return str.substring(0, i) + removeAlls(ch, str.substring(i + 1));
            }
        }
        return str;
    }
    
    public String concatLeft(String str) {
        String newString = str.concat(textString);
        textString = newString;
        return newString;
    }
    
    @Override
    public ImageRepresentation determineCurrImg(BitmapContext bitmapContext, 
            int currIndex, boolean isActive, Color parentActiveColor, Color parentInactiveColor) {
 
        if (isActive){
            return new LetterRepresentation(
                    getFinalActiveColor(parentActiveColor), 
                    Color.BLACK, 
                    toString().charAt(currIndex),
                    bitmapContext.getCharPixelWidth(),
                    bitmapContext.getCharPixelHeight());
        } else {
            return new LetterRepresentation(
                    getFinalInactiveColor(parentInactiveColor), 
                    Color.BLACK,
                    toString().charAt(currIndex),
                    bitmapContext.getCharPixelWidth(),
                    bitmapContext.getCharPixelHeight());
        }
    }
    
    private Color getFinalActiveColor(Color parentActiveColor) {
        if (getCustomActiveColor() != null) {
            return getCustomActiveColor();
        } else if (isAncillary) {
            return DEFAULT_ANCILLARY_TEXT_COLOR;
        } else if (parentActiveColor != null){
            return parentActiveColor;
        } else {
            return DEFAULT_ACTIVE_COLOR;
        }
    }
    
    private Color getFinalInactiveColor(Color parentInactiveColor) {
        if (getCustomInactiveColor() != null) {
            return getCustomInactiveColor();
        } else if (isAncillary) {
            return DEFAULT_ANCILLARY_TEXT_COLOR;
        } else if (parentInactiveColor != null){
            return parentInactiveColor;
        } else {
            return DEFAULT_INACTIVE_COLOR;
        }
    }
    
    @Override
    public int getLength() {
        return this.textString.length();
    } 
    
    @Override
    public boolean isAncillary() {
        return isAncillary;
    }
    
    public void setIsAncillary(boolean isAncillary) {
        this.isAncillary = isAncillary;
    }
//    
//    public void setNormal() {
//        this.isAncillary = false;
//    }
    
    public boolean isEmpty() {
        return textString.isEmpty();
    }

    public void removeLastChar() {
        textString = textString.substring(0, textString.length() - 1); 
    }
    
    public void removeLeftChar() {
        textString = textString.substring(1);
    }
    
    @Override
    public String toString(){
        return this.textString;
    }

    @Override
    public void overlayGUI(ImageRepresentation[][] mainImRepMatrix) {
    }
    
//    public int[] getTextCodes() {
//        return this.textCodes;
//    }
}
