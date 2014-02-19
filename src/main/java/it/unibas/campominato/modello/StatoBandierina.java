package it.unibas.campominato.modello;

import it.unibas.campominato.Costanti;
import javax.swing.ImageIcon;
import it.unibas.campominato.vista.Utilita;

public class StatoBandierina extends StatoAstratto {

    public StatoBandierina(Cella cella) {
        super(cella);
    }

    @Override
    public ImageIcon getImage() {
        return Utilita.creaIcona(Costanti.BANDIERINA);
    }

    @Override
    public boolean isCliccabile() {
        return true;
    }

    @Override
    public boolean isBandierina() {
        return true;
    }

    @Override
    public void commutaBandierina() {
        this.cella.setStato(new StatoCellaCoperta(this.cella));
    }

    @Override
    public void scopriCella() {

    }

    @Override
    public void scopriMinaAllaFine() {
        if (!this.cella.isMina()) {
            this.cella.setStato(new StatoMinaAssente(this.cella));
        }
    }

    @Override
    public boolean isScoperta() {
        if (this.cella.isMina()) {
            return true;
        }
        return false;
    }
}
