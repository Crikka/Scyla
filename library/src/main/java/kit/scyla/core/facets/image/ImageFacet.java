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

package kit.scyla.core.facets.image;

import kit.scyla.core.facets.Facet;
import kit.scyla.core.facets.collision.CollisionFacet;
import kit.scyla.core.facets.drawing.DrawingFacet;
import kit.scyla.core.facets.rotation.RotationFacet;
import kit.scyla.core.shapes.Shape;

/**
 * Created with IntelliJ
 * Created by Ferrand
 * Date 24/12/2014
 */
@SuppressWarnings({"unused", "unchecked"})
@Deprecated
public abstract class ImageFacet<TShape extends Shape<TShape, ?>> extends Facet<TShape> {
    private CollisionFacet<TShape> m_collision;
    private DrawingFacet<TShape, ?> m_drawing;
    private RotationFacet<TShape> m_rotation;

    public ImageFacet() {
        super();
    }

    @Override
    public void onShapeDefined(TShape shape) {
        this.m_collision = shape.collisionFacet();
        this.m_drawing = shape.drawingFacet();
        this.m_rotation = shape.rotationFacet();
    }

    public abstract TShape createInstance();
    /*private FingerEvent m_fingerEvent = null;
    private ForcesSystem m_forcesSystem;*/

    public abstract void restore(TShape shape);

    protected void defineRules(TShape shape) {
        /*shape.defineCollisionFacet(m_collision);
        shape.defineDrawingFacet(m_drawing);
        shape.defineBehaviorRule(m_behavior);
        shape.defineRotationFacet(m_rotation);

        m_collision.defineShape(shape);
        m_drawing.defineShape(shape);
        m_behavior.defineShape(shape);
        m_rotation.defineShape(shape);*/

        //shape.collisionFacet().recalculateContactArea();
        // Missing : Drawing "paint"
    }
}
