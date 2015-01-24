package kit.scyla.canvas;

import android.content.Context;

import kit.scyla.Scyla;
import kit.scyla.canvas.render.ScylaCanvasSurface;
import kit.scyla.canvas.views.ScylaCanvasView;
import kit.scyla.canvas.views.ViewHandler;

/**
 * Created with IntelliJ
 * Created by Ferrand
 * Date : 22/01/2015
 */
public abstract class ScylaCanvas extends Scyla<ScylaCanvasSurface, ScylaCanvasView> {
    @Override
    public ScylaCanvasSurface setup() {
        return new ScylaCanvasSurface(this) {
            @Override
            public ScylaCanvasView defaultView(ViewHandler handler, Context context) {
                return firstView(handler, context);
            }
        };
    }
}
