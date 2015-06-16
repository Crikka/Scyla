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
