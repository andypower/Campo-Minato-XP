package it.unibas.campominato.controllo;

import it.unibas.campominato.Costanti;
import it.unibas.campominato.modello.Scacchiera;
import it.unibas.campominato.vista.Utilita;
import it.unibas.ping.annotazioni.DisabilitataAllAvvio;
import it.unibas.ping.annotazioni.Inietta;
import it.unibas.ping.azioni.AzionePingAstratta;
import it.unibas.ping.framework.Controllo;
import it.unibas.ping.framework.MessaggioPing;
import it.unibas.ping.framework.PingException;
import it.unibas.ping.framework.StatoPing;
import java.util.EventObject;
import it.unibas.ping.utilita.IPosizione;
import javax.sound.sampled.Clip;
import javax.swing.Timer;

@DisabilitataAllAvvio(false)
public class AzioneGioca extends AzionePingAstratta{
    
    private Scacchiera scacchiera;
    private Clip clipAudio;
    private Timer timer;
    
    @Inietta(Costanti.SCACCHIERA)
    public void setScacchiera(Scacchiera scacchiera) {
        this.scacchiera = scacchiera;
    }
       
    @Inietta(Costanti.CLIPAUDIO)
    public void setClipAudio(Clip clipAudio) {
        this.clipAudio = clipAudio;
    }
    
    @Inietta(Costanti.TIMER)
    public void setTimer(Timer timer){
        this.timer = timer;
    }
    
    public void esegui(EventObject object) throws PingException {
        IPosizione posizione = vista.getPosizioneCellaSelezione("tabella");
        if (posizione != null) {
            int x = posizione.getRiga();
            int y = posizione.getColonna();
            this.scacchiera.scopriCasella(x, y);
            this.avviaTempo();
            this.verificaMina(scacchiera);
        }
    }
    
    private void avviaTempo() {
        if (!this.timer.isRunning()) {
            this.timer.start();
        }
    }
    
    private void fermaTempo() {
        this.timer.stop();
    }
    
    private void verificaMina(Scacchiera scacchiera) {
        boolean giocoFinito = (Boolean)modello.getBean("giocoFinito");
        if (giocoFinito == true) {
            vista.eseguiSchermo("rimuoviListenersTabella");
            this.fermaTempo();
            scacchiera.scopriMineAllaFine();
            this.modello.putBean(Controllo.STATO, new StatoPing(Costanti.STATO_MINA_TROVATA));
            this.modello.putBean(Controllo.MESSAGGIO_STATO, new MessaggioPing("Peccato, la mina e' esplosa!!!"));
            this.clipAudio.close();
            Utilita.riproduciFileAudio("explo.wav", clipAudio);
            modello.putBean("giocoFinito", false);
            vista.eseguiSchermo("mostraFinestraMinaTrovata");
        } else {
            this.verificaGiocoFinito();
        }
    }
    
    private void verificaGiocoFinito() {
        if (this.scacchiera.isGiocoFinito()) {
            this.fermaTempo();
            vista.eseguiSchermo("rimuoviListenersTabella");
            this.clipAudio.close();
            this.modello.putBean(Controllo.STATO, new StatoPing(Costanti.STATO_MINA_TROVATA));
            Utilita.riproduciFileAudio("newRecord.wav", clipAudio);
            this.modello.putBean(Controllo.MESSAGGIO_STATO, new MessaggioPing("Fantastico Partita Vinta!"));
            vista.eseguiSchermo("visualizzaFinestraNuovoRecord");
        }
    }
}
