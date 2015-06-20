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

package kit.scyla.canvas.cache;

import android.graphics.Bitmap;

import kit.scyla.canvas.shapes.custom.skinned.SkinnedShape;
import kit.scyla.core.cache.CacheEngine;

/**
 * Created with IntelliJ
 * Created by Lucas Nelaupe
 * Date 23/01/15
 */
public class SkinnedShapeCache extends CacheEngine<SkinnedShape, Bitmap> {

    private static SkinnedShapeCache INSTANCE = new SkinnedShapeCache();

    private SkinnedShapeCache() {
        super();
    }

    public static SkinnedShapeCache getInstance() {

        return INSTANCE;
    }

}