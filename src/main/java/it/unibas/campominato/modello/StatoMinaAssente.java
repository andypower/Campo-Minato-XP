package it.unibas.campominato.modello;

import it.unibas.campominato.Costanti;
import javax.swing.ImageIcon;
import it.unibas.campominato.vista.Utilita;

public class StatoMinaAssente extends StatoAstratto {

    public StatoMinaAssente(Cella cella) {
        super(cella);
    }

    @Override
    public ImageIcon getImage() {
        return Utilita.creaIcona(Costanti.MINAASSENTE);
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
