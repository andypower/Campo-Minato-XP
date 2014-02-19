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
        this.x = x;
        this.y = y;
        this.stato = new StatoCellaCoperta(this);
    }
    
    @Override
    public Scacchiera getScacchiera() {
        return this.scacchiera;
    }
    
    @Override
    public int getX() {
        return this.x;
    }
    
    @Override
    public int getY() {
        return this.y;
    }
    
    @Override
    public void commutaBandierina() {
        this.stato.commutaBandierina();
    }
    
    @Override
    public void scopriCella() {
        this.stato.scopriCella();
    }
    
    @Override
    public void scopriMinaAllaFine() {
        this.stato.scopriMinaAllaFine();
    }
    
    @Override
    public ImageIcon getImage() {
        return this.stato.getImage();
    }
    
    @Override
    public boolean isScoperta() {
        return this.stato.isScoperta();
    }
    
    @Override
    public void setStato(Stato stato) {
        this.stato = stato;
        Applicazione.getInstance().getModello().notificaModificaCella(this.scacchiera, Costanti.CELLE, x, y);
    }
    
    @Override
    public boolean isBandierina() {
        return this.stato.isBandierina();
    }
    
    @Override
    public boolean isCliccabile() {
        return this.stato.isCliccabile();
    }
    
    @Override
    public abstract boolean isMina();

    public void setScacchiera(Scacchiera scacchiera) {
        this.scacchiera = scacchiera;
    }
    
}
