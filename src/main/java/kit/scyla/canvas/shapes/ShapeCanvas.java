package kit.scyla.canvas.shapes;

import android.graphics.Canvas;
import android.graphics.Point;

import kit.scyla.canvas.facets.drawing.NoneDrawingCanvasFacet;
import kit.scyla.core.facets.drawing.DrawingFacet;
import kit.scyla.core.shapes.Shape;

/**
 * Created with IntelliJ
 * Created by Ferrand
 * Date : 23/01/2015
 */
public abstract class ShapeCanvas<TSelf extends ShapeCanvas<TSelf>> extends Shape<TSelf, Canvas> {
    private DrawingFacet<TSelf, Canvas> m_drawingFacet;

    public ShapeCanvas(Point gravityCenter) {
        super(gravityCenter);
    }

    @SuppressWarnings("unchecked")
    @Override
    public final void defineDrawingFacet(DrawingFacet<TSelf, Canvas> drawingFacet) {
        drawingFacet.defineShape((TSelf) this);
        this.m_drawingFacet = drawingFacet;
    }


    @Override
    public DrawingFacet<TSelf, Canvas> drawingFacet() {
        if (m_drawingFacet == null) {
            defineDrawingFacet(new NoneDrawingCanvasFacet<TSelf>());
        }

        return m_drawingFacet;
    }
}
