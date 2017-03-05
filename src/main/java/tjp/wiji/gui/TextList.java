package tjp.wiji.gui;

import java.util.ArrayList;
import java.util.List;
import tjp.wiji.drawing.BitmapContext;
import tjp.wiji.drawing.Color;
import tjp.wiji.representations.ImageRepresentation;
import tjp.wiji.representations.LetterRepresentation;

public abstract class TextList {
    private final List<GUIelement> textList = new ArrayList<GUIelement>();

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
    public boolean add(GUIelement text) {
        return textList.add(text);
    }
    
    /**
     * Returns the maximum-length textString of a GUIText object within the 
     * current TextCollection.
     * @return the width of the GUI item
    */
    public int getWidth() {
        int max = textList.get(0).getLength();
        for(int i = 0; i < textList.size(); i++) {
            if (textList.get(i).getLength() > max) {
                max = textList.get(i).getLength();
            }
        }
        return max;
    }
    
    public int getHeight() {
        return textList.size();
    }
    
//    public String getCurrentChoiceName() {
//        return textList.get(currentChoiceIndex).getName();
//    }
    
    public GUIelement getCurrentChoice() {
        return textList.get(currentChoiceIndex);
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
            currentChoiceIndex = textList.size()-1;
        }
        else{
            System.out.println(
                    "yo, cycleActiveUp in ChoiceList is bein' wierd");
        }
        
        if(textList.get(currentChoiceIndex) instanceof AncillaryGUItext) {
            cycleUp();
        }
    }
    
    /**
     * Moves the current choice index down (or right), wraps around.
     */
    public void cycleDown() {
        currentChoiceIndex = (currentChoiceIndex + 1) % textList.size();
        if(textList.get(currentChoiceIndex) instanceof AncillaryGUItext) {
            cycleDown();
        }
    }
    
    public abstract int getScreenX();
    
    public abstract int getScreenY();
    
    public abstract boolean isCentered();
    
    /**
     * Overlays the TextCollection onto the specified ImageRepresentation grid.
     * For ScreenText, this is simply a displayOnto. For MapText, the text must
     * first be translated from map-space to screen-space.
     * @param mainImRepMatrix 
     */
    public abstract void overlayGUI(ImageRepresentation[][] mainImRepMatrix);
    
    private int findCenterPoint(int width, int stringWidth) {
        return (width - stringWidth) / 2;
    }
    
    
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
        int currentY = getScreenY();
        //cycle through all the GUI elements
        for(int i = 0; i < textList.size(); i++){
            GUIelement currElement = textList.get(i);
            
            int currentX;
            if (this.isCentered()) {
                currentX = findCenterPoint(displayArea.length, currElement.getLength()); 
            } else {
                currentX = getScreenX();
            }
            for(int j = 0; j < currElement.getLength() ;j++){
                boolean isActive = (i == currentChoiceIndex);
                ImageRepresentation currentImg = currElement.determineCurrImg(bitmapContext, j, 
                        isActive);
                
                int x = (currElement.customX >= 0) ? currElement.customX + j :  currentX;
                int y = (currElement.customY >= 0) ? currElement.customY : currentY;
                if (x < displayArea.length && x >= 0 &&
                    y < displayArea[0].length && y >= 0) {

                    displayArea[x][y] = currentImg;
                }
                currentX++;
            }
            currentY++;
        }        
    }
}
