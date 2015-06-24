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
import android.graphics.Paint;

import kit.scyla.canvas.shapes.text.Text;

/**
 * Created with IntelliJ
 * Created by Nelaupe Lucas
 * Date 13/10/14
 */
@SuppressWarnings({"unused", "unchecked"})
public class TextDrawingFacet extends DrawingCanvasFacet<Text> {

    public TextDrawingFacet() {
        super();
    }


    public TextDrawingFacet(Paint paint) {
        super(paint);
    }

    @Override
    public void onShapeDefined(Text text) {
        super.onShapeDefined(text);

        paint().setColor(text.getColor());
        paint().setTextSize(text.getSize());
        paint().setTypeface(text.getTypeface());

        if (shape().isCenter()) {
            paint().setTextAlign(Paint.Align.CENTER);
        }
    }

    @Override
    public void draw(Canvas canvas) {

        Text shapeText = shape();
        int height = shape().getTextHeight();

        canvas.drawText(shapeText.getText(), shape().gravityCenterFacet().getGravityCenter().x, shape().gravityCenterFacet().getGravityCenter().y + height / 2, paint());
    }

}

