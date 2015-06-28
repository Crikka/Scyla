/*
 *  Copyright 2015-present Lucas Nelaupe and Ferrand
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

package kit.scyla.core;

import android.content.Context;
import android.graphics.Point;
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
@SuppressWarnings({"unused", "unchecked"})
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
            public void onMovingEvent(Shape shape, Point PointFinger) {

                shape.fingerEvents(PointFinger, TouchTypeEvent.Move);
            }

            @Override
            public void onLongPressEvent(Shape shape, Point PointFinger) {

                shape.fingerEvents(PointFinger, TouchTypeEvent.LongTouch);
            }


            @Override
            public void onDoubleTapEvent(Shape shape, Point PointFinger) {

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

    public boolean onBackPressed(){
        return false;
    }

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        return false;
    }

    protected ScylaView self() {
        return this;
    }

    public Context context() {
        return m_context;
    }

}
