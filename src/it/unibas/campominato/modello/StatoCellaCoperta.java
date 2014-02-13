package it.unibas.campominato.modello;

import it.unibas.campominato.Costanti;
import javax.swing.ImageIcon;
import it.unibas.campominato.vista.Utilita;
import it.unibas.ping.framework.Applicazione;

public class StatoCellaCoperta extends StatoAstratto {
    
    public StatoCellaCoperta(Cella cella) {
        super(cella);
    }
    
    public ImageIcon getImage() {
        return Utilita.creaIcona(Costanti.QUADRATINO);
    }
    
    public boolean isCliccabile() {
        return true;
    }
    
    public boolean isBandierina() {
        return false;
    }
    
    public void commutaBandierina() {
        this.cella.setStato(new StatoBandierina(this.cella));
    }
    
    public void scopriCella(){
        if (this.cella.isMina()) {
            this.cella.setStato(new StatoMinaEsplosa(this.cella));
            Applicazione.getInstance().getModello().putBean("giocoFinito", true);
        } else { // cella vuota
            this.cella.setStato(new StatoCellaScoperta(this.cella));
            if (this.contaMineContorno() == 0) {
                scopriCelleVuoteContorno();
            }
        }
    }
    
    public void scopriMinaAllaFine() {
        if (this.cella.isMina()) {
            this.cella.setStato(new StatoCellaScoperta(this.cella));
        }
    }
    
    private void scopriCelleVuoteContorno() {
        int x = this.cella.getX();
        int y = this.cella.getY();
        Scacchiera scacchiera = this.cella.getScacchiera();
        for (int i = x - 1; i <= x + 1; i++) {
            for (int j = y - 1; j <= y + 1; j++) {
                if (i >= 0 && i < scacchiera.getNumRighe() && j >= 0 && j < scacchiera.getNumColonne()) {
                    scacchiera.scopriCasella(i, j);
                }
            }
        }
    }
    
    public boolean isScoperta() {
        return false;
    }
}
