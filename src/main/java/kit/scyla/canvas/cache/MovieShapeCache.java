package kit.scyla.canvas.cache;

import android.graphics.Bitmap;
import android.graphics.Movie;

import kit.scyla.canvas.shapes.custom.movies.MovieShape;
import kit.scyla.canvas.shapes.custom.skinned.SkinnedShape;
import kit.scyla.core.cache.CacheEngine;

/**
 * Created with IntelliJ
 * Created by lucas
 * Date 23/01/15
 */
public class MovieShapeCache  extends CacheEngine<MovieShape, Movie> {

    private static MovieShapeCache INSTANCE = new MovieShapeCache();

    private MovieShapeCache(){}

    public static MovieShapeCache getInstance()
    {	return INSTANCE;
    }

}