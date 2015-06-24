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

package kit.scyla.core.facets.behavior;

import kit.scyla.core.shapes.Shape;

/**
 * Created with IntelliJ
 * Created by Ferrand
 * Date : 06/01/2015
 */
@SuppressWarnings({"unused", "unchecked"})
public abstract class Interaction<TShape1 extends Shape, TShape2 extends Shape> {
    private TShape1 m_shape1;
    private TShape2 m_shape2;

    protected Interaction() {
        this.m_shape1 = null;
        this.m_shape2 = null;
    }

    public Interaction(TShape1 shape1, TShape2 shape2) {
        this.m_shape1 = shape1;
        this.m_shape2 = shape2;
    }

    public Interaction(TShape2 shape2) {
        this.m_shape2 = shape2;
    }

    private boolean secure(Shape shape1, Shape shape2) {
        return ((shape1 == m_shape1) && (shape2 == m_shape2));
    }

    public void onInteract(Shape shape1, Shape shape2, InteractionType type) {
        if (secure(shape1, shape2)) {
            interact(m_shape1, m_shape2, type);
        }
    }

    public abstract void interact(TShape1 shape1, TShape2 shape2, InteractionType type);

    public TShape1 shape1() {
        return m_shape1;
    }

    public TShape2 shape2() {
        return m_shape2;
    }
}
