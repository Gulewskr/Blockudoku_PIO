package blokudoku;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;

import static javax.sound.sampled.FloatControl.Type.MASTER_GAIN;

public class MusicPlayer extends Thread {
    private Clip clip;
    private boolean isMusicStopped;

    @Override
    public void run() {
        try {
            clip = AudioSystem.getClip();
            AudioInputStream inputStream = AudioSystem.getAudioInputStream(
                    Sounds.class.getResourceAsStream("sounds/main_theme.wav"));
            clip.open(inputStream);

            FloatControl gainControl = (FloatControl) clip.getControl(MASTER_GAIN);
            gainControl.setValue(20f * (float) Math.log10(0.05f));

        } catch (Exception e) {
            System.err.println(e.getMessage());
            System.exit(1);
        }

        while (true)
            while (!clip.isActive()) {
                if (!isMusicStopped) {
                    clip.setFramePosition(0);
                    clip.start();
                }
            }
    }


    public void stopMusic() {
        clip.stop();
        isMusicStopped = true;
    }

    public void resumeMusic() {
        isMusicStopped = false;
    }
}
