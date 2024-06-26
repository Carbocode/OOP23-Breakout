package it.unibo.controller;

import java.util.Iterator;
import java.util.Set;

import it.unibo.api.GameEntity;

/**
 * Class responsible for removing dead entities from a set.
 */
public final class DeathCollector {

    private DeathCollector() {
    }

    /**
     * Checks entities and removes those that are not alive.
     * 
     * @param entities the set of game entities to check
     */
    public static void checkEntities(final Set<? extends GameEntity> entities) {
        final Iterator<? extends GameEntity> iterator = entities.iterator();
        while (iterator.hasNext()) {
            final GameEntity entity = iterator.next();
            if (!entity.isAlive()) {
                iterator.remove();
            }
        }
    }
}
