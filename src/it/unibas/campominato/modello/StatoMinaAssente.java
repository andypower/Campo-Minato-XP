package it.unibas.campominato.modello;

import it.unibas.campominato.Costanti;
import javax.swing.ImageIcon;
import it.unibas.campominato.vista.Utilita;

public class StatoMinaAssente extends StatoAstratto {
    
    public StatoMinaAssente(Cella cella) {
        super(cella);
    }
    
    public ImageIcon getImage() {
        return Utilita.creaIcona(Costanti.MINAASSENTE);
    }
    
    public boolean isCliccabile() {
        return false;
    }    
           
    public boolean isBandierina() {
        return false;
    }
        
    public void commutaBandierina() {        
    }

    public void scopriCella(){
    }
    
    public void scopriMinaAllaFine() {
    }
    
    public boolean isScoperta() {
        return true;
    }
}
