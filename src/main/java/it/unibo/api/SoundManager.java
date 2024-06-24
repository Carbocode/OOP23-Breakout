package it.unibo.api;

/**
 * this is the sound manager.
 * 
 * @author Sohail Mama
 */
public interface SoundManager {
        /**
         * this method plays the background music in-game.
         */
        void playBackgroundSound();

        /**
         * this method plays the game over sound.
         */
        void playGameOverSound();

        /**
         * this method plays the collision sound.
         */
        void playCollisionSound();

        /**
         * this method plays the intro sound of the game.
         */
        void playGameSound();

        /**
         * this method plays the button sound.
         */

        void playButtonSound();

        /**
         * this method plays the intro sound of the menu.
         */
        void playMenuSound();

        /**
         * this method plays the win sound.
         */
        void playVictorySound();
}
