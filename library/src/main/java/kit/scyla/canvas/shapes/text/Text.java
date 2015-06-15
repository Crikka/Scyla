package kit.scyla.canvas.shapes.text;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Typeface;

import kit.scyla.canvas.Share.SharedElements;
import kit.scyla.canvas.facets.collision.TextCollisionFacet;
import kit.scyla.canvas.facets.drawing.TextDrawingFacet;
import kit.scyla.canvas.shapes.ShapeCanvas;
import kit.scyla.core.facets.behavior.GroundInteraction;
import kit.scyla.core.facets.image.ImageFacet;

/**
 * Created with IntelliJ
 * Created by Nelaupe Lucas
 * Date 13/10/14
 */
public class Text extends ShapeCanvas<Text> {

    private static float scale;
    private int m_size;
    private String m_text;
    private int m_color;
    private boolean m_isCenter;

    public Text(Context context, int text, Point point) {
        super(point);

        scale = context.getResources().getDisplayMetrics().density;
        m_text = context.getResources().getText(text).toString();

        m_size = (int) (20 * SharedElements.ratio * scale * 1.5);
        m_color = Color.BLACK;

        m_isCenter = true;

        recalculateDrawing();
        subscribeInteraction(new GroundInteraction());
    }

    public Text(Context context, String text, Point point) {
        super(point);

        scale = context.getResources().getDisplayMetrics().density;
        m_text = text;

        m_size = (int) (20 * SharedElements.ratio * scale * 1.5);
        m_color = Color.BLACK;

        m_isCenter = true;

        recalculateDrawing();
        subscribeInteraction(new GroundInteraction());
    }

    public int getSize() {
        return m_size;
    }

    public void setSize(float size) {
        m_size = (int) (size * SharedElements.ratio * scale * 1.5);
        recalculateDrawing();
    }

    public String getText() {
        return m_text;
    }

    public void setText(String text) {
        this.m_text = text;
        recalculateDrawing();
    }

    public int getColor() {
        return m_color;
    }

    public void setColor(int color) {
        m_color = color;
        recalculateDrawing();
    }

    @Override
    public ImageFacet<Text> imageFacet() {
        return null; // TODO
    }

    public int getTextWidth() {

        Paint p = new Paint();
        p.setTextSize(m_size);

        return (int) p.measureText(m_text);
    }

    public int getTextHeight() {
        return (int) m_size;
    }

    public void append(String text) {
        this.m_text += text;
        recalculateDrawing();
    }

    public void append(char text) {
        this.m_text += text;
        recalculateDrawing();
    }

    public void remove(int number) {
        if (m_text.length() > 0) {
            m_text = m_text.substring(0, m_text.length() - 1);
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

    public Typeface getTypeface() {
        return Typeface.DEFAULT;
    }

}
