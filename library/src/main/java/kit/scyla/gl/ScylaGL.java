package kit.scyla.gl;

import android.content.Context;

import kit.scyla.Scyla;
import kit.scyla.canvas.views.ViewHandler;
import kit.scyla.gl.render.ScylaGLSurface;
import kit.scyla.gl.views.ScylaGLView;

/**
 * Created with IntelliJ
 * Created by Ferrand
 * Date : 22/01/2015
 */
public abstract class ScylaGL extends Scyla<ScylaGLSurface, ScylaGLView> {
    @Override
    public ScylaGLSurface setup() {
        return new ScylaGLSurface(this) {
            @Override
            public ScylaGLView defaultView(ViewHandler handler, Context context) {
                return firstView(handler, context);
            }
        };
    }
}
