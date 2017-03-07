package tjp.wiji.gui;

import tjp.wiji.drawing.Color;

public class TimeInputField extends InputField {   
    private static final int MIN_HOUR_COLON = 4;
    private static final int SECOND_MIN_COLON = 2;
    
    private static final int SECONDS_IN_MINUTE = 60;
    private static final int MINUTES_IN_HOUR = 60;
    
    private TimeInputField() {
        super(Color.BLACK, Color.BLUE, 0);
    }
    
    public TimeInputField(Color activeColor, Color inactiveColor, 
            int maximumLength, String emptyPrompt, Color backgroundColor) {

        super(activeColor, inactiveColor, maximumLength, emptyPrompt, backgroundColor);
    }
    
    @Override
    public void pop() {
        int textLength = inProgressText.getLength();
        
        if (!isEmpty()) {
            inProgressText.removeLastChar();
            rectifyColons();
        }
    }
    
    @Override
    public void push(char newChar) {
        int textLength = inProgressText.getLength();
        if (textLength < maximumLength) {
            inProgressText.concat(Character.toString(newChar));
            rectifyColons();
        }
    }  
    
    private void rectifyColons() {
        inProgressText.removeAll(':');
        rectifyColonsHelper(MIN_HOUR_COLON);
        rectifyColonsHelper(SECOND_MIN_COLON);
    }
    
    private void rectifyColonsHelper(int col) {
        int insertIndex = inProgressText.getLength() - col;
        if (insertIndex > 0) {
            inProgressText.insertChar(':', insertIndex);
        }
    }
    
    public int getNumberOfSeconds() {
        if (inProgressText.toString().isEmpty()) {
            return 0;
        }
        
        String[] splitString = inProgressText.toString().split(":");
        int chunks = splitString.length;
        
        int seconds= 0, minutes = 0, hours = 0;
        seconds = Integer.parseInt(splitString[chunks-1]);
        if (chunks > 1) {
            minutes = Integer.parseInt(splitString[chunks-2]);
        }
        if (chunks > 2) {
            hours = Integer.parseInt(splitString[chunks-3]);
        }
        return seconds + 
                (SECONDS_IN_MINUTE * minutes) + 
                (SECONDS_IN_MINUTE * MINUTES_IN_HOUR * hours);
    }
}
