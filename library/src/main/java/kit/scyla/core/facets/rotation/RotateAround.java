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

import kit.scyla.core.shapes.Shape;


/**
 * Created with IntelliJ
 * Created by Nelaupe Lucas
 * Date 23/12/14
 */
@SuppressWarnings({"unused", "unchecked"})
public class RotateAround<TShape extends Shape<TShape, ?>> extends RotationFacet<TShape> {

    public RotateAround(int speed, Point point) {
        super(speed, point);
    }

    public RotateAround(int speed, Point point, double offset) {
        super(speed, point, offset);
    }

    @Override
    public void onMoveNewPosition() {

        int xToGo = (int) (m_gravityRotation.x + m_radius * Math.cos(m_rotation));
        int yToGo = (int) (m_gravityRotation.y + m_radius * Math.sin(m_rotation));

        m_rotation += (m_clockwise ? m_speedRotation : -m_speedRotation) / 70;

        shape().gravityCenterFacet().moveGravityCenterTo(xToGo, yToGo);
    }
}
