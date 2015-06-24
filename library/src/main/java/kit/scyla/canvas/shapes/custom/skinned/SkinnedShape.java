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

package kit.scyla.canvas.shapes.custom.skinned;

import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.graphics.Point;

import kit.scyla.canvas.Share.SharedElements;
import kit.scyla.canvas.cache.SkinnedShapeCache;
import kit.scyla.canvas.facets.collision.SkinnedShapeCollisionFacet;
import kit.scyla.canvas.facets.drawing.SkinnedShapeDrawingFacet;
import kit.scyla.canvas.facets.image.SkinnedShapeImageFacet;
import kit.scyla.canvas.shapes.ShapeCanvas;

/**
 * Created with IntelliJ
 * Created by Nelaupe Lucas
 * Date 18/10/14
 */
@SuppressWarnings({"unused", "unchecked"})
public abstract class SkinnedShape<TSelf extends SkinnedShape<TSelf>> extends ShapeCanvas<TSelf> {

    private Bitmap m_skin;

    @SuppressWarnings("unchecked")
    public SkinnedShape(Bitmap skin, Point position, boolean autoScale) {
        super(position);

        if (autoScale) {
            int newWidth = (int) Math.round(skin.getWidth() * SharedElements.ratio);
            int newHeight = (int) Math.round(skin.getHeight() * SharedElements.ratio);
            Class<? extends SkinnedShape> classShape = ((TSelf) this).getClass();

            SkinnedShapeCache cache = SkinnedShapeCache.getInstance();
            Bitmap cacheScaled = cache.getBitmapFromMemCache(classShape);
            if (cacheScaled != null) {
                m_skin = cacheScaled;
            } else {
                m_skin = getResizedBitmap(skin, newHeight, newWidth);
                cache.addBitmapToMemoryCache(classShape, m_skin);
            }

        } else {
            m_skin = skin;
        }

        defineDrawingFacet(new SkinnedShapeDrawingFacet<TSelf>());
        defineCollisionFacet(new SkinnedShapeCollisionFacet<TSelf>());
    }

    public Bitmap getResizedBitmap(Bitmap bm, int newHeight, int newWidth) {
        int width = bm.getWidth();
        int height = bm.getHeight();
        float scaleWidth = ((float) newWidth) / width;
        float scaleHeight = ((float) newHeight) / height;
        Matrix matrix = new Matrix();
        matrix.postScale(scaleWidth, scaleHeight);
        return Bitmap.createBitmap(bm, 0, 0, width, height, matrix, false);
    }

    public Bitmap getSkin() {
        return m_skin;
    }

    @Override
    public SkinnedShapeImageFacet<TSelf> imageFacet() {
        return new SkinnedShapeImageFacet<>();
    }
}
