package kit.scyla.canvas.Share;

import android.content.Context;
import android.util.DisplayMetrics;

/**
 * Created with IntelliJ
 * Created by Nelaupe Lucas
 * Date 17/10/14
 */
public class SharedElements {

    private static final double INCH_DEFAULT = 12;
    public static double ratio = 0;

    public static boolean isNotInit() {
        return ratio == 0;
    }


    public static void initRadio(Context context, int width, int height) {

        DisplayMetrics dm = context.getResources().getDisplayMetrics();

        int dens = dm.densityDpi;
        double wi = (double) width / (double) dens;
        double hi = (double) height / (double) dens;
        double x = Math.pow(wi, 2);
        double y = Math.pow(hi, 2);
        double screenInches = Math.sqrt(x + y);
        ratio = screenInches / INCH_DEFAULT;
    }


    public static int giveMeRandom(int min, int max) {
        return (int) (Math.random() * (max - min)) + min;
    }

    /**
     * @param v  the tested value
     * @param v1 one limit
     * @param v2 other limit
     * @return return true if v1 < v < v2 otherwise
     */
    public static boolean between(int v, int v1, int v2) {
        int lower, upper;
        if (v1 < v2) {
            lower = v1;
            upper = v2;
        } else {
            lower = v2;
            upper = v1;
        }

        return ((lower < v) && (v < upper));
    }

}
