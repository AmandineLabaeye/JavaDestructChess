package com.company;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.net.URL;

/**
 * Un son qui peut être joué
 */
public class Son {
    private Clip clip;

    /**
     * @param soundName Le nom du fichier sonore à jouer.
     *  Doit être placer dans le dossier resources et être au format .wav
     */
    public Son(String soundName) {
        try {
            clip = AudioSystem.getClip();

            // Récupère le fichier audio
            URL file = getClass().getClassLoader().getResource(soundName + ".wav");
            if (file == null) {
                System.out.println("Fichier audio non trouver " + soundName + ".wav");
                return;
            }

            // Ouvre et lit le fichier
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(file);
            clip.open(audioInputStream);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Jouer le son un fois
     */
    public void jouer(){
        clip.setFramePosition(0);
        clip.start();
    }

    /**
     * Jouer le son en boucle
     */
    public void jouerEnBoucle(){
        clip.loop(Clip.LOOP_CONTINUOUSLY);
        jouer();
    }

    /**
     * Stopper le son
     */
    public void stop(){
        clip.stop();
    }
}
