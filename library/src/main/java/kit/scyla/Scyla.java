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

package kit.scyla;

import android.content.Context;
import android.view.SurfaceView;

import kit.scyla.canvas.views.ViewHandler;
import kit.scyla.core.ScylaFragment;
import kit.scyla.core.ScylaSurface;
import kit.scyla.core.ScylaView;

/**
 * Created with IntelliJ
 * Created by Lucas Nelaupe
 * Date 15/01/15
 */
@SuppressWarnings({"unused", "unchecked"})
public abstract class Scyla<TSurface extends SurfaceView & ScylaSurface<TView>, TView extends ScylaView> {

    @Deprecated
    private ScylaFragment<TSurface> m_Scyla_fragment;

    @Deprecated
    public abstract int layoutID();

    @Deprecated
    public abstract int renderID();

    @Deprecated
    public abstract TView firstView(ViewHandler handler, Context context);

    @Deprecated
    public abstract TSurface setup();

}
