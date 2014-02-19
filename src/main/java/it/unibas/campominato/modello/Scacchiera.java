package it.unibas.campominato.modello;

import it.unibas.ping.framework.Applicazione;
import java.util.Random;

public class Scacchiera {
    
    private final int numRighe;
    private final int numColonne;
    private final int numMine;
    private CellaAstratta[][] celleScacchiera;
    
    public Scacchiera(int numRighe, int numColonne, int numMine) {
        this.numRighe = numRighe;
        this.numColonne = numColonne;
        this.numMine = numMine;
        this.celleScacchiera = new CellaAstratta[numRighe][numColonne];
        this.inizializzaCelleScacchiera();
        this.disponiMine();
    }
    
    private void inizializzaCelleScacchiera(){
        for (int i=0; i < this.numRighe; i++) {
            for (int j=0; j < this.numColonne; j++) {
                this.celleScacchiera[i][j]= new CellaVuota(this, i, j);
            }
        }
    }
    
    private void disponiMine() {
        Random posizioneCasuale = new Random();
        int x, y;
        int i=0;
        while (i < this.numMine) {
            x=posizioneCasuale.nextInt(this.numRighe);
            y=posizioneCasuale.nextInt(this.numColonne);
            if (!(this.celleScacchiera[x][y].isMina())) {
                this.celleScacchiera[x][y] = new CellaMina(this, x, y);
                i++;
            }
        }
    }
    
    public void scopriCasella(int x, int y) {
        this.controllaPosizioneAccettabile(x,y);
        this.celleScacchiera[x][y].scopriCella();
    }
    
    public void setBandierina(int x, int y) {
        this.controllaPosizioneAccettabile(x,y);
        this.celleScacchiera[x][y].commutaBandierina();
        Applicazione.getInstance().getModello().notificaModificaProprieta(this, "numMine");
    }
    
    public void scopriMineAllaFine() {
        for (int i=0; i < this.numRighe; i++) {
            for (int j=0; j < this.numColonne; j++) {
                this.celleScacchiera[i][j].scopriMinaAllaFine();
            }
        }
    }
    
    public int getNumMine() {
        int bandierine = 0;
        for (int i = 0; i < this.numRighe; i++) {
            for (int j = 0; j < this.numColonne; j++) {
                if (this.celleScacchiera[i][j].isBandierina()) {
                    bandierine++;
                }
            }
        }
        return this.numMine - bandierine;
    }
    
    public boolean isGiocoFinito() {
        for (int i=0; i < this.numRighe; i++) {
            for (int j=0; j < this.numColonne; j++) {
                if (!this.celleScacchiera[i][j].isScoperta()) {
                    return false;
                }
            }
        }
        return true;
    }
    
    public CellaAstratta getCella(int x, int y) {
        if(this.controllaPosizioneAccettabile(x, y)) {
            return this.celleScacchiera[x][y];
        }
        return null;
    }
    
    private boolean controllaPosizioneAccettabile(int x, int y) {
        if (x < 0 || x > this.numRighe || y < 0 || y > this.numColonne) {
            throw new IllegalArgumentException("Posizione passata scorretta");
        }
        return true;
    }
    
    public int getNumColonne() {
        return this.numColonne;
    }
    
    public int getNumRighe() {
        return this.numRighe;
    }
    
    public CellaAstratta[][] getCelleScacchiera() {
        return celleScacchiera;
    }
    
    public void setCelleScacchiera(CellaAstratta[][] celleScacchiera) {
        for (int i = 0; i < celleScacchiera.length; i++) {
            for (int j = 0; j < celleScacchiera.length; j++) {
                celleScacchiera[i][j].setScacchiera(this);
            }
        }
        this.celleScacchiera = celleScacchiera;
    }
    
}

