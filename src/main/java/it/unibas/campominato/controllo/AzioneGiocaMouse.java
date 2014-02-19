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
import java.awt.event.MouseEvent;
import javax.sound.sampled.Clip;
import javax.swing.JTable;
import javax.swing.Timer;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

@DisabilitataAllAvvio(false)
public class AzioneGiocaMouse extends AzionePingAstratta{
    
    private int x;
    private int y;
    private Log logger = LogFactory.getLog(this.getClass());
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
        Object o = object.getSource();
        IPosizione posizione = vista.getPosizioneCellaSelezione("tabella");
        MouseEvent mouse = (MouseEvent)object;
        if (mouse != null) {
            if (posizione != null && mouse.getButton() == mouse.BUTTON3) {
                this.avviaTempo();
                JTable tabella = (JTable)o;
                this.x = tabella.rowAtPoint(mouse.getPoint());
                this.y = tabella.columnAtPoint(mouse.getPoint());
                this.scacchiera.setBandierina(this.x, this.y);
                this.verificaGiocoFinito();
//                mouse.toString().lastIndexOf("MOUSE_RELEASED") != -1
            }
        }
    }
    
    private void avviaTempo() {
        if (!this.timer.isRunning()) {
            timer.start();
        }
    }
    
    private void fermaTempo() {
        this.timer.stop();
    }
    
    private void verificaGiocoFinito() {
        if (this.scacchiera.isGiocoFinito()) {
            vista.eseguiSchermo("rimuoviListenersTabella");
            this.fermaTempo();
            this.clipAudio.close();
            this.modello.putBean(Controllo.STATO, new StatoPing(Costanti.STATO_MINA_TROVATA));
            Utilita.riproduciFileAudio("newRecord.wav", clipAudio);
            this.modello.putBean(Controllo.MESSAGGIO_STATO, new MessaggioPing("Fantastico Partita Vinta!"));
            vista.eseguiSchermo("visualizzaFinestraNuovoRecord");
        }
    }
    
}
