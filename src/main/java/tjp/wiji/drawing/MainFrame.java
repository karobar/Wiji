package tjp.wiji.drawing;

import static com.google.common.base.Preconditions.checkNotNull;

import java.net.URISyntaxException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import tjp.wiji.event.EventProcessor;
import tjp.wiji.event.GameEvent;
import tjp.wiji.gui.Screen;
import tjp.wiji.gui.ScreenContext;
import tjp.wiji.representations.ImageRepresentation;

public abstract class MainFrame extends ApplicationAdapter
        implements InputProcessor {

    private SpriteBatch batch;
    
    private EventProcessor eventProcessor; 
    
    private Texture spriteSheet;
    private long startTime = 0;
    private long timeSinceLastRender = 0;
    
    private BitmapContext bitmapContext;
    private ShaderContext shaderContext;
    private ScreenContext screenContext;
    
    private final int widthInSlots, heightInSlots;
    private final static Logger logger = LoggerFactory.getLogger(MainFrame.class);
    
    public MainFrame(final BitmapContext bitmapContext,
            final int widthInSlots, final int heightInSlots) {

        this.bitmapContext = bitmapContext;
        this.widthInSlots = widthInSlots;
        this.heightInSlots = heightInSlots;
    }
    
    @Override
    public void create() {        
        //eventProcessor = new EventProcessor(currentScreen);
        Gdx.input.setInputProcessor(this);
        
        batch = new SpriteBatch();
        
        bitmapContext.init();
        spriteSheet = new Texture(bitmapContext.getCharsheet());
        try {
            shaderContext = new ShaderContext();
        } catch (URISyntaxException e) {
            Gdx.app.error("ERROR", "my error message", e);
            System.exit(0);
        }
        this.screenContext = createStartingScreenContext();
    }
    
    protected abstract ScreenContext createStartingScreenContext();
    
    protected BitmapContext getBitmapContext() {
        return bitmapContext;
    }
    
    public Screen getCurrentScreen() {
        return screenContext.getCurrentScreen();
    }
    
    protected int getImageGridHeight() {
        return heightInSlots;
    }
    
    protected int getImageGridWidth() {
        return widthInSlots;
    }
    
    @Override
    public final boolean keyDown(int keycode) {
        getCurrentScreen().handleEvent(new GameEvent(keycode));
        return false;
    }

    @Override
    public final boolean keyTyped(char character) {
        getCurrentScreen().handleEvent(new GameEvent(character));
        return false;
    }

    @Override
    public final boolean keyUp(int keycode) {
        return false;
    }
    
    @Override
    public final boolean mouseMoved(int screenX, int screenY) {
        return false;
    }
    
    @Override
    public void dispose() {
        batch.dispose();
        spriteSheet.dispose();
        shaderContext.getShader().dispose();
        disposeHook();
        System.exit(0);
    }
    
    protected abstract void disposeHook();

    @Override
    public void render () {
//        eventProcessor.processEventList(getCurrentScreen());
        
        timeSinceLastRender = System.nanoTime() - startTime;
        ImageRepresentation[][] cellsToDraw = getCurrentScreen().render(timeSinceLastRender/1000, 
                getImageGridWidth(), getImageGridHeight());
        timeSinceLastRender = 0;
        startTime = System.nanoTime();
        
        Gdx.gl.glClearColor(0f, 1f, 0f, 0f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        batch.setShader(shaderContext.getShader());
        int pixelWidth = bitmapContext.getCharPixelWidth();
        int pixelHeight = bitmapContext.getCharPixelHeight();
        for(int row = 0; row < cellsToDraw.length ; row++) {
            for(int col = 0; col < cellsToDraw[row].length ; col++) {
                ImageRepresentation currCell = cellsToDraw[row][col];
                drawBatch(row, col, pixelWidth, pixelHeight, currCell);
                batch.flush();
            }
        }
        
        batch.end();
    }
    
    private void configureShaderForCell(final Color backColor, final Color foreColor) {
        shaderContext.getShader().setAttributef("a_backColor",
                backColor.getClampedRed(), backColor.getClampedGreen(), backColor.getClampedBlue(), 1);
        shaderContext.getShader().setAttributef("a_frontColor",
                foreColor.getClampedRed(), foreColor.getClampedGreen(), foreColor.getClampedBlue(), 1);
    }
    
    private void drawBatch(int row, int col, int pixelWidth, int pixelHeight, 
            ImageRepresentation currCell) {
        
        checkNotNull(currCell);
        checkNotNull(currCell.getBackColor());
        checkNotNull(currCell.getForeColor());
        configureShaderForCell(currCell.getBackColor(), currCell.getForeColor());
        
        int graphicIndex = currCell.getImgChar();
        int charsheetWidth = bitmapContext.getCharsheetGridHeight();
        int charCodeX = graphicIndex % charsheetWidth;
        int charCodeY = graphicIndex / charsheetWidth;

        int x = row * pixelWidth;
        int y = (heightInSlots - col - 1) * pixelHeight;
        batch.draw(
                spriteSheet, 
                //coordinates in screen space
                x, y,
                //coordinates of the scaling and rotation origin 
                //relative to the screen space coordinates
                x, y,
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
                pixelWidth, pixelHeight,
                //whether to flip the sprite horizontally or vertically
                false,false);
    }

    @Override
    public final boolean scrolled(int amount) {
        return false;
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
