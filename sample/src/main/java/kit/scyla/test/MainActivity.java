package kit.scyla.test;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;

import kit.scyla.canvas.ScylaCanvasFragment;
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

            m_Scyla_fragment = new ScylaCanvasFragment();

            Bundle args = new Bundle();
            args.putSerializable(ScylaFragment.INITIALIZE, myView.class);
            m_Scyla_fragment.setArguments(args);

            FragmentManager fm = getFragmentManager();
            FragmentTransaction ft = fm.beginTransaction();
            ft.replace(R.id.render, m_Scyla_fragment, "scyla");
            ft.commit();
            fm.executePendingTransactions();
        }
    }

    @Override
    protected void onPause() {
        m_Scyla_fragment.onPause();
        super.onPause();
    }

}
