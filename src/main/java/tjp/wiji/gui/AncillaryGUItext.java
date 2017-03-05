package tjp.wiji.gui;

import tjp.wiji.drawing.Color;

/**
 * @author      Travis Pressler (travisp471@gmail.com)
 * @version     %I%, %G%
 */
public class AncillaryGUItext extends GUItext {
    public final static Color DEFAULT_ANCILLARY_TEXT_COLOR = Color.GRAY;
    
    /**
     * Basic constructor for an ancillary text item.
     * @param inName the textString to be displayed
     */
    public AncillaryGUItext(String text) {
        super(text);
        setInactiveColor(DEFAULT_ANCILLARY_TEXT_COLOR);
        setActiveColor(DEFAULT_ANCILLARY_TEXT_COLOR);
    }
    
    /**
     * Basic constructor for an ancillary text item with a color.
     * @param inName the textString to be displayed
     */
    public AncillaryGUItext(String text, Color color) {
        super(text, color, color);
    }
    
    /**
     * Constructor for a textString item with a specified position
     * @param inName the textString to be displayed
     * @param inSpecX the x position of the textString item
     * @param inSpecY the y position of the textString item
     */
    public AncillaryGUItext(String text, int specX, int specY) {
        super(text, specX, specY);
    }
}
