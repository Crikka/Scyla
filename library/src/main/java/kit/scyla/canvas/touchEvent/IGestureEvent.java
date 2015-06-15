package kit.scyla.canvas.touchEvent;

import android.graphics.Point;

import kit.scyla.core.shapes.Shape;

/**
 * Created with IntelliJ
 * Created by Nelaupe Lucas
 * Date 04/01/15
 */
public interface IGestureEvent {

    public void onTouchEvent(Shape shape, Point PointFinger);

    public void onMovingEvent(Shape shape, Point PointFinger);

    public void onLongPressEvent(Shape shape, Point PointFinger);

    public void onDoubleTapEvent(Shape shape, Point PointFinger);

    public void onRaiseEvent(Shape shape, Point PointFinger);

}
