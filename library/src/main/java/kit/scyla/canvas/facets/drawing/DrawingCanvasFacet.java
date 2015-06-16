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

package kit.scyla.canvas.facets.drawing;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import kit.scyla.canvas.shapes.ShapeCanvas;
import kit.scyla.core.facets.drawing.DrawingFacet;

/**
 * Created with IntelliJ
 * Created by Ferrand
 * Date : 23/01/2015
 */
public abstract class DrawingCanvasFacet<TShape extends ShapeCanvas<TShape>> extends DrawingFacet<TShape, Canvas> {
    private final transient Paint m_paint;


    public DrawingCanvasFacet() {
        super();

        this.m_paint = new Paint();
        this.m_paint.setColor(Color.WHITE);
        this.m_paint.setAntiAlias(true);
        this.m_paint.setDither(true);
        this.m_paint.setFilterBitmap(true);
    }

    public DrawingCanvasFacet(Paint paint) {
        super();

        this.m_paint = paint;
    }

    @Override
    public void onShapeDefined(TShape shape) {
    }

    protected Paint paint() {
        return m_paint;
    }
}
