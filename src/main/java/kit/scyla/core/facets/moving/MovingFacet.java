package kit.scyla.core.facets.moving;

import android.graphics.Point;

import kit.scyla.core.facets.Facet;
import kit.scyla.core.shapes.Shape;

/**
 * Created with IntelliJ
 * Created by Nelaupe Lucas
 * Date 02/01/15
 */
public abstract class MovingFacet<TShape extends Shape<TShape, ?>> extends Facet<TShape> {
    protected boolean direction;
    protected Point m_pointA;
    protected Point m_pointB;
    private double m_speedMoving;

    public MovingFacet() {
        super();

        direction = true;

        m_pointA = null;
        m_pointB = null;
    }

    public MovingFacet(int speed, Point pointA, Point pointB) {
        super();

        direction = true;

        m_speedMoving = speed;

        m_pointA = pointA;
        m_pointB = pointB;

    }

    @Override
    public void onShapeDefined(TShape shape) {
        if (m_pointA == null) {
            m_pointA = shape.gravityCenterFacet().getGravityCenter();
        }

        if (m_pointB == null) {
            m_pointB = shape.gravityCenterFacet().getGravityCenter();
        }
    }

    public abstract void onMoveNewPosition();

    public void moveNewPosition() {
        onMoveNewPosition();
        shape().collisionFacet().recalculateContactArea();
    }

    public int speed() {
        return (int) m_speedMoving / 10;
    }
}
