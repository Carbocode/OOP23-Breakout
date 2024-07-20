package it.unibo.model;

/**
 * PowerUP description enum.
 */
public enum PowerUp {
    /**
     * Bomb power up.
     */
    BOMB(15, 15_000),
    /**
     * Duplication power up.
     */
    DUPLI(50, 2_000),
    /**
     * Enlargement.
     */
    ENLARGE(10, 5_000);

    private final double probability;
    private final long cooldownMillis;
    private long lastUsedTime;

    /**
     * 
     * @param probability
     * @param cooldownMillis
     */
    PowerUp(final double probability, final long cooldownMillis) {
        this.probability = probability;
        this.cooldownMillis = cooldownMillis;
        this.lastUsedTime = 0; // Initialize lastUsedTime to 0 (not used yet)
    }

    /**
     * 
     * @return Probability
     */
    public double getProbability() {
        return probability;
    }

    /**
     * 
     * @return Cooldown of the power up.
     */
    public long getCooldownMillis() {
        return cooldownMillis;
    }

    /**
     * 
     * @return remaining CD
     */
    public int getCDInSecs() {
        if (!isOnCooldown()) {
            return 0;
        }
        return (int) (cooldownMillis - (System.currentTimeMillis() - lastUsedTime)) / 1000;
    }

    /**
     * 
     * @return is it on CD?
     */
    public boolean isOnCooldown() {
        return System.currentTimeMillis() - lastUsedTime < cooldownMillis;
    }

    /**
     * ALWAYS USE WHEN ACTIVATING POWER UP.
     */
    public void use() {
        lastUsedTime = System.currentTimeMillis();
    }
}
