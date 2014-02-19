package it.unibas.campominato;

import it.unibas.campominato.controllo.AzioneGioca;
import it.unibas.campominato.controllo.AzioneGiocaMouse;
import it.unibas.campominato.controllo.AzioneLivelloAvanzato;
import it.unibas.campominato.controllo.AzioneLivelloBase;
import it.unibas.campominato.controllo.AzioneLivelloIntermedio;
import it.unibas.campominato.controllo.AzioneLivelloPersonalizzato;
import it.unibas.campominato.controllo.AzionePausa;
import it.unibas.campominato.controllo.AzioneStopSuono;
import it.unibas.campominato.controllo.AzioneSuonaHarsh;
import it.unibas.campominato.controllo.AzioneSuonaLoose;
import it.unibas.campominato.controllo.AzioneSuonaSoft;
import it.unibas.campominato.controllo.AzioneSuonaTipsy;
import it.unibas.campominato.controllo.NuovaPartita;
import it.unibas.campominato.controllo.VisualizzaInfo;
import it.unibas.campominato.controllo.VisualizzaLegenda;
import it.unibas.campominato.controllo.VisualizzaRecord;
import it.unibas.campominato.modello.Scacchiera;
import it.unibas.ping.azioni.AzioneEsci;
import it.unibas.ping.azioni.AzioneInformazioniApplicazione;
import it.unibas.ping.azioni.AzioneInformazioniPing;

public class Costanti {
    
    public static final String AZIONE_LIVELLO_BASE = AzioneLivelloBase.class.getName();
    public static final String AZIONE_LIVELLO_INTERMEDIO = AzioneLivelloIntermedio.class.getName();
    public static final String AZIONE_LIVELLO_AVANZATO = AzioneLivelloAvanzato.class.getName();
    public static final String AZIONE_LIVELLO_PERSONALIZZATO = AzioneLivelloPersonalizzato.class.getName();
    public static final String AZIONE_SUONA_TIPSY = AzioneSuonaTipsy.class.getName();
    public static final String AZIONE_SUONA_HARSH = AzioneSuonaHarsh.class.getName();
    public static final String AZIONE_SUONA_LOOSE = AzioneSuonaLoose.class.getName();
    public static final String AZIONE_SUONA_SOFT = AzioneSuonaSoft.class.getName();
    public static final String AZIONE_STOP_SUONO = AzioneStopSuono.class.getName();
    public static final String AZIONE_ESCI = AzioneEsci.class.getName();
    public static final String AZIONE_ABOUT = AzioneInformazioniPing.class.getName();
    public static final String AZIONE_ABOUT_APPLICAZIONE = AzioneInformazioniApplicazione.class.getName();
    public static final String AZIONE_NUOVA_PARTITA = NuovaPartita.class.getName();
    public static final String AZIONE_GIOCA = AzioneGioca.class.getName();
    public static final String AZIONE_GIOCA_MOUSE = AzioneGiocaMouse.class.getName();
    public static final String AZIONE_PAUSA = AzionePausa.class.getName();
//    public static final String AZIONE_INIZIALE = AzioneIniziale.class.getName();
    
    public static final String AZIONE_VISUALIZZA_INFO = VisualizzaInfo.class.getName();
    public static final String AZIONE_VISUALIZZA_RECORD = VisualizzaRecord.class.getName();
    public static final String AZIONE_VISUALIZZA_LEGENDA = VisualizzaLegenda.class.getName();
    
//    public static final String PAUSA = "pausa";
    public static final String SCACCHIERA = "scacchiera";
    public static final String CELLE = "celle";
    public static final String NUMERO_MINE = "numMine";
    public static final String CLIPAUDIO = "clipAudio";
    public static final String TIMER = "timer";
    
    public static final int STATO_MINA_TROVATA = 1;
    public static final int STATO_MINA_NASCOSTA = 0;
    public static final int STATO_PAUSA = 2;
    
    public static final String FILE_SEPARATOR = System.getProperty("file.separator");
    
    public final static String PERCORSO = "/risorse/img/";
    public final static String PERCORSOAUDIO = "/risorse/suoni/";
    public final static String MINA = "mina.gif";
    public final static String BANDIERINA = "flag.jpg";
    public final static String QUADRATINO = "quadratino.gif";
    public final static String UNO = "uno.gif";
    public final static String DUE = "due.gif";
    public final static String TRE = "tre.gif";
    public final static String QUATTRO = "quattro.gif";
    public final static String CINQUE = "cinque.gif";
    public final static String SEI = "sei.gif";
    public final static String SETTE = "sette.gif";
    public final static String OTTO = "otto.gif";
    public final static String MINAASSENTE = "minaAssente.gif";
    public final static String MINAESPLOSA = "minaEsplosa.gif";
    public final static String SPUNTA = "ok.png";
    public final static String SFONDO_PAUSA = "matrix.gif";
}
