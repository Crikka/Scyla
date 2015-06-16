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

package kit.scyla.canvas.shapes.text;

import android.content.Context;
import android.graphics.Point;

import kit.scyla.canvas.facets.collision.TextCollisionFacet;
import kit.scyla.canvas.facets.drawing.TextButtonDrawingFacet;

/**
 * Created with IntelliJ
 * Created by Nelaupe Lucas
 * Date 13/10/14
 */
public class TextButton extends Text {

    public TextButton(Context context, int text, Point point) {
        super(context, text, point);

        defineDrawingFacet(new TextButtonDrawingFacet());
        defineCollisionFacet(new TextCollisionFacet());
    }

    @Override
    public void recalculateDrawing() {
        defineDrawingFacet(new TextButtonDrawingFacet());
        defineCollisionFacet(new TextCollisionFacet());
    }
}
