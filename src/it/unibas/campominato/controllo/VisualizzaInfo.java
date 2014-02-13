package it.unibas.campominato.controllo;

import it.unibas.ping.annotazioni.AcceleratoreSwing;
import it.unibas.ping.annotazioni.DescrizioneSwing;
import it.unibas.ping.annotazioni.DisabilitataAllAvvio;
import it.unibas.ping.annotazioni.MnemonicoSwing;
import it.unibas.ping.annotazioni.NomeSwing;
import it.unibas.ping.azioni.AzionePingAstratta;
import it.unibas.ping.framework.PingException;
import java.util.EventObject;

@NomeSwing("Info Gioco")
@DescrizioneSwing("Visualizza le info sul gioco")
@MnemonicoSwing("H")
@AcceleratoreSwing("F1")
@DisabilitataAllAvvio(false)
public class VisualizzaInfo extends AzionePingAstratta{
    
    public void esegui(EventObject object) throws PingException {
        vista.eseguiSchermo("showInfo");
    }
}
