package kit.scyla.core.facets.force;

/**
 * Created with IntelliJ
 * Created by Ferrand
 * Date 25/11/2014
 */
public class Gravity extends Force {
    static double G = 0.01;
    private static Gravity INSTANCE = null;

    private Gravity() {
        super();
    }

    public static Gravity getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new Gravity();
        }

        return INSTANCE;
    }

    public static Gravity getReinitializedInstance() {
        if (INSTANCE == null) {
            INSTANCE = new Gravity();
        }

        INSTANCE.reinitialize();

        return INSTANCE;
    }

    public static void setGravity(double gravity) {
        if (isGravityHasChanged(gravity)) {
            getInstance().reinitialize();
        }

        G = gravity / 1000;
    }

    private static boolean isGravityHasChanged(double gravity){
        return (!((gravity > 0 && G > 0) || (gravity < 0 && G < 0)));
    }

    public void stepGravityForward() {
        if (G != 0) {
            super.stepForward();
        }
    }

    @Override
    public int onStepX() {
        return 0;
    }

    @Override
    public int onStepY() {
        int y = (int) (G * instant() * instant());
        return y > 20 ? 20 : y;
    }

    @Override
    public void refresh() {
    }

    @Override
    public int getDuration() {
        return -1; // Unlimited
    }
}
