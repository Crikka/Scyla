/*
 *  Copyright 2015-present Lucas Nelaupe and Ferrand
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

package kit.scyla.core.moving;

import android.graphics.Point;

import kit.scyla.core.facets.moving.MovingFacet;
import kit.scyla.core.shapes.Shape;

/**
 * Created with IntelliJ
 * Created by Nelaupe Lucas
 * Date 02/01/15
 */
@SuppressWarnings({"unused", "unchecked"})
public class MoveBetweenInX<TShape extends Shape<TShape, ?>> extends MovingFacet<TShape> {

    @Deprecated
    public MoveBetweenInX(TShape shape, int speed, Point pointA, Point pointB) {
        super(speed, pointA, pointB);
    }

    @Deprecated
    public MoveBetweenInX(int speed, Point pointA, Point pointB) {
        super(speed, pointA, pointB);
    }

    @Override
    public void onMoveNewPosition() {

        Point current = shape().gravityCenterFacet().getGravityCenter();

        if (direction) {

            // Go to Point A

            if (shape().gravityCenterFacet().getGravityCenter().x > m_pointA.x) {
                direction = !direction;
            } else {
                current.x += speed();
                shape().gravityCenterFacet().moveGravityCenterTo(current);
            }

        } else {

            // Go to Point B

            if (shape().gravityCenterFacet().getGravityCenter().x < m_pointB.x) {
                direction = !direction;
            } else {
                current.x -= speed();
                shape().gravityCenterFacet().moveGravityCenterTo(current);
            }

        }
    }
}
