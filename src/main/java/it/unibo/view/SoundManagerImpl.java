package it.unibo.view;

import it.unibo.api.SoundManager;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class SoundManagerImpl implements SoundManager {
    private Clip backgroundClip;
    private Clip collisionClip;

    @Override
    public void playBackgroundSound() {
        try {
            String fileNameBg = "sounds/main_theme.mp3";
            File backgroundFileAudio = new File(fileNameBg);
            try (AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(backgroundFileAudio)) {
                backgroundClip = AudioSystem.getClip();
                backgroundClip.open(audioInputStream);

                // Play the background sound
                backgroundClip.start();
            }
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void stopBackgroundSound() {
        if (backgroundClip != null && backgroundClip.isRunning()) {
            backgroundClip.stop();
        }
    }

    @Override
    public void playCollisionSound() {
        try {
            String fileNameColl = "sounds/hit1.wav";
            File collisionFileAudio = new File(fileNameColl);
            try (AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(collisionFileAudio)) {
                collisionClip = AudioSystem.getClip();
                collisionClip.open(audioInputStream);

                // Play the collision sound
                collisionClip.start();
            }
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }
}
