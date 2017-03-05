package tjp.wiji.gui;

public class ScreenContext {
    private Screen grandparentScreen;
    private Screen previousScreen;
    private Screen currentScreen;
    
    public void init(Screen startingScreen) {
        this.currentScreen = this.previousScreen = this.grandparentScreen = startingScreen;
    }
    
    /**
     * Used when exiting a screen to make sure that all pointers decrement by 
     * one.
     */
    public void stepScreenBackwards() {
        currentScreen  = previousScreen;
        previousScreen = grandparentScreen;
        currentScreen.stepToScreenTrigger();
    }

    /**
     * used when creating a new screen to make sure that the user can return to 
     * the screen they were just at.
     */
    public void stepScreenForwards(Screen newScreen) {
        grandparentScreen = previousScreen;
        previousScreen = currentScreen;
        currentScreen = newScreen;
        currentScreen.stepToScreenTrigger();
    }
    
    public Screen getCurrentScreen() {
        return currentScreen;
    }
}
