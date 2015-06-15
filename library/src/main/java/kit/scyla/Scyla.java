package kit.scyla;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.KeyEvent;
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
public abstract class Scyla<TSurface extends SurfaceView & ScylaSurface<TView>, TView extends ScylaView> extends Activity {

    private ScylaFragment<TSurface> m_Scyla_fragment;

    public abstract int layoutID();

    public abstract int renderID();

    public abstract TView firstView(ViewHandler handler, Context context);

    public abstract TSurface setup();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layoutID());

        if(savedInstanceState != null){
            m_Scyla_fragment = (ScylaFragment<TSurface>) getFragmentManager().findFragmentByTag("scyla");
        } else {
            this.m_Scyla_fragment = new ScylaFragment<TSurface>(setup()) {
                @Override
                public ScylaView startView(ViewHandler handler, Context context) {
                    return firstView(handler, context);
                }
            };

            FragmentManager fm = getFragmentManager();
            FragmentTransaction ft = fm.beginTransaction();


            ft.replace(renderID(), m_Scyla_fragment, "scyla");

            ft.commit();
            fm.executePendingTransactions();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        m_Scyla_fragment.surface().scene().stopRender();
    }

    @Override
    public boolean onKeyDown(int keyCode, @NonNull KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            onBackPressed();
        } else {
            m_Scyla_fragment.surface().onKeyDown(keyCode, event);
        }

        return true;
    }

    @Override
    public void onBackPressed() {
        m_Scyla_fragment.surface().onBackPressed();
    }
}
