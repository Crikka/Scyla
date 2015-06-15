package kit.scyla.core.facets.behavior;

import kit.scyla.core.shapes.Shape;

/**
 * Created with IntelliJ
 * Created by Ferrand
 * Date : 29/12/2014
 */
public class GroundInteraction extends SimpleInteraction {
    public GroundInteraction() {
        super();
    }

    @Override
    public void interact(Shape shape1, Shape shape2, InteractionType type) {
        if (type == InteractionType.Enter) {
            shape2.forcesSystem().blockGravity();
        }
    }
}
