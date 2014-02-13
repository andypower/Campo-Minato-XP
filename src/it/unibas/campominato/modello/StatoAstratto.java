package it.unibas.campominato.modello;

import javax.swing.ImageIcon;

public abstract class StatoAstratto implements Stato {
    
    protected Cella cella;
    
    public StatoAstratto(Cella cella) {
        this.cella = cella;
    }
    
    public abstract void commutaBandierina(); 
    
    public abstract void scopriCella();
    
    public abstract void scopriMinaAllaFine();
    
    public abstract ImageIcon getImage();
    
    public abstract boolean isBandierina();
    
    public abstract boolean isCliccabile();
        
    public abstract boolean isScoperta();
    
    protected int contaMineContorno() {
        Scacchiera scacchiera = this.cella.getScacchiera();
        int x = this.cella.getX();
        int y = this.cella.getY();
        int numMine = 0;
        for (int i = x - 1; i <= x + 1; i++) {
            for (int j = y - 1; j <= y + 1; j++) {
                if (i >= 0 && i < scacchiera.getNumRighe() && j >= 0 && j < scacchiera.getNumColonne()) {
                    if (scacchiera.getCella(i, j).isMina()) {
                        numMine ++;
                    }
                }
            }
        }
        return numMine;
    }
}
