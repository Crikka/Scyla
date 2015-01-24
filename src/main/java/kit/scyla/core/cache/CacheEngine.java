package kit.scyla.core.cache;

import android.graphics.Bitmap;
import android.graphics.Movie;
import android.util.LruCache;

import java.util.HashMap;

import kit.scyla.canvas.shapes.custom.movies.MovieShape;
import kit.scyla.canvas.shapes.custom.skinned.SkinnedShape;
import kit.scyla.core.shapes.Shape;


/**
 * Created with IntelliJ
 * Created by Nelaupe Lucas
 * Date 01/01/15
 */
public abstract class CacheEngine<TSelf extends Shape, Binary> {

    private LruCache<Class<? extends TSelf>, Binary> mMemoryCache;

    private void init(){

        final int maxMemory = (int) (Runtime.getRuntime().maxMemory() / 1024);

        // Use 1/8th of the available memory for this memory cache.
        final int cacheSize = maxMemory / 16;

        mMemoryCache = new LruCache<>(cacheSize);
    }


    public void addBitmapToMemoryCache(Class<? extends TSelf> key, Binary bitmap){
        if (getBitmapFromMemCache(key) == null) {
            mMemoryCache.put(key, bitmap);
        }
    }

    public Binary getBitmapFromMemCache(Class<? extends TSelf>  key) {
        if(mMemoryCache == null){
            init();
        }

        return mMemoryCache.get(key);
    }

}