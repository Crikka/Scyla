package kit.scyla.canvas.facets.collision;

import android.graphics.Paint;
import android.graphics.Region;

import kit.scyla.core.facets.collision.CollisionFacet;
import kit.scyla.canvas.shapes.text.Text;

/**
 * Created with IntelliJ
 * Created by Ferrand
 * Date 10/10/2014
 */
public class TextCollisionFacet extends CollisionFacet<Text> {
    private Region genericDetermineRegion() {

        int width = shape().getTextWidth();
        int height = shape().getTextHeight();

        if(shape().isCenter()) {

            int topX = shape().gravityCenterFacet().getGravityCenter().x - width / 2;
            int topY = shape().gravityCenterFacet().getGravityCenter().y - height / 4;


            int bottomX = shape().gravityCenterFacet().getGravityCenter().x + width / 2;
            int bottomY = shape().gravityCenterFacet().getGravityCenter().y + height / 2;


            return new Region(topX, topY, bottomX, bottomY);

        }else{

            int topX = shape().gravityCenterFacet().getGravityCenter().x;
            int topY = shape().gravityCenterFacet().getGravityCenter().y - height;

            int bottomX = shape().gravityCenterFacet().getGravityCenter().x + width;
            int bottomY = shape().gravityCenterFacet().getGravityCenter().y + height;

            return new Region(topX, topY, bottomX, bottomY);
        }

    }

    @Override
    protected Region determineRegion() {
        return genericDetermineRegion();
    }

    @Override
    public boolean fingerOn(int x, int y) {
        return genericDetermineRegion().contains(x, y);
    }

}
