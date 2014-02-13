package it.unibas.campominato.modello;

import it.unibas.campominato.Costanti;
import it.unibas.ping.framework.Applicazione;
import javax.swing.ImageIcon;

public abstract class CellaAstratta implements Cella {
    
    private Scacchiera scacchiera;
    private Stato stato;
    private int x;
    private int y;
    
    public CellaAstratta(Scacchiera scacchiera, int x, int y) {
        this.setScacchiera(scacchiera);
        this.stato = stato;
        this.x = x;
        this.y = y;
        this.stato = new StatoCellaCoperta(this);
    }
    
    public Scacchiera getScacchiera() {
        return this.scacchiera;
    }
    
    public int getX() {
        return this.x;
    }
    
    public int getY() {
        return this.y;
    }
    
    public void commutaBandierina() {
        this.stato.commutaBandierina();
    }
    
    public void scopriCella() {
        this.stato.scopriCella();
    }
    
    public void scopriMinaAllaFine() {
        this.stato.scopriMinaAllaFine();
    }
    
    public ImageIcon getImage() {
        return this.stato.getImage();
    }
    
    public boolean isScoperta() {
        return this.stato.isScoperta();
    }
    
    public void setStato(Stato stato) {
        this.stato = stato;
        Applicazione.getInstance().getModello().notificaModificaCella(this.scacchiera, Costanti.CELLE, x, y);
    }
    
    public boolean isBandierina() {
        return this.stato.isBandierina();
    }
    
    public boolean isCliccabile() {
        return this.stato.isCliccabile();
    }
    
    public abstract boolean isMina();

    public void setScacchiera(Scacchiera scacchiera) {
        this.scacchiera = scacchiera;
    }
    
}
