package kit.scyla.test;

import android.content.Context;
import android.graphics.Point;

import kit.scyla.canvas.render.Scene;
import kit.scyla.canvas.shapes.text.Text;
import kit.scyla.canvas.touchEvent.TouchEvent;
import kit.scyla.canvas.touchEvent.TouchTypeEvent;
import kit.scyla.canvas.views.ScylaCanvasView;
import kit.scyla.canvas.views.ViewHandler;
import kit.scyla.canvas.views.templateEngine.GridTemplate;

/**
 * Created with IntelliJ
 * Created by lucas
 * Date 26/03/15
 */
public class myView extends ScylaCanvasView {

    public myView(ViewHandler handler, Context context) {
        super(handler, context);
    }

    @Override
    public void addCustomElements(Scene scene, GridTemplate grid) {
        final Text txt = new Text(context(), context().getString(R.string.hello_world), grid.getCenter());
        txt.setSize(40);
        scene.addStaticElement(txt);

        this.subscribeTouchListener(txt, new TouchEvent() {
            @Override
            public void onTouch(Point point, TouchTypeEvent event) {
                if (event == TouchTypeEvent.Move) {
                    txt.gravityCenterFacet().moveGravityCenterTo(point);
                }
            }
        });
    }

    @Override
    public void onBackPressed() {

    }

}