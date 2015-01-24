package kit.scyla.canvas.facets.image;

import kit.scyla.canvas.shapes.custom.movies.MovieShape;
import kit.scyla.core.facets.image.ImageFacet;

/**
 * Created by Ferrand on 24/12/2014.
 * TODO TODO TODO
 */
public class MovieShapeImageFacet<TShape extends MovieShape<TShape>> extends ImageFacet<TShape> {
    @Override
    public TShape createInstance() {
        return null;
    }

    @Override
    public void restore(TShape shape) {

    }
}
