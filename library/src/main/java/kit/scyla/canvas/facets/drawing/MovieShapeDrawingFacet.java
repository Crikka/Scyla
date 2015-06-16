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

            int x = shape.gravityCenterFacet().getGravityCenter().x - movie.width() / 2;
            int y = shape.gravityCenterFacet().getGravityCenter().y - movie.height() / 2;

            movie.draw(canvas, x, y);

        }
    }
}