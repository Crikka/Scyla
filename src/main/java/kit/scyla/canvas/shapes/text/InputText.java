package kit.scyla.canvas.shapes.text;

import android.content.Context;
import android.graphics.Point;

import kit.scyla.canvas.facets.collision.InputTextCollisionFacet;
import kit.scyla.canvas.facets.drawing.InputTextDrawingFacet;
import kit.scyla.core.facets.image.ImageFacet;

/**
 * Created with IntelliJ
 * Created by Nelaupe Lucas
 * Date 13/10/14
 */
public class InputText extends Text {

    public InputText(Context context, int text, Point point) {
        super(context, text, point);

        defineDrawingFacet(new InputTextDrawingFacet());
        defineCollisionFacet(new InputTextCollisionFacet());
    }

    @Override
    public ImageFacet<Text> imageFacet() {
        // TODO
        return null;
    }
}
