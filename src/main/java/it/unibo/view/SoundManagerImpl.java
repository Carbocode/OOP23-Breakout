package it.unibo.view;

import it.unibo.api.SoundManager;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.util.logging.Logger;

import java.io.IOException;
import java.net.URL;

/**
 * This is the soundmanager, that plays the music when the game starts and play
 * the collision sound.
 * 
 */
public class SoundManagerImpl implements SoundManager {
    public static final long serialVersionUID = 432835743;
    private final Logger log = Logger.getLogger(GameView.class.getName());

    @Override
    public final void playBackgroundSound() {
        try {
            final URL indFile = getClass().getClassLoader().getResource("sounds/main_theme(passionfruit).wav");
            final AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(indFile);
            final Clip backgroundClip = AudioSystem.getClip();
            backgroundClip.open(audioInputStream);
            // Riproduci il suono
            backgroundClip.start();
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            log.warning(e.getMessage());
        }
    }

    @Override
    public final void playGameOverSound() {
        try {
            final URL indFile = getClass().getClassLoader().getResource("sounds/gameover.wav");
            final AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(indFile);
            final Clip gameoverClip = AudioSystem.getClip();
            gameoverClip.open(audioInputStream);
            // Riproduci il suono
            gameoverClip.start();
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            log.warning(e.getMessage());
        }
    }

    @Override
    public final void playCollisionSound() {
        try {
            final URL indFile = getClass().getClassLoader().getResource("sounds/hit2.wav");
            final AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(indFile);
            final Clip collisionClip = AudioSystem.getClip();
            collisionClip.open(audioInputStream);
            // Riproduci il suono
            collisionClip.start();
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            log.warning(e.getMessage());
        }
    }

    @Override
    public final void playGameSound() {
        try {
            final URL indFile = getClass().getClassLoader().getResource("sounds/introgame.wav");
            final AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(indFile);
            final Clip gameClip = AudioSystem.getClip();
            gameClip.open(audioInputStream);
            // Riproduci il suono
            gameClip.start();
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            log.warning(e.getMessage());
        }
    }

    @Override
    public final void playButtonSound() {
        try {
            final URL indFile = getClass().getClassLoader().getResource("sounds/buttonsound.wav");
            final AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(indFile);
            final Clip buttonClip = AudioSystem.getClip();
            buttonClip.open(audioInputStream);
            // Riproduci il suono
            buttonClip.start();
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            log.warning(e.getMessage());
        }
    }

    @Override
    public final void playMenuSound() {
        try {
            final URL indFile = getClass().getClassLoader().getResource("sounds/intromenu.wav");
            final AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(indFile);
            final Clip menuClip = AudioSystem.getClip();
            menuClip.open(audioInputStream);
            // Riproduci il suono
            menuClip.start();
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            log.warning(e.getMessage());
        }
    }

    @Override
    public final void playVictorySound() {
        try {
            final URL indFile = getClass().getClassLoader().getResource("sounds/victory.wav");
            final AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(indFile);
            final Clip victoryClip = AudioSystem.getClip();
            victoryClip.open(audioInputStream);
            // Riproduci il suono
            victoryClip.start();
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            log.warning(e.getMessage());
        }
    }

    @Override
    public final void playBombSound() {
        try {
            final URL indFile = getClass().getClassLoader().getResource("sounds/bomb.wav");
            final AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(indFile);
            final Clip bombClip = AudioSystem.getClip();
            bombClip.open(audioInputStream);
            // Riproduci il suono
            bombClip.start();
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            log.warning(e.getMessage());
        }
    }

    @Override
    public final void playBonusSound() {
        try {
            final URL indFile = getClass().getClassLoader().getResource("sounds/bonus.wav");
            final AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(indFile);
            final Clip bombClip = AudioSystem.getClip();
            bombClip.open(audioInputStream);
            // Riproduci il suono
            bombClip.start();
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            log.warning(e.getMessage());
        }
    }
}
