package it.unibo.api;
interface GameEntity {
    Pos2D getPosition();
    Size getSize();
    void onCollision();
    boolean isAlive();
}