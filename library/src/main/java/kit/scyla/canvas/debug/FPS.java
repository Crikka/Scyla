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

package kit.scyla.canvas.debug;

/**
 * Created with IntelliJ
 * Created by Nelaupe Lucas
 * Date 15/10/14
 */
public class FPS {

    private long mLastTime = 0;
    private int framePerSecond = 0, iFPS = 0;

    /**
     * Calculates and returns frames per second
     */
    private long fps() {
        long now = System.currentTimeMillis();

        iFPS++;
        if (now > (mLastTime + 1000)) {
            mLastTime = now;
            framePerSecond = iFPS;
            iFPS = 0;
        }
        return framePerSecond;
    }

    public String getFPS() {
        return "" + fps();
    }

}