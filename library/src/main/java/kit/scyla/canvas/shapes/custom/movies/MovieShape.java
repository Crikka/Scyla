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

package kit.scyla.canvas.shapes.custom.movies;

import android.graphics.Movie;
import android.graphics.Point;

import java.io.InputStream;

import kit.scyla.canvas.cache.MovieShapeCache;
import kit.scyla.canvas.facets.drawing.MovieShapeDrawingFacet;
import kit.scyla.canvas.shapes.ShapeCanvas;

/**
 * Created with IntelliJ
 * Created by Nelaupe Lucas
 * Date 19/10/14
 */
@SuppressWarnings({"unused", "unchecked"})
public abstract class MovieShape<TSelf extends MovieShape<TSelf>> extends ShapeCanvas<TSelf> {

    private Movie m_movie;

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

}
