package it.unibas.campominato.persistenza;

import java.io.File;

public class DAOUtilita {
        
    // ***********************
    //      Copia del DTD
    // ***********************
        
    private static final String DTD = "records.dtd";

    private static String tempDir;

    static {
        tempDir = System.getProperty("java.io.tmpdir");
        if (!tempDir.endsWith(File.separator)) {
            tempDir += File.separator;
        }
    }

    public static String getPathRecordXMLFile() {
        return tempDir + "recordCampoMinato.xml";
    }

    public static String getTempDir() {
        return tempDir;
    }

    public static void copiaDTD(String percorso) throws DAOException {
        try {
            java.io.InputStream streamOriginale = DAOUtilita.class.getResourceAsStream("/risorse/" + DTD);
            String nomeNuovoFileDTD = percorso + DTD;
            java.io.File nuovoFileDTD = new java.io.File(nomeNuovoFileDTD);
            copiaFile(streamOriginale, nuovoFileDTD);
        } catch (java.io.IOException ioe) {
            System.err.println(ioe);
            throw new DAOException("\n--------------------------------------\n"
                                 + "ATTENZIONE: verificare il percorso relativo al file\n" 
                                 + "records.dtd nel file DAOUtilita.java\n" 
                                 + "----------------------------------------\n" + ioe);
        }
    }

    public static String estraiPercorso(String nomeFile) {
        int ultimaPosizioneSeparatore = nomeFile.lastIndexOf("\\");
        String percorso = nomeFile.substring(0, ultimaPosizioneSeparatore + 1);
        return percorso;
    }

    private static void copiaFile(java.io.InputStream source, java.io.File dest) throws java.io.IOException {
        java.io.BufferedInputStream in= null;
        java.io.BufferedOutputStream out = null;
        try {
            in = new java.io.BufferedInputStream(source);
            out = new java.io.BufferedOutputStream(new java.io.FileOutputStream(dest));
            copiaStream(in, out);
        } finally {
            if (out != null) {
                out.close();
            }
            if (in != null) {
                in.close();
            }
        }
    }
    
    private static void copiaStream(java.io.BufferedInputStream in, java.io.BufferedOutputStream out) throws java.io.IOException {
        byte[] buffer = new byte[1024];
        int numberRead;
        while ((numberRead = in.read(buffer)) >= 0) {
            out.write(buffer, 0, numberRead);
        }
    }    
}

