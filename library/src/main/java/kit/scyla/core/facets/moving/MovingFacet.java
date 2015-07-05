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
    private final double m_speedMoving;
    protected boolean direction;
    protected Point m_pointA;
    protected Point m_pointB;

    protected MovingFacet(int speed, Point pointA, Point pointB) {
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

    protected abstract void onMoveNewPosition();

    public void moveNewPosition() {
        onMoveNewPosition();
        shape().collisionFacet().recalculateContactArea();
    }

    protected int speed() {
        return (int) m_speedMoving / 10;
    }
}
