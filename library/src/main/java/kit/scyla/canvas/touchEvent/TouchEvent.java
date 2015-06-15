package kit.scyla.canvas.touchEvent;

import android.graphics.Point;

/**
 * Created with IntelliJ
 * Created by Nelaupe Lucas
 * Date 01/01/15
 */
public abstract class TouchEvent {

    public abstract void onTouch(Point point, TouchTypeEvent event);

}
