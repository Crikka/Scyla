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

package kit.scyla.core.cache;

import android.util.LruCache;

import kit.scyla.core.shapes.Shape;


/**
 * Created with IntelliJ
 * Created by Nelaupe Lucas
 * Date 01/01/15
 */
public abstract class CacheEngine<TSelf extends Shape, Binary> {

    private LruCache<Class<? extends TSelf>, Binary> mMemoryCache;

    public CacheEngine() {
        init();
    }

    private void init() {

        final int maxMemory = (int) (Runtime.getRuntime().maxMemory() / 1024);

        // Use 1/8th of the available memory for this memory cache.
        final int cacheSize = maxMemory / 8;

        mMemoryCache = new LruCache<>(cacheSize);

    }

    public void addBitmapToMemoryCache(Class<? extends TSelf> key, Binary bitmap) {
        if (getBitmapFromMemCache(key) == null) {
            mMemoryCache.put(key, bitmap);
        }
    }

    public Binary getBitmapFromMemCache(Class<? extends TSelf> key) {
        return mMemoryCache.get(key);
    }

}