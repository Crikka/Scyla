package kit.scyla.canvas.debug;

/**
 * Created with IntelliJ
 * Created by Nelaupe Lucas
 * Date 17/10/14
 */
public class DebugEngine {

    private static DebugEngine INSTANCE = new DebugEngine();
    private Level DEBUG_Level;


    private DebugEngine() {
        DEBUG_Level = Level.NONE;

    }

    public static DebugEngine getInstance() {
        return INSTANCE;
    }

    public void upgrade() {

        switch (DEBUG_Level) {
            case LOW:
                DEBUG_Level = Level.FULL;
                break;
            case FULL:
                DEBUG_Level = Level.NONE;
                break;
            case NONE:
                DEBUG_Level = Level.LOW;
                break;

        }
    }

    public boolean minDebugLevel(Level necessarylevel) {

        return DEBUG_Level != Level.NONE &&
                (!(necessarylevel == Level.FULL) || DEBUG_Level == Level.FULL);
    }

}
