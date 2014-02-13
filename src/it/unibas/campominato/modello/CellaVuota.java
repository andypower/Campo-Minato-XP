package it.unibas.campominato.modello;

public class CellaVuota extends CellaAstratta {
        
    public CellaVuota(Scacchiera scacchiera, int x, int y) {
        super(scacchiera, x, y);
    }
    
    public boolean isMina() {
        return false;
    }

}