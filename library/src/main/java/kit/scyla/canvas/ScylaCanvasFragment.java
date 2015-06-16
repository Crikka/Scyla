package kit.scyla.canvas;

import android.support.annotation.NonNull;
import android.view.KeyEvent;

import kit.scyla.canvas.render.ScylaCanvasSurface;
import kit.scyla.core.ScylaFragment;

/**
 * Created with IntelliJ
 * Created by lucas
 * Date 26/03/15
 */
public class ScylaCanvasFragment extends ScylaFragment<ScylaCanvasSurface> {

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
