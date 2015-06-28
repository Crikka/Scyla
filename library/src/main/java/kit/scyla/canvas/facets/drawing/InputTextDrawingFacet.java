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
import android.graphics.RectF;

import kit.scyla.canvas.shapes.text.Text;

/**
 * Created with IntelliJ
 * Created by Nelaupe Lucas
 * Date 13/10/14
 */
@SuppressWarnings({"unused", "unchecked"})
@Deprecated
public class InputTextDrawingFacet extends TextDrawingFacet {

    public InputTextDrawingFacet() {
        super();

    }

    public InputTextDrawingFacet(Paint paint) {
        super(paint);
    }

    @Override
    public void draw(Canvas canvas) {
        Text shapeText = shape();

        int width = shapeText.getTextWidth();
        int height = shapeText.getTextHeight();

        int topX = shape().gravityCenterFacet().getGravityCenter().x - 10;

        int bottomX = shape().gravityCenterFacet().getGravityCenter().x + width + 10;
        int bottomY = shape().gravityCenterFacet().getGravityCenter().y + height / 2 + 20;

        int topY = bottomY - 10;

        paint().setColor(Color.WHITE);

        RectF r = new RectF(topX, topY, bottomX, bottomY);
        canvas.drawRoundRect(r, 10, 10, paint());

        paint().setColor(shapeText.getColor());

        canvas.drawText(shapeText.getText(), shape().gravityCenterFacet().getGravityCenter().x, shape().gravityCenterFacet().getGravityCenter().y + height / 2, paint());
    }
}

