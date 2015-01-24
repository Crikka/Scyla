package kit.scyla.canvas.views;

import android.content.Context;

import kit.scyla.canvas.render.Scene;
import kit.scyla.core.ScylaView;

/**
 * Created with IntelliJ
 * Created by Ferrand
 * Date 29/11/2014
 */
public abstract class ViewHandler {
    private ScylaView m_current;
    private Scene m_scene;
    private Context m_context;

    public ViewHandler(Context context, Scene scene) {
        this.m_context = context;
        this.m_scene = scene;
        this.m_current = bootstrap(this);
    }

    public final ScylaView getCurrent() {
        return m_current;
    }

    public final void load(ScylaView stage) {
        m_current = stage;
        onNext();
    }

    public final void reload() {
        onNext();
    }

    public abstract void onNext();

    public Scene scene() {
        return m_scene;
    }

    public Context context() {
        return m_context;
    }

    public void setCurrentView(ScylaView view) {
        m_current = view;
    }

    public abstract ScylaView bootstrap(ViewHandler handler);

}
