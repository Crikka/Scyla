package kit.scyla.canvas.shapes.custom.helper;

import java.util.ArrayList;

import kit.scyla.canvas.shapes.custom.skinned.SkinnedShape;

/**
 * Created with IntelliJ
 * Created by Nelaupe Lucas
 * Date 04/01/15
 */
public abstract class CombinationShapes<TShape extends SkinnedShape<TShape>> {

    private ArrayList<TShape> m_elements;

    public CombinationShapes() {
        m_elements = new ArrayList<>();
    }

    public ArrayList<TShape> getElements() {
        return m_elements;
    }

    public void addShape(TShape shape) {
        m_elements.add(shape);
    }
}
