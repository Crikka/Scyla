package kit.scyla.core.facets.force;

/**
 * Created with IntelliJ
 * Created by Ferrand
 * Date 25/11/2014
 */
public abstract class Force {
    private int m_instant;
    private int m_stepX;
    private int m_stepY;

    public Force() {
        super();

        this.m_instant = 0;
    }

    public final void stepForward() {
        m_instant++;
        m_stepX = onStepX();
        m_stepY = onStepY();
    }

    protected int instant() {
        return m_instant;
    }

    public boolean isTerminated() {
        return false;
    }

    public final void reinitialize() {
        m_instant = 0;
    }

    public abstract int onStepX();

    public abstract int onStepY();

    public abstract void refresh();

    public final int getStepX() {
        return m_stepX;
    }

    public final int getStepY() {
        return m_stepY;
    }

    public abstract int getDuration();
}
