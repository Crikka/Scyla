package kit.scyla.canvas.facets.drawing;

import android.graphics.Canvas;
import android.graphics.Paint;

import kit.scyla.canvas.shapes.text.Text;

/**
 * Created with IntelliJ
 * Created by Nelaupe Lucas
 * Date 13/10/14
 */
public class TextDrawingFacet extends DrawingCanvasFacet<Text> {
    public TextDrawingFacet() {
        super();
    }


    public TextDrawingFacet(Paint paint) {
        super(paint);
    }

    @Override
    public void onShapeDefined(Text text) {
        super.onShapeDefined(text);

        paint().setColor(text.getColor());
        paint().setTextSize(text.getSize());
        paint().setTypeface(text.getTypeface());
    }

    @Override
    public void draw(Canvas canvas) {
        Text shapeText = shape();
        paint().setTextAlign(Paint.Align.CENTER);

        int height = shape().getTextHeight();

        canvas.drawText(shapeText.getText(), shape().getGravityCenter().x, shape().getGravityCenter().y + height / 2, paint());
    }

}

