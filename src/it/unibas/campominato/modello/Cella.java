package it.unibas.campominato.modello;

import javax.swing.ImageIcon;

public interface Cella {
    
    public Scacchiera getScacchiera();
    
    public int getX();
    
    public int getY();
    
    public void commutaBandierina(); 
    
    public void scopriCella();
   
    public void scopriMinaAllaFine();
    
    public ImageIcon getImage();
    
    public void setStato(Stato stato);

    public boolean isBandierina();
    
    public boolean isCliccabile();

    public boolean isMina();
    
    public boolean isScoperta();
   
}
