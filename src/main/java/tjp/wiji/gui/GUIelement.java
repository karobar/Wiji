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
            int currIndex, boolean isActive, Color parentActiveColor, Color parentInactiveColor);
    
    public int customX = -1;
    public int customY = -1;
    
    private Color customActiveColor;
    private Color customInactiveColor;
    
    public Color getCustomActiveColor() {
        return customActiveColor;
    }
    
    public Color getCustomInactiveColor() {
        return customInactiveColor;
    }
    
    public void setCustomActiveColor(Color activeColor) {
        this.customActiveColor = checkNotNull(activeColor);
    }
    
    public void setCustomInactiveColor(Color inactiveColor) {
        this.customInactiveColor = checkNotNull(inactiveColor);
    }
    
    public abstract boolean isAncillary();
}
