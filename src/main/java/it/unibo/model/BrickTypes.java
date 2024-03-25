package it.unibo.model;

import it.unibo.api.GameEntityImpl;

public enum BrickTypes {
    ONE_HIT(GameEntityImpl.MIN_HEALTH),
    DOUBLE_HIT(2),
    IMMORTAL(GameEntityImpl.IMMORTAL_ENTITY_HEALTH);

    private int health;

    private BrickTypes(int health) {
        this.health = health;
    }

    public int getHealth() {
        return health;
    }
}
