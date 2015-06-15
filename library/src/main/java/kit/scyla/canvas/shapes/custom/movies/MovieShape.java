package kit.scyla.canvas.shapes.custom.movies;

import android.graphics.Movie;
import android.graphics.Point;

import java.io.InputStream;

import kit.scyla.canvas.cache.MovieShapeCache;
import kit.scyla.canvas.facets.drawing.MovieShapeDrawingFacet;
import kit.scyla.canvas.facets.image.MovieShapeImageFacet;
import kit.scyla.canvas.shapes.ShapeCanvas;

/**
 * Created with IntelliJ
 * Created by Nelaupe Lucas
 * Date 19/10/14
 */
public abstract class MovieShape<TSelf extends MovieShape<TSelf>> extends ShapeCanvas<TSelf> {

    private Movie m_movie;

    @SuppressWarnings("unchecked")
    public MovieShape(InputStream skin, Point position) {
        super(position);

        Class c = ((TSelf) this).getClass();

        MovieShapeCache cache = MovieShapeCache.getInstance();

        Movie movie = cache.getBitmapFromMemCache(c);
        if (movie != null) {
            m_movie = movie;
        } else {
            m_movie = Movie.decodeStream(skin);
            cache.addBitmapToMemoryCache(c, m_movie);
        }
        defineDrawingFacet(new MovieShapeDrawingFacet<TSelf>());
    }

    public Movie getMovie() {
        return m_movie;
    }

    @SuppressWarnings("unchecked")
    @Override
    public MovieShapeImageFacet<TSelf> imageFacet() {
        return new MovieShapeImageFacet<>();
    }
}
