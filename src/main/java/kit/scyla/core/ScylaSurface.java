package kit.scyla.core;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.KeyEvent;

import kit.scyla.canvas.render.Scene;
import kit.scyla.canvas.views.ViewHandler;

/**
 * Created with IntelliJ
 * Created by Ferrand
 * Date : 22/01/2015
 */
public interface ScylaSurface<TView extends ScylaView> {
    TView defaultView(ViewHandler handler, Context context);

    Scene scene();

    boolean onKeyDown(int keyCode, @NonNull KeyEvent event);

    void onBackPressed();
}
