package tjp.wiji.gui;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import tjp.wiji.drawing.BitmapContext;
import tjp.wiji.drawing.Color;
import tjp.wiji.representations.ImageRepresentation;

public class LongList<T extends Object> extends ScreenTextList {
    public static class LongListBuilder<T> {
        private BitmapContext bitmapContext;
        //        private int width = 0;
//        private int height = 0;
        private boolean centered = false;
        //private CellDimensions dims;
        private int endY;
        private Color inactiveColor, activeColor, tertiaryColor;
        private String initialItem;
        private int x, y;
        
        public LongListBuilder<T> activeColor(Color activeColor) {
            this.activeColor = activeColor;
            return this;
        }
        
        public LongListBuilder<T> bitmapContext(BitmapContext bitmapContext) {
            this.bitmapContext = bitmapContext;
            return this;
        }
        
        /**
         * TODO: add all sorts of checks to make sure required combinations
         * are provided in full.
         * @return
         */
        public  LongList<T> build() {
            return new LongList<T>(this);
        }
        
        public LongListBuilder<T> centered() {
            this.centered = true;
            return this;
        }
        
        public LongListBuilder<T> color(Color color) {
            this.inactiveColor = color;
            this.activeColor = color;
            return this;
        }
        
        public LongListBuilder<T> endY(int endY) {
            this.endY = endY;
            return this;
        }
        
        public LongListBuilder<T> inactiveColor(Color inactiveColor) {
            this.inactiveColor = inactiveColor;
            return this;
        }
        
        public LongListBuilder<T> initialItem(String initialItem) {
            this.initialItem = initialItem;
            return this;
        }
        
        public LongListBuilder<T> tertiaryColor(Color tertiaryColor) {
            this.tertiaryColor = tertiaryColor;
            return this;
        }
        
        public LongListBuilder<T> x(int x) {
            this.x = x;
            return this;
        }
        
        public LongListBuilder<T> y(int y) {
            this.y = y;
            return this;
        }
    }  
    
    public static <T> LongListBuilder<T> newLongListBuilder() {
        return new LongListBuilder<T>();
    }
    private int endY;
    
    private final LinkedHashMap<GUIelement, T> objMap = new LinkedHashMap<GUIelement, T>();

    private Color tertiaryColor;
    
    
    
    protected LongList(LongListBuilder<T> builder) {
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
        
        this.endY = builder.endY;
        this.tertiaryColor = builder.tertiaryColor;
        
        this.centered = builder.centered;
        
        if (builder.initialItem != null) {
            add(new GUItext(builder.initialItem, activeColor, inactiveColor));
        }
    }
    
    public void add(GUIelement element, T tiedObj) {
        objMap.put(element, tiedObj);
    }
    
    @Override
    protected int calculateOffset() {
        int topOfScreen = getScreenY();
        
        int totalHeightOnScreen = getListHeight();
        int halfHeight = totalHeightOnScreen / 2;
        if (currentChoiceIndex > getHeight() - halfHeight) {
            topOfScreen = getHeight() - totalHeightOnScreen + 1;
        } else if (currentChoiceIndex > halfHeight) {
            topOfScreen = currentChoiceIndex - halfHeight;
        } 
        return topOfScreen - 1;
    }

    @Override
    public void cycleDown() {
        currentChoiceIndex = (currentChoiceIndex + 1) % objMap.size();
        if (get(currentChoiceIndex).isAncillary()) {
            cycleDown();
        }
        cycleDownHook();
    }
   
    @Override
    protected void cycleDownHook() { }   
    
    /**
     * Moves the current choice index up (or left), wraps around.
     */
    public void cycleUp() {
        if(currentChoiceIndex > 0) {
            currentChoiceIndex = currentChoiceIndex - 1;
        }
        else if (currentChoiceIndex == 0){
            currentChoiceIndex = objMap.size()-1;
        }
        else {
            System.out.println("yo, cycleActiveUp in ChoiceList is bein' wierd");
        }     
        if (get(currentChoiceIndex).isAncillary()) {
            cycleUp();
        }
        cycleUpHook();
    }
    
    @Override
    protected void cycleUpHook() { }

    @Override
    protected GUIelement get(int index) {
        return (GUIelement) objMap.keySet().toArray()[index];
    }
    
    public T getCurrentElement() {
        return objMap.get(getCurrentChoice());
    }
    
    @Override
    public GUIelement getCurrentChoice() {
        return get(currentChoiceIndex);
    }
    
    @Override
    protected ImageRepresentation getCurrImg(GUIelement currElement, BitmapContext bitmapContext,
            int index, boolean isActive, boolean isTertiary) {

        if (isTertiary && !isActive ) {
            return currElement.determineCurrImg(bitmapContext, index, 
                    isActive, getActiveColor(), tertiaryColor);
        }
        else {    
            return currElement.determineCurrImg(bitmapContext, index, 
                    isActive, getActiveColor(), getInactiveColor());
        }
    }
    
    public T getFromKey(GUIelement key) {
        return objMap.get(key);
    }
    
    @Override
    public int getHeight() {
        return objMap.size();
    }
    
    @Override
    protected int getListHeight() {
        return endY - getScreenY();
    }
}
