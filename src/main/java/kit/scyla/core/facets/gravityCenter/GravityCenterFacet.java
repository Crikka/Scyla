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
    @Override
    public void moveGravityCenterTo(int x, int y) {
        shape().getGravityCenter().set(x, y);
    }

    public final void moveGravityCenterTo(Point point) {
        moveGravityCenterTo(point.x, point.y);
    }

    public final void offsetGravityCenter(int x, int y) {
        moveGravityCenterTo(shape().getGravityCenter().x + x, shape().getGravityCenter().y + y);
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
}
