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
            String folderPathBg = "../appdata/sounds";
            String fileNameBg = "main_theme(passionfruit).mp3";
            File backgroundFileAudio = new File(folderPathBg,fileNameBg);
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(backgroundFileAudio);
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
    public void playCollisionSound() {
        try {
            String folderPathColl = "../appdata/sounds";
            String fileNameColl = "hit1.wav";
            File collisionFileAudio = new File(folderPathColl,fileNameColl);
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(collisionFileAudio);
            collisionClip = AudioSystem.getClip();
            collisionClip.open(audioInputStream);

            // Riproduci il suono di collisione
            collisionClip.start();
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }
}
