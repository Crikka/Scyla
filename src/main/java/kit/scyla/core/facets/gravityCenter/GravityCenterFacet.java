package kit.scyla.core.facets.gravityCenter;

import android.graphics.Point;

import kit.scyla.core.facets.Facet;
import kit.scyla.core.shapes.Shape;

/**
 * Created with IntelliJ
 * Created by Ferrand
 * Date : 23/01/2015
 */
public class GravityCenterFacet<TShape extends Shape<TShape, ?>> extends Facet<TShape> implements GravityCenter {

    private Point m_position;

    public GravityCenterFacet(Point position){
        m_position = position;
    }

    @Override
    public Point getGravityCenter() {
        return m_position;
    }

    @Override
    public void moveGravityCenterTo(int x, int y) {
        m_position.set(x, y);
    }

    public final void moveGravityCenterTo(Point point) {
        moveGravityCenterTo(point.x, point.y);
    }

    public final void offsetGravityCenter(int x, int y) {
        moveGravityCenterTo(m_position.x + x, m_position.y + y);
    }

    @Override
    public void translateX(int x) {
        offsetGravityCenter(x, 0);
    }

    @Override
    public void translateY(int y) {
        offsetGravityCenter(0, y);
    }

    @Override
    public void onShapeDefined(TShape shape) {}

    public void follow(Shape shape) {
        moveGravityCenterTo(shape.gravityCenterFacet().getGravityCenter());
        shape.collisionFacet().recalculateContactArea();
    }

}
