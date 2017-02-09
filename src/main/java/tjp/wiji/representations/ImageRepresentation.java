package tjp.wiji.representations;

import tjp.wiji.drawing.Color;

/**
 *  An ImageRepresentation is the visual representation which occupies one of 
 *  the game window's cells. In other words, an ImageRepresentation represents 
 *  all sprites and other graphics.
 * 
 * @author      Travis Pressler (travisp471@gmail.com)
 * @version     %I%, %G%
 */
public abstract class ImageRepresentation {
    private final Color foreColor;
    private final int rawImgChar;
    private Color backColor;
    
    private int[][] RGBMatrix;

    /**
     * An ImageRepresentation where only the foreColor is explicitly 
     * defined, the backColor is likely to be implied by the background 
     * color of the floor below it.
     * @param foreColor
     * @param rawImgChar
     */
    protected ImageRepresentation(final Color foreColor, final int rawImgChar, 
            final int charPixelWidth, final int charPixelHeight) {

        this.foreColor = foreColor;
        this.backColor = Color.BLACK;
        this.rawImgChar = rawImgChar;
        this.RGBMatrix = new int[charPixelWidth][charPixelHeight];
    }
    
    /**
     *  An ImageRepresentation where the foreColor and backColor are 
     *  explicitly defined.
     * @param foreColor
     * @param backColor
     * @param rawImgChar
     */
    protected ImageRepresentation(Color foreColor, Color backColor, int rawImgChar, 
            final int charPixelWidth, final int charPixelHeight) {

        this.foreColor = foreColor;
        this.backColor = backColor;
        this.rawImgChar = rawImgChar;
        this.RGBMatrix = new int[charPixelWidth][charPixelHeight];
    }

    /**
     * Creates a white ImageRep.
     * @param rawImgChar
     */
    public ImageRepresentation(int rawImgChar, final int charPixelWidth, 
            final int charPixelHeight) {

        this.foreColor = Color.WHITE;
        this.backColor = Color.BLACK;
        this.rawImgChar = rawImgChar;
        this.RGBMatrix = new int[charPixelWidth][charPixelHeight];
    }
    
    public Color getForeColor() {
        return this.foreColor;
    }  
    
    public Color getBackColor() {
        return this.backColor;
    }
    
    public int getImgChar() {
        return this.rawImgChar;
    }
    
    public void setBackColor(Color newBackColor) {
        this.backColor = newBackColor;
    }
}