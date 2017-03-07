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
    
    private static final Set<Character> NUMBERS = new HashSet<Character>(
            Arrays.asList('0', '1', '2', '3', '4', '5', '6', '7', '8', '9'));
    
    public boolean isNumber() {
        return isCharOfType(this.charCode, NUMBERS);
    }
    
    public boolean isNormalTypingKey() {
        return isCharOfType(this.charCode, NORMAL_CHARS);
    }
    
    private boolean isCharOfType(char searchChar, Set<Character> set) {
        if ('\u0000' == searchChar) {
            return false;
        }
        
        boolean found = false;
        // think the Character wrapper causes problems with ==
        for (Character ch : set) {
            if (ch.equals(searchChar)) {
                found = true;
            }
        }
        return found;
    }
}
