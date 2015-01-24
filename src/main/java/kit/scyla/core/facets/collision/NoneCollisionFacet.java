package kit.scyla.core.facets.collision;

import android.graphics.Region;

import kit.scyla.core.shapes.Shape;

/**
 * Created with IntelliJ
 * Created by Ferrand
 * Date 25/12/2014
 */
public class NoneCollisionFacet<TShape extends Shape<TShape, ?>> extends CollisionFacet<TShape> {
    @Override
    protected Region determineRegion() {
        return new Region();
    }

    @Override
    public boolean fingerOn(int x, int y) {
        return false;
    }
}
