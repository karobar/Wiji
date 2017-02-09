package tp.naevus.drawing;

/**
 * A BooleanImage is a bitmap image translated into a two-dimensional array
 * of booleans, where a 'true' cell indicates that the current pixel is a 
 * foreground pixel (and therefore, a 'false' cell indicates a background pixel)
 * 
 * @author      Travis Pressler (travisp471@gmail.com)
 * @version     %I%, %G%
 */
public class BooleanImage{
    boolean[][] booleanArray;
    
    BooleanImage(int width, int height) {        
        booleanArray = new boolean[width][height];
        initialize();
    }  
    
    void flipOn(int x, int y) {
        booleanArray[x][y] = true;
    }
    
    void flipOff(int x, int y) {
        booleanArray[x][y] = false;
    }
    
    int getWidth() {
        return booleanArray.length;
    }
    
    int getHeight() {
        return booleanArray[0].length;
    }
    
    boolean isForeground(int x, int y) {
        return booleanArray[x][y];
    }
    
    private void initialize() {
        for (int i = 0; i < booleanArray.length; i++) {
            for (int j = 0; j < booleanArray[0].length; j++) {
                flipOff(i,j);
            }
        }
    }
}
