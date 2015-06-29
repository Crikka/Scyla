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

package kit.scyla.canvas.facets.collision;

import android.graphics.Region;

import kit.scyla.canvas.shapes.text.Text;

/**
 * Created with IntelliJ
 * Created by Ferrand
 * Date 10/10/2014
 */
public class TextButtonCollisionFacet extends TextCollisionFacet {

    @Override
    protected void determineRegion(Region hitBox, Region fingerHitBox) {

        Text shapeText = shape();

        int width = shapeText.getTextWidth();
        int height = shapeText.getTextHeight();

        int topX = shape().gravityCenterFacet().getGravityCenter().x - width / 2 - 20;
        int topY = shape().gravityCenterFacet().getGravityCenter().y - height / 4 - 20;

        int bottomX = shape().gravityCenterFacet().getGravityCenter().x + width / 2 + 20;
        int bottomY = shape().gravityCenterFacet().getGravityCenter().y + height / 2 + 20;

        hitBox.set(topX, topY, bottomX, bottomY);
        fingerHitBox.set(topX, topY, bottomX, bottomY);

    }

}
