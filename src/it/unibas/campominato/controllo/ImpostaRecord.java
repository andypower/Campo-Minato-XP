package it.unibas.campominato.controllo;

import it.unibas.campominato.modello.Record;
import it.unibas.ping.azioni.AzionePingAstratta;
import it.unibas.ping.framework.Controllo;
import it.unibas.ping.framework.MessaggioPing;
import it.unibas.ping.framework.PingException;
import java.util.EventObject;

public class ImpostaRecord extends AzionePingAstratta{
    
    public void esegui(EventObject object) throws PingException {
        this.modello.putBean(Controllo.MESSAGGIO_STATO, new MessaggioPing("Nuova Record Impostato"));
        String nomeGiocatore = (String)vista.getValore("campoNome");
        if (nomeGiocatore != null) {
            Record.setValoreRecord(nomeGiocatore);
            vista.eseguiSchermo("aggiornaRecord");
        }
        vista.eseguiSchermo("nascondiFinestraNuovoRecord");
    }
}
