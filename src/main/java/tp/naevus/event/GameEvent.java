package tp.naevus.event;

/**
 * @author      Travis Pressler (travisp471@gmail.com)
 * @version     %I%, %G%
 */
public class GameEvent {
    int intCode;
    char charCode;
    
    public GameEvent(int keycode) {
        this.intCode = keycode;
    }
    
    public GameEvent(char keycode) {
        this.charCode = keycode;
    }
    
    public int getIntCode() {
        return this.intCode;
    }
    
    public char getCharCode() {
        return this.charCode;
    }
}
