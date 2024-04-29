package it.unibo.api;
import java.awt.Dimension;
import java.awt.Point;
import java.util.Set;

import it.unibo.model.Ball;
import it.unibo.model.BarImpl;

public class CollisionManager {
    private BrickWall bricks;
    private Set<Ball> balls;
    private BarImpl paddle;

    public CollisionManager(Set<Ball> balls, BrickWall brickWall, BarImpl paddle) {
        this.balls = balls;
        this.bricks = brickWall;
        this.paddle = paddle;
        
    }

    public void checkAll() {
        for(Ball ball : balls){
            boolean collision = false;
            if (!ball.isAlive())
                continue;
            for(GameEntity brick : bricks.getWall()){
                if (!brick.isAlive())
                    continue;
                if (collides(ball, brick)) {
                    
                    //Sometimes the ball collides with multiple bricks at the same time. this calls its onCollision twice, thus having no effect
                    if(GameInfo.DEBUG_MODE){
                        System.out.println("Ball at  (" + ball.getPosition().toString()+ ") collides with (" + brick.getPosition().toString() + ")");
                    }
                    collision = true;
                    brick.onCollision();
                }
            }
            // then we check with paddle
            if (collides(ball, paddle)) {
                System.out.println("Paddle hit");
                collision = true;
            }
            if(collision){
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
        return posA.getX() < posB.getX() + sizeB.getWidth()/2 &&
                posA.getX() + sizeA.getWidth()/2 > posB.getX() &&
                posA.getY() < posB.getY() + sizeB.getHeight()/2 &&
                posA.getY() + sizeA.getHeight()/2 > posB.getY();
    }

}
