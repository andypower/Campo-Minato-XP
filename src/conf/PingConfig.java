package conf;

import it.unibas.campominato.controllo.AzioneIniziale;
import it.unibas.campominato.vista.FinestraInfo;
import it.unibas.campominato.vista.FinestraLegenda;
import it.unibas.campominato.vista.FinestraLivelloPersonalizzato;
import it.unibas.campominato.vista.FinestraNuovoRecord;
import it.unibas.campominato.vista.FinestraRecord;
import it.unibas.campominato.vista.FramePrincipale;
import it.unibas.campominato.vista.PannelloPrincipale;
import it.unibas.ping.configurazione.Configurazione;

public class PingConfig extends Configurazione{
    
    public PingConfig() {
        super.setNomeFileSplashScreen("/risorse/splashScreen.jpg");
        super.setAutore("N. Sileno - G. Mecca");
        super.setNomeApplicazione("Campo Minato XP");
        super.setAzioneIniziale(AzioneIniziale.class.getName());
        super.setVistaPrincipale(FramePrincipale.class.getName());
        super.addSottoVista(PannelloPrincipale.class.getName());
        super.addSottoVista(FinestraLivelloPersonalizzato.class.getName());
        super.addSottoVista(FinestraInfo.class.getName());
        super.addSottoVista(FinestraLegenda.class.getName());
        super.addSottoVista(FinestraNuovoRecord.class.getName());
        super.addSottoVista(FinestraRecord.class.getName());
        super.setRegistraOsservatori(true);
    }
    
}
