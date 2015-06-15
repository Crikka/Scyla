package kit.scyla.core.facets.collision;

import android.graphics.Region;

import kit.scyla.core.facets.Facet;
import kit.scyla.core.shapes.Shape;

/**
 * Created with IntelliJ
 * Created by Ferrand
 * Date 04/10/2014
 */
public abstract class CollisionFacet<TShape extends Shape<TShape, ?>> extends Facet<TShape> {
    private transient Region m_region;

    public CollisionFacet() {
        super();
    }

    @Override
    public void onShapeDefined(TShape shape) {
        this.m_region = determineRegion();
    }

    public abstract boolean fingerOn(int x, int y);

    protected abstract Region determineRegion();

    public final Region getHitBox() {
        return m_region;
    }

    public final void recalculateContactArea() {
        m_region = determineRegion();
    }

    public final boolean intersect(Shape other) {
        return (!m_region.quickReject(other.collisionFacet().getHitBox()) && m_region.op(other.collisionFacet().getHitBox(), Region.Op.INTERSECT));
    }
}
