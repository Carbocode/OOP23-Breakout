package it.unibo.api;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.Random;
import java.util.ArrayList;
import java.util.List;
import it.unibo.model.Ball;
import it.unibo.model.Bar;
import it.unibo.controller.GameLoop;
import it.unibo.model.PowerUp;
import it.unibo.model.Bomb;
import it.unibo.model.PowerUpBubble;
import it.unibo.view.SoundManagerImpl;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

/**
 * Class that checks for collisions.
 */
public class CollisionManager {
    private static final int BOMB_SIZE_RATIO = 5;
    private static int maxBalls = 10;
    /**
     * points increase for a brick hit.
     */
    public static final int POINTS_INCREASE = 200;
    private final Random rnd;
    private final SoundManager sound;
    private final Logger log;
    private final GameLoopAccessor master;
    private boolean bomb;

    /**
     * parameter for paddle / ball hit position.
     */
    private static final int NUMBER_OF_SECTIONS = 5;
    private static final int MEDIUM = -2;
    private static final int SLOW = -1;
    private static final int SLOW_SECTOR_RIGHT = 4;

    /**
     * 
     * @param master
     */
    public CollisionManager(final GameLoop master) {
        this.master = master.getAccessor();
        rnd = new Random();
        sound = new SoundManagerImpl();
        log = Logger.getLogger(CollisionManager.class.getName());
    }

    /**
     * Checks all objects for collision.
     */
    public final void checkAll() {
        final List<Ball> newBalls = new ArrayList<>();
        final long startTime = System.nanoTime();
        for (final Ball ball : new ArrayList<>(master.getBalls())) { // Create a copy to iterate over
            boolean collision = false;
            if (!ball.isAlive()) {
                continue;
            }
            boolean greyCollision = false;
            // Collision with bricks
            final long brickStartTime = System.nanoTime();
            Point lastbrickpos = null;
            for (final GameEntity brick : master.getBricks()) {
                if (!brick.isAlive()) {
                    continue;
                }
                if (collides(ball, brick)) {
                    collision = true;
                    if (brick.getHealth() != -1) {
                        master.increaseScore(POINTS_INCREASE);
                        lastbrickpos = brick.getPosition();
                    } else {
                        greyCollision = true;
                        handleGreyCollision(ball, brick);
                    }
                    brick.onCollision();
                }
            }
            final long brickEndTime = System.nanoTime();
            debugPrint("Brick Collision", brickEndTime - brickStartTime);
            // Collision with paddle
            final long paddleStartTime = System.nanoTime();
            final Bar paddle = master.getBar();
            if (collides(ball, paddle)) {
                log.info("Paddle hit");

                final float ballX = ball.getPosition().x;
                final float paddleX = paddle.getPosition().x;
                final float paddleWidth = paddle.getSize().width;
                final float sectionWidth = paddleWidth / NUMBER_OF_SECTIONS;
                int collisionFactor = 0; // value for center hit

                if (ballX < paddleX + (sectionWidth * 2)) {
                    collisionFactor = -1; // Default collision factor for the left half
                    if (ballX < paddleX + sectionWidth) {
                        ball.guidedCollision(collisionFactor * -MEDIUM, SLOW);
                    } else {
                        ball.guidedCollision(collisionFactor * -SLOW, SLOW);
                    }
                } else if (ballX > paddleX + (sectionWidth * 3)) {
                    collisionFactor = 1; // Default collision factor for the right half
                    if (ballX < paddleX + sectionWidth * SLOW_SECTOR_RIGHT) {
                        ball.guidedCollision(collisionFactor * -SLOW, SLOW);
                    } else {
                        ball.guidedCollision(collisionFactor * -MEDIUM, SLOW);
                    }
                } else {
                    // center of the paddle
                    ball.guidedCollision(collisionFactor, SLOW);
                }
            }
            final long paddleEndTime = System.nanoTime();
            debugPrint("Paddle Collision check", paddleEndTime - paddleStartTime);
            // Handle collisions
            final int prob = 35;
            if (collision && !greyCollision) {
                ball.onCollision();
                if (rnd.nextInt(100) < prob) { // chance to create a powerup
                    master.addPowerUpBubble(new PowerUpBubble(lastbrickpos));
                }
                if (bomb) {
                    sound.playBombSound();
                    bomb(ball);
                    bomb = false;
                }
            }

            for (final PowerUpBubble bubble : master.getPowerUpBubbles()) {
                if (collides(paddle, bubble)) {
                    final long powerUPStartTime = System.nanoTime();

                    for (final PowerUp pu : PowerUp.values()) {
                        if (!pu.isOnCooldown() && rnd.nextInt(100) <= pu.getProbability()) {
                            // Handle powerup creation
                            switch (pu) {
                                case ENLARGE:
                                    sound.playBonusSound();
                                    handleEnlargePowerUp();
                                    break;
                                case BOMB:
                                    bomb = true;
                                    break;
                                case DUPLI:
                                    sound.playBonusSound();
                                    newBalls.add(new Ball(ball));
                                    PowerUp.DUPLI.use();
                                    break;
                                default:
                                    break;
                            }
                        }
                    }

                    final long powerUpEndTime = System.nanoTime();
                    debugPrint("Power Up Handling", powerUpEndTime - powerUPStartTime);
                }
            }

        }

        // Add new balls
        final long addBallsStartTime = System.nanoTime();
        if (master.getBalls().size() + newBalls.size() <= maxBalls) {
            master.addBalls(newBalls);
        }
        final long addBallsEndTime = System.nanoTime();
        debugPrint("Add Balls ", addBallsEndTime - addBallsStartTime);
        final long endTime = System.nanoTime();
        debugPrint("TOTAL", endTime - startTime);
    }

