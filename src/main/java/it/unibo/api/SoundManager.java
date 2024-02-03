package it.unibo.api;

public interface SoundManager {
        void playBackgroundSound(String filePath);

        void stopBackgroundSound();

        void playCollisionSound(String filePath);
}
