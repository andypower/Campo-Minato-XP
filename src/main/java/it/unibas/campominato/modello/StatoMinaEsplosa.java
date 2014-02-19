package it.unibas.campominato.modello;

import it.unibas.campominato.Costanti;
import it.unibas.campominato.vista.Utilita;
import javax.swing.ImageIcon;

public class StatoMinaEsplosa extends StatoAstratto {

    public StatoMinaEsplosa(Cella cella) {
        super(cella);
    }

    @Override
    public ImageIcon getImage() {
        return Utilita.creaIcona(Costanti.MINAESPLOSA);
    }

    @Override
    public boolean isCliccabile() {
        return false;
    }

    @Override
    public boolean isBandierina() {
        return false;
    }

    @Override
    public void commutaBandierina() {
    }

    @Override
    public void scopriCella() {
    }

    @Override
    public void scopriMinaAllaFine() {
    }

    @Override
    public boolean isScoperta() {
        return true;
    }
}
