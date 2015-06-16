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

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;

import kit.scyla.canvas.shapes.custom.skinned.SkinnedShape;


/**
 * Created with IntelliJ
 * Created by Nelaupe Lucas
 * Date 18/10/14
 */
public class SkinnedShapeDrawingFacet<TShape extends SkinnedShape<TShape>> extends DrawingCanvasFacet<TShape> {
    public SkinnedShapeDrawingFacet() {
        super();
    }

    public SkinnedShapeDrawingFacet(Paint paint) {
        super(paint);
    }

    @Override
    public void draw(Canvas canvas) {
        SkinnedShape shape = shape();
        Bitmap skin = shape.getSkin();
        int x = shape.gravityCenterFacet().getGravityCenter().x - skin.getWidth() / 2;
        int y = shape.gravityCenterFacet().getGravityCenter().y - skin.getHeight() / 2;
        canvas.drawBitmap(skin, x, y, paint());
    }
}