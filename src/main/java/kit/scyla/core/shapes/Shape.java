package kit.scyla.core.shapes;

import android.graphics.Point;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import kit.scyla.canvas.touchEvent.TouchEvent;
import kit.scyla.canvas.touchEvent.TouchTypeEvent;
import kit.scyla.core.facets.behavior.Interaction;
import kit.scyla.core.facets.behavior.InteractionType;
import kit.scyla.core.facets.collision.CollisionFacet;
import kit.scyla.core.facets.collision.NoneCollisionFacet;
import kit.scyla.core.facets.drawing.DrawingFacet;
import kit.scyla.core.facets.force.ForcesSystem;
import kit.scyla.core.facets.gravityCenter.GravityCenterFacet;
import kit.scyla.core.facets.image.ImageFacet;
import kit.scyla.core.facets.moving.MovingFacet;
import kit.scyla.core.facets.moving.NoneMovingFacet;
import kit.scyla.core.facets.rotation.NoneRotationFacet;
import kit.scyla.core.facets.rotation.RotationFacet;
import rx.functions.Action0;

/**
 * Created with IntelliJ
 * Created by Ferrand
 * Date 03/10/2014
 */
public abstract class Shape<TSelf extends Shape<TSelf, TSlate>, TSlate> {
    /* ----- Facet ----- */
    private CollisionFacet<TSelf> m_collisionFacet;
    private RotationFacet<TSelf> m_rotationFacet;
    private MovingFacet<TSelf> m_movingFacet;
    private GravityCenterFacet<TSelf> m_gravityCenterFacet;
    /* ----------------- */

    private List<Interaction> m_interactions;
    private ArrayList<TouchEvent> m_fingerEvents = null;
    private ForcesSystem m_forcesSystem;

    /* ---- Data ---- */
    private Point m_gravityCenter;
    /* -------------- */

    private HashMap<String, Action0> m_actionOnEachTick;


    public Shape(Point gravityCenter) {
        this.m_forcesSystem = new ForcesSystem(this);
        this.m_interactions = new ArrayList<>();
        this.m_fingerEvents = new ArrayList<>();
        this.m_gravityCenter = gravityCenter;

        m_actionOnEachTick = new HashMap<>();

        defineGravityCenterFacet(new GravityCenterFacet<TSelf>());
    }

    @SuppressWarnings("unchecked")
    public final void defineGravityCenterFacet(GravityCenterFacet<TSelf> gravityCenterFacet) {
        gravityCenterFacet.defineShape((TSelf) this);
        this.m_gravityCenterFacet = gravityCenterFacet;
    }

    @SuppressWarnings("unchecked")
    public final void defineCollisionFacet(CollisionFacet<TSelf> collisionFacet) {
        collisionFacet.defineShape((TSelf) this);
        this.m_collisionFacet = collisionFacet;
    }

    @SuppressWarnings("unchecked")
    public abstract void defineDrawingFacet(DrawingFacet<TSelf, TSlate> drawingFacet);

    public final void subscribeFingerEvent(TouchEvent... fingerEvent) {
        Collections.addAll(this.m_fingerEvents, fingerEvent);
    }

    @SuppressWarnings("unchecked")
    public final void defineRotationFacet(RotationFacet<TSelf> rotationFacet) {
        rotationFacet.defineShape((TSelf) this);
        this.m_rotationFacet = rotationFacet;

        m_actionOnEachTick.put("rotation", new Action0() {
            @Override
            public void call() {
                m_rotationFacet.moveNewPosition();
            }
        });
    }

    @SuppressWarnings("unchecked")
    public final void defineMovingFacet(MovingFacet<TSelf> moveFacet) {
        moveFacet.defineShape((TSelf) this);
        this.m_movingFacet = moveFacet;

        m_actionOnEachTick.put("moving", new Action0() {
            @Override
            public void call() {
                m_movingFacet.moveNewPosition();
            }
        });

    }

    public final void subscribeInteraction(Interaction... interactions) {
        Collections.addAll(this.m_interactions, interactions);
    }

    @SuppressWarnings("unchecked")
    public final void interactWith(Shape other) {
        for (Interaction<TSelf, ? extends Shape> interaction : m_interactions) {
            interaction.onInteract(this, other, InteractionType.Enter);
        }
    }

    @SuppressWarnings("unchecked")
    public final void maintainWith(Shape other) {
        for (Interaction<TSelf, ? extends Shape> interaction : m_interactions) {
            interaction.onInteract(this, other, InteractionType.Hover);
        }
    }

    public final void fingerEvents(Point point, TouchTypeEvent event) {
        for (TouchEvent events : this.m_fingerEvents) {
            events.onTouch(point, event);
        }
    }

    public void follow(Shape shape) {
        gravityCenterFacet().moveGravityCenterTo(shape.getGravityCenter());
        collisionFacet().recalculateContactArea();
    }


    public abstract DrawingFacet<TSelf, TSlate> drawingFacet();

    public final CollisionFacet<TSelf> collisionFacet() {
        if (m_collisionFacet == null) {
            defineCollisionFacet(new NoneCollisionFacet<TSelf>());
        }

        return m_collisionFacet;
    }

    public final RotationFacet<TSelf> rotationFacet() {
        if (m_rotationFacet == null) {
            defineRotationFacet(new NoneRotationFacet<TSelf>());
        }

        return m_rotationFacet;
    }

    public final MovingFacet<TSelf> movingFacet() {
        if (m_movingFacet == null) {
            defineMovingFacet(new NoneMovingFacet<TSelf>());
        }
        return m_movingFacet;
    }

    public final GravityCenterFacet<TSelf> gravityCenterFacet() {
        if (m_gravityCenterFacet == null) {
            defineGravityCenterFacet(new GravityCenterFacet<TSelf>());
        }

        return m_gravityCenterFacet;
    }

    public final ForcesSystem forcesSystem() {
        return m_forcesSystem;
    }

    public abstract ImageFacet<TSelf> imageFacet();

    public Point getGravityCenter() {
        return m_gravityCenter;
    }

    public final void hide() {
        m_collisionFacet = null;
        m_interactions.clear();
    }

    public void doIt(){
        Collection<Action0> actions = m_actionOnEachTick.values();

        for(Action0 action :actions){
            action.call();
        }
    }

    public void removeRotation(){
        m_actionOnEachTick.remove("rotation");
    }
}
