package it.unibas.campominato.controllo;

import it.unibas.campominato.Costanti;
import it.unibas.campominato.modello.Record;
import it.unibas.campominato.modello.Scacchiera;
import it.unibas.ping.azioni.IAzioneIniziale;
import it.unibas.ping.framework.Applicazione;
import it.unibas.ping.framework.Controllo;
import it.unibas.ping.framework.Modello;
import it.unibas.ping.framework.StatoPing;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class AzioneIniziale implements IAzioneIniziale{
    
    private Log logger = LogFactory.getLog(this.getClass());
    private Modello modello = Applicazione.getInstance().getModello();
    
    public void esegui(Controllo controllo) {
        this.logger.info("Sto eseguendo l'azione inziale");
        modello.putBean("righeScacchiera", 16);
        modello.putBean("colonneScacchiera", 16);
        modello.putBean("mineScacchiera", 40);
        modello.putBean("giocoFinito", false);
        modello.putBean("records", Record.getRecords());
        Scacchiera scacchiera = new Scacchiera(16,16,40);
        modello.putBean(Costanti.SCACCHIERA, scacchiera);
        modello.putBean("livello", 1);
        Clip clipAudio = null;
        try {
            clipAudio = AudioSystem.getClip();
        } catch (Exception e) {
            this.logger.error("Problemi con l'audio");
        }
        modello.putBean(Costanti.CLIPAUDIO, clipAudio);
        this.modello.putBean(Controllo.STATO, new StatoPing(Costanti.STATO_MINA_NASCOSTA));
        controllo.getVista().eseguiSchermo("aggiornaPannelloPrincipale");
        this.logger.info("Azione inziale completata");
    }
    
}
