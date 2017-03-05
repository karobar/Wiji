package tjp.wiji.gui;

import tjp.wiji.drawing.BitmapContext;
import tjp.wiji.drawing.Color;
import tjp.wiji.representations.ImageRepresentation;
import tjp.wiji.representations.LetterRepresentation;

public class InputField extends GUIelement {
    private Color inactiveColor, activeColor;
    private GUItext inProgressText;
    private int emptyLength;
    private BitmapContext bitmapContext;

    public InputField(BitmapContext bitmapContext, Color inactiveColor, Color activeColor, 
            int emptyLength) {

        this.bitmapContext = bitmapContext;
        this.inactiveColor = inactiveColor;
        this.activeColor = activeColor;
        this.emptyLength = emptyLength;
    }
    
    @Override
    public ImageRepresentation determineCurrImg(BitmapContext bitmapContext, 
            int currIndex, boolean isActive) {
 
        if (isActive && activeColor != null){
            return new LetterRepresentation(
                    this.getActiveColor(), 
                    Color.BLACK, 
                    '!',
                    bitmapContext.getCharPixelWidth(),
                    bitmapContext.getCharPixelHeight());
        } else {
            return new LetterRepresentation(
                    this.getInactiveColor(), 
                    Color.BLACK,
                    '?',
                    bitmapContext.getCharPixelWidth(),
                    bitmapContext.getCharPixelHeight());
        }
    }

    @Override
    public int getLength() {
        return emptyLength;
    }

}
