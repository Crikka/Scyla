package kit.scyla.canvas.facets.drawing;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;

import kit.scyla.canvas.shapes.text.Text;

/**
 * Created with IntelliJ
 * Created by Nelaupe Lucas
 * Date 13/10/14
 */
public class TextButtonDrawingFacet extends TextDrawingFacet {

    public TextButtonDrawingFacet() {
        super();

    }

    public TextButtonDrawingFacet(Paint paint) {
        super(paint);
    }

    @Override
    public void draw(Canvas canvas) {

        Text shapeText = shape();
        paint().setTextAlign(Paint.Align.CENTER);

        int width = shapeText.getTextWidth();
        int height = shapeText.getTextHeight();

        int topX = shape().getGravityCenter().x - width / 2 - 20;
        int topY = shape().getGravityCenter().y - height / 4 - 20;

        int bottomX = shape().getGravityCenter().x + width / 2 + 20;
        int bottomY = shape().getGravityCenter().y + height / 2 + 20;

        canvas.drawText(shapeText.getText(), shape().getGravityCenter().x, shape().getGravityCenter().y + height / 2, paint());

        paint().setColor(Color.WHITE);

        RectF r = new RectF(topX, topY, bottomX, bottomY);
        canvas.drawRoundRect(r, 10, 10, paint());

        paint().setColor(shapeText.getColor());

        canvas.drawText(shapeText.getText(), shape().getGravityCenter().x, shape().getGravityCenter().y + height / 2, paint());

    }
}

