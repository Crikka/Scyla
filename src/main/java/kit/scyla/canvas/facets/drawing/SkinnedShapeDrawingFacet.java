package kit.scyla.canvas.facets.drawing;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;

import kit.scyla.canvas.shapes.custom.skinned.SkinnedShape;


/**
 * Created with IntelliJ
 * Created by Nelaupe Lucas
 * Date 18/10/14
 */
public class SkinnedShapeDrawingFacet<TShape extends SkinnedShape<TShape>> extends DrawingCanvasFacet<TShape> {
    public SkinnedShapeDrawingFacet() {
        super();
    }

    public SkinnedShapeDrawingFacet(Paint paint) {
        super(paint);
    }

    @Override
    public void draw(Canvas canvas) {
        SkinnedShape shape = shape();
        Bitmap skin = shape.getSkin();
        int x = shape.gravityCenterFacet().getGravityCenter().x - skin.getWidth() / 2;
        int y = shape.gravityCenterFacet().getGravityCenter().y - skin.getHeight() / 2;
        canvas.drawBitmap(skin, x, y, paint());
    }
}