package tp.naevus.representations;

public enum Graphic {
    EMPTY_CELL(0),
    FILLED_CELL(219),
    LIGHT_MIST(176),
    MIST(177),
    HEAVY_MIST(178),
    DONUT(7),
    PLAYER_SYMBOL(64),
    TORCH_GRAPHIC(47),
    HAT_GRAPHIC(254),
    CENTERED_DOT(250),
    QUESTION_MARK(63);
    
    private int rawImageChar;
    
    private Graphic(int rawImageChar) {
        this.rawImageChar = rawImageChar;
    }
    
    public int getRawImageChar() {
        return rawImageChar;
    }
}
