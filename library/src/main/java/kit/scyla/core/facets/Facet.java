package kit.scyla.core.facets;

import java.io.Serializable;

import kit.scyla.core.shapes.Shape;

/**
 * Created with IntelliJ
 * Created by Ferrand
 * Date 04/10/2014
 */
public abstract class Facet<TShape extends Shape> implements Serializable {
    private transient TShape m_shape;

    public abstract void onShapeDefined(TShape shape);

    public void defineShape(TShape shape) {
        this.m_shape = shape;
        onShapeDefined(shape);
    }

    protected TShape shape() {
        return m_shape;
    }

}
