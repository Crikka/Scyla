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
