package kit.scyla.core.facets.force;

/**
 * Created with IntelliJ
 * Created by Ferrand
 * Date 25/11/2014
 */
public abstract class FadingForce extends Force {
    private int m_initialFaDeCount;
    private int m_fadeCount;

    public FadingForce(int fadeCount) {
        super();

        this.m_initialFaDeCount = fadeCount;
        this.m_fadeCount = fadeCount;
    }

    @Override
    public boolean isTerminated() {

        return m_fadeCount < instant();
    }

    @Override
    public int getDuration() {
        return m_fadeCount;
    }

    @Override
    public void refresh() {
        m_fadeCount = (m_initialFaDeCount + instant());
    }
}
