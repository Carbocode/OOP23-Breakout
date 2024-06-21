package it.unibo.model;

public class BigBar extends Bar {
    private boolean bigBar = false;
    private int time = 0;
    private final int fullTime = 300;
    private Bar bar;

    public BigBar() {
        this.bar = new Bar(getPosition(), getSize(), IMMORTAL_ENTITY_HEALTH, DEFAULT_COLOR);
    }

    public final void extendedBar() {
        if (time < fullTime) {
            time++;
            this.bar.setWidth((int) (getSize().getWidth() * 0.5));
            return;
        } else {
            time = 0;
        }

    }

}
