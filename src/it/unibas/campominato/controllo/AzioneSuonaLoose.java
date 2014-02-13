package it.unibas.campominato.controllo;

import it.unibas.campominato.Costanti;
import it.unibas.campominato.vista.Utilita;
import it.unibas.ping.annotazioni.AcceleratoreSwing;
import it.unibas.ping.annotazioni.DescrizioneSwing;
import it.unibas.ping.annotazioni.DisabilitataAllAvvio;
import it.unibas.ping.annotazioni.Inietta;
import it.unibas.ping.annotazioni.MnemonicoSwing;
import it.unibas.ping.annotazioni.NomeSwing;
import it.unibas.ping.azioni.AzionePingAstratta;
import it.unibas.ping.framework.Controllo;
import it.unibas.ping.framework.MessaggioPing;
import it.unibas.ping.framework.PingException;
import java.util.EventObject;
import javax.sound.sampled.Clip;

@NomeSwing("Loose")
@DescrizioneSwing("Cambia la musica di sottofondo con Loose")
@MnemonicoSwing("O")
@AcceleratoreSwing("ctrl O")
@DisabilitataAllAvvio(false)
public class AzioneSuonaLoose extends AzionePingAstratta{
    
    private Clip clipAudio;
    
    @Inietta(Costanti.CLIPAUDIO)
    public void setClipAudio(Clip clipAudio) {
        this.clipAudio = clipAudio;
    }
    
    public void esegui(EventObject object) throws PingException {
        this.clipAudio.close();
        Utilita.riproduciFileAudioContinuamente("loose.wav", clipAudio);
        this.modello.putBean(Controllo.MESSAGGIO_STATO, new MessaggioPing("Riproduco Loose"));
    }
    
}
