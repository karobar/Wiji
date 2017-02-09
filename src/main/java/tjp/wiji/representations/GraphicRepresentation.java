package tjp.wiji.representations;

import tjp.wiji.drawing.Color;

public class GraphicRepresentation extends ImageRepresentation {
    public GraphicRepresentation(final Color foreColor, final Graphic image, 
            final int charPixelWidth, final int charPixelHeight) {

        super(foreColor, image.getRawImageChar(), charPixelWidth, charPixelHeight);
    }
    
    public GraphicRepresentation(Color foreColor, Color backColor, Graphic image, 
            final int charPixelWidth, final int charPixelHeight) {

        super(foreColor, backColor, image.getRawImageChar(), charPixelWidth, charPixelHeight);
    }

    public GraphicRepresentation(Graphic image, final int charPixelWidth,
            final int charPixelHeight) {
        
        super(image.getRawImageChar(), charPixelWidth, charPixelHeight);
    }
}
