package kit.scyla;

import android.content.Context;
import android.view.SurfaceView;

import kit.scyla.canvas.views.ViewHandler;
import kit.scyla.core.ScylaFragment;
import kit.scyla.core.ScylaSurface;
import kit.scyla.core.ScylaView;

/**
 * Created with IntelliJ
 * Created by lucas
 * Date 15/01/15
 */
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
