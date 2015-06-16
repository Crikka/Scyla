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

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import kit.scyla.canvas.shapes.custom.helper.CombinationShapes;
import kit.scyla.canvas.shapes.custom.skinned.SkinnedShape;
import kit.scyla.core.ScylaView;
import kit.scyla.core.facets.force.Gravity;
import kit.scyla.core.shapes.Shape;
import rx.Scheduler;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * Created with IntelliJ
 * Created by Nelaupe Lucas
 * Date 04/01/15
 */
public abstract class Scene {
    private ArrayList<Shape> m_dynamicsElements;
    private ArrayList<Shape> m_staticsElements;
    private ArrayList<Shape> m_phantomsElements;
    private ArrayList<Action0> m_actions;

    private boolean m_reload, m_play, m_lock;
    private int m_width, m_height;
    private ScylaView m_stage;

    public Scene() {
        this.m_dynamicsElements = new ArrayList<>();
        this.m_staticsElements = new ArrayList<>();
        this.m_phantomsElements = new ArrayList<>();
        this.m_actions = new ArrayList<>();

        this.m_width = 0;
        this.m_height = 0;
        this.m_reload = false;
        this.m_play = false;
        this.m_lock = true;
        this.m_stage = null;

        final Action0 actionRender = new Action0() {
            @Override
            public void call() {
                try {
                    render();
                    afterRender();
                    m_reload = false;
                } catch (Throwable exception) {
                    exception.printStackTrace();
                }
            }
        };

        final Scene self = this;
        Scheduler.Worker worker = Schedulers.computation().createWorker();
        worker.schedulePeriodically(new Action0() {

            @Override
            public void call() {
                if (m_stage != null && !m_reload) {
                    // Load a level into scene
                    m_reload = true;
                    m_dynamicsElements.clear();
                    m_staticsElements.clear();
                    m_phantomsElements.clear();
                    m_stage.setup();
                    m_stage.addElements(self, m_width, m_height);
                    m_stage = null;
                    m_reload = false;
                }

                if (!m_reload && !m_lock) { // prevent over rating
                    m_reload = true;

                    Gravity.getInstance().stepGravityForward();

                    for (Shape shape : m_dynamicsElements) {
                        if (m_play) {
                            try {
                                shape.forcesSystem().stepForward();
                            } catch (Throwable exception) {
                                exception.printStackTrace();
                            }
                        }
                    }

                    AndroidSchedulers.mainThread().createWorker().schedule(actionRender);
                }
            }
        }, 0, 16, TimeUnit.MILLISECONDS);
        // 60 FPS
        // 60 Frame -> 1000 MS
        // 1 -> 16.6 MS
    }

    public void afterRender() {
        if (!m_actions.isEmpty()) {
            for (Action0 action : m_actions) {
                action.call();
            }

            m_actions.clear();
        }
    }

    public void load(ScylaView stage, int width, int height) {
        m_stage = stage;
        m_width = width;
        m_height = height;
    }

    /**
     * Declare dynamic (i.e. moving) element to a scene
     */
    public void addDynamicElement(final Shape element) {
        subscribeNewDynamicElement(element);

        m_dynamicsElements.add(element);
    }

    private void subscribeNewDynamicElement(final Shape element) {
        onEachStaticElement(new Action1<Shape>() {

            @Override
            public void call(Shape shape) {
                element.forcesSystem().subscribe(shape);
            }
        });

        onEachDynamicElement(new Action1<Shape>() {

            @Override
            public void call(Shape shape) {
                if (shape != element) {
                    element.forcesSystem().subscribe(shape);
                }
            }
        });
    }

    public int getSizeElements() {
        return m_staticsElements.size();
    }

    /**
     * Declare static (i.e. non-moving) element to a scene
     */
    public void addStaticElement(final Shape element) {
        if (m_play) {
            addStaticElementAtRuntime(element);
        } else {
            addStaticElementBasic(element);
        }
    }

    public void addStaticElement(final CombinationShapes shapes) {

        ArrayList<SkinnedShape> list = shapes.getElements();

        for (SkinnedShape shape : list) {
            addStaticElement(shape);
        }
    }

    private void addStaticElementBasic(final Shape element) {
        onEachDynamicElement(new Action1<Shape>() {

            @Override
            public void call(Shape shape) {
                shape.forcesSystem().subscribe(element);
            }
        });

        m_staticsElements.add(element);
    }

    /**
     * Add element when scene already displayed
     */
    private void addStaticElementAtRuntime(final Shape element) {
        this.subscribeRuntimeAction(new Action0() {
            @Override
            public void call() {
                m_staticsElements.add(element);
            }
        });

    }

    public void removeElement(final Shape element) {
        if (m_play) {
            removeElementRuntime(element);
        } else {
            removeElementBasic(element);
        }
    }


    public void removeElement(final List<Shape> element) {
        for (Shape shape : element) {
            removeElement(shape);
        }
    }

    public void removeElement(final CombinationShapes shapes) {

        ArrayList<SkinnedShape> list = shapes.getElements();

        for (SkinnedShape shape : list) {
            removeElement(shape);
        }
    }

    private void removeElementBasic(final Shape element) {
        boolean resetSubscription = false;
        if (m_phantomsElements.contains(element)) {
            m_phantomsElements.remove(element);
        } else {
            if (m_staticsElements.contains(element)) {
                m_staticsElements.remove(element);
                resetSubscription = true;
            } else {
                if (m_dynamicsElements.contains(element)) {
                    m_dynamicsElements.remove(element);
                    resetSubscription = true;
                }
            }
        }

        if (resetSubscription) {
            onEachDynamicElement(new Action1<Shape>() {

                @Override
                public void call(Shape shape) {
                    shape.forcesSystem().reset();
                }
            });

            onEachDynamicElement(new Action1<Shape>() {

                @Override
                public void call(Shape shape) {
                    subscribeNewDynamicElement(shape);
                }
            });
        }
    }

    /**
     * remove element when scene already displayed
     */
    private void removeElementRuntime(final Shape element) {
        this.subscribeRuntimeAction(new Action0() {
            @Override
            public void call() {
                removeElementBasic(element);
            }
        });
    }

    /**
     * Declare phantom (i.e. with no physic) element to a scene
     */
    public void addPhantomElement(Shape element) {
        m_phantomsElements.add(element);
    }

    public final void onEachElement(Action1<Shape> function) {
        onEachPhantomElement(function);
        onEachDynamicElement(function);
        onEachStaticElement(function);
    }

    public final void onEachDynamicElement(Action1<Shape> function) {
        for (Shape element : m_dynamicsElements) {
            function.call(element);
        }
    }

    public final void onEachStaticElement(Action1<Shape> function) {
        for (Shape element : m_staticsElements) {
            function.call(element);
        }
    }

    public final void onEachPhantomElement(Action1<Shape> function) {
        for (Shape element : m_phantomsElements) {
            function.call(element);
        }
    }

    public final void play() {
        if (!m_play) {
            onEachElement(new Action1<Shape>() {
                @Override
                public void call(Shape shape) {
                    shape.collisionFacet().recalculateContactArea();
                }
            });

            m_play = true;
        }
    }

    public final boolean isPaused() {
        return !m_play;
    }

    public final void startRender() {
        m_lock = false;
    }

    public final void stopRender() {
        m_lock = true;
    }

    public void pause() {
        m_play = false;
    }

    public abstract void render();

    public void subscribeRuntimeAction(Action0 action) {
        m_actions.add(action);
    }
}