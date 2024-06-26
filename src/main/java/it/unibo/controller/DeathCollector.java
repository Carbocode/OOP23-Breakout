package it.unibo.controller;

import java.util.Iterator;
import java.util.Set;

import it.unibo.api.GameEntity;

/**
 * Class responsible for removing dead entities from a set.
 */
public class DeathCollector {

    /**
     * Checks entities and removes those that are not alive.
     * 
     * @param entities the set of game entities to check
     */
    public static void checkEntities(Set<? extends GameEntity> entities) {
        Iterator<? extends GameEntity> iterator = entities.iterator();
        while (iterator.hasNext()) {
            GameEntity entity = iterator.next();
            if (!entity.isAlive()) {
                iterator.remove();
                System.out.println("Entity removed: " + entity.getClass());
            }
        }
    }
}
