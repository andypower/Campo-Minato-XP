package it.unibas.campominato.controllo;

import it.unibas.campominato.Costanti;
import it.unibas.campominato.modello.Scacchiera;
import it.unibas.ping.annotazioni.AcceleratoreSwing;
import it.unibas.ping.annotazioni.DescrizioneSwing;
import it.unibas.ping.annotazioni.DisabilitataAllAvvio;
import it.unibas.ping.annotazioni.Inietta;
import it.unibas.ping.annotazioni.MnemonicoSwing;
import it.unibas.ping.annotazioni.NomeSwing;
import it.unibas.ping.azioni.AzionePingAstratta;
import it.unibas.ping.framework.Controllo;
import it.unibas.ping.framework.MessaggioPing;
import it.unibas.ping.framework.StatoPing;
import java.util.EventObject;
import javax.swing.Timer;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

@NomeSwing("Pause")
@DescrizioneSwing("Mette in pausa il gioco o lo fa ripartire")
@AcceleratoreSwing("ctrl P")
@MnemonicoSwing("P")
@DisabilitataAllAvvio(false)
public class AzionePausa extends AzionePingAstratta{
    
    private Log logger = LogFactory.getLog(this.getClass());
    private Timer timer;
    
    @Inietta(Costanti.TIMER)
    public void setTimer(Timer timer){
        this.timer = timer;
    }

    public void esegui(EventObject e) {
        StatoPing stato = (StatoPing)this.modello.getBean(Controllo.STATO);
        Scacchiera scacchiera = null;
        if (stato.getId() == Costanti.STATO_PAUSA) {
            vista.eseguiSchermo("visualizzaTabellaDopoPausa");
            this.timer.start();
            this.modello.putBean(Controllo.MESSAGGIO_STATO, new MessaggioPing("Ripresa del gioco dopo la pausa"));
            this.modello.putBean(Controllo.STATO, new StatoPing(Costanti.STATO_MINA_NASCOSTA));
        } else {
            vista.eseguiSchermo("rimuoviListenersTabella");
            this.timer.stop();
            this.vista.eseguiSchermo("visualizzaSchermoPausa");
            this.modello.putBean(Controllo.STATO, new StatoPing(Costanti.STATO_PAUSA));
            this.modello.putBean(Controllo.MESSAGGIO_STATO, new MessaggioPing("Gioco in pausa"));
        }
    }
    
    public boolean abilita(Integer statusId) {
        if (statusId == Costanti.STATO_MINA_NASCOSTA || statusId == Costanti.STATO_PAUSA) {
            return true;
        }
        return false;
    }
    
    public boolean disabilita(Integer statusId) {
        if (statusId == Costanti.STATO_MINA_TROVATA) {
            return true;
        }
        return false;
    }
}
