package bataillenavale;

import java.io.File;
import java.io.IOException;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineEvent;
import javax.sound.sampled.LineListener;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

/**
 * Classe permettant d'introduire un document audio
 * 
 * @source
 * https://www.codejava.net/coding/how-to-play-back-audio-in-java-with-examples
 */
public class Audio implements LineListener {

    boolean playCompleted;

    /**
    * Recupère un fichier audio à partir du chemin du fichier
    * @param audioPath le chemin du fichier audio à récuperer
    */
    void play(String audioFilePath) {
        File audioFile = new File(audioFilePath);

        try {
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioFile);
            AudioFormat format = audioStream.getFormat();
            DataLine.Info info = new DataLine.Info(Clip.class, format);
            Clip audioClip = (Clip) AudioSystem.getLine(info);
            audioClip.addLineListener(this);
            audioClip.open(audioStream);
            audioClip.start();

            while (!playCompleted) {
                // wait for the playback completes
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
            }
            audioClip.close();
        } catch (UnsupportedAudioFileException ex) {
            System.out.println("Ce ficher audio n'est pas supporté.");
            ex.printStackTrace();
        } catch (LineUnavailableException ex) {
            System.out.println("Le nom du fichier n'est pas valide.");
            ex.printStackTrace();
        } catch (IOException ex) {
            System.out.println("Impossible de jouer le son.");
            ex.printStackTrace();
        }
    }

    public void update(LineEvent event) {
        LineEvent.Type type = event.getType();

        if (type == LineEvent.Type.START) {
            System.out.println(" ");

        } else if (type == LineEvent.Type.STOP) {
            playCompleted = true;
            System.out.println(" ");
        }
    }
}
