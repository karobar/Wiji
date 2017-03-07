package tjp.wiji.gui;

import static com.google.common.base.Preconditions.checkNotNull;

import tjp.wiji.drawing.BitmapContext;
import tjp.wiji.drawing.Color;
import tjp.wiji.representations.Graphic;
import tjp.wiji.representations.GraphicRepresentation;
import tjp.wiji.representations.ImageRepresentation;
import tjp.wiji.representations.LetterRepresentation;

public class InputField extends GUIelement {
    // optional
    private Color backgroundColor;
    
    // optional
    private int emptyLength;

    private String emptyPrompt;

    protected final GUItext inProgressText;
    
    protected int maximumLength;
    
    protected InputField(Color activeColor, Color inactiveColor, int maximumLength) {
        this.inProgressText = new GUItext("");
        setCustomInactiveColor(checkNotNull(inactiveColor));
        setCustomActiveColor(checkNotNull(activeColor));
        this.maximumLength = maximumLength;
    }
    
    public InputField(Color activeColor, Color inactiveColor, 
            int emptyLength, int maximumLength) {

        this.inProgressText = new GUItext("");
        setCustomInactiveColor(checkNotNull(inactiveColor));
        setCustomActiveColor(checkNotNull(activeColor));
        this.emptyLength = emptyLength;
        this.maximumLength = maximumLength;
    }

    public InputField(Color activeColor, Color inactiveColor, 
            int maximumLength, String emptyPrompt, Color backgroundColor) {

        this(activeColor, inactiveColor, maximumLength);
        this.emptyPrompt = checkNotNull(emptyPrompt);
        this.backgroundColor = backgroundColor;
    }

    @Override
    public ImageRepresentation determineCurrImg(BitmapContext bitmapContext, 
            int currIndex, boolean isActive, Color parentActiveColor, Color parentInactiveColor) {
        
        checkNotNull(bitmapContext);
        
        if (isEmpty()) {
            if (emptyPrompt != null) {
                return handleEmptyPrompt(bitmapContext, currIndex, isActive);
            } else {
                return handleEmptyBlank(bitmapContext, isActive);
            }
        } else {
            return handleNonEmpty(bitmapContext, currIndex, isActive); 
        }
    }
    
    @Override
    public int getLength() {
        if (isEmpty()) {
            if (emptyPrompt != null) {
                return emptyPrompt.length();   
            } else {
                return emptyLength;
            }
        } else {
            return inProgressText.getLength();
        }
    }
    
    private ImageRepresentation handleEmptyBlank(BitmapContext bitmapContext, boolean isActive) {
        if (isActive){
            return new GraphicRepresentation(
                    this.getCustomActiveColor(), 
                    Color.BLACK, 
                    Graphic.FILLED_CELL,
                    bitmapContext.getCharPixelWidth(),
                    bitmapContext.getCharPixelHeight());
        } else {
            checkNotNull(this.getCustomInactiveColor());
            return new GraphicRepresentation(
                    this.getCustomInactiveColor(), 
                    Color.BLACK,
                    Graphic.FILLED_CELL,
                    bitmapContext.getCharPixelWidth(),
                    bitmapContext.getCharPixelHeight());
        }
    }
    
    private ImageRepresentation handleEmptyPrompt(BitmapContext bitmapContext, 
            int currIndex, boolean isActive) {
        char currChar = emptyPrompt.charAt(currIndex);
        
        if (isActive){
            return new LetterRepresentation(
                    this.getCustomActiveColor(), 
                    backgroundColor, 
                    currChar,
                    bitmapContext.getCharPixelWidth(),
                    bitmapContext.getCharPixelHeight());
        } else {
            checkNotNull(this.getCustomInactiveColor());
            return new LetterRepresentation(
                    this.getCustomInactiveColor(), 
                    backgroundColor,
                    currChar,
                    bitmapContext.getCharPixelWidth(),
                    bitmapContext.getCharPixelHeight());
        }
    }
    
    private ImageRepresentation handleNonEmpty(BitmapContext bitmapContext, 
            int currIndex, boolean isActive) {
        
        if (isActive){
            return new LetterRepresentation(
                    this.getCustomActiveColor(), 
                    Color.BLACK, 
                    inProgressText.charAt(currIndex),
                    bitmapContext.getCharPixelWidth(),
                    bitmapContext.getCharPixelHeight());
        } else {
            checkNotNull(this.getCustomInactiveColor());
            return new LetterRepresentation(
                    this.getCustomInactiveColor(), 
                    Color.BLACK,
                    inProgressText.charAt(currIndex),
                    bitmapContext.getCharPixelWidth(),
                    bitmapContext.getCharPixelHeight());
        }
    }
    
    public void insertChar(char insertChar, int index) {
        inProgressText.insertChar(insertChar, index);
    }
    
    @Override
    public boolean isAncillary() {
        return false;
    }
    
    public boolean isEmpty() {
        return inProgressText.isEmpty();
    }
    
    @Override
    public void overlayGUI(ImageRepresentation[][] mainImRepMatrix) {
    }
    
    public void pop() {
        if (!isEmpty()) {
            inProgressText.removeLastChar();
        }
    }
    
    public void push(char newChar) {
        if (inProgressText.getLength() <= maximumLength) {
            inProgressText.concat(Character.toString(newChar));
        }
    }
    
    public void pushLeft(char newChar) {
        if (inProgressText.getLength() <= maximumLength) {
            inProgressText.concatLeft(Character.toString(newChar));
        }
    }

    public void removeAll(char ch) {
        inProgressText.removeAll(ch);
    }

    protected void setEmptyPrompt(String emptyPrompt) {
        this.emptyPrompt = emptyPrompt;
    }

    @Override
    public String toString() {
        return inProgressText.toString();
    }
}
