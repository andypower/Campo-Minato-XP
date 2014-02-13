package it.unibas.campominato.controllo;

import it.unibas.campominato.Costanti;
import it.unibas.campominato.modello.Scacchiera;
import it.unibas.ping.annotazioni.DescrizioneSwing;
import it.unibas.ping.annotazioni.IconaSwing;
import it.unibas.ping.azioni.AzionePingAstratta;
import it.unibas.ping.framework.Controllo;
import it.unibas.ping.framework.MessaggioPing;
import it.unibas.ping.framework.PingException;
import it.unibas.ping.framework.StatoPing;
import java.util.EventObject;
//import it.unibas.ping.utilita.Convalidatore;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

@IconaSwing(Costanti.PERCORSO + Costanti.SPUNTA)
@DescrizioneSwing("Effettua le verifiche ed avvia il livello personalizzato")
public class EseguiLivelloPersonalizzato extends AzionePingAstratta{
    
    private Log logger = LogFactory.getLog(this.getClass());
    
    public void esegui(EventObject object) throws PingException {
        this.modello.putBean(Controllo.STATO, new StatoPing(Costanti.STATO_MINA_NASCOSTA));
        int numRighe = Integer.parseInt((String)vista.getValore("numeroRighe"));
        int numColonne = Integer.parseInt((String)vista.getValore("numeroColonne"));
        int numMine = Integer.parseInt((String)vista.getValore("numeroMine"));
        Scacchiera scacchiera = new Scacchiera(numRighe, numColonne, numMine);
        modello.putBean("righeScacchiera", numRighe);
        modello.putBean("colonneScacchiera", numColonne);
        modello.putBean("mineScacchiera", numMine);
        modello.putBean("livello", 3);
        long secondi = 0;
        this.modello.putBean("secondi", secondi);
        modello.putBean(Costanti.SCACCHIERA, scacchiera);
        vista.eseguiSchermo("nascondiFinestraLivelloPersonalizzato");
        vista.eseguiSchermo("aggiornaPannelloPrincipale");
        this.modello.putBean(Controllo.MESSAGGIO_STATO, new MessaggioPing("Livello Personalizzato Avviato"));
    }
    
}
