package kit.scyla.canvas.shapes.custom.skinned;

import android.graphics.Bitmap;
import android.graphics.Point;

import kit.scyla.canvas.Share.SharedElements;
import kit.scyla.canvas.cache.SkinnedShapeCache;
import kit.scyla.canvas.shapes.ShapeCanvas;
import kit.scyla.core.cache.CacheEngine;
import kit.scyla.canvas.facets.collision.SkinnedShapeCollisionFacet;
import kit.scyla.canvas.facets.drawing.SkinnedShapeDrawingFacet;
import kit.scyla.canvas.facets.image.SkinnedShapeImageFacet;

/**
 * Created with IntelliJ
 * Created by Nelaupe Lucas
 * Date 18/10/14
 */
public abstract class SkinnedShape<TSelf extends SkinnedShape<TSelf>> extends ShapeCanvas<TSelf> {

    private Bitmap m_skin;

    @SuppressWarnings("unchecked")
    public SkinnedShape(Bitmap skin, Point position, boolean autoScale) {
        super(position);

        if (autoScale) {
            int width = (int) Math.round(skin.getWidth() * SharedElements.ratio);
            int height = (int) Math.round(skin.getHeight() * SharedElements.ratio);
            Class<? extends SkinnedShape> classShape = ((TSelf) this).getClass();

            SkinnedShapeCache cache = SkinnedShapeCache.getInstance();
            Bitmap cacheScaled = cache.getBitmapFromMemCache(classShape);
            if (cacheScaled != null) {
                m_skin = cacheScaled;
            } else {
                m_skin = Bitmap.createScaledBitmap(skin, width, height, false);
                cache.addBitmapToMemoryCache(classShape, m_skin);
            }

        } else {
            m_skin = skin;
        }

        defineDrawingFacet(new SkinnedShapeDrawingFacet<TSelf>());
        defineCollisionFacet(new SkinnedShapeCollisionFacet<TSelf>());
    }

    public Bitmap getSkin() {
        return m_skin;
    }

    @Override
    public SkinnedShapeImageFacet<TSelf> imageFacet() {
        return new SkinnedShapeImageFacet<>();
    }
}
