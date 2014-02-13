package it.unibas.campominato.controllo;

import it.unibas.campominato.Costanti;
import it.unibas.campominato.modello.Scacchiera;
import it.unibas.ping.annotazioni.DescrizioneSwing;
import it.unibas.ping.annotazioni.DisabilitataAllAvvio;
import it.unibas.ping.annotazioni.Inietta;
import it.unibas.ping.annotazioni.NomeSwing;
import it.unibas.ping.azioni.AzionePingAstratta;
import it.unibas.ping.framework.Controllo;
import it.unibas.ping.framework.MessaggioPing;
import it.unibas.ping.framework.PingException;
import it.unibas.ping.framework.StatoPing;
import java.util.EventObject;
import javax.swing.Timer;

@NomeSwing("Livello Intermedio")
@DescrizioneSwing("Sceglie il livello intermedio")
@DisabilitataAllAvvio(false)
public class AzioneLivelloIntermedio extends AzionePingAstratta{
    
    private Timer timer;
    
    @Inietta(Costanti.TIMER)
    public void setTimer(Timer timer){
        this.timer = timer;
    }
    
    public void esegui(EventObject object) throws PingException {
        this.timer.stop();
        vista.eseguiSchermo("rimuoviListenersTabella");
        this.modello.removeBean(Costanti.SCACCHIERA);
        vista.eseguiSchermo("nascondiFinestraMinaTrovata");
        Scacchiera scacchiera = new Scacchiera(16,16,40);
        modello.putBean("righeScacchiera", 16);
        modello.putBean("colonneScacchiera", 16);
        modello.putBean("mineScacchiera", 40);
        modello.putBean("livello", 1);
        long secondi = 0;
        this.modello.putBean("secondi", secondi);
        modello.putBean(Costanti.SCACCHIERA, scacchiera);
        this.modello.putBean(Controllo.STATO, new StatoPing(Costanti.STATO_MINA_NASCOSTA));
        vista.eseguiSchermo("aggiornaPannelloPrincipale");
        this.modello.putBean(Controllo.MESSAGGIO_STATO, new MessaggioPing("Livello Intermedio Avviato"));
    }
    
    public boolean abilita(Integer statusId) {
        if (statusId != Costanti.STATO_PAUSA) {
            return true;
        }
        return false;
    }
    
    public boolean disabilita(Integer statusId) {
        if (statusId == Costanti.STATO_PAUSA) {
            return true;
        }
        return false;
    }
}
