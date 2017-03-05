package tjp.wiji.event;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * @author      Travis Pressler (travisp471@gmail.com)
 * @version     %I%, %G%
 */
public class GameEvent {
    private int intCode;
    private char charCode;
    
    private static final int A = 29;
    private static final int Z = 54;
    
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
    
    private static final Set<Character> NORMAL_CHARS = 
            new HashSet<Character>(Arrays.asList('a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j',
                    'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'));
    
    public boolean isNormalTypingKey() {
        boolean found = false;
        // think the Character wrapper causes problems with ==
        for (Character ch : NORMAL_CHARS) {
            if (ch.equals(this.charCode)) {
                found = true;
            }
        }
        
        return '\u0000' != this.charCode && found;
    }
}
