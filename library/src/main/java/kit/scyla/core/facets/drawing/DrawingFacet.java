package kit.scyla.core.facets.drawing;

import kit.scyla.core.facets.Facet;
import kit.scyla.core.shapes.Shape;

/**
 * Created with IntelliJ
 * Created by Ferrand
 * Date 04/10/2014
 */
public abstract class DrawingFacet<TShape extends Shape<TShape, TSlate>, TSlate> extends Facet<TShape> implements Drawing<TSlate> {
}
