package it.unibo.view;

import it.unibo.api.SoundManager;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import java.io.IOException;
import java.net.URL;
import java.io.InputStream;

/**
 * This is the soundmanager, that plays the music when the game starts and play
 * the collision sound.
 * 
 * @author Sohail Mama
 */
public class SoundManagerImpl implements SoundManager {
    private Clip backgroundClip;
    private Clip collisionClip;

    @Override
    public final void playBackgroundSound() {
        try {
            InputStream musicStream;

            URL indFile = getClass().getClassLoader().getResource("sounds/main_theme(passionfruit).wav");
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
    public final void stopBackgroundSound() {
        if (backgroundClip != null && backgroundClip.isRunning()) {
            backgroundClip.stop();
        }
    }

    @Override
    public final void playCollisionSound() {
        try {
            InputStream musicStream;

            URL indFile = getClass().getClassLoader().getResource("sounds/hit1.wav");
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(indFile);
            backgroundClip = AudioSystem.getClip();
            backgroundClip.open(audioInputStream);
            // Riproduci il suono
            backgroundClip.start();
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }
}
