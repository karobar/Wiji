package tp.naevus.gui;

/**
 * @author      Travis Pressler (travisp471@gmail.com)
 * @version     %I%, %G%
 */
public class AncillaryGUIText extends GUIText {
    
    /**
     * Basic constructor for an ancillary text item.
     * @param inName the textString to be displayed
     */
    public AncillaryGUIText(String text) {
        this.textString = text;
    }
    
    /**
     * Constructor for a textString item with a specified position
     * @param inName the textString to be displayed
     * @param inSpecX the x position of the textString item
     * @param inSpecY the y position of the textString item
     */
    public AncillaryGUIText(String text, int specX, int specY) {
        this.textString = text;
        this.specX = specX;
        this.specY = specY;
    }
}
