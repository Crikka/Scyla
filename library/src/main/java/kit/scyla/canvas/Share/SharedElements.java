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

package kit.scyla.canvas.Share;

import android.content.Context;
import android.util.DisplayMetrics;

/**
 * Created with IntelliJ
 * Created by Nelaupe Lucas
 * Date 17/10/14
 */
public class SharedElements {

    private static final double INCH_DEFAULT = 12;
    public static double ratio = 0;

    public static boolean isNotInit() {
        return ratio == 0;
    }


    public static void initRadio(Context context, int width, int height) {

        DisplayMetrics dm = context.getResources().getDisplayMetrics();

        int dens = dm.densityDpi;
        double wi = (double) width / (double) dens;
        double hi = (double) height / (double) dens;
        double x = Math.pow(wi, 2);
        double y = Math.pow(hi, 2);
        double screenInches = Math.sqrt(x + y);
        ratio = screenInches / INCH_DEFAULT;
    }


    public static int giveMeRandom(int min, int max) {
        return (int) (Math.random() * (max - min)) + min;
    }

    /**
     * @param v  the tested value
     * @param v1 one limit
     * @param v2 other limit
     * @return return true if v1 < v < v2 otherwise
     */
    public static boolean between(int v, int v1, int v2) {
        int lower, upper;
        if (v1 < v2) {
            lower = v1;
            upper = v2;
        } else {
            lower = v2;
            upper = v1;
        }

        return ((lower < v) && (v < upper));
    }

}
