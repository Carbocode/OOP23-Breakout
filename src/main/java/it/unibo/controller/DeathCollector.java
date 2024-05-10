package it.unibo.controller;

import java.util.Iterator;
import java.util.Set;

import it.unibo.api.GameEntityImpl;

public class DeathCollector {
    public static void checkEntities(Set<GameEntityImpl> entities) {
        Iterator<GameEntityImpl> iterator = entities.iterator();
        while (iterator.hasNext()) {
            GameEntityImpl entity = iterator.next();
            if (!entity.isAlive()) {
                iterator.remove();
            }
        }
    }
}
