package tp.naevus.drawing;

public class Color {
    public static final Color BLACK = new Color(0xFF000000);
    public static final Color BLUE = new Color(0xFF0000AA);
    public static final Color GREEN = new Color(0xFF00AA00);
    public static final Color CYAN = new Color(0xFF00AAAA);
    public static final Color RED = new Color(0xFFAA0000);
    public static final Color MAGENTA = new Color(0xFFAA00AA);
    public static final Color BROWN = new Color(0xFFAA5500);
    public static final Color LIGHT_GRAY = new Color(0xFFAAAAAA);
    public static final Color GRAY = new Color(0xFF555555);
    public static final Color LIGHT_BLUE = new Color(0xFF5555FF);
    public static final Color LIGHT_GREEN = new Color(0xFF55FF55);
    public static final Color LIGHT_CYAN = new Color(0xFF55FFFF);
    public static final Color LIGHT_RED = new Color(0xFFFF5555);
    public static final Color LIGHT_MAGENTA = new Color(0xFFFF55FF);
    public static final Color YELLOW = new Color(0xFFFFFF55);
    public static final Color WHITE = new Color(0xFFFFFFFF);
    public static final Color WOOD = new Color(0xFF4A2E20);
    public static final Color LIGHT_WOOD = new Color(0xFF573626);
    public static final Color CONTROL_COLOR = new Color(0xFF33236B);
    public static final Color CONTROL_FORECOLOR = WHITE;
    
    private int RGBhex;
    
    public Color(int RGBhex) {
        this.RGBhex = RGBhex;
    }
    
    public float getRed() {
        return ((RGBhex & RED_MASK) >> RED_SHIFT) / SCALE_FACTOR_255_TO_0_1;
    }
    
    public float getGreen() {
        return ((RGBhex & GREEN_MASK) >> GREEN_SHIFT) / SCALE_FACTOR_255_TO_0_1;
    }
    
    public float getBlue() {
        return (RGBhex & BLUE_MASK) / SCALE_FACTOR_255_TO_0_1;
    }
    
    private final static int RED_MASK = 0x00FF0000;
    private final static int RED_SHIFT = 16;
    private final static int GREEN_MASK = 0x0000FF00;
    private final static int GREEN_SHIFT = 8;
    private final static int BLUE_MASK = 0x000000FF;
    
    private final static float SCALE_FACTOR_255_TO_0_1 = 255f;
}
