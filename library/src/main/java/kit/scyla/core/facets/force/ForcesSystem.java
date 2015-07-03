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

package kit.scyla.core.facets.force;

import java.util.ArrayList;
import java.util.HashSet;

import kit.scyla.core.shapes.Shape;
import rx.functions.Action1;
import rx.subjects.PublishSubject;

/**
 * Created with IntelliJ
 * Created by Ferrand
 * Date 25/11/2014
 */
@SuppressWarnings({"unused", "unchecked"})
public final class ForcesSystem {

    private final ArrayList<Force> m_forces;
    private boolean m_subjectToGravity;
    private final Shape m_element;
    private PublishSubject<Shape> m_subject;
    private final HashSet<Shape> m_collidingElements;
    private boolean m_blockX, m_blockY;
    private short m_blockGravityIndicator; // 0 -> no block; >0 -> block gravity from top to bot; <0 -> Block from bot to top

    public ForcesSystem(Shape element) {
        this.m_forces = new ArrayList<>();

        this.m_element = element;
        this.m_subject = PublishSubject.create();
        this.m_collidingElements = new HashSet<>();
        this.m_blockX = false;
        this.m_blockY = false;
        this.m_blockGravityIndicator = 0;
        this.m_subjectToGravity = true;
    }

    private boolean isFreeFall() {
        return m_collidingElements.isEmpty();
    }

    public void setSubjectToGravity(boolean subjectToGravity) {
        this.m_subjectToGravity = subjectToGravity;
    }

    public void subscribeForce(Force force) {
        m_forces.add(force);
    }

    public void subscribe(final Shape element) {
        m_subject.subscribe(new Action1<Shape>() {

            @Override
            public void call(Shape dynamicElement) {
                if (m_collidingElements.contains(element)) {
                    if (!dynamicElement.collisionFacet().intersect(element)) {
                        m_collidingElements.remove(element);
                    } else {
                        // Continue to collide
                        element.maintainWith(dynamicElement);
                    }
                } else {
                    if (dynamicElement.collisionFacet().intersect(element)) {
                        m_collidingElements.add(element);
                        element.interactWith(dynamicElement);
                    }
                }
            }
        });
    }

    public void reset() {
        m_subject = PublishSubject.create();
    }

    public void blockX() {
        m_blockX = true;
    }

    public void blockY() {
        m_blockY = true;
    }

    public void blockGravity() {
        m_blockGravityIndicator = (short) Gravity.getInstance().getStepY();
    }

    public void stepForward() {
        m_element.collisionFacet().recalculateContactArea();
        m_subject.onNext(m_element); // Signal to every shape, move will begin

        int ending = m_forces.size();
        int vectorDestinationX = (m_subjectToGravity ? Gravity.getInstance().getStepX() : 0);
        int vectorDestinationY = (m_subjectToGravity ? Gravity.getInstance().getStepY() : 0);
        for (int i = ending - 1; i >= 0; i--) {
            Force force = m_forces.get(i);
            if (force.isTerminated()) {
                m_forces.remove(i);
            }
        }
        ending = m_forces.size();

        for (int i = 0; i < ending; i++) {
            Force force = m_forces.get(i);
            force.stepForward();

            int stepX = force.getStepX();
            int stepY = force.getStepY();

            double duration = (double) force.getDuration();
            double current = (double) force.instant();

            if (duration != -1) {
                double percent = (duration - current) / duration;
                vectorDestinationX += stepX * percent;
                vectorDestinationY += stepY * percent;
            } else {
                vectorDestinationX += stepX;
                vectorDestinationY += stepY;
            }
        }

        int currentX = m_element.gravityCenterFacet().getGravityCenter().x;
        int currentY = m_element.gravityCenterFacet().getGravityCenter().y;
        int destinationX = currentX + (m_blockX ? 0 : vectorDestinationX);
        int destinationY = currentY + (m_blockY ? 0 : vectorDestinationY);

        if (m_blockGravityIndicator != 0) {
            if ((m_blockGravityIndicator > 0 && Gravity.getInstance().getStepY() > 0) || (m_blockGravityIndicator < 0 && Gravity.getInstance().getStepY() < 0)) {
                destinationY -= Gravity.getInstance().getStepY();
            } else {
                this.m_blockGravityIndicator = 0;
            }
        }

        this.m_blockX = false;
        this.m_blockY = false;

        m_element.gravityCenterFacet().moveGravityCenterTo(destinationX, destinationY);  // to enhanced
    }
}
