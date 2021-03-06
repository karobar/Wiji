package tjp.wiji.gui;

import tjp.wiji.drawing.BitmapContext;
import tjp.wiji.drawing.CellDimensions;
import tjp.wiji.drawing.Color;
import tjp.wiji.representations.ImageRepresentation;

/**
 * Basically a Collection<GUIText> but with extra handling of current item.
 * @author      Travis Pressler (travisp471@gmail.com)
 * @version     %I%, %G%
 */
public class ScreenTextList extends TextList {
    public static class ScreenTextListBuilder {
        private BitmapContext bitmapContext;

        private boolean centered = false;
        //private CellDimensions dims;
        private Color inactiveColor, activeColor;
        private String initialItem;
        private int x, y;
        
        public ScreenTextListBuilder activeColor(Color activeColor) {
            this.activeColor = activeColor;
            return this;
        }
        
        public ScreenTextListBuilder bitmapContext(BitmapContext bitmapContext) {
            this.bitmapContext = bitmapContext;
            return this;
        }
        
        /**
         * TODO: add all sorts of checks to make sure required combinations
         * are provided in full.
         * @return
         */
        public ScreenTextList build() {
            return new ScreenTextList(this);
        }
        
        public ScreenTextListBuilder centered() {
            this.centered = true;
            return this;
        }
        
        public ScreenTextListBuilder color(Color color) {
            this.inactiveColor = color;
            this.activeColor = color;
            return this;
        }
        
        public ScreenTextListBuilder inactiveColor(Color inactiveColor) {
            this.inactiveColor = inactiveColor;
            return this;
        }
        
        public ScreenTextListBuilder initialItem(String initialItem) {
            this.initialItem = initialItem;
            return this;
        }
        
        public ScreenTextListBuilder x(int x) {
            this.x = x;
            return this;
        }
        
        public ScreenTextListBuilder y(int y) {
            this.y = y;
            return this;
        }
    }
    public static ScreenTextListBuilder newBuilder() {
        return new ScreenTextListBuilder();
    }
    protected BitmapContext bitmapContext;
    
    protected boolean centered = false;
    
    protected int screenX, screenY;
    
    protected ScreenTextList() { }
    
    /**
     * Basic Constructor for TextCollection for text which does not change 
     * colors and contains one text item.
     * @param 
     * @param inInactive color of the text
     * @param x position of upper-left corner of the list (assuming 
     *        position is not explicitly assigned with GUIText.specX)
     * @param y position of upper-left corner of the list (assuming 
     *        position is not explicitly assigned with GUIText.specY)
     */
    public ScreenTextList(BitmapContext bitmapContext, CellDimensions dimension,
              Color inInactive, String initialItem, int x, int y) {
    
          this.bitmapContext = bitmapContext;
          this.inactiveColor = inInactive;
          this.activeColor = null;
          this.screenX = x;
          this.screenY = y;
          this.add(new GUItext(initialItem));
    }
    
    /**
     * Augmented Base constructor for text which changes color and which does not have
     * coordinates. 
     * @param inInactive color of the text
     * @param inputActive color of the text when active
   */
   public ScreenTextList(final BitmapContext bitmapContext, 
           Color inInactive, Color inputActive){

       this.bitmapContext = bitmapContext;
       this.inactiveColor = inInactive;
       this.activeColor   = inputActive;
   }
    
    /**
      * Augmented Base constructor for text which changes color. 
      * @param inInactive color of the text
      * @param inputActive color of the text when active
      * @param x position of upper-left corner of the list (assuming 
      *        position is not explicitly assigned with GUIText.specX)
      * @param y position of upper-left corner of the list (assuming 
      *        position is not explicitly assigned with GUIText.specY)
    */
    public ScreenTextList(final BitmapContext bitmapContext, Color inInactive, Color inputActive, 
            int x, int y){

        this.bitmapContext = bitmapContext;
        this.inactiveColor = inInactive;
        this.activeColor   = inputActive;
        this.screenX = x;
        this.screenY = y;
    }
   
    /**
      * Basic Constructor for TextCollection for text which does not change 
      * colors.
      * @param inInactive color of the text
      * @param x position of upper-left corner of the list (assuming 
      *        position is not explicitly assigned with GUIText.specX)
      * @param y position of upper-left corner of the list (assuming 
      *        position is not explicitly assigned with GUIText.specY)
    */
    public ScreenTextList(BitmapContext bitmapContext, Color inInactive, int x, int y) {
        this.bitmapContext = bitmapContext;
        this.inactiveColor = inInactive;
        this.activeColor = null;
        this.screenX = x;
        this.screenY = y;
    }

     /**
     * Basic Constructor for TextCollection for text which does not change 
     * colors and contains one text item.
     * @param 
     * @param inInactive color of the text
     * @param x position of upper-left corner of the list (assuming 
     *        position is not explicitly assigned with GUIText.specX)
     * @param y position of upper-left corner of the list (assuming 
     *        position is not explicitly assigned with GUIText.specY)
   */
   public ScreenTextList(BitmapContext bitmapContext,
           Color inInactive, String initialItem, int x, int y) {

       this.bitmapContext = bitmapContext;
       this.inactiveColor = inInactive;
       this.activeColor = null;
       this.screenX = x;
       this.screenY = y;
       this.add(new GUItext(initialItem));
   }
    
    protected ScreenTextList(ScreenTextListBuilder builder) {
        this.bitmapContext = builder.bitmapContext;
        
        
        
        if (builder.activeColor == null) {
            this.activeColor = GUIelement.DEFAULT_ACTIVE_COLOR;
        } else {
            this.activeColor = builder.activeColor;
        }
        if (builder.inactiveColor == null) {
            this.inactiveColor = GUIelement.DEFAULT_INACTIVE_COLOR;
        } else {
            this.inactiveColor = builder.inactiveColor;
        }
        
        this.screenX = builder.x;
        this.screenY = builder.y;
        
        this.centered = builder.centered;
        
        if (builder.initialItem != null) {
            add(new GUItext(builder.initialItem, activeColor, inactiveColor));
        }
    }
    
    @Override
    public ImageRepresentation determineCurrImg(BitmapContext bitmapContext, int currIndex,
            boolean isActive, Color parentActiveColor, Color parentInactiveColor) {
        // TODO Auto-generated method stub
        return null;
    }
    
    @Override
    public int getLength() {
        // TODO Auto-generated method stub
        return 0;
    }
    
    @Override
    public int getScreenX() {
        return screenX;
    }
    
    @Override
    public int getScreenY() {
        return screenY;
    }
    
    @Override
    public boolean isAncillary() {
        return false;
    }

    @Override
    public boolean isCentered() {
        return centered;
    }

    @Override
    public void overlayGUI(ImageRepresentation[][] mainImRepMatrix) {
        displayOnto(mainImRepMatrix, bitmapContext);
    }

    void setScreenX(int inX) {
        screenX = inX;
    }

    void setScreenY(int inY) {
        screenY = inY;
    }

    @Override
    protected void cycleUpHook() {}

    @Override
    protected void cycleDownHook() {}    
}
