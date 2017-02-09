package tjp.wiji.gui;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import tjp.wiji.drawing.BitmapContext;
import tjp.wiji.drawing.Color;
import tjp.wiji.representations.ImageRepresentation;
import tjp.wiji.representations.LetterRepresentation;

public abstract class TextCollection {
    private final List<GUIText> textCollection = new ArrayList<GUIText>();
    
    public final static Color DEFAULT_INACTIVE_COLOR = Color.WHITE;
    public final static Color DEFAULT_ACTIVE_COLOR   = Color.YELLOW;
    public final static Color DEFAULT_ANCILLARY_TEXT_COLOR = Color.GRAY;
    Color inactiveColor = Color.WHITE;
    Color activeColor = Color.WHITE;

    int currentChoiceIndex = 0;

    /**
     * Adds a GUIText element along with its corresponding ObjectTemplate to the 
     * TextCollection.
     * For example, add a new GUIText with the textString "Torch" along with a 
     * specific ObjectTemplate with the textString Torch.  This is useful for 
     * the inventory screen, for example. This providese a tie between the GUI 
     * textString object and a logical game object.
     * @param textString the textString to be displayed
     * @param obj the object corresponding to the textString.
     */
    public boolean add(GUIText text) {
        return textCollection.add(text);
    }
    
    /**
     * Returns the maximum-length textString of a GUIText object within the 
     * current TextCollection.
     * @return the width of the GUI item
    */
    public int getWidth() {
        int max = textCollection.get(0).getName().length();
        for(int i = 0; i < textCollection.size(); i++) {
            if (textCollection.get(i).getName().length() > max) {
                max = textCollection.get(i).getName().length();
            }
        }
        return max;
    }
    
    public int getHeight() {
        return textCollection.size();
    }
    
    public String getCurrentChoiceName() {
        return textCollection.get(currentChoiceIndex).getName();
    }
    
    public GUIText getCurrentChoice() {
        return textCollection.get(currentChoiceIndex);
    }
    
    Color getInactiveColor() {
        return inactiveColor;
    }
    
    Color getActiveColor() {
        return activeColor;
    }
    
//    /**
//     * Adds ancillary textString (extra textString not included in the list 
//     * proper).
//     */
//    public void addAncillaryText(GUIText infoText) {
//        ancillaryText.add(infoText);
//    }
    
    /**
     * Moves the current choice index up (or left), wraps around.
     */
    public void cycleUp() {
        if(currentChoiceIndex > 0) {
            currentChoiceIndex = currentChoiceIndex - 1;
        }
        else if (currentChoiceIndex == 0){
            currentChoiceIndex = textCollection.size()-1;
        }
        else{
            System.out.println(
                    "yo, cycleActiveUp in ChoiceList is bein' wierd");
        }
        
        if(textCollection.get(currentChoiceIndex) instanceof AncillaryGUIText) {
            cycleUp();
        }
    }
    
    /**
     * Moves the current choice index down (or right), wraps around.
     */
    public void cycleDown() {
        currentChoiceIndex = (currentChoiceIndex + 1) % textCollection.size();
        if(textCollection.get(currentChoiceIndex) instanceof AncillaryGUIText) {
            cycleDown();
        }
    }
    
    public abstract int getScreenX();
    
    public abstract int getScreenY();
    
    /**
     * Overlays the TextCollection onto the specified ImageRepresentation grid.
     * For ScreenText, this is simply a displayOnto. For MapText, the text must
     * first be translated from map-space to screen-space.
     * @param mainImRepMatrix 
     */
    public abstract void overlayGUI(ImageRepresentation[][] mainImRepMatrix);
    
    /**
     * Overwrites screen squares with the choiceList.
     * TODO: probably move this to make this a non-static method of Screen
     * PRECONDITION:  Given a display area (usually MainScreen.mainImRepMatrix)
     * POSTCONDITION: (Over)writes ImageRepresentations onto the given display 
     *                matrix, taken from the ints translated from all 
     *                GUIText.textString within the TextCollection
     * @param displayArea the displayArea which will be over-written
     */
    public void displayOnto(ImageRepresentation[][] displayArea, BitmapContext bitmapContext) {
        int currentX = getScreenX();
        int currentY = getScreenY();
        //cycle through all the GUIText elements
        for(int i = 0; i < textCollection.size(); i++){
            GUIText currText = textCollection.get(i);
            //turns the choice's textString into an array of ints
            String choiceName = currText.getName();
            //loop over all the integers (representing chars)
            for(int j = 0; j < choiceName.length() ;j++){
                char currentLetter = choiceName.charAt(j);
                ImageRepresentation currentImg;
                if(currText instanceof AncillaryGUIText) {
                    currentImg = new LetterRepresentation(
                                    DEFAULT_ANCILLARY_TEXT_COLOR, 
                                    Color.BLACK, 
                                    currentLetter,
                                    bitmapContext.getCharPixelWidth(),
                                    bitmapContext.getCharPixelHeight());
                }
                else if((i == currentChoiceIndex) && activeColor != null){
                    currentImg = new LetterRepresentation(
                            this.getActiveColor(), 
                            Color.BLACK, 
                            currentLetter,
                            bitmapContext.getCharPixelWidth(),
                            bitmapContext.getCharPixelHeight());
                } 
                else {
                    currentImg = new LetterRepresentation(
                            this.getInactiveColor(), 
                            Color.BLACK,
                            currentLetter,
                            bitmapContext.getCharPixelWidth(),
                            bitmapContext.getCharPixelHeight());
                }        
                displayArea[(currText.specX >= 0) ? currText.specX + j :  currentX]
                           [(currText.specY >= 0) ? currText.specY : currentY] = currentImg;
                currentX++;
            }
            currentX = getScreenX();
            currentY++;
        }        
    }
}
