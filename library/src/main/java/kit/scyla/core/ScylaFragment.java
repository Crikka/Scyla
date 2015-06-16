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
public class ScylaFragment<TSurface extends SurfaceView> extends Fragment {

    public static final String INITIALIZE = "surface";

    private TSurface m_render;

    @Deprecated
    public ScylaFragment(TSurface render) {
        super();
        this.m_render = render;
    }

    public ScylaFragment(){
        super();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (savedInstanceState != null){
            m_render = (TSurface) savedInstanceState.getSerializable(INITIALIZE);
        } else {
            m_render = (TSurface) getArguments().getSerializable(INITIALIZE);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return m_render;
    }

    public TSurface surface() {
        return m_render;
    }

    @Deprecated
    public ScylaView startView(ViewHandler handler, Context context){
        return null;
    }
}