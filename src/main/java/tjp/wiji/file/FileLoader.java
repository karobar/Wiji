package tjp.wiji.file;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.files.FileHandle;

public class FileLoader {
    private URL getResource(String resource) {
        final List<ClassLoader> classLoaders = new ArrayList<ClassLoader>();
        classLoaders.add(Thread.currentThread().getContextClassLoader());
        classLoaders.add(FileLoader.class.getClassLoader());

        for (ClassLoader classLoader : classLoaders) {
            final URL url = getResourceWith(classLoader, resource);
            if (url != null) {
                return url;
            }
        }

        final URL systemResource = ClassLoader.getSystemResource(resource);
        if (systemResource != null) {
            return systemResource;
        } else {
            try {
                return new File(resource).toURI().toURL();
            } catch (MalformedURLException e) {
                return null;
            }
        }
    }

    private URL getResourceWith(ClassLoader classLoader, String resource) {
        if (classLoader != null) {
            return classLoader.getResource(resource);
        }
        return null;
    }
    
    public FileHandle getFileHandle(String URL) throws URISyntaxException {
        URL path = new FileLoader().getResource(URL);
        File charFile = new File(path.toURI());
        return new FileHandle(charFile);
    }
}
