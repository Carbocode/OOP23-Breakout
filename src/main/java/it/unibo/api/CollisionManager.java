package it.unibo.api;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.Set;
import java.util.Random;
import java.util.ArrayList;
import java.util.List;
import it.unibo.model.Ball;
import it.unibo.model.Bar;
import it.unibo.model.BarExtender;
import it.unibo.controller.GameLoop.PowerUp;
import it.unibo.model.Bomb;
import it.unibo.view.SoundManagerImpl;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

/**
 * Class that checks for collisions.
 */
public class CollisionManager {
    private final BrickWall bricks;
    private final Set<Ball> balls;
    private final Bar paddle;
    private static final int BOMB_SIZE_RATIO = 5;
    private static int maxBalls = 10;
    private final ScoreManager score;
    private static final int POINTS_INCREASE = 200;
    private final Random rnd;
    private final ScheduledExecutorService scheduler;
    private final SoundManager sound;
    private final Logger log;

    /**
     * Initializes CollisionManager.
     * 
     * @param balls
     * @param brickWall
     * @param paddle
     * @param score
     */
    public CollisionManager(final Set<Ball> balls, final BrickWall brickWall, final Bar paddle,
            final ScoreManager score) {
        this.balls = balls;
        this.bricks = brickWall;
        this.paddle = paddle;
        this.score = score;
        rnd = new Random();
        scheduler = Executors.newScheduledThreadPool(1);
        sound = new SoundManagerImpl();
        log = Logger.getLogger(CollisionManager.class.getName());
    }

    /**
     * Checks all objects for collision.
     */
    public final void checkAll() {
        final List<Ball> newBalls = new ArrayList<>();
        final long startTime = System.nanoTime();
        for (final Ball ball : new ArrayList<>(balls)) { // Create a copy to iterate over
            boolean collision = false;
            if (!ball.isAlive()) {
                continue;
            }
            boolean greyCollision = false;
            int forcedDirection = 0;
            // Collision with bricks
            final long brickStartTime = System.nanoTime();
            for (final GameEntity brick : bricks.getWall()) {
                if (!brick.isAlive()) {
                    continue;
                }
                if (collides(ball, brick)) {
                    collision = true;
                    if (brick.getHealth() != -1) {
                        score.increment(POINTS_INCREASE);
                    } else {
                        greyCollision = true;
                        if (brick.getPosition().x > ball.getPosition().x) {
                            forcedDirection = -1;
                        } else {
                            forcedDirection = 1;
                        }
                    }
                    brick.onCollision();
                }
            }
            final long brickEndTime = System.nanoTime();
            debugPrint("Brick Collision", brickEndTime - brickStartTime);
            // Collision with paddle
            final long paddleStartTime = System.nanoTime();
            if (collides(ball, paddle)) {
                log.info("Paddle hit");
                if (ball.getPosition().x < paddle.getPosition().x + (paddle.getSize().width / 2)) {
                    ball.guidedCollision(-1);
                } else {
                    ball.guidedCollision(1);
                }
            }
            final long paddleEndTime = System.nanoTime();
            debugPrint("Paddle Collision check", paddleEndTime - paddleStartTime);
            // Handle collisions
            if (collision) {
                final long powerUPStartTime = System.nanoTime();
                if (greyCollision) {
                    ball.guidedCollision(forcedDirection);
                } else {
                    ball.onCollision();
                }

                if (!greyCollision) {
                    for (final PowerUp pu : PowerUp.values()) {
                        if (rnd.nextInt(100) <= pu.getProbability() && !pu.isOnCooldown()) {
                            switch (pu) {
                                case ENLARGE:
                                    sound.playBonusSound();
                                    handleEnlargePowerUp();
                                    break;
                                case BOMB:
                                    sound.playBombSound();
                                    bomb(ball);
                                    break;
                                case DUPLI:
                                    sound.playBonusSound();
                                    newBalls.add(new Ball(ball)); // Collect new ball
                                    PowerUp.DUPLI.use();
                                    break;
                                default:
                                    break;
                            }
                        }
                    }
                }
                final long powerUpEndTime = System.nanoTime();
                debugPrint("Power Up Handling", powerUpEndTime - powerUPStartTime);
            }
        }

        // Add new balls
        final long addBallsStartTime = System.nanoTime();
        if (balls.size() + newBalls.size() <= maxBalls) {
            balls.addAll(newBalls);
        }
        final long addBallsEndTime = System.nanoTime();
        debugPrint("Add Balls ", addBallsEndTime - addBallsStartTime);
        final long endTime = System.nanoTime();
        debugPrint("TOTAL", endTime - startTime);
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

    private void handleEnlargePowerUp() {
        PowerUp.ENLARGE.use();
        final Dimension originalSize = paddle.getSize();
        BarExtender.extendBar(paddle);
        // Schedule a task to reverse the ENLARGE effect after 5 seconds
        scheduler.schedule(() -> {
            paddle.setSize(originalSize);
        }, PowerUp.ENLARGE.getCDInSecs(), TimeUnit.SECONDS);
    }

    private void bomb(final GameEntity ball) {
        PowerUp.BOMB.use();
        final Bomb bomb = new Bomb(new Point(ball.getPosition().x - GameInfo.GAME_WIDTH / (BOMB_SIZE_RATIO * 2),
                ball.getPosition().y - GameInfo.GAME_WIDTH / (BOMB_SIZE_RATIO * 2)),
                new Dimension(GameInfo.GAME_WIDTH / BOMB_SIZE_RATIO,
                        GameInfo.GAME_WIDTH / BOMB_SIZE_RATIO));
        for (final GameEntity brick : bricks.getWall()) {
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
