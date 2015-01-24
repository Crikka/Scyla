package kit.scyla.canvas.shapes.custom.helper;

import android.content.Context;
import android.graphics.Point;

import kit.scyla.canvas.shapes.custom.skinned.SkinnedShape;
import kit.scyla.core.facets.behavior.Interaction;
import kit.scyla.core.facets.behavior.InteractionType;
import kit.scyla.core.facets.rotation.RotateAround;
import kit.scyla.core.shapes.Shape;

/**
 * Created with IntelliJ
 * Created by Nelaupe Lucas
 * Date 21/12/14
 */
public abstract class BarOfShape<TShape extends SkinnedShape<TShape>> extends CombinationShapes<TShape> {

    private Context m_context;
    private Point m_gravityPoint;
    private int m_number;

    public BarOfShape(Context context, Point point, int number) {
        m_context = context;
        m_gravityPoint = point;
        m_number = number;
        initialize();
    }

    public final void defineRotationRule(RotateAround rotation) {

        Point previousShape = null;
        Point rotationPoint = null;

        for (TShape shape : getElements()) {

            if (previousShape == null) {
                rotationPoint = rotation.rotationPoint();
            }

            shape.defineRotationFacet(new RotateAround<TShape>(rotation.speed(), rotationPoint, true, rotation.angle()));

            previousShape = shape.getGravityCenter();
        }

    }

    public void initialize() {
        addElements(m_number);

        Point previousShape = null;
        Point offset = null;

        for (TShape shape : getElements()) {

            if (previousShape == null) {
                shape.gravityCenterFacet().moveGravityCenterTo(m_gravityPoint);
            } else {
                int x = previousShape.x + 2 * offset.x / 2;
                int y = previousShape.y + 2 * offset.y / 2;

                shape.gravityCenterFacet().moveGravityCenterTo(x, y);
            }

            previousShape = shape.getGravityCenter();
            offset = new Point(shape.getSkin().getWidth(), shape.getSkin().getHeight());
        }
    }

    public abstract void addElements(int number);

    public Context context() {
        return m_context;
    }

    public final void subscribeInteraction(Interaction... interactions) {

        for (TShape shape : getElements()) {

            for (final Interaction interaction : interactions) {
                shape.subscribeInteraction(new Interaction<Shape, Shape>(shape, interaction.shape2()) {
                    @Override
                    public void interact(Shape shape, Shape shape2, InteractionType type) {
                        interaction.interact(shape, shape2, type);
                    }
                });
            }

        }
    }
}