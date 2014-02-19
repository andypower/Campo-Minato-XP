package it.unibas.campominato.controllo;

import it.unibas.campominato.Costanti;
import it.unibas.ping.annotazioni.DescrizioneSwing;
import it.unibas.ping.annotazioni.DisabilitataAllAvvio;
import it.unibas.ping.annotazioni.Inietta;
import it.unibas.ping.annotazioni.NomeSwing;
import it.unibas.ping.azioni.AzionePingAstratta;
import it.unibas.ping.framework.Controllo;
import it.unibas.ping.framework.PingException;
import it.unibas.ping.framework.StatoPing;
import java.util.EventObject;
import javax.swing.Timer;

@NomeSwing("Livello Personalizzato")
@DescrizioneSwing("Sceglie il livello personalizzato")
@DisabilitataAllAvvio(false)
public class AzioneLivelloPersonalizzato extends AzionePingAstratta{
    
    private Timer timer;
    
    @Inietta(Costanti.TIMER)
    public void setTimer(Timer timer){
        this.timer = timer;
    }
    
    public void esegui(EventObject object) throws PingException {
        this.modello.putBean(Controllo.STATO, new StatoPing(Costanti.STATO_MINA_TROVATA));
        this.timer.stop();
        vista.eseguiSchermo("rimuoviListenersTabella");
        this.modello.removeBean(Costanti.SCACCHIERA);
        vista.eseguiSchermo("nascondiFinestraMinaTrovata");
        vista.eseguiSchermo("mostraFinestraLivelloPersonalizzato");
//        this.modello.putBean(Costanti.PAUSA, false);
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
