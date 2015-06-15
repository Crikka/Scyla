package kit.scyla.core;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;

import kit.scyla.canvas.views.ViewHandler;

/**
 * Created with IntelliJ
 * Created by Nelaupe Lucas
 * Date 04/01/15
 */
public abstract class ScylaFragment<TSurface extends SurfaceView> extends Fragment {

    private TSurface m_render;

    public ScylaFragment(TSurface render) {
        super();
        this.m_render = render;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return m_render;
    }

    public TSurface surface() {
        return m_render;
    }

    public abstract ScylaView startView(ViewHandler handler, Context context);
}