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

package kit.scyla.core.facets;

import kit.scyla.core.shapes.Shape;

/**
 * Created with IntelliJ
 * Created by Ferrand
 * Date 04/10/2014
 */
@SuppressWarnings({"unused", "unchecked"})
public abstract class Facet<TShape extends Shape> {

    private TShape m_shape;

    public abstract void onShapeDefined(TShape shape);

    public void defineShape(TShape shape) {
        this.m_shape = shape;
        onShapeDefined(shape);
    }

    protected TShape shape() {
        return m_shape;
    }

}
