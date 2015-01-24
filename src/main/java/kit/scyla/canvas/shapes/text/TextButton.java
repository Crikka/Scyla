package kit.scyla.canvas.shapes.text;

import android.content.Context;
import android.graphics.Point;

import kit.scyla.canvas.facets.drawing.TextButtonDrawingFacet;

/**
 * Created with IntelliJ
 * Created by Nelaupe Lucas
 * Date 13/10/14
 */
public class TextButton extends Text {

    public TextButton(Context context, int text, Point point) {
        super(context, text, point);

        defineDrawingFacet(new TextButtonDrawingFacet());
    }
}
