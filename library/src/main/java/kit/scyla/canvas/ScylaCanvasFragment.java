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

package kit.scyla.canvas;

import android.content.Context;
import android.os.Bundle;
import android.view.KeyEvent;

import java.lang.reflect.Constructor;

import kit.scyla.canvas.render.ScylaCanvasSurface;
import kit.scyla.canvas.views.ScylaCanvasView;
import kit.scyla.canvas.views.ViewHandler;
import kit.scyla.core.ScylaFragment;

/**
 * Created with IntelliJ
 * Created by Lucas Nelaupe
 * Date 26/03/15
 */
@SuppressWarnings({"unused", "unchecked"})
public class ScylaCanvasFragment extends ScylaFragment<ScylaCanvasSurface> {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (savedInstanceState == null) {

            m_render = new ScylaCanvasSurface(getActivity().getApplicationContext()) {
                @Override
                public ScylaCanvasView defaultView(ViewHandler handler, Context context) {

                    try {
                        Class<ScylaCanvasView> viewClass = (Class<ScylaCanvasView>) getArguments().getSerializable(INITIALIZE);
                        if(viewClass == null) {
                            throw new RuntimeException("Unable to get the class");
                        }
                        Constructor<ScylaCanvasView> constructor = viewClass.getConstructor(ViewHandler.class, Context.class);
                        return constructor.newInstance(handler, context);
                    } catch (Exception e) {
                        e.printStackTrace();
                        throw new RuntimeException(e);
                    }
                }
            };
        }
    }

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            return surface().onBackPressed();
        } else {
            return surface().onKeyDown(keyCode, event);
        }
    }

    @Override
    public void onPause() {
        surface().scene().stopRender();
        super.onPause();
    }

    @Override
    public void onResume() {
        super.onResume();
        surface().scene().startRender();
    }
}
