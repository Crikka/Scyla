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

package kit.scyla.canvas.views.templateEngine;

import android.graphics.Point;

/**
 * Created with IntelliJ
 * Created by Nelaupe Lucas
 * Date 06/12/14
 */
@SuppressWarnings({"unused", "unchecked"})
public class GridTemplate {

    public static final int numberOfRow = 30;
    public static final int numberOfColumn = 40;
    private static final GridTemplate INSTANCE = new GridTemplate();
    private static int ScreenWidth;
    private static int ScreenHeight;
    private int offsetColumnLeft = 0;
    private int offsetColumnRight = 0;

    public static GridTemplate getGrid(int width, int height) {
        ScreenWidth = width;
        ScreenHeight = height;

        return INSTANCE;
    }

    public static GridTemplate getInstance() {
        return INSTANCE;
    }


    private Point getP(int column, int row) {
        return new Point(column * ScreenWidth / numberOfColumn, row * ScreenHeight / numberOfRow);
    }

    public Point getPosition(int column, int row) {
        return getP(column + offsetColumnLeft, row);
    }

    public Point getCenter() {
        return getPosition(this.getCenterColumn(), numberOfRow / 2);
    }

    public int getCenterColumn() {
        return (numberOfColumn - offsetColumnLeft - offsetColumnRight) / 2;
    }

    public int getCenterRow() {
        return numberOfRow / 2;
    }

    public int getScreenWidth() {
        return ScreenWidth;
    }

    public int getScreenHeight() {
        return ScreenHeight;
    }

    public GridPoint toGrid(Point screenPosition) {
        return new GridPoint(screenPosition.x * numberOfColumn / ScreenWidth, screenPosition.y * numberOfRow / ScreenHeight);
    }


    public Point getPositionInSimpleGrid(int column, int row) {

        int offsetColumn = 5;
        int offsetRow = 8;

        int positionSignX = offsetColumn + column * 10;
        int positionSignY = offsetRow + row * 8;

        return this.getPosition(positionSignX, positionSignY);


    }

    public GridPoint getGridPointInSimpleGrid(int column, int row) {
        int offsetColumn = 5;
        int offsetRow = 8;

        int positionSignX = offsetColumn + column * 10;
        int positionSignY = offsetRow + row * 8;

        return new GridPoint(positionSignX, positionSignY);
    }

    public Point getPosition(GridPoint point) {
        return getPosition(point.column(), point.row());
    }

    public void setOffset(int columnLeft, int columnRight) {
        offsetColumnLeft = columnLeft;
        offsetColumnRight = columnRight;
    }

    public void resetOffset() {
        offsetColumnLeft = 0;
        offsetColumnRight = 0;
    }

}
