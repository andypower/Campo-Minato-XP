package it.unibas.campominato.persistenza;

public class MioErrorHandler extends org.xml.sax.helpers.DefaultHandler {
    
    private boolean valido = true;
    private String errori = "\n";
    
    public String getErrori() {
        return errori;
    }
    
    public boolean isValido() {
        return valido;
    }
    
    public void error(org.xml.sax.SAXParseException e) throws org.xml.sax.SAXParseException {
        valido = false;
        errori += "- " + e + "\n";
    }
    
    public void warning(org.xml.sax.SAXParseException err) throws org.xml.sax.SAXParseException {
        String avvertimento = "";
        avvertimento += "** Warning"
                + ", line " + err.getLineNumber()
                + ", uri " + err.getSystemId();
        avvertimento += "   " + err.getMessage();
        errori += "- " + avvertimento + "\n";
    }
    
}

