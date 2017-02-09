package tp.naevus.drawing;

import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;

import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;

public class ShaderContext {
    private ShaderProgram shader;
    
    public ShaderContext() throws URISyntaxException {
        createShaders();
    }
    
    private void createShaders() throws URISyntaxException {
        String vertexShader = makeFileHandle("vertex.glsl").readString();
        String fragmentShader = makeFileHandle("fragment.glsl").readString();
        shader = new ShaderProgram(vertexShader, fragmentShader);
        ShaderProgram.pedantic = false;
    }
    
    private FileHandle makeFileHandle(String string) throws URISyntaxException {
        URL path = getClass().getResource(string);
        File charFile = new File(path.toURI());
        return new FileHandle(charFile);
    }
    
    public ShaderProgram getShader() {
        return shader;
    }
}
