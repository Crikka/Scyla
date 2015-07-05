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

package kit.scyla.canvas.render;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.Point;
import android.graphics.PorterDuff;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import kit.scyla.BuildConfig;
import kit.scyla.canvas.Share.SharedElements;
import kit.scyla.canvas.debug.FPS;
import kit.scyla.canvas.views.ScylaCanvasView;
import kit.scyla.canvas.views.ViewHandler;
import kit.scyla.canvas.views.templateEngine.GridTemplate;
import kit.scyla.core.ScylaSurface;
import kit.scyla.core.ScylaView;
import kit.scyla.core.facets.drawing.DrawingFacet;
import kit.scyla.core.shapes.Shape;
import rx.functions.Action2;

/**
 * Created with IntelliJ
 * Created by Ferrand
 * Date : 22/01/2015
 */
@SuppressWarnings({"unused", "unchecked"})
public abstract class ScylaCanvasSurface extends SurfaceView implements SurfaceHolder.Callback, ScylaSurface<ScylaCanvasView> {

    private final ViewHandler m_stageHandler;
    private final Scene m_scene;
    private final GridTemplate grid;
    private final Context m_context;
    private final Action2 m_actionDraw;
    private FPS frameRate;

    public ScylaCanvasSurface(final Context context) {
        super(context);

        if (BuildConfig.DEBUG) {
            frameRate = new FPS();
        }

        m_context = context;

        setZOrderOnTop(true);

        final SurfaceHolder holder = this.getHolder();
        holder.addCallback(this);
        holder.setFormat(PixelFormat.TRANSPARENT);

        grid = GridTemplate.getInstance();

        this.m_scene = new Scene() {

            @Override
            public void render() {
                Canvas canvas = holder.lockCanvas();
                if (canvas == null) {
                    return;
                }
                canvas.drawColor(Color.TRANSPARENT, PorterDuff.Mode.CLEAR);

                draw(canvas);
                holder.unlockCanvasAndPost(canvas);
            }
        };

        m_stageHandler = new ViewHandler(context, m_scene) {

            @Override
            public void onNext() {
                m_scene.pause();

                m_scene.load(getCurrent(), holder.getSurfaceFrame().width(), holder.getSurfaceFrame().height());
            }

            @Override
            public ScylaView bootstrap(ViewHandler handler) {
                return defaultView(handler, m_context);
            }
        };

        m_actionDraw = new Action2<Canvas, Shape>() {
            @Override
            public void call(Canvas canvas, Shape shape) {

                shape.doIt();

                if (BuildConfig.DEBUG) {
                    Paint p = new Paint();
                    p.setColor(Color.RED);
                    if (shape.collisionFacet() != null) {
                        canvas.drawRect(shape.collisionFacet().getHitBox().getBounds(), p);
                    }
                }
                DrawingFacet facet = shape.drawingFacet();
                if (facet != null) {
                    facet.draw(canvas);
                }

                if (BuildConfig.DEBUG) {
                    Paint p = new Paint();
                    p.setColor(Color.WHITE);
                    for (int i = 0; i < GridTemplate.numberOfColumn; i++) {
                        Point start = grid.getPosition(i, 0);
                        Point end = grid.getPosition(i, GridTemplate.numberOfRow);
                        canvas.drawLine(start.x, start.y, end.x, end.y, p);
                    }

                    for (int i = 0; i < GridTemplate.numberOfRow; i++) {
                        Point start = grid.getPosition(0, i);
                        Point end = grid.getPosition(GridTemplate.numberOfColumn, i);
                        canvas.drawLine(start.x, start.y, end.x, end.y, p);
                    }

                }

            }
        };

    }

    @Override
    public boolean onTouchEvent(@SuppressWarnings("NullableProblems") MotionEvent event) {
        m_stageHandler.getCurrent().onTouchViewEvent(event);
        return true;
    }

    @Override
    public void draw(@SuppressWarnings("NullableProblems") final Canvas canvas) {
        super.draw(canvas);

        m_scene.onDrawEachElement(canvas, m_actionDraw);

        if (BuildConfig.DEBUG) {
            Paint p = new Paint();
            p.setColor(Color.WHITE);
            p.setTextSize(30);
            canvas.drawText(frameRate.getFPS(), canvas.getWidth() - 70, 40, p);
        }
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
    }

    @Override
    public void surfaceCreated(final SurfaceHolder holder) {
        SharedElements.initRadio(m_context, holder.getSurfaceFrame().width(), holder.getSurfaceFrame().height());

        m_scene.startRender();
        m_scene.load(m_stageHandler.getCurrent(), holder.getSurfaceFrame().width(), holder.getSurfaceFrame().height());
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        m_scene.stopRender();
    }

    public boolean onBackPressed() {
        return m_stageHandler.getCurrent().onBackPressed();
    }

    @Override
    public boolean onKeyDown(int keyCode, @SuppressWarnings("NullableProblems") KeyEvent event) {
        return m_stageHandler.getCurrent().onKeyDown(keyCode, event);
    }

    public Scene scene() {
        return m_scene;
    }

    public ViewHandler getViewHandler() {
        return m_stageHandler;
    }
}
