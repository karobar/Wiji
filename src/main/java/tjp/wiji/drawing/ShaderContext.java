package tjp.wiji.drawing;

import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;

import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;

import tjp.wiji.file.FileLoader;

public class ShaderContext {
    private ShaderProgram shader;
    
    public ShaderContext() throws URISyntaxException {
        createShaders();
    }
    
    private void createShaders() throws URISyntaxException {
        String vertexShader = makeFileHandle("vertex.glsl").readString();
        String fragmentShader = makeFileHandle("fragment.glsl").readString();
        shader = new ShaderProgram(vertexShader, fragmentShader);
        shader.pedantic = false;
    }
    
    private FileHandle makeFileHandle(String string) throws URISyntaxException {
        return new FileLoader().getFileHandle(string);
    }
    
    public ShaderProgram getShader() {
        return shader;
    }
}
