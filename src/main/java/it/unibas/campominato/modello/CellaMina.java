package it.unibas.campominato.modello;

public class CellaMina extends CellaAstratta {
        
    public CellaMina(Scacchiera scacchiera, int x, int y) {
        super(scacchiera, x, y);
    }
    
    @Override
    public boolean isMina() {
        return true;
    }

}