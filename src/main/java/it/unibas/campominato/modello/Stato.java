package it.unibas.campominato.modello;

import javax.swing.ImageIcon;

public interface Stato {
    
    public void commutaBandierina(); 
    
    public void scopriCella();
    
    public void scopriMinaAllaFine();
    
    public ImageIcon getImage();
    
    public boolean isBandierina();
    
    boolean isCliccabile();
        
    boolean isScoperta();
    
}

