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

package kit.scyla.canvas.shapes;

import android.graphics.Canvas;
import android.graphics.Point;

import kit.scyla.core.facets.drawing.DrawingFacet;
import kit.scyla.core.shapes.Shape;

/**
 * Created with IntelliJ
 * Created by Ferrand
 * Date : 23/01/2015
 */
@SuppressWarnings({"unused", "unchecked"})
public abstract class ShapeCanvas<TSelf extends ShapeCanvas<TSelf>> extends Shape<TSelf, Canvas> {
    private DrawingFacet<TSelf, Canvas> m_drawingFacet;

    public ShapeCanvas(Point gravityCenter) {
        super(gravityCenter);
    }

    @Override
    public final void defineDrawingFacet(DrawingFacet<TSelf, Canvas> drawingFacet) {
        drawingFacet.defineShape((TSelf) this);
        this.m_drawingFacet = drawingFacet;
    }


    @Override
    public DrawingFacet<TSelf, Canvas> drawingFacet() {
        return m_drawingFacet;
    }
}
