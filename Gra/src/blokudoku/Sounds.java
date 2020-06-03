package blokudoku;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;

import static javax.sound.sampled.FloatControl.Type.MASTER_GAIN;

public class Sounds {
    public static synchronized void playGamePointSound() {
        new Thread(() -> {
            try {
                Clip clip = AudioSystem.getClip();
                AudioInputStream inputStream = AudioSystem.getAudioInputStream(
                        Sounds.class.getResource("sounds/GamingPoint.wav"));
                clip.open(inputStream);
                FloatControl gainControl = (FloatControl) clip.getControl(MASTER_GAIN);
                gainControl.setValue(20f * (float) Math.log10(0.2f));
                clip.start();
            } catch (Exception e) {
                System.exit(1);
            }
        }).start();
    }

    public static synchronized void playGameOverSound() {
        new Thread(() -> {
            try {
                Clip clip = AudioSystem.getClip();
                AudioInputStream inputStream = AudioSystem.getAudioInputStream(
                        Sounds.class.getResource("sounds/sadTrombone.wav"));
                clip.open(inputStream);
                FloatControl gainControl = (FloatControl) clip.getControl(MASTER_GAIN);
                gainControl.setValue(20f * (float) Math.log10(0.2f));
                clip.start();
            } catch (Exception e) {
                System.exit(1);
            }
        }).start();
    }
}
