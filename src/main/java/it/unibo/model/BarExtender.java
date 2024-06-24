package it.unibo.model;

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
        try {
            throw new Exception();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * this method when it called extends the bar.
     * 
     * @param bar
     */
    public static void extendBar(final Bar bar) {
        bar.setWidth(bar.getSize().width + INCREASE_AMOUNT);
    }

}
