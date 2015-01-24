package kit.scyla.core.facets.behavior;

import kit.scyla.core.shapes.Shape;

/**
 * Created with IntelliJ
 * Created by Nelaupe Lucas
 * Date 09/01/15
 */
public abstract class SimpleInteraction extends Interaction<Shape, Shape> {
    public SimpleInteraction() {
        super();
    }

    @Override
    public final void onInteract(Shape shape1, Shape shape2, InteractionType type) {
        interact(shape1, shape2, type);
    }

}
