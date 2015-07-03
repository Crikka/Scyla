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

package kit.scyla.core.facets.gravityCenter;

import android.graphics.Point;

import kit.scyla.core.facets.Facet;
import kit.scyla.core.shapes.Shape;

/**
 * Created with IntelliJ
 * Created by Ferrand
 * Date : 23/01/2015
 */
@SuppressWarnings({"unused", "unchecked"})
public class GravityCenterFacet<TShape extends Shape<TShape, ?>> extends Facet<TShape> implements GravityCenter {

    private final Point m_position;

    public GravityCenterFacet(Point position) {
        m_position = position;
    }

    @Override
    public Point getGravityCenter() {
        return m_position;
    }

    @Override
    public void moveGravityCenterTo(int x, int y) {
        m_position.set(x, y);
        shape().collisionFacet().recalculateContactArea();
    }

    public final void moveGravityCenterTo(Point point) {
        moveGravityCenterTo(point.x, point.y);
        shape().collisionFacet().recalculateContactArea();
    }

    public final void offsetGravityCenter(int x, int y) {
        moveGravityCenterTo(m_position.x + x, m_position.y + y);
        shape().collisionFacet().recalculateContactArea();
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
    public void onShapeDefined(TShape shape) {
    }

    public void follow(Shape shape) {
        moveGravityCenterTo(shape.gravityCenterFacet().getGravityCenter());
        shape.collisionFacet().recalculateContactArea();
    }

}
