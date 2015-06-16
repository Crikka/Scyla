package kit.scyla.test;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.os.Bundle;
import android.view.KeyEvent;

import kit.scyla.canvas.ScylaCanvasFragment;
import kit.scyla.canvas.render.ScylaCanvasSurface;
import kit.scyla.canvas.views.ScylaCanvasView;
import kit.scyla.canvas.views.ViewHandler;
import kit.scyla.core.ScylaFragment;

public class MainActivity extends Activity {

    ScylaCanvasFragment m_Scyla_fragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(savedInstanceState != null){
            m_Scyla_fragment = (ScylaCanvasFragment) getFragmentManager().findFragmentByTag("scyla");
        } else {

            ScylaCanvasSurface surface = new ScylaCanvasSurface(getApplicationContext()) {
                @Override
                public ScylaCanvasView defaultView(ViewHandler handler, Context context) {
                    return new myView(handler, context);
                }
            };

            m_Scyla_fragment = new ScylaCanvasFragment();

            Bundle args = new Bundle();
            args.putSerializable(ScylaFragment.INITIALIZE, surface);
            m_Scyla_fragment.setArguments(args);

            FragmentManager fm = getFragmentManager();
            FragmentTransaction ft = fm.beginTransaction();
            ft.replace(R.id.render, m_Scyla_fragment, "scyla");
            ft.commit();
            fm.executePendingTransactions();
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        return m_Scyla_fragment.onKeyDown(keyCode, event);
    }

    @Override
    protected void onPause() {
        m_Scyla_fragment.onPause();
        super.onPause();
    }
}
