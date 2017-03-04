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
public class ScreenTextCollection extends TextCollection {
    private int screenX, screenY;
    private BitmapContext bitmapContext;
    private boolean centered = false;
    
    public static class ScreenTextCollectionBuilder {
        private int x, y;
        private BitmapContext bitmapContext;
        private String initialItem;
        private Color inactiveColor, activeColor;
//        private int width = 0;
//        private int height = 0;
        private boolean centered = false;
        //private CellDimensions dims;
        
        public ScreenTextCollectionBuilder bitmapContext(BitmapContext bitmapContext) {
            this.bitmapContext = bitmapContext;
            return this;
        }
        
        public ScreenTextCollectionBuilder x(int x) {
            this.x = x;
            return this;
        }
        
        public ScreenTextCollectionBuilder y(int y) {
            this.y = y;
            return this;
        }
        
        public ScreenTextCollectionBuilder inactiveColor(Color inactiveColor) {
            this.inactiveColor = inactiveColor;
            return this;
        }
        
        public ScreenTextCollectionBuilder activeColor(Color activeColor) {
            this.activeColor = activeColor;
            return this;
        }
        
        public ScreenTextCollectionBuilder color(Color color) {
            this.activeColor = color;
            return this;
        }
        
        public ScreenTextCollectionBuilder initialItem(String initialItem) {
            this.initialItem = initialItem;
            return this;
        }
        
        public ScreenTextCollectionBuilder centered() {
            this.centered = true;
            return this;
        }
        
        /**
         * TODO: add all sorts of checks to make sure required combinations
         * are provided in full.
         * @return
         */
        public ScreenTextCollection build() {
            return new ScreenTextCollection(this);
        }
    }
    
    private ScreenTextCollection(ScreenTextCollectionBuilder builder) {
        this.bitmapContext = builder.bitmapContext;
        this.activeColor = builder.activeColor;
        this.inactiveColor = builder.inactiveColor;
        
        this.screenX = builder.x;
        this.screenY = builder.y;
        
        this.centered = builder.centered;
        
        if (builder.initialItem != null) {
            add(new GUIText(builder.initialItem));
        }
    }
    
    public static ScreenTextCollectionBuilder newBuilder() {
        return new ScreenTextCollectionBuilder();
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
    public ScreenTextCollection(BitmapContext bitmapContext, Color inInactive, int x, int y) {
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
   public ScreenTextCollection(BitmapContext bitmapContext,
           Color inInactive, String initialItem, int x, int y) {

       this.bitmapContext = bitmapContext;
       this.inactiveColor = inInactive;
       this.activeColor = null;
       this.screenX = x;
       this.screenY = y;
       this.add(new GUIText(initialItem));
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
  public ScreenTextCollection(BitmapContext bitmapContext, CellDimensions dimension,
          Color inInactive, String initialItem, int x, int y) {

      this.bitmapContext = bitmapContext;
      this.inactiveColor = inInactive;
      this.activeColor = null;
      this.screenX = x;
      this.screenY = y;
      this.add(new GUIText(initialItem));
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
    public ScreenTextCollection(final BitmapContext bitmapContext, Color inInactive, Color inputActive, 
            int x, int y){

        this.bitmapContext = bitmapContext;
        this.inactiveColor = inInactive;
        this.activeColor   = inputActive;
        this.screenX = x;
        this.screenY = y;
    }
    
    /**
     * Augmented Base constructor for text which changes color and which does not have
     * coordinates. 
     * @param inInactive color of the text
     * @param inputActive color of the text when active
   */
   public ScreenTextCollection(final BitmapContext bitmapContext, 
           Color inInactive, Color inputActive){

       this.bitmapContext = bitmapContext;
       this.inactiveColor = inInactive;
       this.activeColor   = inputActive;
   }
    
    @Override
    public int getScreenX() {
        return screenX;
    }
    
    @Override
    public int getScreenY() {
        return screenY;
    }
    
    void setScreenX(int inX) {
        screenX = inX;
    }
    
    void setScreenY(int inY) {
        screenY = inY;
    }
    
    @Override
    public void overlayGUI(ImageRepresentation[][] mainImRepMatrix) {
        displayOnto(mainImRepMatrix, bitmapContext);
    }

    @Override
    public boolean isCentered() {
        return centered;
    }
}
