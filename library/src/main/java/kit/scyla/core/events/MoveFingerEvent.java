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

package kit.scyla.core.events;

import android.graphics.Point;

import kit.scyla.canvas.touchEvent.TouchEvent;
import kit.scyla.canvas.touchEvent.TouchTypeEvent;
import kit.scyla.core.shapes.Shape;

/**
 * Created with IntelliJ
 * Created by Ferrand
 * Date : 01/01/2015
 */
@SuppressWarnings({"unused", "unchecked"})
public class MoveFingerEvent extends TouchEvent {
    private Shape m_shape;

    public MoveFingerEvent(Shape shape) {
        this.m_shape = shape;
    }

    @Override
    public void onTouch(Point point, TouchTypeEvent event) {
        if (event == TouchTypeEvent.Touch || event == TouchTypeEvent.Move) {
            m_shape.gravityCenterFacet().moveGravityCenterTo(point);
        }
    }
}
