package it.unibas.campominato.vista;

import it.unibas.campominato.Costanti;
import it.unibas.ping.utilita.ResourceManager;
import java.net.URI;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.ImageIcon;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class Utilita {
    
    private static Log logger = LogFactory.getLog(Utilita.class);
    
    public Utilita() {
    }
    
    public static ImageIcon creaIcona(String tipo) {
        URI uri = ResourceManager.getURI(Costanti.PERCORSO + tipo);
        ImageIcon img = null;
        try {
            img = new ImageIcon(uri.toURL());
        } catch (Exception e) {
            logger.info("Problemi con l'icona");
        }
        return img;
    }
    
    public static void riproduciFileAudioContinuamente(String nomeFile, Clip clipAudio) {
        AudioInputStream musica = null;
        try {
            URI uri = ResourceManager.getURI(Costanti.PERCORSOAUDIO + nomeFile);
            musica = AudioSystem.getAudioInputStream(uri.toURL());
            clipAudio.open(musica);
            clipAudio.loop(Clip.LOOP_CONTINUOUSLY);
        } catch (Exception e) {
            logger.info("Problemi con il suono" + e);
        }
    }
    
    public static void riproduciFileAudio(String nomeFile, Clip clipAudio) {
        Utilita.riproduciFileAudioContinuamente(nomeFile, clipAudio);
        clipAudio.loop(0);
    }
    
}
