package it.unibo.view;

import it.unibo.api.SoundManager;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import java.io.IOException;
import java.net.URL;

/**
 * This is the soundmanager, that plays the music when the game starts and play
 * the collision sound.
 * 
 */
public class SoundManagerImpl implements SoundManager {
    private Clip backgroundClip;
    private Clip collisionClip;
    private Clip buttonClip;
    private Clip menuClip;
    private Clip gameClip;
    private Clip gameoverClip;
    private Clip victoryClip;
    private Clip bombClip;

    @Override
    public final void playBackgroundSound() {
        try {
            final URL indFile = getClass().getClassLoader().getResource("sounds/main_theme(passionfruit).wav");
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(indFile);
            backgroundClip = AudioSystem.getClip();
            backgroundClip.open(audioInputStream);
            // Riproduci il suono
            backgroundClip.start();
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    @Override
    public final void playGameOverSound() {
        try {
            final URL indFile = getClass().getClassLoader().getResource("sounds/gameover.wav");
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(indFile);
            gameoverClip = AudioSystem.getClip();
            gameoverClip.open(audioInputStream);
            // Riproduci il suono
            gameoverClip.start();
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    @Override
    public final void playCollisionSound() {
        try {
            final URL indFile = getClass().getClassLoader().getResource("sounds/hit2.wav");
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(indFile);
            collisionClip = AudioSystem.getClip();
            collisionClip.open(audioInputStream);
            // Riproduci il suono
            collisionClip.start();
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    @Override
    public final void playGameSound() {
        try {
            final URL indFile = getClass().getClassLoader().getResource("sounds/introgame.wav");
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(indFile);
            gameClip = AudioSystem.getClip();
            gameClip.open(audioInputStream);
            // Riproduci il suono
            gameClip.start();
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    @Override
    public final void playButtonSound() {
        try {
            final URL indFile = getClass().getClassLoader().getResource("sounds/buttonsound.wav");
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(indFile);
            buttonClip = AudioSystem.getClip();
            buttonClip.open(audioInputStream);
            // Riproduci il suono
            buttonClip.start();
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    @Override
    public final void playMenuSound() {
        try {
            final URL indFile = getClass().getClassLoader().getResource("sounds/intromenu.wav");
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(indFile);
            menuClip = AudioSystem.getClip();
            menuClip.open(audioInputStream);
            // Riproduci il suono
            menuClip.start();
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    @Override
    public final void playVictorySound() {
        try {
            final URL indFile = getClass().getClassLoader().getResource("sounds/victory.wav");
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(indFile);
            victoryClip = AudioSystem.getClip();
            victoryClip.open(audioInputStream);
            // Riproduci il suono
            victoryClip.start();
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    @Override
    public final void playBombSound() {
        try {
            final URL indFile = getClass().getClassLoader().getResource("sounds/bomb.wav");
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(indFile);
            bombClip = AudioSystem.getClip();
            bombClip.open(audioInputStream);
            // Riproduci il suono
            bombClip.start();
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    @Override
    public final void playBonusSound() {
        try {
            final URL indFile = getClass().getClassLoader().getResource("sounds/bonus.wav");
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(indFile);
            bombClip = AudioSystem.getClip();
            bombClip.open(audioInputStream);
            // Riproduci il suono
            bombClip.start();
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }
}
