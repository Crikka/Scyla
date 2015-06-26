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

package kit.scyla.gl.render;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.PixelFormat;
import android.graphics.PorterDuff;
import android.opengl.GLSurfaceView;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.SurfaceHolder;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import kit.scyla.canvas.Share.SharedElements;
import kit.scyla.canvas.debug.FPS;
import kit.scyla.canvas.render.Scene;
import kit.scyla.canvas.views.ViewHandler;
import kit.scyla.core.ScylaSurface;
import kit.scyla.core.ScylaView;
import kit.scyla.gl.views.ScylaGLView;

/**
 * Created with IntelliJ
 * Created by Ferrand
 * Date : 22/01/2015
 */
@SuppressWarnings({"unused", "unchecked"})
@Deprecated
public abstract class ScylaGLSurface extends GLSurfaceView implements GLSurfaceView.Renderer, ScylaSurface<ScylaGLView> {

    private ViewHandler m_stageHandler;
    private Scene m_scene;

    // For debug engine
    private FPS frameRate = new FPS();

    private Context m_context;

    public ScylaGLSurface(final Context context) {
        super(context);

        m_context = context;

        setZOrderOnTop(true);

        final SurfaceHolder holder = this.getHolder();
        holder.addCallback(this);
        holder.setFormat(PixelFormat.TRANSPARENT);

        //GridTemplate grid = GridTemplate.getInstance();

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

    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        m_stageHandler.getCurrent().onTouchViewEvent(event);
        return true;
    }

    /*@Override
    public void draw(  final Canvas canvas) {

        m_scene.onEachElement(new Action1<SimpleShape>() {
            @Override
            public void call(SimpleShape shape) {

                if (DebugEngine.getInstance().minDebugLevel(Level.FULL)) {
                    Paint p = new Paint();
                    p.setColor(Color.RED);
                    canvas.drawRect(shape.collisionFacet().getHitBox().getBounds(), p);
                }
                shape.drawingFacet().draw(canvas);

                if (DebugEngine.getInstance().minDebugLevel(Level.FULL)) {
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
        });

        if (DebugEngine.getInstance().minDebugLevel(Level.LOW)) {
            Paint p = new Paint();
            p.setColor(Color.WHITE);
            p.setTextSize(30);
            canvas.drawText(frameRate.getFPS(), canvas.getWidth() - 70, 40, p);
        }
    }*/

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
        m_stageHandler.getCurrent().onBackPressed();
        return true;
    }

    public void onKDown(int keyCode, KeyEvent event) {
        m_stageHandler.getCurrent().onKeyDown(keyCode, event);
    }

    @Override
    public void onSurfaceCreated(GL10 gl, EGLConfig config) {

    }

    @Override
    public void onSurfaceChanged(GL10 gl, int width, int height) {

    }

    @Override
    public void onDrawFrame(GL10 gl) {

    }

    @Override
    public Scene scene() {
        return m_scene;
    }

    public ViewHandler getViewHandler() {
        return m_stageHandler;
    }
}
