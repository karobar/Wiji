package tjp.wiji.drawing;

import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;

import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;

import tjp.wiji.file.FileLoader;

public class ShaderContext {
    private ShaderProgram shader;
    private static final String DEFAULT_VERTEX_SHADER = 
        "attribute vec4 a_position;\n" +
        "attribute vec4 a_color;\n" +
        "attribute vec2 a_texCoord0;\n" +
        "attribute vec4 a_frontColor;\n" +
        "attribute vec4 a_backColor;\n" +

        "uniform mat4 u_projTrans;\n" +

        "varying vec4 v_color;\n" +
        "varying vec2 v_texCoords;\n" +
        "varying vec4 v_frontColor;\n" +
        "varying vec4 v_backColor;\n" +

        "void main() {\n" +
        "    v_color = a_color;\n" +
        "    v_texCoords = a_texCoord0;\n" +
        "    v_frontColor = a_frontColor;\n" +
        "    v_backColor = a_backColor;\n" +

        "    gl_Position = u_projTrans * a_position;\n" +
        "}";
    
    private static final String DEFAULT_FRAGMENT_SHADER = 
          "#ifdef GL_ES\n" +
          "#define LOWP lowp\n" +
          "    precision mediump float;\n" +
          "#else\n" +
          "    #define LOWP\n" +
          "#endif\n" +

          "varying LOWP vec4 v_color;\n" +
          "varying vec2 v_texCoords;\n" +
          "varying vec4 v_backColor;\n" +
          "varying vec4 v_frontColor;\n" +

          "uniform sampler2D u_texture;\n" +

          "void main() {\n" +
          "    vec4 switchColor = v_color * texture2D(u_texture, v_texCoords);\n" +
          "    if(switchColor.r == 1.0f && switchColor.g == 1.0f && switchColor.b == 1.0f) {\n" +
          "        gl_FragColor = v_frontColor;\n" +
          "    }\n" +
          "    else {\n" +
          "        gl_FragColor = v_backColor;\n" +
          "    }\n" +
          "}";
    
    public ShaderContext() throws URISyntaxException {
        createShaders();
    }
    
    private void createShaders() throws URISyntaxException {
        String vertexShader = DEFAULT_VERTEX_SHADER;
        String fragmentShader = DEFAULT_FRAGMENT_SHADER;
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
