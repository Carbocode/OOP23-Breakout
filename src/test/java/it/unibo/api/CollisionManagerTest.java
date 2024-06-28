package it.unibo.api;
import it.unibo.controller.GameLoop;
import it.unibo.controller.GameLoop.PowerUp;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import java.awt.Dimension;
import java.awt.Point;
import java.util.HashSet;
import java.util.ArrayList;


/**
 * JUnit tests for the {@link CollisionManager} class.
 */
public class CollisionManagerTest {

    private <T> List<T> setToList(final Set<T> set) {
        ArrayList<T> result = new ArrayList<>();
        for (T t : set) {
            result.add(t);
        }
        return result;
    }
}

