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
import kit.scyla.core.facets.collision.CollisionFacet;

/**
 * Created with IntelliJ
 * Created by Ferrand
 * Date 10/10/2014
 */
public class InputTextCollisionFacet extends CollisionFacet<Text> {

    public InputTextCollisionFacet() {
        super();
    }

    private Region genericDetermineRegion() {

        int width = shape().getTextWidth();
        int height = shape().getTextHeight();

        int topX = shape().gravityCenterFacet().getGravityCenter().x;
        int topY = shape().gravityCenterFacet().getGravityCenter().y - height / 4;


        int bottomX = shape().gravityCenterFacet().getGravityCenter().x + width;
        int bottomY = shape().gravityCenterFacet().getGravityCenter().y + height / 2;
        return new Region(topX, topY, bottomX, bottomY);

    }

    @Override
    protected Region determineRegion() {
        return genericDetermineRegion();
    }

}
