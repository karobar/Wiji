package tjp.wiji.gui;

/**
 * A bag of screens
 * @author travis
 */
public class ScreenContext {
    private Screen greatGrandparentScreen;
    private Screen grandparentScreen;
    private Screen previousScreen;
    private Screen currentScreen;
    
    public void init(Screen startingScreen) {
        this.currentScreen = 
                this.previousScreen = 
                this.grandparentScreen = 
                this.greatGrandparentScreen = 
                startingScreen;
        this.currentScreen.stepToScreenTrigger();
    }
    
    /**
     * Used when exiting a screen to make sure that all pointers decrement by 
     * one.
     */
    public void stepScreenBackwards() {
        currentScreen  = previousScreen;
        previousScreen = grandparentScreen;
        grandparentScreen = greatGrandparentScreen;
        currentScreen.stepToScreenTrigger();
    }
    
    
    public void shadowStepForwards(Screen newScreen) {
        greatGrandparentScreen = grandparentScreen;
        grandparentScreen = previousScreen;
        currentScreen = newScreen;
        currentScreen.stepToScreenTrigger();
    }

    /**
     * used when creating a new screen to make sure that the user can return to 
     * the screen they were just at.
     */
    public void stepScreenForwards(Screen newScreen) {
        greatGrandparentScreen = grandparentScreen;
        grandparentScreen = previousScreen;
        previousScreen = currentScreen;
        currentScreen = newScreen;
        currentScreen.stepToScreenTrigger();
    }
    
    public Screen getCurrentScreen() {
        return currentScreen;
    }
}
