package it.unibo.model;

import it.unibo.api.GameEntityImpl;

public enum BrickTypes {
    ONE_HIT(GameEntityImpl.MIN_HEALTH, 10),
    DOUBLE_HIT(2, 40),
    IMMORTAL(GameEntityImpl.IMMORTAL_ENTITY_HEALTH, 110);

    private int health;
    private int occurence;

    private BrickTypes(int health, int occurence) {
        this.health = health;
        this.occurence = occurence;
    }

    public int getHealth() {
        return this.health;
    }

    public int getOccurence() {
        return this.occurence;
    }
}
