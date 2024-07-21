package it.unibo.model;

import java.awt.Dimension;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * This is one of the bonus of the game.
 * when the bricks that contains it get broken, the bar will extend.
 */
public final class BarExtender {
    private static final int INCREASE_AMOUNT = 50;

    /**
     * Barextender constructor.
     */
    private BarExtender() {
        throw new UnsupportedOperationException();
    }

    /**
     * this method when it called extends the bar.
     * 
     * @param bar
     */
    public static void extendBar(final Bar bar) {
        final ScheduledExecutorService scheduler;
        scheduler = Executors.newScheduledThreadPool(1);
        final Dimension originalSize = bar.getSize();
        bar.setWidth(bar.getSize().width + INCREASE_AMOUNT);
        scheduler.schedule(() -> {
            bar.setSize(originalSize);
        }, PowerUp.ENLARGE.getCDInSecs(), TimeUnit.SECONDS);
    }

    /**
     * This method returns the increase amount.
     * 
     * @return INCREASE_AMOUNT
     */
    public static int getIncreaseAmount() {
        return INCREASE_AMOUNT;
    }
}
