package kit.scyla.canvas.cache;

import android.graphics.Bitmap;

import kit.scyla.canvas.shapes.custom.skinned.SkinnedShape;
import kit.scyla.core.cache.CacheEngine;

/**
 * Created with IntelliJ
 * Created by lucas
 * Date 23/01/15
 */
public class SkinnedShapeCache extends CacheEngine<SkinnedShape, Bitmap> {

    private static SkinnedShapeCache INSTANCE = new SkinnedShapeCache();

    private SkinnedShapeCache(){}

    public static SkinnedShapeCache getInstance()
    {	return INSTANCE;
    }

}