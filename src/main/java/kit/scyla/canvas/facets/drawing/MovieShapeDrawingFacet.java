package kit.scyla.canvas.facets.drawing;

import android.graphics.Canvas;
import android.graphics.Movie;
import android.graphics.Paint;
import android.os.SystemClock;

import kit.scyla.canvas.shapes.custom.movies.MovieShape;


/**
 * Created with IntelliJ
 * Created by Nelaupe Lucas
 * Date 17/10/14
 */
public class MovieShapeDrawingFacet<TShape extends MovieShape<TShape>> extends DrawingCanvasFacet<TShape> {
    private long movieStart = 0;

    public MovieShapeDrawingFacet() {
        super();
    }

    public MovieShapeDrawingFacet(Paint paint) {
        super(paint);
    }

    @Override
    public void draw(Canvas canvas) {
        MovieShape shape = shape();
        Movie movie = shape.getMovie();
        if (movie != null) {
            long now = SystemClock.uptimeMillis();

            if (movieStart == 0) movieStart = now;

            int relTime;
            relTime = (int) ((now - movieStart) % movie.duration());
            movie.setTime(relTime);

            int x = shape.getGravityCenter().x - movie.width() / 2;
            int y = shape.getGravityCenter().y - movie.height() / 2;

            movie.draw(canvas, x, y);

        }
    }
}