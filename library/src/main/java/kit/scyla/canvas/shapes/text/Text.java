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

package kit.scyla.canvas.shapes.text;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Typeface;

import kit.scyla.canvas.share.SharedElements;
import kit.scyla.canvas.facets.collision.TextCollisionFacet;
import kit.scyla.canvas.facets.drawing.TextDrawingFacet;
import kit.scyla.canvas.shapes.ShapeCanvas;

/**
 * Created with IntelliJ
 * Created by Nelaupe Lucas
 * Date 13/10/14
 */
@SuppressWarnings({"unused", "unchecked"})
public class Text extends ShapeCanvas<Text> {

    private static float scale;
    private int m_size;
    private String m_text;
    private int m_color;
    private boolean m_isCenter;
    private int m_textWidth;

    public Text(Context context, int text, Point point) {
        super(point);

        scale = context.getResources().getDisplayMetrics().density;
        m_text = context.getResources().getText(text).toString();

        m_size = (int) (20 * SharedElements.RATIO * scale * 1.5);
        m_color = Color.BLACK;

        m_isCenter = true;

        m_textWidth = recalculateSize();
        recalculateDrawing();
    }

    public Text(Context context, String text, Point point) {
        super(point);

        scale = context.getResources().getDisplayMetrics().density;
        m_text = text;

        m_size = (int) (20 * SharedElements.RATIO * scale * 1.5);
        m_color = Color.BLACK;

        m_isCenter = true;

        m_textWidth = recalculateSize();
        recalculateDrawing();
    }

    public int getSize() {
        return m_size;
    }

    public void setSize(float size) {
        m_size = (int) (size * SharedElements.RATIO * scale * 1.5);
        m_textWidth = recalculateSize();
        recalculateDrawing();
    }

    public String getText() {
        return m_text;
    }

    public void setText(String text) {
        this.m_text = text;
        m_textWidth = recalculateSize();
        recalculateDrawing();
    }

    public int getColor() {
        return m_color;
    }

    public void setColor(int color) {
        m_color = color;
        recalculateDrawing();
    }

    public int getTextWidth() {
        return m_textWidth;
    }

    public int getTextHeight() {
        return m_size;
    }

    public void append(String text) {
        this.m_text += text;
        m_textWidth = recalculateSize();
        recalculateDrawing();
    }

    public void append(char text) {
        this.m_text += text;
        m_textWidth = recalculateSize();
        recalculateDrawing();
    }

    public void remove(int number) {
        if (m_text.length() > 0) {
            if (m_text.length() < number) {
                throw new IllegalArgumentException("The number is less than the text length");
            }
            m_text = m_text.substring(0, m_text.length() - number);
            m_textWidth = recalculateSize();
            recalculateDrawing();
        }
    }

    public void recalculateDrawing() {
        defineDrawingFacet(new TextDrawingFacet());
        defineCollisionFacet(new TextCollisionFacet());
    }

    public boolean isCenter() {
        return m_isCenter;
    }

    public void setCenter(boolean isCenter) {
        m_isCenter = isCenter;
        recalculateDrawing();
    }

    protected int recalculateSize() {
        Paint p = new Paint();
        p.setTextSize(m_size);
        return (int) p.measureText(m_text);
    }

    public Typeface getTypeface() {
        return Typeface.DEFAULT;
    }

}
