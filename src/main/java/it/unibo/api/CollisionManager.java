package it.unibo.api;
<<<<<<< HEAD

=======
>>>>>>> origin/master
import java.awt.Dimension;
import java.awt.Point;
import java.util.Set;

import it.unibo.model.Ball;

public class CollisionManager {
    private Set<GameEntity> bricks;
    private Set<Ball> balls;
    private GameEntity paddle;

    public CollisionManager(Set<Ball> balls, Set<GameEntity> bricks, GameEntity paddle) {
        // TODO
        this.balls = balls;
        this.bricks = bricks;
        this.paddle = paddle;
    }

    public void checkAll() {
        for(Ball ball : balls){
            if (!ball.isAlive())
                continue;
            for(GameEntity brick : bricks){
                if (!brick.isAlive())
                    continue;
                if (collides(ball, brick)) {
                    // BAD. it should notify the controller, so it can produce effects.
                    ball.onCollision();
                    brick.onCollision();
                }
            }
            // then we check with paddle
            if (collides(ball, paddle)) {
                // again, awful.
                ball.onCollision();
            }

        }

    }

    private boolean collides(GameEntity a, GameEntity b) {
        Point posA = a.getPosition();
        Dimension sizeA = a.getSize();
        Point posB = b.getPosition();
        Dimension sizeB = b.getSize();
        // Simple collision detection
        return posA.getX() < posB.getX() + sizeB.getWidth() &&
                posA.getX() + sizeA.getWidth() > posB.getX() &&
                posA.getY() < posB.getY() + sizeB.getHeight() &&
                posA.getY() + sizeA.getHeight() > posB.getY();
    }

}
