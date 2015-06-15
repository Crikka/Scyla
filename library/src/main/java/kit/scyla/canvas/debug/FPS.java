package kit.scyla.canvas.debug;

/**
 * Created with IntelliJ
 * Created by Nelaupe Lucas
 * Date 15/10/14
 */
public class FPS {

    private long mLastTime = 0;
    private int frameps = 0, ifps = 0;

    /**
     * Calculates and returns frames per second
     */
    private long fps() {
        long now = System.currentTimeMillis();

        ifps++;
        if (now > (mLastTime + 1000)) {
            mLastTime = now;
            frameps = ifps;
            ifps = 0;
        }
        return frameps;
    }

    public String getFPS() {
        return "" + fps();
    }

}