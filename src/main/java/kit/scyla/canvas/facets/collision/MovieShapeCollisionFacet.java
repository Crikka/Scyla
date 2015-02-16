package kit.scyla.canvas.facets.collision;

import android.graphics.Region;

import kit.scyla.canvas.shapes.custom.movies.MovieShape;
import kit.scyla.core.facets.collision.CollisionFacet;


/**
 * Created with IntelliJ
 * Created by Ferrand
 * Date 10/10/2014
 */
public class MovieShapeCollisionFacet<TShape extends MovieShape<TShape>> extends CollisionFacet<TShape> {
    private Region genericDetermineRegion() {
        int width = shape().getMovie().width(), height = shape().getMovie().height();
        int xTopLeft = shape().gravityCenterFacet().getGravityCenter().x - width / 2;
        int yTopLeft = shape().gravityCenterFacet().getGravityCenter().y - height / 2;

        return new Region(xTopLeft, yTopLeft, xTopLeft + width, yTopLeft + height);
    }

    @Override
    protected Region determineRegion() {
        return genericDetermineRegion();
    }

    @Override
    public boolean fingerOn(int x, int y) {
        return genericDetermineRegion().contains(x, y);
    }
}
