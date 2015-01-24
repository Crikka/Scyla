package kit.scyla.core.moving;

import android.graphics.Point;

import kit.scyla.core.facets.moving.MovingFacet;
import kit.scyla.core.shapes.Shape;

/**
 * Created with IntelliJ
 * Created by Nelaupe Lucas
 * Date 02/01/15
 */
public class MoveBetweenInX<TShape extends Shape<TShape, ?>> extends MovingFacet<TShape> {

    public MoveBetweenInX(TShape shape, int speed, Point pointA, Point pointB) {
        super(speed, pointA, pointB);
    }

    @Override
    public void onMoveNewPosition() {

        Point current = shape().getGravityCenter();

        if (direction) {

            // Go to Point A

            if (shape().getGravityCenter().x > m_pointA.x) {
                direction = !direction;
            } else {
                current.x += speed();
                shape().gravityCenterFacet().moveGravityCenterTo(current);
            }

        } else {

            // Go to Point B

            if (shape().getGravityCenter().x < m_pointB.x) {
                direction = !direction;
            } else {
                current.x -= speed();
                shape().gravityCenterFacet().moveGravityCenterTo(current);
            }

        }
    }
}
