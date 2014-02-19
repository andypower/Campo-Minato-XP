package it.unibas.campominato.modello;

import it.unibas.campominato.Costanti;
import javax.swing.ImageIcon;
import it.unibas.campominato.vista.Utilita;

public class StatoCellaScoperta extends StatoAstratto {

    public StatoCellaScoperta(Cella cella) {
        super(cella);
    }

    @Override
    public ImageIcon getImage() {
        String immagine = null;
        if (!this.cella.isMina()) {
            int numMine = this.contaMineContorno();
            switch (numMine) {
                case 0:
                    immagine = "";
                    break;
                case 1:
                    immagine = Costanti.UNO;
                    break;
                case 2:
                    immagine = Costanti.DUE;
                    break;
                case 3:
                    immagine = Costanti.TRE;
                    break;
                case 4:
                    immagine = Costanti.QUATTRO;
                    break;
                case 5:
                    immagine = Costanti.CINQUE;
                    break;
                case 6:
                    immagine = Costanti.SEI;
                    break;
                case 7:
                    immagine = Costanti.SETTE;
                    break;
                case 8:
                    immagine = Costanti.OTTO;
                    break;
            }
        } else { // si trata di una mina scoperta
            immagine = Costanti.MINA;
        }
        return Utilita.creaIcona(immagine);
    }

    @Override
    public boolean isCliccabile() {
        return false;
    }

    public void scopriCella(Scacchiera scacchiera) {
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