    private void handleGreyCollision(final Ball ball, final GameEntity brick) {
        final Rectangle ballRect = new Rectangle(ball.getPosition(), ball.getSize());
        final Rectangle brickRect = new Rectangle(brick.getPosition(), brick.getSize());

        // Calculate penetration depth in each direction
        final int penetrationLeft = ballRect.x + ballRect.width - brickRect.x;
        final int penetrationRight = brickRect.x + brickRect.width - ballRect.x;
        final int penetrationTop = ballRect.y + ballRect.height - brickRect.y;
        final int penetrationBottom = brickRect.y + brickRect.height - ballRect.y;

        // Find the smallest penetration depth to determine the direction of the
        // collision
        final int minPenetration = Math.min(Math.min(penetrationLeft, penetrationRight),
                Math.min(penetrationTop, penetrationBottom));

        // Adjust ball position and direction based on the collision side
        if (minPenetration == penetrationLeft) {
            ball.setPosition(new Point(brickRect.x - ballRect.width, ball.getPosition().y));
            // force reverse horizontal
            ball.guidedCollision(-ball.getDirection().getHorizontalVelocity(),
                    ball.getDirection().getVerticalVelocity());
        } else if (minPenetration == penetrationRight) {
            ball.setPosition(new Point(brickRect.x + brickRect.width + ballRect.width, ball.getPosition().y));
            ball.guidedCollision(-ball.getDirection().getHorizontalVelocity(),
                    ball.getDirection().getVerticalVelocity());
        } else if (minPenetration == penetrationTop) {
            ball.setPosition(new Point(ball.getPosition().x, brickRect.y - ballRect.height));
            ball.guidedCollision(rnd.nextInt(3) - 1, -ball.getDirection().getVerticalVelocity());
        } else if (minPenetration == penetrationBottom) {
            ball.setPosition(new Point(ball.getPosition().x, brickRect.y + brickRect.height));
            ball.guidedCollision(rnd.nextInt(3) - 1, -ball.getDirection().getVerticalVelocity());
        }
    }

    private void debugPrint(final String name, final long difference) {
        if (!GameInfo.DEBUG_MODE) {
            return;
        }
        final long milliseconds = TimeUnit.NANOSECONDS.toMillis(difference);
        final String output = String.format("%s took %d ms", name, milliseconds);
        if (milliseconds > 10) {
            log.warning("\u001B[31m" + output + "\u001B[0m"); // ANSI escape code for red color
        }
    }

    /**
     * ONLY ACCESS FOR TEST.
     */
    protected void handleEnlargePowerUp() {
        PowerUp.ENLARGE.use();
        master.extendPaddle();
    }

    /**
     * ONLY ACCESS FOR TEST.
     * 
     * @param ball
     */
    protected void bomb(final GameEntity ball) {
        PowerUp.BOMB.use();
        final Bomb bomb = new Bomb(new Point(ball.getPosition().x - GameInfo.GAME_WIDTH / (BOMB_SIZE_RATIO * 2),
                ball.getPosition().y - GameInfo.GAME_WIDTH / (BOMB_SIZE_RATIO * 2)),
                new Dimension(GameInfo.GAME_WIDTH / BOMB_SIZE_RATIO,
                        GameInfo.GAME_WIDTH / BOMB_SIZE_RATIO));
        for (final GameEntity brick : master.getBricks()) {
            if (!brick.isAlive()) {
                continue;
            }
            if (collides(bomb, brick)) {
                brick.onCollision();
            }
        }
    }

    private boolean collides(final GameEntity a, final GameEntity b) {
        final Point posA = a.getPosition();
        final Dimension sizeA = a.getSize();
        final Point posB = b.getPosition();
        final Dimension sizeB = b.getSize();
        final Rectangle aR = new Rectangle(posA, sizeA);
        final Rectangle bR = new Rectangle(posB, sizeB);
        return aR.intersects(bR);
    }
}
