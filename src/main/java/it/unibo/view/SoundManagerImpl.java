package it.unibo.view;

import it.unibo.api.SoundManager;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class SoundManagerImpl implements SoundManager {
    private Clip backgroundClip;
    private Clip collisionClip;

    @Override
    public void playBackgroundSound(String filePath) {
        try {
            File soundFile = new File(filePath);
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(soundFile);
            backgroundClip = AudioSystem.getClip();
            backgroundClip.open(audioInputStream);

            // Riproduci il suono
            backgroundClip.start();
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
    public void playCollisionSound(String filePath) {
        try {
            File soundFile = new File(filePath);
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(soundFile);
            collisionClip = AudioSystem.getClip();
            collisionClip.open(audioInputStream);

            // Riproduci il suono di collisione
            collisionClip.start();
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }
}
