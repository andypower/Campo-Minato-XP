package it.unibas.campominato.modello;

import it.unibas.campominato.Costanti;
import javax.swing.ImageIcon;
import it.unibas.campominato.vista.Utilita;

public class StatoBandierina extends StatoAstratto {
    
    public StatoBandierina(Cella cella) {
        super(cella);
    }
    
    public ImageIcon getImage() {
        return Utilita.creaIcona(Costanti.BANDIERINA);
    }
    
    public boolean isCliccabile() {
        return true;
    }
    
    public boolean isBandierina() {
        return true;
    }
    
    public void commutaBandierina() {
        this.cella.setStato(new StatoCellaCoperta(this.cella));
    }
    
    public void scopriCella(){
        
    }
    
    public void scopriMinaAllaFine() {
        if (!this.cella.isMina()) {
            this.cella.setStato(new StatoMinaAssente(this.cella));
        }
    }
    
    public boolean isScoperta() {
        if (this.cella.isMina()) {
            return true;
        }
        return false;
    }
}
