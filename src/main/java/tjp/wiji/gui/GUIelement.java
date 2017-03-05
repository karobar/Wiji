package tjp.wiji.gui;

import static com.google.common.base.Preconditions.checkNotNull;

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
    
    private Color activeColor = DEFAULT_ACTIVE_COLOR;
    private Color inactiveColor = DEFAULT_INACTIVE_COLOR;
    
    public Color getActiveColor() {
        return activeColor;
    }
    
    public Color getInactiveColor() {
        return inactiveColor;
    }
    
    public void setActiveColor(Color activeColor) {
        this.activeColor = checkNotNull(activeColor);
    }
    
    public void setInactiveColor(Color inactiveColor) {
        this.inactiveColor = checkNotNull(inactiveColor);
    }
}
