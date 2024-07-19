package it.unibo.model;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import it.unibo.api.GameInfo;

public class PowerUpBubbles {
    private List<PowerUpBubble> bubbles;

    public PowerUpBubbles() {
        this.bubbles = new ArrayList<>();
    }

    public void addBubble(PowerUpBubble bubble) {
        bubbles.add(bubble);
    }

    public void update() {
        Iterator<PowerUpBubble> iterator = bubbles.iterator();
        while (iterator.hasNext()) {
            PowerUpBubble bubble = iterator.next();
            bubble.update();
            if (bubble.getPosition().y > GameInfo.GAME_HEIGHT) { // Fuori dallo schermo
                iterator.remove();
            }
        }
    }

    public List<PowerUpBubble> getBubbles() {
        return bubbles;
    }
}
