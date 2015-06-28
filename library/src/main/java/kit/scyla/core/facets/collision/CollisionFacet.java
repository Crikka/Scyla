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

package kit.scyla.core.facets.collision;

import android.graphics.Region;

import kit.scyla.core.facets.Facet;
import kit.scyla.core.shapes.Shape;

/**
 * Created with IntelliJ
 * Created by Ferrand
 * Date 04/10/2014
 */
public abstract class CollisionFacet<TShape extends Shape<TShape, ?>> extends Facet<TShape> {

    private Region m_regionHitBox;
    private Region m_regionFingerHitBox;

    public CollisionFacet() {
        super();
    }

    @Override
    public void onShapeDefined(TShape shape) {
        this.m_regionHitBox = determineRegion();
    }

    public boolean fingerOn(int x, int y){
        return getTouchHitBox().contains(x, y);
    }

    protected abstract Region determineRegion();

    protected Region determineFingerRegion() {
        return m_regionHitBox;
    }

    public final Region getHitBox() {
        return m_regionHitBox;
    }

    public Region getTouchHitBox() {
        return m_regionFingerHitBox;
    }

    public final void recalculateContactArea() {
        m_regionHitBox = determineRegion();
        m_regionFingerHitBox = determineFingerRegion();
    }

    public final boolean intersect(Shape other) {
        return (!m_regionHitBox.quickReject(other.collisionFacet().getHitBox()) && m_regionHitBox.op(other.collisionFacet().getHitBox(), Region.Op.INTERSECT));
    }
}
