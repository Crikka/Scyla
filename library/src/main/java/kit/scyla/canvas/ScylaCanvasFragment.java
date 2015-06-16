package kit.scyla.canvas;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.KeyEvent;

import java.lang.reflect.Constructor;

import kit.scyla.canvas.render.ScylaCanvasSurface;
import kit.scyla.canvas.views.ScylaCanvasView;
import kit.scyla.canvas.views.ViewHandler;
import kit.scyla.core.ScylaFragment;

/**
 * Created with IntelliJ
 * Created by lucas
 * Date 26/03/15
 */
public class ScylaCanvasFragment extends ScylaFragment<ScylaCanvasSurface> {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (savedInstanceState == null){

            m_render = new ScylaCanvasSurface(getActivity().getApplicationContext()) {
                @Override
                public ScylaCanvasView defaultView(ViewHandler handler, Context context) {

                    try {
                        Class<ScylaCanvasView> viewClass = (Class<ScylaCanvasView>) getArguments().getSerializable(INITIALIZE);
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

    public boolean onKeyDown(int keyCode, @NonNull KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            surface().onBackPressed();
        } else {
            surface().onKeyDown(keyCode, event);
        }
        return true;
    }

    @Override
    public void onPause() {
        surface().scene().stopRender();
        super.onPause();
    }

}
