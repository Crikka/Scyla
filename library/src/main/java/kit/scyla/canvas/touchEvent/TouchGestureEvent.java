package kit.scyla.canvas.touchEvent;

import android.graphics.Point;
import android.os.Handler;

import java.util.ArrayList;
import java.util.Calendar;

import kit.scyla.core.shapes.Shape;

/**
 * Created with IntelliJ
 * Created by Nelaupe Lucas
 * Date 04/01/15
 */
public abstract class TouchGestureEvent implements IGestureEvent {

    /**
     * For long press Test.
     */
    private final Handler handler = new Handler();
    private Shape shapeMoving;
    private ArrayList<Shape> touchedShape;
    private Point PointFinger;
    Runnable mLongPressed = new Runnable() {
        public void run() {
            if (shapeMoving != null) {
                onLongPressEvent(shapeMoving, PointFinger);
            }
        }
    };
    private long lastTap = Calendar.getInstance().getTimeInMillis();


    public TouchGestureEvent() {
        touchedShape = new ArrayList<>();
    }

    public void addElement(Shape shape) {
        touchedShape.add(shape);
    }

    public void removeElement(Shape shape) {
        touchedShape.remove(shape);
    }

    public void clear() {
        touchedShape.clear();
    }

    public void onTouchEvent(android.view.MotionEvent event) {
        PointFinger = new Point((int) event.getX(), (int) event.getY());

        switch (event.getAction()) {
            case android.view.MotionEvent.ACTION_DOWN:

                long now = Calendar.getInstance().getTimeInMillis();

                Shape currentShapeMoving = findClosestElement();

                int minDurationBetweenDoubleTap = 500;
                if (now - lastTap < minDurationBetweenDoubleTap && shapeMoving != null && currentShapeMoving != null && currentShapeMoving.equals(shapeMoving)) {
                    onDoubleTapEvent(shapeMoving, PointFinger);
                    break;
                } else {
                    shapeMoving = currentShapeMoving;
                    int minDurationLongPress = 200;
                    handler.postDelayed(mLongPressed, minDurationLongPress);
                    lastTap = Calendar.getInstance().getTimeInMillis();
                }
                break;
            case android.view.MotionEvent.ACTION_MOVE:
                handler.removeCallbacks(mLongPressed);
                if (shapeMoving != null) {
                    onMovingEvent(shapeMoving, PointFinger);
                }
                break;
            case android.view.MotionEvent.ACTION_UP:
                handler.removeCallbacks(mLongPressed);
                if (shapeMoving != null) {
                    onTouchEvent(shapeMoving, PointFinger);
                    onRaiseEvent(shapeMoving, PointFinger);
                }
                break;
        }

    }

    private Shape findClosestElement() {
        Shape res = null;

        for (Shape element : touchedShape) {
            if (element.collisionFacet().fingerOn(PointFinger.x, PointFinger.y)) {
                res = element;
                break;
            }
        }

        return res;
    }
}