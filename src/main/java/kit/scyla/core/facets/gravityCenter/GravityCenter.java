package kit.scyla.core.facets.gravityCenter;

import android.graphics.Point;

/**
 * Created with IntelliJ
 * Created by Ferrand
 * Date : 23/01/2015
 */
public interface GravityCenter {
    Point getGravityCenter();
    void moveGravityCenterTo(int x, int y);
    void moveGravityCenterTo(Point point);
    void offsetGravityCenter(int x, int y);
    void translateX(int x);
    void translateY(int y);
}
