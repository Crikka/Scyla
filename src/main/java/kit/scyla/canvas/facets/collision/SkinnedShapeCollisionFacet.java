package kit.scyla.canvas.facets.collision;

import android.graphics.Region;

import kit.scyla.canvas.shapes.custom.skinned.SkinnedShape;
import kit.scyla.core.facets.collision.CollisionFacet;

/**
 * Created with IntelliJ
 * Created by Ferrand
 * Date 10/10/2014
 */
public class SkinnedShapeCollisionFacet<TShape extends SkinnedShape<TShape>> extends CollisionFacet<TShape> {
    private Region genericDetermineRegion() {
        int width = shape().getSkin().getWidth(), height = shape().getSkin().getHeight();
        int xTopLeft = shape().getGravityCenter().x - width / 2;
        int yTopLeft = shape().getGravityCenter().y - height / 2;

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
