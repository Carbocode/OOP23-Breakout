import java.util.List;

import main.java.it.unibo.model.Ball;

public class CollisionManager {
    private List<GameEntity> bricks;
    private List<Ball> balls;
    private GameEntity paddle;
    public CollisionManager(){
        //TODO
    }
    public boolean checkAll() {
        // for every ball
        for (int i = 0; i < balls.size(); i++) {
            Ball ball = balls.get(i);
            if (!ball.isAlive())
                continue;
            // we check every brick
            for (int j = 0; j < bricks.size(); j++) {
                GameEntity brick = bricks.get(j);
                if (!brick.isAlive())
                    continue;
                if (isColliding(ball, brick)) {
                    // BAD. it should notify the controller, so it can produce effects.
                    ball.onCollision();
                    brick.onCollision();
                }
            }
            // then we check with paddle
            if (isColliding(ball, paddle)) {
                // again, awful.
                ball.onCollision();
            }
        }
    }

    private boolean collides(GameEntity a, GameEntity b) {
        Pos2D posA = a.getPosition();
        Size sizeA = a.getSize();
        Pos2D posB = b.getPosition();
        Size sizeB = b.getSize();
        // Simple collision detection
        return posA.getX() < posB.getX() + sizeB.getWidth() &&
                posA.getX() + sizeA.getWidth() > posB.getX() &&
                posA.getY() < posB.getY() + sizeB.getHeight() &&
                posA.getY() + sizeA.getHeight() > posB.getY();
    }

}
