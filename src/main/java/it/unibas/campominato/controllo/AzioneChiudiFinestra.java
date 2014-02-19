package it.unibas.campominato.controllo;

import it.unibas.ping.azioni.AzionePingAstratta;
import java.util.EventObject;

public class AzioneChiudiFinestra extends AzionePingAstratta{
    
    public void esegui(EventObject eb) {
        this.vista.eseguiSchermo("nascondiFinestraMinaTrovata");
        this.vista.eseguiSchermo("nascondiInfo");
        this.vista.eseguiSchermo("nascondiLegenda");
        this.vista.eseguiSchermo("nascondiFinestraNuovoRecord");
        this.vista.eseguiSchermo("nascondiFinestraRecord");
    }
    
}
