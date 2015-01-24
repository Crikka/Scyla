package kit.scyla.canvas.facets.drawing;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import kit.scyla.canvas.shapes.ShapeCanvas;
import kit.scyla.core.facets.drawing.DrawingFacet;

/**
 * Created with IntelliJ
 * Created by Ferrand
 * Date : 23/01/2015
 */
public abstract class DrawingCanvasFacet<TShape extends ShapeCanvas<TShape>> extends DrawingFacet<TShape, Canvas> {
    private final transient Paint m_paint;


    public DrawingCanvasFacet() {
        super();

        this.m_paint = new Paint();
        this.m_paint.setColor(Color.WHITE);
        this.m_paint.setAntiAlias(true);
        this.m_paint.setDither(true);
        this.m_paint.setFilterBitmap(true);
    }

    @Override
    public void onShapeDefined(TShape shape) {}


    public DrawingCanvasFacet(Paint paint) {
        super();

        this.m_paint = paint;
    }

    protected Paint paint() {
        return m_paint;
    }
}
