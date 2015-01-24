package kit.scyla.core.facets.behavior;

import kit.scyla.core.shapes.Shape;

/**
 * Created with IntelliJ
 * Created by Ferrand
 * Date : 06/01/2015
 */
public abstract class Interaction<TShape1 extends Shape, TShape2 extends Shape> {
    private TShape1 m_shape1;
    private TShape2 m_shape2;

    protected Interaction() {
        this.m_shape1 = null;
        this.m_shape2 = null;
    }

    public Interaction(TShape1 shape1, TShape2 shape2) {
        this.m_shape1 = shape1;
        this.m_shape2 = shape2;
    }

    public Interaction(TShape2 shape2) {
        this.m_shape2 = shape2;
    }

    private boolean secure(Shape shape1, Shape shape2) {
        return ((shape1 == m_shape1) && (shape2 == m_shape2));
    }

    public void onInteract(Shape shape1, Shape shape2, InteractionType type) {
        if (secure(shape1, shape2)) {
            interact(m_shape1, m_shape2, type);
        }
    }

    public abstract void interact(TShape1 shape1, TShape2 shape2, InteractionType type);

    public TShape1 shape1() {
        return m_shape1;
    }

    public TShape2 shape2() {
        return m_shape2;
    }
}
