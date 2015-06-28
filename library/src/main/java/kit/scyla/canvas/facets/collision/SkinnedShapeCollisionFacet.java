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

import kit.scyla.canvas.shapes.custom.skinned.SkinnedShape;
import kit.scyla.core.facets.collision.CollisionFacet;

/**
 * Created with IntelliJ
 * Created by Ferrand
 * Date 10/10/2014
 */
public class SkinnedShapeCollisionFacet<TShape extends SkinnedShape<TShape>> extends CollisionFacet<TShape> {

    @Override
    protected void determineRegion(Region hitBox, Region fingerHitBox) {
        int width = shape().getSkin().getWidth(), height = shape().getSkin().getHeight();
        int xTopLeft = shape().gravityCenterFacet().getGravityCenter().x - width / 2;
        int yTopLeft = shape().gravityCenterFacet().getGravityCenter().y - height / 2;

        hitBox.set(xTopLeft, yTopLeft, xTopLeft + width, yTopLeft + height);
        fingerHitBox.set(xTopLeft, yTopLeft, xTopLeft + width, yTopLeft + height);

    }

}
