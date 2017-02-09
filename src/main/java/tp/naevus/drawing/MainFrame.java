package tp.naevus.drawing;

import java.net.URISyntaxException;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import tp.naevus.event.EventProcessable;
import tp.naevus.event.EventProcessor;
import tp.naevus.event.GameEvent;
import tp.naevus.gui.Screen;
import tp.naevus.representations.ImageRepresentation;

public abstract class MainFrame extends ApplicationAdapter
        implements InputProcessor {

    private SpriteBatch batch;
    
    private EventProcessor eventProcessor;
    
    private Screen grandparentScreen;
    private Screen previousScreen;
    private Screen currentScreen; 
    
    private Texture spriteSheet;
    private long startTime = 0;
    private long timeSinceLastRender = 0;
    
    private BitmapContext bitmapContext;
    private ShaderContext shaderContext;
    
    private final int widthInSlots, heightInSlots;
    
    public MainFrame(final BitmapContext bitmapContext,
            final int widthInSlots, final int heightInSlots, final Screen startingScreen) {

        this.bitmapContext = bitmapContext;
        this.widthInSlots = widthInSlots;
        this.heightInSlots = heightInSlots;
        this.currentScreen = this.previousScreen = this.grandparentScreen = startingScreen;
    }
    
    @Override
    public void create() {        
        eventProcessor = new EventProcessor(currentScreen);
        Gdx.input.setInputProcessor(this);
        
        batch = new SpriteBatch();
        spriteSheet = new Texture(bitmapContext.getCharsheet());
        try {
            shaderContext = new ShaderContext();
        } catch (URISyntaxException e) {
            Gdx.app.error("ERROR", "my error message", e);
            System.exit(0);
        }
        createHook();
    }
    
    protected abstract void createHook();
    
    public Screen getCurrentScreen() {
        return this.currentScreen;
    }
    
    private BitmapContext getBitmapContext() {
        return bitmapContext;
    }
    
    protected int getImageGridHeight() {
        return heightInSlots;
    }
    
    protected int getImageGridWidth() {
        return widthInSlots;
    }
    
    @Override
    public boolean keyDown(int keycode) {
        return false;
    }
    @Override
    public boolean keyTyped(char character) {
        return false;
    }
    @Override
    public boolean keyUp(int keycode) {
        return false;
    }
    
    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public void render () {
        eventProcessor.processEventList(currentScreen);
        
        timeSinceLastRender = System.nanoTime() - startTime;
        ImageRepresentation[][] cellsToDraw = currentScreen.render(timeSinceLastRender/1000, 
                getImageGridWidth(), getImageGridHeight());
        timeSinceLastRender = 0;
        startTime = System.nanoTime();
        
        Gdx.gl.glClearColor(1f, 0f, 1f, 1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        batch.setShader(shaderContext.getShader());
        
        int pixelWidth = bitmapContext.getCharPixelWidth();
        int pixelHeight = bitmapContext.getCharPixelHeight();
        for(int row = 0; row < cellsToDraw.length ; row++) {
            for(int col = 0; col < cellsToDraw[row].length ; col++) {
                ImageRepresentation currCell = cellsToDraw[row][col];
                drawBatch(row, col, pixelWidth, pixelHeight, currCell);
            }
        }
        batch.end();
    }
    
    private void configureShaderForCell(final Color backColor, final Color foreColor) {
        shaderContext.getShader().setAttributef("a_backColor",
                backColor.getRed(), backColor.getGreen(), backColor.getBlue(), 1);
        shaderContext.getShader().setAttributef("a_frontColor",
                foreColor.getRed(), foreColor.getGreen(), foreColor.getBlue(), 1);
    }
    
    private void drawBatch(int row, int col, int pixelWidth, int pixelHeight, 
            ImageRepresentation currCell) {

        configureShaderForCell(currCell.getBackColor(), currCell.getForeColor());
        
        int charCodeX = currCell.getImgChar() % getImageGridWidth();
        int charCodeY = currCell.getImgChar() / getImageGridWidth();

        batch.flush();
        batch.draw(
                spriteSheet, 
                //coordinates in screen space
                row * pixelWidth, (heightInSlots - col - 1) * pixelHeight,
                //coordinates of the scaling and rotation origin 
                //relative to the screen space coordinates
                row * pixelWidth, (heightInSlots - col - 1) * pixelHeight,
                //width and height in pixels
                pixelWidth, pixelHeight, 
                //scale of the rectangle around originX/originY
                1,1,
                //the angle of counter clockwise rotation of the 
                //rectangle around originX/originY
                0,
                //coordinates in texel space
                charCodeX * pixelWidth, charCodeY * pixelHeight, 
                //source width and height in texels
                8, 12,
                //whether to flip the sprite horizontally or vertically
                false,false);
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }

    /**
     * Used when exiting a screen to make sure that all pointers decrement by 
     * one.
     */
    void stepScreenBackwards() {
        currentScreen  = previousScreen;
        previousScreen = grandparentScreen;
    }

    /**
     * used when creating a new screen to make sure that the user can return to 
     * the screen they were just at.
     */
    void stepScreenForwards(Screen newScreen) {
        grandparentScreen = previousScreen;
        previousScreen = currentScreen;
        currentScreen = newScreen;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }
}
