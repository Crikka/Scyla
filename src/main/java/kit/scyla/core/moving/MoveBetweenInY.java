package kit.scyla.core.moving;

import android.graphics.Point;

import kit.scyla.core.facets.moving.MovingFacet;
import kit.scyla.core.shapes.Shape;

/**
 * Created with IntelliJ
 * Created by Nelaupe Lucas
 * Date 02/01/15
 */
public class MoveBetweenInY<TShape extends Shape<TShape, ?>> extends MovingFacet<TShape> {

    public MoveBetweenInY(int speed, Point pointA, Point pointB) {
        super(speed, pointA, pointB);
    }

    @Override
    public void onMoveNewPosition() {

        Point current = shape().getGravityCenter();

        if (direction) {

            // Go to Point A

            if (shape().getGravityCenter().y > m_pointA.y) {
                direction = !direction;
            } else {
                current.y += speed();
                shape().gravityCenterFacet().moveGravityCenterTo(current);
            }

        } else {

            // Go to Point B

            if (shape().getGravityCenter().y < m_pointB.y) {
                direction = !direction;
            } else {
                current.y -= speed();
                shape().gravityCenterFacet().moveGravityCenterTo(current);
            }

        }
    }
}
