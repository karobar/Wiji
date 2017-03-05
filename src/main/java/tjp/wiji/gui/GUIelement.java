package tjp.wiji.gui;

import tjp.wiji.drawing.BitmapContext;
import tjp.wiji.drawing.Color;
import tjp.wiji.representations.ImageRepresentation;

public abstract class GUIelement {
    public abstract int getLength();
    
    protected final static Color DEFAULT_INACTIVE_COLOR = Color.WHITE;
    protected final static Color DEFAULT_ACTIVE_COLOR   = Color.YELLOW;
    
    public abstract ImageRepresentation determineCurrImg(BitmapContext bitmapContext,
            int currIndex, boolean isActive);
    
    public int customX = -1;
    public int customY = -1;
    
    public Color activeColor, inactiveColor;
    
    public Color getActiveColor() {
        return activeColor;
    }
    
    public Color getInactiveColor() {
        return inactiveColor;
    }
}
