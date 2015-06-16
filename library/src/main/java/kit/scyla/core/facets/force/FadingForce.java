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
