package it.unibo.model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import it.unibo.api.GameInfo;

/**
 * Class that contains the power up bubbles.
 */
public class PowerUpBubbles {
    private final List<PowerUpBubble> bubbles;

    /**
     * PowerUpBubbles constructor.
     */
    public PowerUpBubbles() {
        this.bubbles = new ArrayList<>();
    }

    /**
     * this method adds a new bubble to the bubbles list.
     * 
     * @param bubble
     */
    public void addBubble(final PowerUpBubble bubble) {
        bubbles.add(bubble);
    }

    /**
     * this method removes a bubble from the bubbles list.
     */
    public void update() {
        final Iterator<PowerUpBubble> iterator = bubbles.iterator();
        while (iterator.hasNext()) {
            final PowerUpBubble bubble = iterator.next();
            bubble.update();
            if (bubble.getPosition().y > GameInfo.GAME_HEIGHT) { // Fuori dallo schermo
                iterator.remove();
            }
        }
    }

    /**
     * this method returns the bubbles list.
     * 
     * @return bubbles
     */
    @SuppressFBWarnings
    public List<PowerUpBubble> getBubbles() {
        return this.bubbles;
    }
}
