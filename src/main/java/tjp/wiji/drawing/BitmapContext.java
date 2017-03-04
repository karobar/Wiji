package tjp.wiji.drawing;

import java.awt.image.BufferedImage;
import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLClassLoader;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;

import tjp.wiji.file.FileLoader;
import tjp.wiji.representations.Graphic;
import tjp.wiji.representations.GraphicRepresentation;
import tjp.wiji.representations.ImageRepresentation;

public class BitmapContext {
    public final static int DEFAULT_CHAR_PIXEL_WIDTH  = 8;
    public final static int DEFAULT_CHAR_PIXEL_HEIGHT = 12;
    public final static int CHARSHEET_GRID_HEIGHT = 16;
    private final int charPixelWidth, charPixelHeight;
    private FileHandle charsheet; 
    private final static String DEFAULT_CHARSHEET = "charsheet_8x12.bmp";
    
    /**
     * Defaults for char pixel height, width, and charsheet.
     * @throws URISyntaxException 
     */
    public BitmapContext() throws URISyntaxException {
        this.charPixelWidth = DEFAULT_CHAR_PIXEL_WIDTH;
        this.charPixelHeight = DEFAULT_CHAR_PIXEL_HEIGHT;
    }
    
    public BitmapContext(final int charPixelWidth, final int charPixelHeight,
            final FileHandle charsheet) {
        
        this.charPixelWidth = charPixelWidth;
        this.charPixelHeight = charPixelHeight;
        this.charsheet = charsheet;
    }
    
    /**
     * IMPORTANT: You can only reference Gdx.files.internal during/after
     * the create() method has been called.
     */
    public void init() {
        this.charsheet = Gdx.files.internal("charsheet_8x12.bmp");
    }
    
    public int getCharPixelWidth() {
        return charPixelWidth;
    }
    
    public int getCharPixelHeight() {
        return charPixelHeight;
    }
    
    public int getCharsheetGridHeight() {
        return CHARSHEET_GRID_HEIGHT;
    }
    
    public FileHandle getCharsheet() {
        return charsheet;
    }
    
    /**
     * Nifty tool for translating a bitmap image into a two-dimensional 
     * array of ImageRepresentations, where the background color of every
     * ImageRepresentation is grabbed from the corresponding pixel of the 
     * bitmap image.
     */
    public ImageRepresentation[][] bmpToImRep(BufferedImage inBMP){
        ImageRepresentation[][] finishedImRepMatrix = 
                new ImageRepresentation[inBMP.getWidth()][inBMP.getHeight()];
        
        for(int i=0;i<inBMP.getWidth();i++){
            for(int j=0;j<inBMP.getHeight(); j++){
                finishedImRepMatrix[i][j] = 
                        new GraphicRepresentation(new Color(inBMP.getRGB(i,j)), Graphic.FILLED_CELL,
                                getCharPixelWidth(), getCharPixelHeight());
            }
        }
        return finishedImRepMatrix;
    }
}
