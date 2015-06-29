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

package kit.scyla.core.facets.rotation;

import android.graphics.Point;

import kit.scyla.core.facets.Facet;
import kit.scyla.core.shapes.Shape;

/**
 * Created with IntelliJ
 * Created by Nelaupe Lucas
 * Date 23/12/14
 */
@SuppressWarnings({"unused", "unchecked"})
public abstract class RotationFacet<TShape extends Shape<TShape, ?>> extends Facet<TShape> {

    protected Point m_gravityRotation;
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

    public RotationFacet(int speed, Point point) {
        super();

        m_gravityRotation = point;
        m_rotation = 0;
        m_speedRotation = speed;
        this.m_clockwise = true;
    }

    public RotationFacet(int speed, Point point, double offset) {
        super();

        m_gravityRotation = point;
        m_rotation = offset;
        m_speedRotation = speed;
        this.m_clockwise = true;
    }

    public void invertClockwise() {
        m_clockwise = !m_clockwise;
    }

    @Override
    public void onShapeDefined(TShape shape) {
        if (m_gravityRotation == null) {
            m_gravityRotation = shape.gravityCenterFacet().getGravityCenter();
        }
        m_radius = distance(m_gravityRotation, shape.gravityCenterFacet().getGravityCenter());
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
