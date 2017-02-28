package tjp.wiji.gui;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;

import tjp.wiji.drawing.BitmapContext;
import tjp.wiji.event.EventProcessable;
import tjp.wiji.event.GameEvent;
import tjp.wiji.representations.ImageRepresentation;

/** 
 * A screen is a logical representation of a series of graphical elements and 
 * corresponding control instructions.
 * 
 * @author      Travis Pressler (travisp471@gmail.com)
 * @version     %I%, %G%
 */
public abstract class Screen implements EventProcessable {
    private final Collection<TextCollection> activeGUIElements = new HashSet<TextCollection>();

    final private BitmapContext bitmapContext;
    
    //how long an animation frame lasts
    final private long FRAME_TIME_IN_MS = 1000;
    //one millisecond is 1000 nanoseconds
    final private long NANO_TO_MS_FACTOR = 1000;
    
    private long nanosSinceLastRender = 0;
    
    protected Screen(BitmapContext bitmapContext) {
        this.bitmapContext = bitmapContext;
    }
    
    protected void addGUIelement(TextCollection GUIElement) {
        activeGUIElements.add(GUIElement);
    }
    
    protected BitmapContext getBitmapContext() {
        return bitmapContext;
    }
    
    protected abstract ImageRepresentation getCurrentCell(int i, int j);
    
    protected abstract void handleFrameChange();
    
    /**
     * Gets all GUI elements (both screen and map relative) and overrides 
     * the game element representations which are overlaid.
     */
    private void overlayGUI(ImageRepresentation[][] mainImRepMatrix) {
        for(TextCollection text : activeGUIElements) {
            text.overlayGUI(mainImRepMatrix); 
        }
    }
    
    /**
     * Load all cells into the main image representation matrix.
     */
    private void prepareReps(ImageRepresentation[][] mainImRepMatrix) {
        for(int i = 0; i < mainImRepMatrix.length; i++) {
            for(int j = 0; j < mainImRepMatrix[i].length; j++) {
                mainImRepMatrix[i][j] = getCurrentCell(i,j);
            }
        }
    }
    
    /**
     * Create a 2d array of ImageRepresentations which will form the final output to the screen.
     * @param inLastRenderTime
     * @return 
     */
    public ImageRepresentation[][] render(long inLastRenderTime, int width, int height) { 
        nanosSinceLastRender += inLastRenderTime;
        //this handles animation frame changes
        sendFrameChangeEvery(NANO_TO_MS_FACTOR * FRAME_TIME_IN_MS);
        ImageRepresentation[][] mainImRepMatrix = new ImageRepresentation[width][height];
        //first, prepare the representations (add them to mainImRepMatrix)
        prepareReps(mainImRepMatrix);
        //then, overlay all active GUI elements (add them to mainImRepMatrix,
        //overwriting the prepared reps)
        overlayGUI(mainImRepMatrix);
        return mainImRepMatrix;
    }

    protected void sendFrameChangeEvery(long frameTime) {
        if (nanosSinceLastRender > frameTime) {
            handleFrameChange();
            nanosSinceLastRender = 0;
        }
    }
}