package it.unibas.campominato.modello;

import it.unibas.campominato.persistenza.DAOException;
import it.unibas.campominato.persistenza.DAOMock;
import it.unibas.ping.framework.Applicazione;
import it.unibas.campominato.persistenza.DAORecord;
import it.unibas.ping.framework.Modello;
import java.io.*;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class Record {
    
    private static Record singleton;
    private static String nomeFileRecord;
    private boolean nuovoRecord;
    private boolean uguagliato;
    private static Modello modello = Applicazione.getInstance().getModello();
    private static Log logger = LogFactory.getLog(Record.class);
    
    static {
        singleton = new Record();
        String tempDir = System.getProperty("java.io.tmpdir");
        singleton.nomeFileRecord = tempDir + "recordCampoMinato.xml";
    }
    
    private Record() {}
    
    public static Record getInstance() {
        return singleton;
    }
    
    public static List getRecords() {
        BufferedReader file = null;
        List livelliRecord = null;
        try {
            file = new BufferedReader(new FileReader(nomeFileRecord));
            livelliRecord = DAORecord.caricaLivelliRecord(nomeFileRecord);
            logger.info("Carico record da file xml");
        } catch (FileNotFoundException f) {
            logger.info("Errore nella lettura del record: " + f);
            livelliRecord = DAOMock.caricaLivelliRecord();
            try {
                DAORecord.salvaRecords(livelliRecord);
            } catch (DAOException e) {
                logger.info("DAOException errore nel salvataggio del record: " + e);
            }
        } catch (IOException e) {
            logger.info("Errore nella lettura del record: " + e);
        } catch (DAOException e) {
            logger.info("DAOException in getRecords: " + e);
        } finally {
            try {
                if (file != null) {
                    file.close();
                }
            } catch (IOException ioe) {}
        }
        return livelliRecord;
    }
    
    private static Tempo fabbricaTempo(String nomeGiocatore) {
        int numRighe = (Integer)modello.getBean("righeScacchiera");;
        int numColonne = (Integer)modello.getBean("colonneScacchiera");
        int numMine = (Integer)modello.getBean("mineScacchiera");
        long numSecondi = (Long)modello.getBean("secondi");
        Tempo tempo = new Tempo(numSecondi, nomeGiocatore, numColonne, numRighe, numMine);
        return tempo;
    }
    
    public static void setValoreRecord(String nomeGiocatore) {
        Integer valoreLivello = (Integer)modello.getBean("livello");
        if (valoreLivello != null) {
            Tempo tempo = fabbricaTempo(nomeGiocatore);
            List<Livello> listaRecord = (List)modello.getBean("records");
            switch(valoreLivello) {
                case 0: for (Livello livelloIesimo : listaRecord) {
                    if (livelloIesimo.toString().equalsIgnoreCase("Livello Base")) {
                        livelloIesimo.addTempo(tempo);
                    }
                }; break; //livello base
                case 1: for (Livello livelloIesimo : listaRecord) {
                    if (livelloIesimo.toString().equalsIgnoreCase("Livello Intermedio")) {
                        livelloIesimo.addTempo(tempo);
                    }
                };break; //livello intermedio
                case 2: for (Livello livelloIesimo : listaRecord) {
                    if (livelloIesimo.toString().equalsIgnoreCase("Livello Avanzato")) {
                        livelloIesimo.addTempo(tempo);
                    }
                };break; //livello avanzato
                case 3: for (Livello livelloIesimo : listaRecord) {
                    if (livelloIesimo.toString().equalsIgnoreCase("Livello Personalizzato")) {
                        livelloIesimo.addTempo(tempo);
                    }
                };break; //livello personalizzato
            }
            try {
                DAORecord.salvaRecords(listaRecord);
            } catch (DAOException e) {
                logger.info("DAOException errore nel salvataggio del record: " + e);
            }
        }
    }
    
    public boolean isNuovoRecord() {
        return nuovoRecord;
    }
    
    public boolean isUguagliato() {
        return uguagliato;
    }
    
}
