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

package kit.scyla.gl;

import android.content.Context;

import kit.scyla.Scyla;
import kit.scyla.canvas.views.ViewHandler;
import kit.scyla.gl.render.ScylaGLSurface;
import kit.scyla.gl.views.ScylaGLView;

/**
 * Created with IntelliJ
 * Created by Ferrand
 * Date : 22/01/2015
 */
@SuppressWarnings({"unused", "unchecked"})
@Deprecated
public abstract class ScylaGL extends Scyla<ScylaGLSurface, ScylaGLView> {

    @Override
    @Deprecated
    public ScylaGLSurface setup() {
        return new ScylaGLSurface(null) {
            @Override
            public ScylaGLView defaultView(ViewHandler handler, Context context) {
                return firstView(handler, context);
            }
        };
    }
}
