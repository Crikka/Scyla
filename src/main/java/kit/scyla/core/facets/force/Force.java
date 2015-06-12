package kit.scyla.core.facets.force;

import kit.scyla.canvas.Share.SharedElements;

/**
 * Created with IntelliJ
 * Created by Ferrand
 * Date 25/11/2014
 */
public abstract class Force {
    private int m_instant;
    private double m_stepX;
    private double m_stepY;

    public Force() {
        super();

        this.m_instant = 0;
    }

    public final void stepForward() {
        m_instant++;
        m_stepX = Math.round(onStepX() * SharedElements.ratio);
        m_stepY = Math.round(onStepY() * SharedElements.ratio);
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
        return (int) m_stepX;
    }

    public final int getStepY() {
        return (int) m_stepY;
    }

    public abstract int getDuration();
}
