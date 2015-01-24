package kit.scyla.core.facets.rotation;

import android.graphics.Point;

import kit.scyla.core.facets.Facet;
import kit.scyla.core.shapes.Shape;

/**
 * Created with IntelliJ
 * Created by Nelaupe Lucas
 * Date 23/12/14
 */
public abstract class RotationFacet<TShape extends Shape<TShape, ?>> extends Facet<TShape> {
    protected transient Point m_gravityRotation;
    protected double m_radius;
    protected double m_rotation;
    protected double m_speedRotation;
    protected boolean m_clockwise;

    public RotationFacet() {
        super();

        this.m_gravityRotation = null;
        this.m_rotation = 0;
        this.m_clockwise = true;
    }

    @Deprecated
    public RotationFacet(int speed, Point point, boolean clockwise) {
        super();

        m_gravityRotation = point;
        m_rotation = 0;
        m_speedRotation = speed;
        this.m_clockwise = clockwise;
    }

    public RotationFacet(int speed, Point point) {
        super();

        m_gravityRotation = point;
        m_rotation = 0;
        m_speedRotation = speed;
        this.m_clockwise = true;
    }

    @Deprecated
    public RotationFacet(int speed, Point point, boolean clockwise, double offset) {
        super();

        m_gravityRotation = point;
        m_rotation = offset;
        m_speedRotation = speed;
        this.m_clockwise = clockwise;
    }

    public RotationFacet(int speed, Point point, double offset) {
        super();

        m_gravityRotation = point;
        m_rotation = offset;
        m_speedRotation = speed;
        this.m_clockwise = true;
    }

    public void invertClockwise(){
        m_clockwise = !m_clockwise;
    }

    @Override
    public void onShapeDefined(TShape shape) {
        if(m_gravityRotation == null) {
            m_gravityRotation = shape.getGravityCenter();
        }
        m_radius = distance(m_gravityRotation, shape.getGravityCenter());
    }

    public abstract void onMoveNewPosition();

    public int speed() {
        return (int) m_speedRotation;
    }

    public Point rotationPoint() {
        return m_gravityRotation;
    }

    private double distance(Point p1, Point p2) {

        double x = (p2.y - p1.y) * (p2.y - p1.y);
        double y = (p2.x - p1.x) * (p2.x - p1.x);

        return Math.sqrt(x + y);
    }

    public void moveNewPosition() {
        onMoveNewPosition();
        shape().collisionFacet().recalculateContactArea();
    }

    public double angle() {
        return m_rotation;
    }
}
