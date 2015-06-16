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

package kit.scyla.canvas.views;

import android.content.Context;

import kit.scyla.core.ScylaView;

/**
 * Created with IntelliJ
 * Created by Ferrand
 * Date : 22/01/2015
 */
public abstract class ScylaCanvasView extends ScylaView {
    public ScylaCanvasView(ViewHandler handler, Context context) {
        super(handler, context);
    }
}
