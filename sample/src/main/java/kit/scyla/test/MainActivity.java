package kit.scyla.test;

import android.content.Context;

import kit.scyla.canvas.ScylaCanvas;
import kit.scyla.canvas.views.ScylaCanvasView;
import kit.scyla.canvas.views.ViewHandler;

public class MainActivity extends ScylaCanvas {

    @Override
    public int layoutID() {
        return R.layout.activity_main;
    }

    @Override
    public int renderID() {
        return R.id.render;
    }

    @Override
    public ScylaCanvasView firstView(ViewHandler handler, Context context) {
        return new myView(handler, context);
    }

}
