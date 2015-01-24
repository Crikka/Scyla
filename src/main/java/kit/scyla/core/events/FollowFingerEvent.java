package kit.scyla.core.events;

import android.graphics.Point;

import kit.scyla.canvas.touchEvent.TouchEvent;
import kit.scyla.canvas.touchEvent.TouchTypeEvent;
import kit.scyla.core.shapes.Shape;

/**
 * Created with IntelliJ
 * Created by Nelaupe Lucas
 * Date 11/01/15
 */
public class FollowFingerEvent<TSelf extends Shape<TSelf, ?>> extends TouchEvent {

    private TSelf m_shape;

    public FollowFingerEvent(TSelf shape) {
        m_shape = shape;
    }

    @Override
    public void onTouch(Point point, TouchTypeEvent event) {
        if (event == TouchTypeEvent.Touch || event == TouchTypeEvent.Move) {
            m_shape.gravityCenterFacet().moveGravityCenterTo(point);
        }
    }
}
