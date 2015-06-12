package kit.scyla.core;

import android.content.Context;
import android.graphics.Point;
import android.support.annotation.NonNull;
import android.view.KeyEvent;
import android.view.MotionEvent;

import kit.scyla.canvas.render.Scene;
import kit.scyla.canvas.touchEvent.TouchEvent;
import kit.scyla.canvas.touchEvent.TouchGestureEvent;
import kit.scyla.canvas.touchEvent.TouchTypeEvent;
import kit.scyla.canvas.views.ViewHandler;
import kit.scyla.canvas.views.templateEngine.GridTemplate;
import kit.scyla.core.shapes.Shape;

/**
 * Created with IntelliJ
 * Created by Nelaupe Lucas
 * Date 13/10/14
 */
public abstract class ScylaView {

    private final Context m_context;
    private final ViewHandler m_handler;

    private TouchGestureEvent touchGestureEvent;

    public ScylaView(final ViewHandler handler, Context context) {
        m_handler = handler;
        m_context = context;
        touchGestureEvent = new TouchGestureEvent() {
            @Override
            public void onTouchEvent(Shape shape, Point PointFinger) {

                shape.fingerEvents(PointFinger, TouchTypeEvent.Touch);

            }

            @Override
            public void onMovingEvent(@NonNull Shape shape, Point PointFinger) {

                shape.fingerEvents(PointFinger, TouchTypeEvent.Move);
            }

            @Override
            public void onLongPressEvent(@NonNull Shape shape, Point PointFinger) {

                shape.fingerEvents(PointFinger, TouchTypeEvent.LongTouch);
            }


            @Override
            public void onDoubleTapEvent(@NonNull Shape shape, Point PointFinger) {

                shape.fingerEvents(PointFinger, TouchTypeEvent.DoubleTap);
            }

            @Override
            public void onRaiseEvent(Shape shape, Point PointFinger) {
                shape.fingerEvents(PointFinger, TouchTypeEvent.Raise);
            }

        };
    }

    protected ViewHandler handler() {
        return m_handler;
    }

    public void addElements(final Scene scene, int screenWidth, int screenHeight) {
        GridTemplate grid = GridTemplate.getGrid(screenWidth, screenHeight);
        addCustomElements(scene, grid);
    }

    public abstract void addCustomElements(final Scene scene, GridTemplate grid);

    public void setup() {
        touchGestureEvent.clear();
    }

    public final void switchToView(ScylaView view) {
        handler().load(view);
    }

    public void subscribeTouchListener(Shape shape, TouchEvent... events) {
        touchGestureEvent.addElement(shape);

        shape.subscribeFingerEvent(events);
    }

    public void unsubscribeTouchListener(Shape shape) {
        touchGestureEvent.removeElement(shape);
    }

    public void onTouchViewEvent(MotionEvent event) {
        touchGestureEvent.onTouchEvent(event);
    }

    public abstract void onBackPressed();

    public void onKeyDown(int keyCode, KeyEvent event) {
    }

    protected ScylaView self() {
        return this;
    }

    public Context context() {
        return m_context;
    }

}
