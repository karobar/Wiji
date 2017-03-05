package tjp.wiji.gui;

import tjp.wiji.drawing.Color;

/**
 * @author      Travis Pressler (travisp471@gmail.com)
 * @version     %I%, %G%
 */
public class AncillaryGUIText extends GUItext {
    public final static Color DEFAULT_ANCILLARY_TEXT_COLOR = Color.GRAY;
    Color color;

    
    /**
     * Basic constructor for an ancillary text item.
     * @param inName the textString to be displayed
     */
    public AncillaryGUIText(String text) {
        this.textString = text;
        color = DEFAULT_ANCILLARY_TEXT_COLOR;
    }
    
    /**
     * Basic constructor for an ancillary text item with a color.
     * @param inName the textString to be displayed
     */
    public AncillaryGUIText(String text, Color color) {
        this.color = color;
    }
    
    /**
     * Constructor for a textString item with a specified position
     * @param inName the textString to be displayed
     * @param inSpecX the x position of the textString item
     * @param inSpecY the y position of the textString item
     */
    public AncillaryGUIText(String text, int specX, int specY) {
        this.textString = text;
        this.customX = specX;
        this.customY = specY;
    }
    
    public Color getActiveColor() {
        return color;
    }
    
    public Color getInactiveColor() {
        return color;
    }
}
