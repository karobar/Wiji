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
    
    //int[] textCodes;

    String text;

    /**
     * Basic constructor for a textString item.
     * @param inName the textString to be displayed
     */
    public GUItext(String text) {
        this.text = text;
        //textCodes = Translator.translate(text);
    }
    
    /**
     * Basic constructor for an ancillary text item.
     * @param inName the textString to be displayed
     */
    public GUItext(String text, boolean isAncillary) {
        this.text = text;
        this.isAncillary = true;
    }

    /**
     * Basic constructor for an ancillary text item with a color.
     * @param inName the textString to be displayed
     */
    public GUItext(String text, Color color, boolean isAncillary) {
        this.text = text;
        setCustomInactiveColor(checkNotNull(color));
        setCustomActiveColor(checkNotNull(color));
        this.isAncillary = true;
    }
    
    public GUItext(String text, Color activeColor, Color inactiveColor) {
        this.text = text;
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
        this.text = text;
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
        this.text = text;
        this.customX = specX;
        this.customY = specY;
        this.isAncillary = true;
    }
    
    public char charAt(int index) {
        return text.charAt(index);
    }
    
    public void setText(String text) {
        this.text = text;
    }
    
    public void clear() {
        text = "";
    }
    
    public String concat(String str) {
        String newString = text.concat(str);
        text = newString;
        return newString;
    }
    
    public void insertChar(char insertChar, int index) {
        if (index >= 0 && index < text.length()) {
            String leftPart = text.substring(0, index);
            String rightPart = text.substring(index);
            text = leftPart + insertChar + rightPart;
        }
    }
    
    public void removeAll(char ch) {
        text = removeAlls(ch, text);
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
        String newString = str.concat(text);
        text = newString;
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
        return this.text.length();
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
        return text.isEmpty();
    }

    public void removeLastChar() {
        text = text.substring(0, text.length() - 1); 
    }
    
    public void removeLeftChar() {
        text = text.substring(1);
    }
    
    @Override
    public String toString(){
        return this.text;
    }

    @Override
    public void overlayGUI(ImageRepresentation[][] mainImRepMatrix) {
    }
    
//    public int[] getTextCodes() {
//        return this.textCodes;
//    }
}
