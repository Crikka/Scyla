package kit.scyla.canvas.facets.image;


import kit.scyla.canvas.shapes.custom.skinned.SkinnedShape;
import kit.scyla.core.facets.image.ImageFacet;

/**
 * Created by Ferrand on 24/12/2014.
 * TODO TODO TODO
 */
public class SkinnedShapeImageFacet<TShape extends SkinnedShape<TShape>> extends ImageFacet<TShape> {
    public SkinnedShapeImageFacet() {
        super();
    }

    @Override
    public TShape createInstance() {
        return null;
    }

    @Override
    public void restore(TShape shape) {

    }
}
