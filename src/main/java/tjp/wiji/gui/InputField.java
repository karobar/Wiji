package tjp.wiji.gui;

import static com.google.common.base.Preconditions.checkNotNull;

import tjp.wiji.drawing.BitmapContext;
import tjp.wiji.drawing.Color;
import tjp.wiji.representations.Graphic;
import tjp.wiji.representations.GraphicRepresentation;
import tjp.wiji.representations.ImageRepresentation;
import tjp.wiji.representations.LetterRepresentation;

public class InputField extends GUIelement {
    private final GUItext inProgressText;
    private int emptyLength;
    private int maximumLength;

    public InputField(Color activeColor, Color inactiveColor, 
            int emptyLength, int maximumLength) {

        this.inProgressText = new GUItext("");
        setInactiveColor(checkNotNull(inactiveColor));
        setActiveColor(checkNotNull(activeColor));
        this.emptyLength = emptyLength;
        this.maximumLength = maximumLength;
    }
    
    public void push(char newChar) {
        if (inProgressText.getLength() <= maximumLength) {
            inProgressText.concat(Character.toString(newChar));
            System.out.println(inProgressText.getName());
        }
    }
    
    public void pop() {
        if (!isEmpty()) {
            inProgressText.removeLastChar();
            System.out.println(inProgressText.getName());
        }
    }
    
    public boolean isEmpty() {
        return inProgressText.isEmpty();
    }
    
    @Override
    public ImageRepresentation determineCurrImg(BitmapContext bitmapContext, 
            int currIndex, boolean isActive) {
        
        checkNotNull(bitmapContext);
        
        if (isEmpty()) {
            return handleEmpty(bitmapContext, isActive);
        } else {
            return handleNonEmpty(bitmapContext, currIndex, isActive); 
        }
    }
    
    private ImageRepresentation handleNonEmpty(BitmapContext bitmapContext, 
            int currIndex, boolean isActive) {
        
        if (isActive){
            return new LetterRepresentation(
                    this.getActiveColor(), 
                    Color.BLACK, 
                    inProgressText.charAt(currIndex),
                    bitmapContext.getCharPixelWidth(),
                    bitmapContext.getCharPixelHeight());
        } else {
            checkNotNull(this.getInactiveColor());
            return new LetterRepresentation(
                    this.getInactiveColor(), 
                    Color.BLACK,
                    inProgressText.charAt(currIndex),
                    bitmapContext.getCharPixelWidth(),
                    bitmapContext.getCharPixelHeight());
        }
    }
    
    private ImageRepresentation handleEmpty(BitmapContext bitmapContext, boolean isActive) {
        if (isActive){
            return new GraphicRepresentation(
                    this.getActiveColor(), 
                    Color.BLACK, 
                    Graphic.FILLED_CELL,
                    bitmapContext.getCharPixelWidth(),
                    bitmapContext.getCharPixelHeight());
        } else {
            checkNotNull(this.getInactiveColor());
            return new GraphicRepresentation(
                    this.getInactiveColor(), 
                    Color.BLACK,
                    Graphic.FILLED_CELL,
                    bitmapContext.getCharPixelWidth(),
                    bitmapContext.getCharPixelHeight());
        }
    }

    @Override
    public int getLength() {
        if (isEmpty()) {
            return emptyLength;
        } else {
            return inProgressText.getLength();
        }
    }
}
