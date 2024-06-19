package it.unibo.controller;

import java.util.Iterator;
import java.util.Set;

import it.unibo.api.GameEntity;

public class DeathCollector {
    public static void checkEntities(Set<? extends GameEntity> entities) {
        Iterator<? extends GameEntity> iterator = entities.iterator();
        while (iterator.hasNext()) {
            GameEntity entity = iterator.next();
            if (!entity.isAlive()) {
                iterator.remove();
                System.out.println("Entità eliminata " + entity.getClass());
            }
        }
    }
}
