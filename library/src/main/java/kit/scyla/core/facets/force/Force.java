/*
 *  Copyright 2015-present Lucas Nelaupe and Ferrand
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

package kit.scyla.core.facets.force;

/**
 * Created with IntelliJ
 * Created by Ferrand
 * Date 25/11/2014
 */
@SuppressWarnings({"unused", "unchecked"})

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
