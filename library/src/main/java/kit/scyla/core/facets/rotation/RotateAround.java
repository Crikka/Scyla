package kit.scyla.core.facets.rotation;

import android.graphics.Point;

import kit.scyla.core.shapes.Shape;


/**
 * Created with IntelliJ
 * Created by Nelaupe Lucas
 * Date 23/12/14
 */
public class RotateAround<TShape extends Shape<TShape, ?>> extends RotationFacet<TShape> {

    @Deprecated
    public RotateAround(int speed, Point point, boolean clockwise) {
        super(speed, point, clockwise);
    }

    public RotateAround(int speed, Point point) {
        super(speed, point);
    }

    @Deprecated
    public RotateAround(int speed, Point point, boolean clockwise, double offset) {
        super(speed, point, clockwise, offset);
    }

    public RotateAround(int speed, Point point, double offset) {
        super(speed, point, offset);
    }

    @Override
    public void onMoveNewPosition() {

        int xToGo = (int) (m_gravityRotation.x + m_radius * Math.cos(m_rotation));
        int yToGo = (int) (m_gravityRotation.y + m_radius * Math.sin(m_rotation));

        Point pointToGo = new Point(xToGo, yToGo);

        m_rotation += (m_clockwise ? m_speedRotation : -m_speedRotation) / 70;

        shape().gravityCenterFacet().moveGravityCenterTo(pointToGo);
    }
}
