package kit.scyla.core.facets.image;

import kit.scyla.core.facets.Facet;
import kit.scyla.core.facets.collision.CollisionFacet;
import kit.scyla.core.facets.drawing.DrawingFacet;
import kit.scyla.core.facets.rotation.RotationFacet;
import kit.scyla.core.shapes.Shape;

/**
 * Created with IntelliJ
 * Created by Ferrand
 * Date 24/12/2014
 */
public abstract class ImageFacet<TShape extends Shape<TShape, ?>> extends Facet<TShape> {
    private CollisionFacet<TShape> m_collision;
    private DrawingFacet<TShape, ?> m_drawing;
    private RotationFacet<TShape> m_rotation;

    public ImageFacet() {
        super();
    }

    @Override
    public void onShapeDefined(TShape shape) {
        this.m_collision = shape.collisionFacet();
        this.m_drawing = shape.drawingFacet();
        this.m_rotation = shape.rotationFacet();
    }

    public abstract TShape createInstance();
    /*private FingerEvent m_fingerEvent = null;
    private ForcesSystem m_forcesSystem;*/

    public abstract void restore(TShape shape);

    protected void defineRules(TShape shape) {
        /*shape.defineCollisionFacet(m_collision);
        shape.defineDrawingFacet(m_drawing);
        shape.defineBehaviorRule(m_behavior);
        shape.defineRotationFacet(m_rotation);

        m_collision.defineShape(shape);
        m_drawing.defineShape(shape);
        m_behavior.defineShape(shape);
        m_rotation.defineShape(shape);*/

        //shape.collisionFacet().recalculateContactArea();
        // Missing : Drawing "paint"
    }
}
