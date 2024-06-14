package it.unibo.model;
import it.unibo.view.*;
import it.unibo.api.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import it.unibo.api.GameEntityImpl;

<<<<<<< HEAD:src/main/java/it/unibo/model/Bar.java
public class Bar  extends GameEntityImpl {
=======
public class BarImpl  extends GameEntityImpl {
>>>>>>> 30235e45bb4703725d3a00c0ab214cc528cd8fdc:src/main/java/it/unibo/model/BarImpl.java

    // (-1) left - (1) right - (0) do not move
    private static final int LEFT_VALUE = -1;
    private static final int RIGHT_VALUE = 1;
    private static final int STOP_VALUE = 0;

    private static final int MOVE_VALUE = 5;

    private int direction;

<<<<<<< HEAD:src/main/java/it/unibo/model/Bar.java
    public Bar(Point position, Dimension size, int health, Color color) {
=======
    public BarImpl(final Point position, final Dimension size, final int health, final Color color) {
>>>>>>> 30235e45bb4703725d3a00c0ab214cc528cd8fdc:src/main/java/it/unibo/model/BarImpl.java
        super(position, size, health, color);
    }

    @Override
    public void onCollision() {
        // play sound
    }

    public final void move() {
        switch (direction) {
            case LEFT_VALUE:
                if (position.x - MOVE_VALUE > 0) {
                    position.x -= MOVE_VALUE;
                }
                break;

            case RIGHT_VALUE:
                if (position.x + size.width + MOVE_VALUE < GameInfo.GAME_WIDTH) {
                    position.x += MOVE_VALUE;
                }
                break;
            default:
                break;
        }
    }

    public final void setWidth(final int newwidth) {
        size.width = newwidth;
        return;
    }

    public final void buttonPressed(final KeyEvent e) {

        switch (e.getKeyCode()) {

            case KeyEvent.VK_LEFT:
                // left arrow button pressed -> move left
                direction = LEFT_VALUE;
                break;

            case KeyEvent.VK_RIGHT:
                // right arrow button pressed -> move right
                direction = RIGHT_VALUE;
                break;

            default:
                // do nothing

        }
    }

    public final void buttonReleased(final KeyEvent e) {
        // button released -> stop moving
        if (e.getKeyCode() == KeyEvent.VK_LEFT && this.direction == LEFT_VALUE
                || e.getKeyCode() == KeyEvent.VK_RIGHT && this.direction == RIGHT_VALUE) {
            direction = STOP_VALUE;
        }
    }

    
}
