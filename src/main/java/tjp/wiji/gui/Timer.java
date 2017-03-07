package tjp.wiji.gui;

public class Timer extends GUItext {
    private static final int SECONDS_IN_MINUTE = 60;
    private static final int MINUTES_IN_HOUR = 60;
    
    public Timer(String text) {
        super(text);
    }
    
    public Timer(String text, boolean isAncillary) {
        super(text, isAncillary);
    }

    
    public void setTime(int totalSeconds) {
        int numberOfHours = totalSeconds / (SECONDS_IN_MINUTE * MINUTES_IN_HOUR);
        int numberOfMinutes = totalSeconds / SECONDS_IN_MINUTE % MINUTES_IN_HOUR;
        int remainingSeconds =  totalSeconds % SECONDS_IN_MINUTE;
        
        this.clear();
        StringBuilder builder = new StringBuilder();
        
        if (numberOfHours > 0) {
            builder.append("" + numberOfHours + ":");
        }
        
        if (numberOfHours > 0 && numberOfMinutes < 10) {
            builder.append("0");   
        }
        builder.append("" + numberOfMinutes + ":");
        
        if (remainingSeconds < 10) {
            builder.append("0");
        }
        builder.append("" + remainingSeconds);
        this.concat(builder.toString());
    }
}
