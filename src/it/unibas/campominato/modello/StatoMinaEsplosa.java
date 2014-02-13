package it.unibas.campominato.modello;

import it.unibas.campominato.Costanti;
import it.unibas.campominato.vista.Utilita;
import javax.swing.ImageIcon;

public class StatoMinaEsplosa extends StatoAstratto {
    
    public StatoMinaEsplosa(Cella cella) {
        super(cella);
    }   

    public ImageIcon getImage() {
        return Utilita.creaIcona(Costanti.MINAESPLOSA);
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
