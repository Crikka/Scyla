package kit.scyla.core.events;

import android.graphics.Point;

import kit.scyla.core.shapes.Shape;
import kit.scyla.canvas.touchEvent.TouchEvent;
import kit.scyla.canvas.touchEvent.TouchTypeEvent;

/**
 * Created with IntelliJ
 * Created by Ferrand
 * Date : 01/01/2015
 */
public class MoveFingerEvent extends TouchEvent {
    private Shape m_shape;

    public MoveFingerEvent(Shape shape) {
        this.m_shape = shape;
    }

    @Override
    public void onTouch(Point point, TouchTypeEvent event) {
        if (event == TouchTypeEvent.Touch || event == TouchTypeEvent.Move) {
            m_shape.gravityCenterFacet().moveGravityCenterTo(point);
        }
    }
}
