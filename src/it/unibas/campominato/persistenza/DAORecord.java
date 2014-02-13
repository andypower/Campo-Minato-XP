package it.unibas.campominato.persistenza;

import it.unibas.campominato.modello.Livello;
import it.unibas.campominato.modello.LivelloPersonalizzato;
import it.unibas.campominato.modello.Tempo;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;


public class DAORecord {
    
    private static Log logger = LogFactory.getLog(DAORecord.class); 
    private static String tempDir = System.getProperty("java.io.tmpdir");
    private final static String NOMEFILE = tempDir + "recordCampoMinato.xml";
    
    public static List<Livello> caricaLivelliRecord(String nomeFile) throws DAOException {
        org.w3c.dom.Document document = costruisciDOM(nomeFile);
        List<Livello> livelliRecord = new ArrayList<Livello>();
        analizzaRadice(document, livelliRecord);
        return livelliRecord;
    }
    
    private static org.w3c.dom.Document costruisciDOM(String nomeFile) throws DAOException {
        org.w3c.dom.Document document = null;
        javax.xml.parsers.DocumentBuilderFactory factory = javax.xml.parsers.DocumentBuilderFactory.newInstance();
        factory.setValidating(true);
        factory.setNamespaceAware(true);
        MioErrorHandler mioErrorHandler = new MioErrorHandler();
        try {
            javax.xml.parsers.DocumentBuilder builder = factory.newDocumentBuilder();
            builder.setErrorHandler(mioErrorHandler);
            java.io.File file = new java.io.File(nomeFile);
            document = builder.parse(file);
        } catch (javax.xml.parsers.ParserConfigurationException pce) {
            throw new DAOException(pce);
        } catch (org.xml.sax.SAXException sxe) {
            throw new DAOException(sxe);
        } catch (java.io.IOException ioe) {
            logger.info("IOException: " + ioe);
            throw new DAOException(ioe);
        }
        if (!mioErrorHandler.isValido()) {
            throw new DAOException(mioErrorHandler.getErrori());
        }
        return document;
    }
    
    private static void analizzaRadice(org.w3c.dom.Document document, List<Livello> livelliRecord) {
        org.w3c.dom.Element radice = document.getDocumentElement();
        org.w3c.dom.NodeList listaLivelli = document.getElementsByTagName("listaLivelli");
        for (int i = 0; i < listaLivelli.getLength(); i++) {
            org.w3c.dom.Element livelloRecord = (org.w3c.dom.Element)listaLivelli.item(i);
            String difficoltaLivello = livelloRecord.getAttribute("difficoltaLivello");
            Livello livelloEstratto = DAORecord.creaLivello(difficoltaLivello);
            if (livelloEstratto != null) {
                livelliRecord.add(livelloEstratto);
                org.w3c.dom.NodeList listaRecord = livelloRecord.getElementsByTagName("record");
                DAORecord.riempiLivello(livelloEstratto, listaRecord);
            }
        }
    }
    
    private static void riempiLivello(Livello livello, NodeList listaRecord) {
        for (int i = 0; i < listaRecord.getLength(); i++) {
            org.w3c.dom.Element singoloRecord = (org.w3c.dom.Element)listaRecord.item(i);
            String secondi = (singoloRecord.getAttribute("secondi")).trim();
            String nomeGiocatore = (singoloRecord.getAttribute("nomeGiocatore")).trim();
            String numColonne  = (singoloRecord.getAttribute("numColonne")).trim();
            String numRighe = (singoloRecord.getAttribute("numRighe")).trim();
            String numMine = (singoloRecord.getAttribute("numMine")).trim();
            try {
                long secondiLong = Long.parseLong(secondi);
                int numColonneIntero = Integer.parseInt(numColonne);
                int numRigheIntero = Integer.parseInt(numRighe);
                int numMineIntero = Integer.parseInt(numMine);
                Tempo tempo = new Tempo(secondiLong, nomeGiocatore, numColonneIntero, numRigheIntero, numMineIntero);
                livello.addTempo(tempo);
            } catch (NumberFormatException nfe) {
                logger.warn("DAORecord - riempiLivello: errore nella conversione di interi");
            }
        }
    }
    
    private static Livello creaLivello(String difficoltaLivello) {
        Livello livello = null;
        String stringaDifficoltaLivello = difficoltaLivello.trim();
        if(stringaDifficoltaLivello.equalsIgnoreCase("Livello Base")) {
            livello = new Livello("Livello Base");
        } else if (stringaDifficoltaLivello.equalsIgnoreCase("Livello Intermedio")) {
            livello = new Livello("Livello Intermedio");
        } else if (stringaDifficoltaLivello.equalsIgnoreCase("Livello Avanzato")) {
            livello = new Livello("Livello Avanzato");
        } else if (stringaDifficoltaLivello.equalsIgnoreCase("Livello Personalizzato")) {
            livello = new LivelloPersonalizzato();
        }
        return livello;
    }
    
