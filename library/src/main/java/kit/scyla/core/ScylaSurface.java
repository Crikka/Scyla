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
import android.view.KeyEvent;

import kit.scyla.canvas.render.Scene;
import kit.scyla.canvas.views.ViewHandler;

/**
 * Created with IntelliJ
 * Created by Ferrand
 * Date : 22/01/2015
 */
@SuppressWarnings({"unused", "unchecked"})
public interface ScylaSurface<TView extends ScylaView> {
    TView defaultView(ViewHandler handler, Context context);

    Scene scene();

    boolean onKeyDown(int keyCode, KeyEvent event);

    boolean onBackPressed();
}