    private static org.w3c.dom.Element getFirstChildByName(org.w3c.dom.Element elemento, String nome) {
        org.w3c.dom.NodeList figli = elemento.getChildNodes();
        for (int i = 0; i < figli.getLength(); i++) {
            org.w3c.dom.Node figlio = figli.item(i);
            if (figlio.getNodeName().equals(nome)) {
                return (org.w3c.dom.Element)figlio;
            }
        }
        return null;
    }
    
    // Salvataggio
    public static void salvaRecords(List<Livello> listaRecord) throws DAOException {
        if (listaRecord == null) {
            throw new DAOException("La lista dei record non puo' essere vuota");
        }
        org.w3c.dom.Document document = creaDocumento();
        aggiungiListaRecord(listaRecord, document);
        salvaDOM(document, NOMEFILE);
        DAOUtilita.copiaDTD(tempDir);
    }
    
    private static org.w3c.dom.Document creaDocumento() throws DAOException {
        org.w3c.dom.Document document = null;
        javax.xml.parsers.DocumentBuilderFactory factory = javax.xml.parsers.DocumentBuilderFactory.newInstance();
        factory.setValidating(true);
        try {
            javax.xml.parsers.DocumentBuilder builder = factory.newDocumentBuilder();
            document = builder.newDocument();
            org.w3c.dom.Element radice = (org.w3c.dom.Element)document.createElement("records");
            document.appendChild(radice);
        } catch (javax.xml.parsers.ParserConfigurationException pce) {
            System.err.println(pce);
            throw new DAOException(pce);
        } catch (org.w3c.dom.DOMException doe) {
            System.err.println(doe);
            throw new DAOException(doe);
        }
        return document;
    }
    
    private static void aggiungiListaRecord(List<Livello> listaRecord, org.w3c.dom.Document document) {
        org.w3c.dom.Element radice = document.getDocumentElement();
        for (int i = 0; i < listaRecord.size(); i++) {
            org.w3c.dom.Element elementoListaRecord = document.createElement("listaLivelli");
            org.w3c.dom.Attr difficoltaLivello = document.createAttribute("difficoltaLivello");
            difficoltaLivello.setValue(listaRecord.get(i).toString());
            elementoListaRecord.setAttributeNode(difficoltaLivello);
            DAORecord.aggiungiRecord(listaRecord.get(i), elementoListaRecord, document);
            radice.appendChild(elementoListaRecord);
        }
    }
    
    private static void aggiungiRecord(Livello livelloRecord, Element elementoListaRecord,
            org.w3c.dom.Document document) {
        List<Tempo> listaTempi = livelloRecord.getListaTempi();
        for (int i = 0; i < listaTempi.size(); i++) {
            org.w3c.dom.Attr secondi = document.createAttribute("secondi");
            org.w3c.dom.Attr nomeGiocatore = document.createAttribute("nomeGiocatore");
            org.w3c.dom.Attr numColonne = document.createAttribute("numColonne");
            org.w3c.dom.Attr numRighe = document.createAttribute("numRighe");
            org.w3c.dom.Attr numMine = document.createAttribute("numMine");
            secondi.setValue("" + listaTempi.get(i).getSecondi());
            nomeGiocatore.setValue(listaTempi.get(i).getNomeGiocatore());
            numColonne.setValue("" + listaTempi.get(i).getNumColonne());
            numRighe.setValue("" + listaTempi.get(i).getNumRighe());
            numMine.setValue("" + listaTempi.get(i).getNumMine());
            org.w3c.dom.Element record = document.createElement("record");
            record.setAttributeNode(secondi);
            record.setAttributeNode(nomeGiocatore);
            record.setAttributeNode(numColonne);
            record.setAttributeNode(numRighe);
            record.setAttributeNode(numMine);
            elementoListaRecord.appendChild(record);
        }
    }
    
    
    private static void salvaDOM(org.w3c.dom.Document document, String nomeFile) throws DAOException {
        try {
            java.io.File file = new java.io.File(nomeFile);
            javax.xml.transform.TransformerFactory transformerFactory = javax.xml.transform.TransformerFactory.newInstance();
            javax.xml.transform.Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty("indent", "yes");
            transformer.setOutputProperty(javax.xml.transform.OutputKeys.DOCTYPE_SYSTEM, "records.dtd");
            javax.xml.transform.dom.DOMSource source = new javax.xml.transform.dom.DOMSource(document);
            javax.xml.transform.stream.StreamResult result = new javax.xml.transform.stream.StreamResult(file);
            transformer.transform(source, result);
        } catch (javax.xml.transform.TransformerConfigurationException tce) {
            System.err.println(tce);
            throw new DAOException(tce);
        } catch (javax.xml.transform.TransformerException te) {
            System.err.println(te);
            throw new DAOException(te);
        }
    }
    
}